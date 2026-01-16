package com.tfb.aibank.chl.system.ot003.task;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.type.SessionKey;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.validate.ValidatorUtility;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.security.EmailOtpSecurityGuard;
import com.tfb.aibank.chl.component.security.otp.OtpAuthService;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthInitData;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthKeepData;
import com.tfb.aibank.chl.system.ot003.model.NSTOT003051Rq;
import com.tfb.aibank.chl.system.ot003.model.NSTOT003051Rs;
import com.tfb.aibank.common.type.MailParamType;

//@formatter:off
/**
* @(#)NSTOT003051Task.java
* 
* <p>Description:安控驗證共用 - 驗證交易安控 EMAIL-OTP - Task</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/27, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NSTOT003051Task extends AbstractAIBankBaseTask<NSTOT003051Rq, NSTOT003051Rs> {

    public static final int DEFAULT_RESEND_COUNTDOWN_SECONDS = 30;

    @Autowired
    private OtpAuthService otpAuthService;

    @Override
    public void validate(NSTOT003051Rq rqData) throws ActionException {
        // 檢核通知Email(非必填)
        String newEmail = rqData.getEmail();
        if (StringUtils.isNotBlank(newEmail)) {
            if (!ValidatorUtility.checkEMail(newEmail) || !ValidatorUtility.checkLength(newEmail, 1, 40)) {
                addErrorFieldMap("email", I18NUtils.getMessage("validate.email.format.desc", getLocale()));
            }
        }
        else {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

    @Override
    public void handle(NSTOT003051Rq rqData, NSTOT003051Rs rsData) throws ActionException {
        EmailOtpSecurityGuard cache = getFromSession(SessionKey.EMAIL_OTP, EmailOtpSecurityGuard.class);

        String email = cache.getOriginEmail();
        if (StringUtils.isNoneBlank(rqData.getEmail())) {
            email = rqData.getEmail();
        }
        // 產生OTP驗證資料
        OtpAuthKeepData otpAuthKeepData = generateOTP(rqData, rsData, email);

        Map<String, String> mailParams = new HashMap<>();

        buildMailParams(otpAuthKeepData, rqData.getSourceTxnId(), mailParams);
        cache.setOtpAuthKeepData(otpAuthKeepData);
        setToSession(SessionKey.EMAIL_OTP, cache);
        // email驗證信發到新email 規格無說明 依照 npsag001 SA 所述需求
        String subject = I18NUtils.getMessage("email.validate.subject", StringUtils.equals(rqData.getSourceTxnId(), "NPSAG001") ? 1 : 0);
        // NPYAG008 新增 email驗證信
        if (StringUtils.equals(rqData.getSourceTxnId(), "NPYAG008")) {
            subject = I18NUtils.getMessage("email.validate.subject.npyag008");
        }
        sendTxnResultMail(getLoginUser().getCustId(), getLoginUser().getUidDup(), getLoginUser().getUserId(), getLoginUser().getCompanyKind(), subject, email, mailParams);
    }

    /**
     * 產生OTP驗證資料
     * 
     * @param rqData
     *            請求資料
     * @param rsData
     *            回傳資料
     * @param txSecurityGuard
     *            交易安控驗證資料
     * 
     * @throws ActionException
     */
    private OtpAuthKeepData generateOTP(NSTOT003051Rq rqData, NSTOT003051Rs rsData, String email) throws ActionException {

        // 初始化並發送OTP簡訊
        OtpAuthInitData otpAuthInitData = new OtpAuthInitData();

        otpAuthInitData.setIsSendOtp(false);
        // 加參數讓OtpAuthService不發簡訊
        OtpAuthKeepData otpAuthKeepData = otpAuthService.sendOTP(getLoginUser(), otpAuthInitData);

        // 設定回傳資料
        rsData.setEmail(email);

        rsData.setTxCode(otpAuthKeepData.getModel().getTxCode());
        rsData.setUserEncTxCode(otpAuthKeepData.getEncTxCode());
        rsData.setUserTxHash(otpAuthKeepData.getHashTxFactor());
        rsData.setCountdownSeconds(OtpAuthService.DEFAULT_COUNTDOWN_SECONDS);
        rsData.setCanResend(true);
        rsData.setResendCountdownSeconds(DEFAULT_RESEND_COUNTDOWN_SECONDS);

        logger.debug("交易驗證碼: " + otpAuthKeepData.getModel().getOtpPass() + "交易代碼" + otpAuthKeepData.getModel().getTxCode());
        return otpAuthKeepData;
    }

    /**
     * 建立郵件參數
     * 
     * @param rqData
     * @param txSecurityGuard
     * @param output
     */
    protected void buildMailParams(OtpAuthKeepData otpAuthKeepData, String sourceTxnId, Map<String, String> output) {
        output.put("emailOptPass", otpAuthKeepData.getModel().getOtpPass());
        output.put("optPassExpire", DateUtils.getDateTimeStr(otpAuthKeepData.getModel().getExpireTime()));
        output.put("txCode", otpAuthKeepData.getModel().getTxCode());
        String content = StringUtils.equals(sourceTxnId, "NCCAG003") ? I18NUtils.getMessage("nccag003.email.otp.content") : I18NUtils.getMessage("npsag001.email.otp.content");
        output.put("content", content);
        output.put("subTitle", I18NUtils.getMessage("nccag003.email.otp.subsubject"));
        if (StringUtils.isNotBlank(sourceTxnId)) {
            output.put(MailParamType.TXN_ID.getCode(), sourceTxnId);
        }

        output.put(MailParamType.TEMPLATE_NAME.getCode(), "NCCAG003_EMAIL");
    }

}
