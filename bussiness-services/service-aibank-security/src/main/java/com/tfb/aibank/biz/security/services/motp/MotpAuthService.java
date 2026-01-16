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
package com.tfb.aibank.biz.security.services.motp;

import java.lang.reflect.InvocationTargetException;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.security.services.motp.helper.MotpServiceHelper;
import com.tfb.aibank.biz.security.services.motp.helper.model.CreateMotpTransDataCondition;
import com.tfb.aibank.biz.security.services.motp.helper.model.CreateMotpTransDataResult;
import com.tfb.aibank.biz.security.services.motp.helper.model.FindMotpTransDataCondition;
import com.tfb.aibank.biz.security.services.motp.helper.model.FindMotpTransDataResult;
import com.tfb.aibank.biz.security.services.motp.helper.model.PreAuthCheckCondition;
import com.tfb.aibank.biz.security.services.motp.helper.model.PreAuthCheckResult;
import com.tfb.aibank.biz.security.services.motp.helper.model.SendOfflineOtpCondition;
import com.tfb.aibank.biz.security.services.motp.helper.model.SendOfflineOtpResult;
import com.tfb.aibank.biz.security.services.motp.helper.model.SendPushOtpCondition;
import com.tfb.aibank.biz.security.services.motp.helper.model.SendPushOtpResult;
import com.tfb.aibank.biz.security.services.motp.helper.model.VerifyPushOtpCondition;
import com.tfb.aibank.biz.security.services.motp.helper.model.VerifyPushOtpResult;
import com.tfb.aibank.biz.security.services.motp.model.CheckMotpStatusRequest;
import com.tfb.aibank.biz.security.services.motp.model.CheckMotpStatusResponse;
import com.tfb.aibank.biz.security.services.motp.model.CreatePushOtpRequest;
import com.tfb.aibank.biz.security.services.motp.model.CreatePushOtpResponse;
import com.tfb.aibank.biz.security.services.motp.model.VerifyPushOtpRequest;
import com.tfb.aibank.biz.security.services.motp.model.VerifyPushOtpResponse;
import com.tfb.aibank.biz.security.services.motp.type.MotpSendResult;
import com.tfb.aibank.biz.security.services.motp.type.MotpTxCarrierType;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.type.MotpAuthVerifyType;

