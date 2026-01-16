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
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.type.SessionKey;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.security.TxSecurityGuard;
import com.tfb.aibank.chl.component.security.otp.OtpAuthService;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthInitData;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthKeepData;
import com.tfb.aibank.chl.system.ot003.model.NSTOT003020Rq;
import com.tfb.aibank.chl.system.ot003.model.NSTOT003020Rs;
import com.tfb.aibank.chl.type.PreAuthType;
import com.tfb.aibank.chl.type.TxSecurityStepType;
import com.tfb.aibank.common.error.AIBankErrorCode;

//@formatter:off
/**
* @(#)NSTOT003020Task.java
* 
* <p>Description:安控驗證共用 - 產生OTP - Task</p>
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
public class NSTOT003020Task extends AbstractAIBankBaseTask<NSTOT003020Rq, NSTOT003020Rs> {

    @Autowired
    private OtpAuthService otpAuthService;

    /** 交易安控驗證資料 */
    private TxSecurityGuard txSecurityGuard;

    @Override
    public void validate(NSTOT003020Rq rqData) throws ActionException {

        txSecurityGuard = getFromSession(SessionKey.TX_SECURITY_GUARD, TxSecurityGuard.class);
        if (null == txSecurityGuard) {
            logger.error("Session資料遺失or交易未呼叫初始化");
            throw ExceptionUtils.getActionException(AIBankErrorCode.TX_SECURITY_DATA_CHECK_ERROR);
        }
        // OTP 為空值表示 initSecurity PreAuthType = 2 變成 3 , 不需重新產生OTP
        if (txSecurityGuard.getTxSecurityType().isOtp() && null == txSecurityGuard.getOtpAuthInitData()) {
            if (!PreAuthType.SEQ_TXN_INIT_3.equals(txSecurityGuard.getPreAuth())) {
                logger.error("尚未設定OTP初始資料");
                throw ExceptionUtils.getActionException(AIBankErrorCode.TX_SECURITY_DATA_CHECK_ERROR);
            }
        }

    }

    @Override
    public void handle(NSTOT003020Rq rqData, NSTOT003020Rs rsData) throws ActionException {

        if (txSecurityGuard.getTxSecurityType().isOtp()) {

            if (PreAuthType.SEQ_TXN_INIT_3.equals(txSecurityGuard.getPreAuth()) && // x
                    StringUtils.equals(txSecurityGuard.getTaskId(), txSecurityGuard.getPreAuthTaskId())) {

                txSecurityGuard.setPreAuth(PreAuthType.SEQ_AUTHING_4);
                txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.OTP_SEND);
                rsData.setSkipAuth(true);
            }
            else {
                // 產生OTP驗證資料
                generateOTP(rqData, rsData, txSecurityGuard);
            }

            // 暫存
            setToSession(SessionKey.TX_SECURITY_GUARD, txSecurityGuard);

            logger.debug("### TxSecurityGuard generateTxAuth ### {}", txSecurityGuard.getInfo());
        }

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
    private void generateOTP(NSTOT003020Rq rqData, NSTOT003020Rs rsData, TxSecurityGuard txSecurityGuard) throws ActionException {

        // 初始化並發送OTP簡訊
        OtpAuthInitData otpAuthInitData = txSecurityGuard.getOtpAuthInitData();
        OtpAuthKeepData otpAuthKeepData = otpAuthService.sendOTP(getLoginUser(), otpAuthInitData);
        txSecurityGuard.setOtpAuthKeepData(otpAuthKeepData);

        // 設定回傳資料
        rsData.setPhone(DataMaskUtil.maskMobile(txSecurityGuard.getOtpMobile()));
        rsData.setTxCode(otpAuthKeepData.getModel().getTxCode());
        rsData.setUserEncTxCode(otpAuthKeepData.getEncTxCode());
        rsData.setUserTxHash(otpAuthKeepData.getHashTxFactor());
        rsData.setCountdownSeconds(OtpAuthService.DEFAULT_COUNTDOWN_SECONDS);
        rsData.setCanResend(true);
        rsData.setResendCountdownSeconds(OtpAuthService.DEFAULT_RESEND_COUNTDOWN_SECONDS);

        // 已發送簡訊OTP
        txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.OTP_SEND);
    }

}
