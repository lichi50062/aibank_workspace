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
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.type.SessionKey;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.devicebinding.model.RetrieveUserBindingResponse;
import com.tfb.aibank.chl.component.security.TxSecurityGuard;
import com.tfb.aibank.chl.component.security.motp.MotpAuthService;
import com.tfb.aibank.chl.component.security.motp.bean.MotpAuthInitData;
import com.tfb.aibank.chl.component.security.motp.bean.MotpAuthKeepData;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.system.ot003.model.NSTOT003021Rq;
import com.tfb.aibank.chl.system.ot003.model.NSTOT003021Rs;
import com.tfb.aibank.chl.system.resource.UserResource;
import com.tfb.aibank.chl.type.PreAuthType;
import com.tfb.aibank.chl.type.TxSecurityStepType;
import com.tfb.aibank.common.error.AIBankErrorCode;

//@formatter:off
/**
* @(#)NSTOT003021Task.java
* 
* <p>Description:安控驗證共用 - 產生MOTP - Task</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/16, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NSTOT003021Task extends AbstractAIBankBaseTask<NSTOT003021Rq, NSTOT003021Rs> {

    @Autowired
    private MotpAuthService motpAuthService;

    @Autowired
    private UserResource userResource;

    /** 交易安控驗證資料 */
    private TxSecurityGuard txSecurityGuard;

    @Override
    public void validate(NSTOT003021Rq rqData) throws ActionException {

        txSecurityGuard = getFromSession(SessionKey.TX_SECURITY_GUARD, TxSecurityGuard.class);
        if (null == txSecurityGuard) {
            logger.error("Session資料遺失or交易未呼叫初始化");
            throw ExceptionUtils.getActionException(AIBankErrorCode.TX_SECURITY_DATA_CHECK_ERROR);
        }

        if (txSecurityGuard.getTxSecurityType().isMotp() && null == txSecurityGuard.getMotpAuthInitData()) {
            // #8555 MOTP 也需檢查 PreAuth 驗證結果
            if (!PreAuthType.SEQ_TXN_INIT_3.equals(txSecurityGuard.getPreAuth())) {
                logger.error("尚未設定MOTP初始資料");
                throw ExceptionUtils.getActionException(AIBankErrorCode.TX_SECURITY_DATA_CHECK_ERROR);
            }
        }

    }

    @Override
    public void handle(NSTOT003021Rq rqData, NSTOT003021Rs rsData) throws ActionException {

        if (txSecurityGuard.getTxSecurityType().isMotp()) {

            if (PreAuthType.SEQ_TXN_INIT_3.equals(txSecurityGuard.getPreAuth()) && // x
                    StringUtils.equals(txSecurityGuard.getTaskId(), txSecurityGuard.getPreAuthTaskId())) {
                txSecurityGuard.setPreAuth(PreAuthType.SEQ_AUTHING_4);
                txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.MOTP_SEND);
                rsData.setSkipAuth(true);
            }else {
                // 產生MOTP驗證資料
                generateMOTP(rqData, rsData);
            }


            // 暫存
            setToSession(SessionKey.TX_SECURITY_GUARD, txSecurityGuard);

            logger.debug("### TxSecurityGuard generateTxAuth ### {}", txSecurityGuard.getInfo());
        }

    }

    /**
     * 產生MOTP驗證資料
     * 
     * @throws ActionException
     */
    private void generateMOTP(NSTOT003021Rq rqData, NSTOT003021Rs rsData) throws ActionException {

        AIBankUser user = getLoginUser();
        // 初始化並發送OTP簡訊
        MotpAuthInitData motpAuthInitData = txSecurityGuard.getMotpAuthInitData();
        MotpAuthKeepData motpAuthKeepData = new MotpAuthKeepData();
        motpAuthService.sendMOTP(getLoginUser(), motpAuthInitData, motpAuthKeepData);
        txSecurityGuard.setMotpAuthKeepData(motpAuthKeepData);

        // 設定回傳資料
        RetrieveUserBindingResponse retrieveUserBindingResponse = userResource.retrieveUserBinding(user.getCustId(), user.getUidDup(), user.getUserId(), user.getCompanyKind(), getLocale().toString());

        rsData.setDeviceAlias(StringUtils.defaultString(retrieveUserBindingResponse.getDeviceModel()));
        rsData.setTxCode(motpAuthKeepData.getCreatePushOtpResponse().getTxCode());
        rsData.setCustInfo(motpAuthKeepData.getCreatePushOtpResponse().getCustInfo());
        rsData.setSameDevice(motpAuthKeepData.getCreatePushOtpResponse().isSameDevice());
        rsData.setExpireTime(motpAuthKeepData.getCreatePushOtpResponse().getExpireTime());
        rsData.setClientId(motpAuthKeepData.getCreatePushOtpResponse().getClientId());
        rsData.setChallenge(motpAuthKeepData.getCreatePushOtpResponse().getChallenge());

        // 已發送推播OTP
        txSecurityGuard.setTxSecurityStepType(TxSecurityStepType.MOTP_SEND);
    }

}
