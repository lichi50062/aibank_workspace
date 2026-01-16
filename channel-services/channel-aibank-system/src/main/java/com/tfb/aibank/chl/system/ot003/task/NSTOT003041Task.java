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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.type.SessionKey;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.log.MBAccessLog;
import com.tfb.aibank.chl.component.security.TxSecurityGuard;
import com.tfb.aibank.chl.component.security.motp.MotpAuthService;
import com.tfb.aibank.chl.component.security.motp.bean.MotpAuthKeepData;
import com.tfb.aibank.chl.component.security.motp.bean.MotpAuthVerifyData;
import com.tfb.aibank.chl.component.security.motp.model.VerifyPushOtpResponse;
import com.tfb.aibank.chl.system.ot003.model.NSTOT003041Rq;
import com.tfb.aibank.chl.system.ot003.model.NSTOT003041Rs;
import com.tfb.aibank.chl.type.PreAuthType;
import com.tfb.aibank.chl.type.TxSecurityStepType;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.type.MotpVerifyResultType;

//@formatter:off
/**
* @(#)NSTOT003040Task.java
* 
* <p>Description:安控驗證共用 - 驗證交易安控MOTP - Task</p>
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
public class NSTOT003041Task extends AbstractAIBankBaseTask<NSTOT003041Rq, NSTOT003041Rs> {

    @Autowired
    private MotpAuthService motpAuthService;

    /** 交易安控驗證資料 */
    private TxSecurityGuard txSecurityGuard;

    @Override
    public void validate(NSTOT003041Rq rqData) throws ActionException {

        txSecurityGuard = getFromSession(SessionKey.TX_SECURITY_GUARD, TxSecurityGuard.class);
        if (null == txSecurityGuard) {
            logger.error("Session資料遺失or交易未呼叫初始化");
            throw ExceptionUtils.getActionException(AIBankErrorCode.TX_SECURITY_DATA_CHECK_ERROR);
        }

        if (txSecurityGuard.getTxSecurityType().isMotp() && !txSecurityGuard.getTxSecurityStepType().isMOTPSend()) {
            logger.error("資料狀態有誤");
            throw ExceptionUtils.getActionException(AIBankErrorCode.TX_SECURITY_DATA_CHECK_ERROR);
        }

        if (StringUtils.isEmpty(rqData.getUserOtp())) {
            logger.error("參數錯誤");
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }

    }

    @Override
    public void handle(NSTOT003041Rq rqData, NSTOT003041Rs rsData) throws ActionException {

        if (txSecurityGuard.getTxSecurityType().isMotp()) {
            if (StringUtils.equals("Auth", rqData.getUserOtp()) && //
                    PreAuthType.SEQ_AUTHING_4.equals(txSecurityGuard.getPreAuth()) && //
                    txSecurityGuard.getPreAuthTaskId().equals(txSecurityGuard.getTaskId())) {

                txSecurityGuard.setPreAuth(PreAuthType.SEQ_AUTH_FINISH_5);
                txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.OTP_AUTH_SUCCESS);
                setToSession(SessionKey.TX_SECURITY_GUARD, txSecurityGuard);
                rsData.setMotpResult(NSTOT003041Rs.MOTP_RESULT_SUCCESS);
                return;
            }
            
            // 驗證MOTP
            validateMOTP(rqData, rsData, txSecurityGuard);

            if (PreAuthType.PRE_AUTH_1.equals(txSecurityGuard.getPreAuth()) && //
                    StringUtils.equals(rsData.getMotpResult(), NSTOT003041Rs.MOTP_RESULT_SUCCESS)) {
                txSecurityGuard.setPreAuth(PreAuthType.AUTH_SUCC_2);
                setToSession(SessionKey.TX_SECURITY_GUARD, txSecurityGuard);
            }

            logger.debug("### TxSecurityGuard validateTxAuth ### {}", txSecurityGuard.getInfo());

        }

    }

    /**
     * 驗證MOTP
     * 
     * @param rqData
     *            MOTP驗證請求資料
     * @param rsData
     *            MOTP驗證回傳資料
     * @param txSecurityGuard
     *            交易安控驗證資料
     * 
     * @throws ActionException
     */
    private void validateMOTP(NSTOT003041Rq rqData, NSTOT003041Rs rsData, TxSecurityGuard txSecurityGuard) throws ActionException {

        try {

            // 驗證推播OTP
            MotpAuthKeepData keepData = txSecurityGuard.getMotpAuthKeepData();
            MotpAuthVerifyData verifyData = new MotpAuthVerifyData();
            verifyData.setUserOtp(rqData.getUserOtp());
            motpAuthService.validateMOTP(getLoginUser(), verifyData, keepData);
            txSecurityGuard.setMotpAuthKeepData(keepData);

            // 推播OTP驗證結果
            VerifyPushOtpResponse verifyPushOtpResponse = keepData.getVerifyPushOtpResponse();
            MotpVerifyResultType motpVerifyResultType = MotpVerifyResultType.find(verifyPushOtpResponse.getMotpVerifyResultType());

            // MOTP 驗證錯誤處理
            if (!MotpVerifyResultType.MOTP_VERIFY_SUCCESS.equals(motpVerifyResultType)) {
                if (MotpVerifyResultType.MOTP_VERIFY_EXPIRE.equals(motpVerifyResultType)) {
                    // MOTP驗證逾時
                    // 不可繼續驗證
                    rsData.setMotpResult(NSTOT003041Rs.MOTP_RESULT_ERROR);
                    rsData.setMotpErrorDesc(I18NUtils.getMessage("motp.auth.error.expired"));
                    txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.MOTP_AUTH_FAIL);
                    logger.error("MOTP驗證已逾時");
                }
                else if (MotpVerifyResultType.MOTP_VERIFY_EXCEED_FAIL_TIMES.equals(motpVerifyResultType)) {
                    // MOTP驗證錯誤(已達錯誤次數上限)
                    // 不可繼續驗證
                    rsData.setMotpResult(NSTOT003041Rs.MOTP_RESULT_ERROR);
                    rsData.setMotpErrorDesc(I18NUtils.getMessage("motp.auth.error.over-limit"));
                    txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.MOTP_AUTH_FAIL);
                    logger.error("MOTP驗證失敗次數過多");
                }
                else if (MotpVerifyResultType.MOTP_VERIFY_FAIL.equals(motpVerifyResultType)) {
                    // MOTP驗證錯誤(未達錯誤次數上限)
                    rsData.setMotpResult(NSTOT003041Rs.MOTP_RESULT_VALIDATE_ERROR);
                    rsData.setMotpErrorDesc(I18NUtils.getMessage("motp.auth.error.normal"));
                    logger.error("MOTP驗證不通過");
                }
                else {
                    // MOTP驗證失敗(其他)
                    // 不可繼續驗證
                    rsData.setMotpResult(NSTOT003041Rs.MOTP_RESULT_ERROR);
                    rsData.setMotpErrorDesc(I18NUtils.getMessage("motp.auth.error.unknown"));
                    txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.MOTP_AUTH_FAIL);
                    logger.error("MOTP驗證失敗(其他),Code={},Message={}", verifyPushOtpResponse.getCode(), verifyPushOtpResponse.getMessage());
                }
                updateAccessLog(txSecurityGuard.getTaskId(), txSecurityGuard.getTxSecurityType().getType(), 2);
            }
            else {
                // 驗證成功
                rsData.setMotpResult(NSTOT003041Rs.MOTP_RESULT_SUCCESS);
                txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.MOTP_AUTH_SUCCESS);
                logger.debug("MOTP驗證成功");
                updateAccessLog(txSecurityGuard.getTaskId(), txSecurityGuard.getTxSecurityType().getType(), 0);
            }
        }
        catch (ServiceException e) {
            rsData.setMotpResult(NSTOT003041Rs.MOTP_RESULT_ERROR);
            rsData.setMotpErrorDesc(I18NUtils.getMessage("motp.auth.error.unknown"));
            txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.MOTP_AUTH_FAIL);
            logger.error("MOTP驗證未知錯誤", e);
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
