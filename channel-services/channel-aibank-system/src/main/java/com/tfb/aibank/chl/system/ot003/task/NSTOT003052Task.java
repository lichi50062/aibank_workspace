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
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthKeepData;
import com.tfb.aibank.chl.system.ot003.model.NSTOT003052Rq;
import com.tfb.aibank.chl.system.ot003.model.NSTOT003052Rs;
import com.tfb.aibank.common.type.MailParamType;

//@formatter:off
/**
* @(#)NSTOT003052Task.java
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
public class NSTOT003052Task extends AbstractAIBankBaseTask<NSTOT003052Rq, NSTOT003052Rs> {

    public static final int DEFAULT_RESEND_COUNTDOWN_SECONDS = 30;

    @Autowired
    private OtpAuthService otpAuthService;

    @Override
    public void validate(NSTOT003052Rq rqData) throws ActionException {
        // 檢核通知Email(非必填)
        String newEmail = rqData.getEmail();
        if (StringUtils.isNotBlank(newEmail)) {
            if (!ValidatorUtility.checkEMail(newEmail) || !ValidatorUtility.checkLength(newEmail, 1, 40)) {
                throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
            }
        }
        else {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

    @Override
    public void handle(NSTOT003052Rq rqData, NSTOT003052Rs rsData) throws ActionException {
        EmailOtpSecurityGuard cache = getFromSession(SessionKey.EMAIL_OTP, EmailOtpSecurityGuard.class);

        // 重新發送OTP
        resendOTP(rqData, rsData, cache.getOtpAuthKeepData(), cache);

    }

    /**
     * 重新發送OTP
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
    private void resendOTP(NSTOT003052Rq rqData, NSTOT003052Rs rsData, OtpAuthKeepData otpAuthKeepData, EmailOtpSecurityGuard cache) throws ActionException {

        // 重新發送OTP簡訊
        // OtpAuthKeepData otpAuthKeepData = txSecurityGuard.getOtpAuthKeepData();
        // otpAuthService.setOtpStatus(OtpStatusType.RESEND, otpAuthKeepData);
        // OtpAuthKeepData newOtpAuthKeepData = otpAuthService.genOtpKeepData(getLoginUser(), otpAuthKeepData.getInitData());
        // txSecurityGuard.setOtpAuthKeepData(newOtpAuthKeepData);

        // TODO 取出011的KeepData傳入
        OtpAuthKeepData newOtpAuthKeepData = otpAuthService.resendOTP(getLoginUser(), otpAuthKeepData);

        // 設定回傳資料
        rsData.setEmail(rqData.getEmail());
        rsData.setTxCode(newOtpAuthKeepData.getModel().getTxCode());
        rsData.setUserEncTxCode(newOtpAuthKeepData.getEncTxCode());
        rsData.setUserTxHash(newOtpAuthKeepData.getHashTxFactor());
        rsData.setCountdownSeconds(OtpAuthService.DEFAULT_COUNTDOWN_SECONDS);
        rsData.setCanResend(true);
        rsData.setResendCountdownSeconds(DEFAULT_RESEND_COUNTDOWN_SECONDS);

        Map<String, String> mailParams = new HashMap<>();

        buildMailParams(newOtpAuthKeepData, rqData.getSourceTxnId(), mailParams);
        // email驗證信發到新email 規格無特別說明 依照 npsag001 SA 所述需求
        String subject = I18NUtils.getMessage("email.validate.subject", StringUtils.equals(rqData.getSourceTxnId(), "NPSAG001") ? 1 : 0);
        sendTxnResultMail(getLoginUser().getCustId(), getLoginUser().getUidDup(), getLoginUser().getUserId(), getLoginUser().getCompanyKind(), subject, rqData.getEmail(), mailParams);

        cache.setOtpAuthKeepData(newOtpAuthKeepData);
        setToSession(SessionKey.EMAIL_OTP, cache);
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
        output.put("content", I18NUtils.getMessage("nccag003.email.otp.content"));
        output.put("subTitle", I18NUtils.getMessage("nccag003.email.otp.subsubject"));
        if (StringUtils.isNotBlank(sourceTxnId)) {
            output.put(MailParamType.TXN_ID.getCode(), sourceTxnId);
        }
        output.put(MailParamType.TEMPLATE_NAME.getCode(), "NCCAG003_EMAIL");
    }

}
