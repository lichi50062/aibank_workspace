package com.tfb.aibank.chl.creditcard.ag003.task;

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
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.validate.ValidatorUtility;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.security.otp.OtpAuthService;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthInitData;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthKeepData;
import com.tfb.aibank.chl.creditcard.ag003.cache.NCCAG003CacheData;
import com.tfb.aibank.chl.creditcard.ag003.model.NCCAG003011Rq;
import com.tfb.aibank.chl.creditcard.ag003.model.NCCAG003011Rs;
import com.tfb.aibank.chl.creditcard.ag003.service.NCCAG003Service;
import com.tfb.aibank.common.type.MailParamType;

// @formatter:off
/**
 * @(#)NCCAG003011Task.java
 * 
 * <p>Description:信用卡email驗證 011 genOTP</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG003011Task extends AbstractAIBankBaseTask<NCCAG003011Rq, NCCAG003011Rs> {

    @Autowired
    private OtpAuthService otpAuthService;

    @Override
    public void validate(NCCAG003011Rq rqData) throws ActionException {
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
    public void handle(NCCAG003011Rq rqData, NCCAG003011Rs rsData) throws ActionException {
        NCCAG003CacheData cache = getCache(NCCAG003Service.NCCAG003_CACHE_KEY, NCCAG003CacheData.class);

        // 產生OTP驗證資料
        OtpAuthKeepData otpAuthKeepData = generateOTP(rqData, rsData);

        Map<String, String> mailParams = new HashMap<>();

        buildMailParams(otpAuthKeepData, mailParams);
        cache.setOtpAuthKeepData(otpAuthKeepData);
        setCache(NCCAG003Service.NCCAG003_CACHE_KEY, cache);
        // email驗證信發到新email 規格無說明 依照 npsag001 SA 所述需求
        sendTxnResultMail(getLoginUser().getCustId(), getLoginUser().getUidDup(), getLoginUser().getUserId(), getLoginUser().getCompanyKind(), I18NUtils.getMessage("email.validate.subject", 0), rqData.getEmail(), mailParams);
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
    private OtpAuthKeepData generateOTP(NCCAG003011Rq rqData, NCCAG003011Rs rsData) throws ActionException {

        // 初始化並發送OTP簡訊
        OtpAuthInitData otpAuthInitData = new OtpAuthInitData();

        otpAuthInitData.setIsSendOtp(false);
        // 加參數讓OtpAuthService不發簡訊
        OtpAuthKeepData otpAuthKeepData = otpAuthService.sendOTP(getLoginUser(), otpAuthInitData);

        // 設定回傳資料
        rsData.setEmail(rqData.getEmail());
        rsData.setTxCode(otpAuthKeepData.getModel().getTxCode());
        rsData.setUserEncTxCode(otpAuthKeepData.getEncTxCode());
        rsData.setUserTxHash(otpAuthKeepData.getHashTxFactor());
        rsData.setCountdownSeconds(OtpAuthService.DEFAULT_COUNTDOWN_SECONDS);
        rsData.setCanResend(true);
        rsData.setResendCountdownSeconds(NCCAG003Service.DEFAULT_RESEND_COUNTDOWN_SECONDS);

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
    protected void buildMailParams(OtpAuthKeepData otpAuthKeepData, Map<String, String> output) {
        output.put("emailOptPass", otpAuthKeepData.getModel().getOtpPass());
        output.put("optPassExpire", DateUtils.getDateTimeStr(otpAuthKeepData.getModel().getExpireTime()));
        output.put("txCode", otpAuthKeepData.getModel().getTxCode());
        output.put("content", I18NUtils.getMessage("nccag003.email.otp.content"));
        output.put("subTitle", I18NUtils.getMessage("nccag003.email.otp.subsubject"));
        output.put(MailParamType.TEMPLATE_NAME.getCode(), "NCCAG003_EMAIL");
    }

}
