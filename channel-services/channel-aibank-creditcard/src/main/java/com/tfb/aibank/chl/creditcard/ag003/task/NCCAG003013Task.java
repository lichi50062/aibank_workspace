package com.tfb.aibank.chl.creditcard.ag003.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.security.otp.OtpAuthService;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthKeepData;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthVerifyData;
import com.tfb.aibank.chl.creditcard.ag003.cache.NCCAG003CacheData;
import com.tfb.aibank.chl.creditcard.ag003.model.NCCAG003013Rq;
import com.tfb.aibank.chl.creditcard.ag003.model.NCCAG003013Rs;
import com.tfb.aibank.chl.creditcard.ag003.service.NCCAG003Service;
import com.tfb.aibank.chl.type.OtpStatusType;

// @formatter:off
/**
 * @(#)NCCAG003013Task.java
 * 
 * <p>Description:信用卡email 設定 013 validateOTP</p>
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
public class NCCAG003013Task extends AbstractAIBankBaseTask<NCCAG003013Rq, NCCAG003013Rs> {

    @Autowired
    private OtpAuthService otpAuthService;

    @Override
    public void validate(NCCAG003013Rq rqData) throws ActionException {
        if (StringUtils.isAnyBlank(rqData.getUserEncTxCode(), rqData.getUserTxHash(), rqData.getUserOtp())) {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

    @Override
    public void handle(NCCAG003013Rq rqData, NCCAG003013Rs rsData) throws ActionException {
        NCCAG003CacheData cache = getCache(NCCAG003Service.NCCAG003_CACHE_KEY, NCCAG003CacheData.class);
        OtpAuthKeepData otpAuthKeepData = cache.getOtpAuthKeepData();
        // 驗證OTP
        validateOTP(rqData, rsData, otpAuthKeepData);
        cache.setOtpAuthKeepData(cache.getOtpAuthKeepData());
        setCache(NCCAG003Service.NCCAG003_CACHE_KEY, cache);
    }

    /**
     * 驗證OTP
     * 
     * @param rqData
     *            OTP驗證請求資料
     * @param rsData
     *            OTP驗證回傳資料
     * @param txSecurityGuard
     *            交易安控驗證資料
     * 
     * @throws ActionException
     */
    private void validateOTP(NCCAG003013Rq rqData, NCCAG003013Rs rsData, OtpAuthKeepData otpAuthKeepData) throws ActionException {

        try {

            OtpAuthVerifyData verifyData = new OtpAuthVerifyData();
            verifyData.setUserEncTxCode(rqData.getUserEncTxCode());
            verifyData.setUserTxHash(rqData.getUserTxHash());
            verifyData.setUserOtp(rqData.getUserOtp());
            otpAuthService.validateOTP(getLoginUser(), otpAuthKeepData, verifyData, 5);

            // 驗證後回應的StatusType
            OtpStatusType statusType = OtpStatusType.findByCode(otpAuthKeepData.getModel().getStatus());

            // OTP 驗證錯誤處理
            if (!OtpStatusType.VERIFIED_OK.equals(statusType)) {
                if (OtpStatusType.EXPIRED_ERROR.equals(statusType)) {
                    // OTP驗證逾時，不可繼續驗證
                    rsData.setOtpResult(NCCAG003013Rs.OTP_RESULT_ERROR);
                    rsData.setOtpErrorDesc(I18NUtils.getMessage("email.auth.error.expired"));

                    logger.error("OTP驗證逾時");
                }
                else if (OtpStatusType.VERIFY_OTP_OVER_LIMIT_ERROR.equals(statusType)) {
                    // OTP驗證錯誤(已達錯誤次數上限)，不可繼續驗證
                    rsData.setOtpResult(NCCAG003013Rs.OTP_RESULT_ERROR);
                    rsData.setOtpErrorDesc(I18NUtils.getMessage("email.auth.error.over-limit"));
                    logger.error("OTP驗證錯誤(已達錯誤次數上限)");
                }
                else if (OtpStatusType.VERIFY_OTP_ERROR.equals(statusType)) {
                    // OTP驗證錯誤(未達錯誤次數上限)
                    rsData.setOtpResult(NCCAG003013Rs.OTP_RESULT_VALIDATE_ERROR);
                    rsData.setOtpErrorDesc(I18NUtils.getMessage("email.auth.error.normal"));
                    logger.error("OTP驗證錯誤(未達錯誤次數上限)");
                }
                else {
                    // 特殊錯誤(檢核交易代碼錯誤、檢核簡訊動態密碼生成因子失敗)，不可繼續驗證
                    rsData.setOtpResult(NCCAG003013Rs.OTP_RESULT_ERROR);
                    rsData.setOtpErrorDesc(I18NUtils.getMessage("email.auth.error.unknown"));
                    logger.error("OTP驗證異常,Code={},Desc={}", statusType.getCode(), statusType.getDesc());
                }
            }
            else {
                // 驗證成功
                rsData.setOtpResult(NCCAG003013Rs.OTP_RESULT_SUCCESS);
                logger.debug("OTP驗證成功");

                // 重置交易安控
                super.resetTxSecurity();
                // 啟動交易安控驗證
                super.initTxSecurity();
            }
        }
        catch (ServiceException e) {
            rsData.setOtpResult(NCCAG003013Rs.OTP_RESULT_ERROR);
            rsData.setOtpErrorDesc(I18NUtils.getMessage("otp.auth.error.unknown"));
            logger.error("OTP驗證未知錯誤", e);
        }
        catch (ActionException e) { // Fortify：Poor Error Handling: Overly Broad Catch，必要寫法，需攔截所有錯誤
            rsData.setOtpResult(NCCAG003013Rs.OTP_RESULT_ERROR);
            rsData.setOtpErrorDesc(I18NUtils.getMessage("otp.auth.error.unknown"));
            logger.error("OTP驗證未知錯誤", e);
        }

    }

}