// @formatter:off
/**
 * @(#)MotpAuthService.java
 * 
 * <p>Description:MOTP驗證服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/14, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class MotpAuthService {

    private static final IBLog logger = IBLog.getLog(MotpBindService.class);

    private IdentityService identityService;

    private MotpServiceHelper motpServiceHelper;

    public MotpAuthService(IdentityService identityService, MotpServiceHelper motpServiceHelper) {
        super();
        this.identityService = identityService;
        this.motpServiceHelper = motpServiceHelper;
    }

    /**
     * 使用MOTP驗證前檢查狀態
     * 
     * @param request
     * @return
     * @throws ActionException
     */
    public CheckMotpStatusResponse checkMotpStatus(CheckMotpStatusRequest request) throws ActionException {

        CheckMotpStatusResponse response = new CheckMotpStatusResponse();

        // 取得使用者資料
        IdentityData identityData = getUser(request.getCustId(), request.getUserId(), request.getCompanyKind(), request.getUidDup());

        // MOTP安控前置檢查
        PreAuthCheckCondition preAuthCheckCondition = new PreAuthCheckCondition();
        preAuthCheckCondition.setCompanyKey(identityData.getCompanyKey());
        preAuthCheckCondition.setUserKey(identityData.getUserKey());
        preAuthCheckCondition.setDeviceIxd(request.getDeviceIxd());
        PreAuthCheckResult preAuthCheckResult = motpServiceHelper.preAuthCheck(preAuthCheckCondition);

        // 設置回傳資料
        response.setMotpAuthVerifyType(preAuthCheckResult.getMotpAuthVerifyType().getCode());

        return response;
    }

    /**
     * 發送推播OTP
     * 
     * @param request
     * @return
     * @throws ActionException
     */
    public CreatePushOtpResponse createPushOtp(CreatePushOtpRequest request) throws ActionException {

        CreatePushOtpResponse response = new CreatePushOtpResponse();

        // 取得使用者資料
        IdentityData identityData = getUser(request.getCustId(), request.getUserId(), request.getCompanyKind(), request.getUidDup());

        // MOTP安控前置檢查
        PreAuthCheckCondition preAuthCheckCondition = new PreAuthCheckCondition();
        preAuthCheckCondition.setCompanyKey(identityData.getCompanyKey());
        preAuthCheckCondition.setUserKey(identityData.getUserKey());
        preAuthCheckCondition.setDeviceIxd(request.getDeviceIxd());
        PreAuthCheckResult preAuthCheckResult = motpServiceHelper.preAuthCheck(preAuthCheckCondition);

        // 交易前已會檢查，因為此階段需要裝置資訊，若檢查錯則直接中斷流程
        if (!preAuthCheckResult.getMotpAuthVerifyType().isValid()) {
            if (preAuthCheckResult.getMotpAuthVerifyType() == MotpAuthVerifyType.NO_BINDING_DEVICE) {
                throw new ActionException(AIBankErrorCode.MOTP_BINDING_DEVICE_NOT_FOUND);
            }
            else if (preAuthCheckResult.getMotpAuthVerifyType() == MotpAuthVerifyType.BINDING_STATUS_ABNORMAL) {
                throw new ActionException(AIBankErrorCode.MOTP_BINDING_STATUS_ABNORMAL);
            }
            else {
                throw new ActionException(AIBankErrorCode.MOTP_SERVICE_UNAVALIBLE);
            }
        }

        // 根據發送請求的裝置ID檢查是否與綁定裝置ID相同，若相同則使用離線載具方式
        MotpTxCarrierType motpTxCarrierType = MotpTxCarrierType.PUSH_NOTIFY;
        if (StringUtils.equals(request.getDeviceIxd(), preAuthCheckResult.getBindingDeviceInfo().getDeviceUuid())) {
            motpTxCarrierType = MotpTxCarrierType.OFFLINE;
        }

        // 產生MOTP認證資訊
        CreateMotpTransDataCondition createMotpTransDataCondition = new CreateMotpTransDataCondition();
        createMotpTransDataCondition.setMotpDeviceKey(preAuthCheckResult.getBindingDeviceInfo().getMotpDeviceKey());
        createMotpTransDataCondition.setCompanyKey(identityData.getCompanyKey());
        createMotpTransDataCondition.setUserKey(identityData.getUserKey());
        createMotpTransDataCondition.setDeviceIxd(request.getDeviceIxd());
        createMotpTransDataCondition.setTaskIxd(request.getTaskIxd());
        createMotpTransDataCondition.setTitle(request.getTitle());
        createMotpTransDataCondition.setMessage(request.getMessage());
        createMotpTransDataCondition.setCustInfo(request.getCustInfo());
        createMotpTransDataCondition.setTxFactor(request.getTxFactor());
        createMotpTransDataCondition.setTxSeedType(request.getTxSeedType());
        createMotpTransDataCondition.setClientIp(request.getClientIp());
        createMotpTransDataCondition.setMotpTxCarrierType(motpTxCarrierType);
        CreateMotpTransDataResult createMotpTransDataResult = motpServiceHelper.createMotpTransData(createMotpTransDataCondition);

        // 若為推播載具發送OTP
        if (motpTxCarrierType.isPushNotify()) {

            // 發送推播OTP認證
            SendPushOtpCondition sendPushOtpCondition = new SendPushOtpCondition();
            sendPushOtpCondition.setMotpTransData(createMotpTransDataResult.getMotpTransData());
            sendPushOtpCondition.setCarrierData(createMotpTransDataResult.getCarrierData());
            sendPushOtpCondition.setAccount(preAuthCheckResult.getBindingDeviceInfo().getAccountId());
            sendPushOtpCondition.setGroup(preAuthCheckResult.getBindingDeviceInfo().getGroup());
            SendPushOtpResult sendPushOtpResult = motpServiceHelper.sendPushOtp(sendPushOtpCondition, request.getCustId(), request.getUserId());

            // 處理發送失敗情境
            if (!sendPushOtpResult.getSendResult().isValidStatus()) {
                if (sendPushOtpResult.getSendResult() == MotpSendResult.MOTP_SEND_FAIL_WITHOUT_OTP || sendPushOtpResult.getSendResult() == MotpSendResult.SYSTEM_ERROR_PUSH_MOTP) {
                    throw new ActionException(AIBankErrorCode.MOTP_SEND_OTP_FAILED);
                }
                else {
                    throw new ActionException(AIBankErrorCode.MOTP_SEND_OTP_UNKNOWN_ERROR);
                }
            }

            // 設置回傳資料
            response.setExpireTime(sendPushOtpResult.getExpireTime());
        }
        // 離線載具發送OTP
        else {

            // 發送推播OTP認證
            SendOfflineOtpCondition sendOfflineOtpCondition = new SendOfflineOtpCondition();
            sendOfflineOtpCondition.setMotpTransData(createMotpTransDataResult.getMotpTransData());
            sendOfflineOtpCondition.setCarrierData(createMotpTransDataResult.getCarrierData());
            SendOfflineOtpResult sendOfflineOtpResult = motpServiceHelper.sendOfflineOtp(sendOfflineOtpCondition);

            // 處理發送失敗情境
            if (!sendOfflineOtpResult.isSuccess()) {
                throw new ActionException(AIBankErrorCode.MOTP_SEND_OTP_FAILED);
            }

            // 設置回傳資料
            response.setExpireTime(sendOfflineOtpResult.getExpireTime());
            response.setClientId(preAuthCheckResult.getBindingDeviceInfo().getClientId());
            response.setChallenge(createMotpTransDataResult.getMotpTransData().getChallenge());
        }

        // 設置回傳資料
        response.setDeviceAlias(preAuthCheckResult.getBindingMbDeviceInfo().getDeviceAlias());
        response.setTxCode(createMotpTransDataResult.getMotpTransData().getTxCode());
        response.setCustInfo(createMotpTransDataResult.getCarrierData().getPopup());
        response.setSameDevice(StringUtils.equals(preAuthCheckResult.getBindingDeviceInfo().getDeviceUuid(), request.getDeviceIxd()));
        response.setMotpTransDataKey(createMotpTransDataResult.getMotpTransData().getMotpTransKey());
        response.setMotpVerifyCarrierKey(createMotpTransDataResult.getCarrierData().getMotpVerifyKey());
        response.setAccount(preAuthCheckResult.getBindingDeviceInfo().getAccountId());
        response.setGroup(preAuthCheckResult.getBindingDeviceInfo().getGroup());

        return response;
    }

    /**
     * 驗證推播OTP
     * 
     * @param request
     * @return
     * @throws ActionException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public VerifyPushOtpResponse verifyPushOtp(VerifyPushOtpRequest request) throws ActionException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        VerifyPushOtpResponse response = new VerifyPushOtpResponse();

        // 取得交易認證資訊
        FindMotpTransDataCondition findMotpTransDataCondition = new FindMotpTransDataCondition();
        findMotpTransDataCondition.setMotpTransDataKey(request.getMotpTransDataKey());
        findMotpTransDataCondition.setMotpVerifyCarrierKey(request.getMotpVerifyCarrierKey());
        FindMotpTransDataResult findMotpTransDataResult = motpServiceHelper.findMotpTransData(findMotpTransDataCondition);

        // 驗證推播OTP
        VerifyPushOtpCondition verifyPushOtpCondition = new VerifyPushOtpCondition();
        verifyPushOtpCondition.setMotpTransData(findMotpTransDataResult.getMotpTransData());
        verifyPushOtpCondition.setCarrierData(findMotpTransDataResult.getCarrierData());
        verifyPushOtpCondition.setUserInputOtp(request.getUserInputOtp());
        verifyPushOtpCondition.setAccount(request.getAccount());
        verifyPushOtpCondition.setGroup(request.getGroup());
        VerifyPushOtpResult verifyPushOtpResult = motpServiceHelper.verifyPushOtp(verifyPushOtpCondition);

        // 設置回傳資料
        response.setMotpVerifyResultType(verifyPushOtpResult.getVerifyResult().getCode());
        response.setCode(verifyPushOtpResult.getCode());
        response.setMessage(verifyPushOtpResult.getMessage());

        return response;
    }

    /**
     * 取得使用者資料
     * 
     * @param custId
     * @param userId
     * @param companyKind
     * @param uidDup
     * @return
     * @throws ActionException
     */
    private IdentityData getUser(String custId, String userId, int companyKind, String uidDup) throws ActionException {
        try {
            IdentityData identityData = identityService.query(custId, uidDup, userId, companyKind);
            if (identityData != null && identityData.isAliveUser()) {
                return identityData;
            }
        }
        catch (CryptException e) {
            logger.error(e.getMessage(), e);
        }
        throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
    }

}