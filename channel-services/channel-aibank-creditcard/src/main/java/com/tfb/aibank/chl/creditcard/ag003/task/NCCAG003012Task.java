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
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthKeepData;
import com.tfb.aibank.chl.creditcard.ag003.cache.NCCAG003CacheData;
import com.tfb.aibank.chl.creditcard.ag003.model.NCCAG003012Rq;
import com.tfb.aibank.chl.creditcard.ag003.model.NCCAG003012Rs;
import com.tfb.aibank.chl.creditcard.ag003.service.NCCAG003Service;
import com.tfb.aibank.common.type.MailParamType;

// @formatter:off
/**
 * @(#)NCCAG003012Task.java
 * 
 * <p>Description:信用卡email 設定 012 resendOTP</p>
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
public class NCCAG003012Task extends AbstractAIBankBaseTask<NCCAG003012Rq, NCCAG003012Rs> {

    @Autowired
    private OtpAuthService otpAuthService;

    @Override
    public void validate(NCCAG003012Rq rqData) throws ActionException {
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
    public void handle(NCCAG003012Rq rqData, NCCAG003012Rs rsData) throws ActionException {
        NCCAG003CacheData cache = getCache(NCCAG003Service.NCCAG003_CACHE_KEY, NCCAG003CacheData.class);

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
    private void resendOTP(NCCAG003012Rq rqData, NCCAG003012Rs rsData, OtpAuthKeepData otpAuthKeepData, NCCAG003CacheData cache) throws ActionException {

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
        rsData.setResendCountdownSeconds(NCCAG003Service.DEFAULT_RESEND_COUNTDOWN_SECONDS);

        Map<String, String> mailParams = new HashMap<>();

        buildMailParams(newOtpAuthKeepData, mailParams);
        // email驗證信發到新email 規格無特別說明 依照 npsag001 SA 所述需求
        sendTxnResultMail(getLoginUser().getCustId(), getLoginUser().getUidDup(), getLoginUser().getUserId(), getLoginUser().getCompanyKind(), I18NUtils.getMessage("email.validate.subject", 0), rqData.getEmail(), mailParams);

        cache.setOtpAuthKeepData(newOtpAuthKeepData);
        setCache(NCCAG003Service.NCCAG003_CACHE_KEY, cache);
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
