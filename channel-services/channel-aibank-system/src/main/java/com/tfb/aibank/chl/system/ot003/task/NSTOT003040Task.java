/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.system.ot003.task;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.type.SessionKey;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.log.MBAccessLog;
import com.tfb.aibank.chl.component.security.TxSecurityGuard;
import com.tfb.aibank.chl.component.security.otp.OtpAuthService;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthKeepData;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthVerifyData;
import com.tfb.aibank.chl.system.ot003.model.NSTOT003040Rq;
import com.tfb.aibank.chl.system.ot003.model.NSTOT003040Rs;
import com.tfb.aibank.chl.type.OtpStatusType;
import com.tfb.aibank.chl.type.PreAuthType;
import com.tfb.aibank.chl.type.TxSecurityStepType;
import com.tfb.aibank.common.error.AIBankErrorCode;

//@formatter:off
/**
* @(#)NSTOT003040Task.java
* 
* <p>Description:安控驗證共用 - 驗證交易安控 - Task</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/27, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NSTOT003040Task extends AbstractAIBankBaseTask<NSTOT003040Rq, NSTOT003040Rs> {

    @Autowired
    private OtpAuthService otpAuthService;

    /** 交易安控驗證資料 */
    private TxSecurityGuard txSecurityGuard;

    @Override
    public void validate(NSTOT003040Rq rqData) throws ActionException {

        txSecurityGuard = getFromSession(SessionKey.TX_SECURITY_GUARD, TxSecurityGuard.class);
        if (null == txSecurityGuard) {
            logger.error("Session資料遺失or交易未呼叫初始化");
            throw ExceptionUtils.getActionException(AIBankErrorCode.TX_SECURITY_DATA_CHECK_ERROR);
        }

        if (txSecurityGuard.getTxSecurityType().isOtp()) {
            if (!txSecurityGuard.getTxSecurityStepType().isOTPSend()) {
                logger.error("資料狀態有誤");
                throw ExceptionUtils.getActionException(AIBankErrorCode.TX_SECURITY_DATA_CHECK_ERROR);
            }
        }

    }

    @Override
    public void handle(NSTOT003040Rq rqData, NSTOT003040Rs rsData) throws ActionException {

        if (txSecurityGuard.getTxSecurityType().isOtp()) {
            // SkipAuth UserOtp=Auth
            if (StringUtils.equals("Auth", rqData.getUserOtp()) && //
                    PreAuthType.SEQ_AUTHING_4.equals(txSecurityGuard.getPreAuth()) && //
                    txSecurityGuard.getPreAuthTaskId().equals(txSecurityGuard.getTaskId())) {

                txSecurityGuard.setPreAuth(PreAuthType.SEQ_AUTH_FINISH_5);
                txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.OTP_AUTH_SUCCESS);
                setToSession(SessionKey.TX_SECURITY_GUARD, txSecurityGuard);
                rsData.setOtpResult("0");
                return;
            }

            // 驗證OTP
            validateOTP(rqData, rsData, txSecurityGuard);

            if (PreAuthType.PRE_AUTH_1.equals(txSecurityGuard.getPreAuth()) && //
                    StringUtils.equals(rsData.getOtpResult(), "0")) {
                txSecurityGuard.setPreAuth(PreAuthType.AUTH_SUCC_2);
                setToSession(SessionKey.TX_SECURITY_GUARD, txSecurityGuard);
            }

            logger.debug("### TxSecurityGuard validateTxAuth ### {}", txSecurityGuard.getInfo());

        }

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
    private void validateOTP(NSTOT003040Rq rqData, NSTOT003040Rs rsData, TxSecurityGuard txSecurityGuard) throws ActionException {

        try {

            // 驗證安控
            OtpAuthKeepData otpAuthKeepData = txSecurityGuard.getOtpAuthKeepData();
            OtpAuthVerifyData verifyData = new OtpAuthVerifyData();
            verifyData.setUserEncTxCode(rqData.getUserEncTxCode());
            verifyData.setUserTxHash(rqData.getUserTxHash());
            verifyData.setUserOtp(rqData.getUserOtp());
            otpAuthService.validateOTP(getLoginUser(), otpAuthKeepData, verifyData);

            // 驗證後回應的StatusType
            OtpStatusType statusType = OtpStatusType.findByCode(otpAuthKeepData.getModel().getStatus());

            // OTP 驗證錯誤處理
            if (!OtpStatusType.VERIFIED_OK.equals(statusType)) {
                if (OtpStatusType.EXPIRED_ERROR.equals(statusType)) {
                    // OTP驗證逾時，不可繼續驗證
                    rsData.setOtpResult(NSTOT003040Rs.OTP_RESULT_ERROR);
                    // 驗證碼已逾時
                    rsData.setOtpErrorDesc(I18NUtils.getMessage("otp.auth.error.expired"));
                    rsData.setOtpErrorDescTW(I18NUtils.getMessage("otp.auth.error.expired", Locale.TAIWAN));
                    txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.OTP_AUTH_FAIL);
                    logger.error("OTP驗證逾時");
                }
                else if (OtpStatusType.VERIFY_OTP_OVER_LIMIT_ERROR.equals(statusType)) {
                    // OTP驗證錯誤(已達錯誤次數上限)，不可繼續驗證
                    rsData.setOtpResult(NSTOT003040Rs.OTP_RESULT_ERROR);
                    // 驗證碼錯誤次數已達本次上限
                    rsData.setOtpErrorDesc(I18NUtils.getMessage("otp.auth.error.over-limit"));
                    rsData.setOtpErrorDescTW(I18NUtils.getMessage("otp.auth.error.over-limit", Locale.TAIWAN));
                    txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.OTP_AUTH_FAIL);
                    logger.error("OTP驗證錯誤(已達錯誤次數上限)");
                }
                else if (OtpStatusType.VERIFY_OTP_ERROR.equals(statusType)) {
                    // OTP驗證錯誤(未達錯誤次數上限)
                    rsData.setOtpResult(NSTOT003040Rs.OTP_RESULT_VALIDATE_ERROR);
                    // 驗證失敗
                    rsData.setOtpErrorDesc(I18NUtils.getMessage("otp.auth.error.normal"));
                    rsData.setOtpErrorDescTW(I18NUtils.getMessage("otp.auth.error.normal", Locale.TAIWAN));
                    txSecurityGuard.setOtpAuthKeepData(otpAuthKeepData);
                    logger.error("OTP驗證錯誤(未達錯誤次數上限)");
                }
                else {
                    // 特殊錯誤(檢核交易代碼錯誤、檢核簡訊動態密碼生成因子失敗)，不可繼續驗證
                    rsData.setOtpResult(NSTOT003040Rs.OTP_RESULT_ERROR);
                    // 無法執行簡訊動態密碼驗證，請重新執行交易
                    rsData.setOtpErrorDesc(I18NUtils.getMessage("otp.auth.error.unknown"));
                    rsData.setOtpErrorDescTW(I18NUtils.getMessage("otp.auth.error.unknown", Locale.TAIWAN));
                    txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.OTP_AUTH_FAIL);
                    logger.error("OTP驗證異常,Code={},Desc={}", statusType.getCode(), statusType.getDesc());
                }
                updateAccessLog(txSecurityGuard.getTaskId(), txSecurityGuard.getTxSecurityType().getType(), 2);
            }
            else {
                // 驗證成功
                rsData.setOtpResult(NSTOT003040Rs.OTP_RESULT_SUCCESS);
                txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.OTP_AUTH_SUCCESS);
                logger.debug("OTP驗證成功");
                updateAccessLog(txSecurityGuard.getTaskId(), txSecurityGuard.getTxSecurityType().getType(), 0);
            }
        }
        catch (ServiceException e) {
            rsData.setOtpResult(NSTOT003040Rs.OTP_RESULT_ERROR);
            // 無法執行簡訊動態密碼驗證，請重新執行交易
            rsData.setOtpErrorDesc(I18NUtils.getMessage("otp.auth.error.unknown"));
            rsData.setOtpErrorDescTW(I18NUtils.getMessage("otp.auth.error.unknown", Locale.TAIWAN));
            txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.OTP_AUTH_FAIL);
            logger.error("OTP驗證未知錯誤", e);
        }
        finally {
            setToSession(SessionKey.TX_SECURITY_GUARD, txSecurityGuard);
        }

    }

    private void updateAccessLog(String taskId, int security, int resultCode) {
        MBAccessLog accessLog = getAccessLog();
        accessLog.setTaskId(taskId);
        accessLog.setSecurityType(security);
        accessLog.setResultCode(resultCode);
    }

}
