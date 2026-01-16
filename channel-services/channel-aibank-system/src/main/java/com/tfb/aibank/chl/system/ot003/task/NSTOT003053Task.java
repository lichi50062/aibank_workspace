package com.tfb.aibank.chl.system.ot003.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.ibmb.type.SessionKey;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.security.EmailOtpSecurityGuard;
import com.tfb.aibank.chl.component.security.otp.OtpAuthService;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthKeepData;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthVerifyData;
import com.tfb.aibank.chl.system.ot003.model.NSTOT003053Rq;
import com.tfb.aibank.chl.system.ot003.model.NSTOT003053Rs;
import com.tfb.aibank.chl.type.OtpStatusType;
import com.tfb.aibank.chl.type.TxSecurityStepType;

//@formatter:off
/**
* @(#)NSTOT003053Task.java
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
public class NSTOT003053Task extends AbstractAIBankBaseTask<NSTOT003053Rq, NSTOT003053Rs> {

    @Autowired
    private OtpAuthService otpAuthService;

    @Override
    public void validate(NSTOT003053Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NSTOT003053Rq rqData, NSTOT003053Rs rsData) throws ActionException {

        EmailOtpSecurityGuard cache = getFromSession(SessionKey.EMAIL_OTP, EmailOtpSecurityGuard.class);

        OtpAuthKeepData otpAuthKeepData = cache.getOtpAuthKeepData();

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
                cache.setTxSecurityStepType(TxSecurityStepType.MOTP_AUTH_FAIL);
                if (OtpStatusType.EXPIRED_ERROR.equals(statusType)) {
                    // OTP驗證逾時，不可繼續驗證
                    rsData.setOtpResult(NSTOT003053Rs.OTP_RESULT_ERROR);
                    rsData.setOtpErrorDesc(I18NUtils.getMessage("email.auth.error.expired"));

                    logger.error("OTP驗證逾時");
                }
                else if (OtpStatusType.VERIFY_OTP_OVER_LIMIT_ERROR.equals(statusType)) {
                    // OTP驗證錯誤(已達錯誤次數上限)，不可繼續驗證
                    rsData.setOtpResult(NSTOT003053Rs.OTP_RESULT_ERROR);
                    rsData.setOtpErrorDesc(I18NUtils.getMessage("email.auth.error.over-limit"));
                    logger.error("OTP驗證錯誤(已達錯誤次數上限)");
                }
                else if (OtpStatusType.VERIFY_OTP_ERROR.equals(statusType)) {
                    // OTP驗證錯誤(未達錯誤次數上限)
                    rsData.setOtpResult(NSTOT003053Rs.OTP_RESULT_VALIDATE_ERROR);
                    rsData.setOtpErrorDesc(I18NUtils.getMessage("email.auth.error.normal"));
                    logger.error("OTP驗證錯誤(未達錯誤次數上限)");
                }
                else {
                    // 特殊錯誤(檢核交易代碼錯誤、檢核簡訊動態密碼生成因子失敗)，不可繼續驗證
                    rsData.setOtpResult(NSTOT003053Rs.OTP_RESULT_ERROR);
                    rsData.setOtpErrorDesc(I18NUtils.getMessage("email.auth.error.unknown"));
                    logger.error("OTP驗證異常,Code={},Desc={}", statusType.getCode(), statusType.getDesc());
                }
            }
            else {
                // 驗證成功
                rsData.setOtpResult(NSTOT003053Rs.OTP_RESULT_SUCCESS);
                cache.setTxSecurityStepType(TxSecurityStepType.OTP_AUTH_SUCCESS);
                logger.debug("OTP驗證成功");
            }
        }
        catch (ServiceException e) {
            rsData.setOtpResult(NSTOT003053Rs.OTP_RESULT_ERROR);
            rsData.setOtpErrorDesc(I18NUtils.getMessage("otp.auth.error.unknown"));
            logger.error("OTP驗證未知錯誤", e);
        }
        finally {
            setToSession(SessionKey.EMAIL_OTP, cache);
        }

    }

}
