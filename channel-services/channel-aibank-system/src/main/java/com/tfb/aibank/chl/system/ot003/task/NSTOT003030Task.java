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
import com.ibm.tw.ibmb.type.SessionKey;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.security.TxSecurityGuard;
import com.tfb.aibank.chl.component.security.otp.OtpAuthService;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthKeepData;
import com.tfb.aibank.chl.system.ot003.model.NSTOT003030Rq;
import com.tfb.aibank.chl.system.ot003.model.NSTOT003030Rs;
import com.tfb.aibank.common.error.AIBankErrorCode;

//@formatter:off
/**
* @(#)NSTOT003030Task.java
* 
* <p>Description:安控驗證共用 - 重送OTP - Task</p>
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
public class NSTOT003030Task extends AbstractAIBankBaseTask<NSTOT003030Rq, NSTOT003030Rs> {

    @Autowired
    private OtpAuthService otpAuthService;

    /** 交易安控驗證資料 */
    private TxSecurityGuard txSecurityGuard;

    @Override
    public void validate(NSTOT003030Rq rqData) throws ActionException {

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
    public void handle(NSTOT003030Rq rqData, NSTOT003030Rs rsData) throws ActionException {

        if (txSecurityGuard.getTxSecurityType().isOtp()) {

            // 重新發送OTP
            resendOTP(rqData, rsData, txSecurityGuard);

            // 暫存
            setToSession(SessionKey.TX_SECURITY_GUARD, txSecurityGuard);

            logger.debug("### TxSecurityGuard resendTxAuth ### {}", txSecurityGuard.getInfo());
        }
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
    private void resendOTP(NSTOT003030Rq rqData, NSTOT003030Rs rsData, TxSecurityGuard txSecurityGuard) throws ActionException {

        // 重新發送OTP簡訊
        OtpAuthKeepData otpAuthKeepData = txSecurityGuard.getOtpAuthKeepData();
        OtpAuthKeepData newOtpAuthKeepData = otpAuthService.resendOTP(getLoginUser(), otpAuthKeepData);
        txSecurityGuard.setOtpAuthKeepData(newOtpAuthKeepData);

        // 設定回傳資料
        rsData.setPhone(DataMaskUtil.maskMobile(txSecurityGuard.getOtpMobile()));
        rsData.setTxCode(newOtpAuthKeepData.getModel().getTxCode());
        rsData.setUserEncTxCode(newOtpAuthKeepData.getEncTxCode());
        rsData.setUserTxHash(newOtpAuthKeepData.getHashTxFactor());
        rsData.setCountdownSeconds(OtpAuthService.DEFAULT_COUNTDOWN_SECONDS);
        rsData.setCanResend(true);
        rsData.setResendCountdownSeconds(OtpAuthService.DEFAULT_RESEND_COUNTDOWN_SECONDS);
    }

}
