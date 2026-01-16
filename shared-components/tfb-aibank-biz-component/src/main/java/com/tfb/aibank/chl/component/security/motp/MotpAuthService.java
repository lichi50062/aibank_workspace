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
package com.tfb.aibank.chl.component.security.motp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfb.aibank.chl.component.security.motp.bean.MotpAuthInitData;
import com.tfb.aibank.chl.component.security.motp.bean.MotpAuthKeepData;
import com.tfb.aibank.chl.component.security.motp.bean.MotpAuthVerifyData;
import com.tfb.aibank.chl.component.security.motp.model.CreatePushOtpRequest;
import com.tfb.aibank.chl.component.security.motp.model.CreatePushOtpResponse;
import com.tfb.aibank.chl.component.security.motp.model.VerifyPushOtpRequest;
import com.tfb.aibank.chl.component.security.motp.model.VerifyPushOtpResponse;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.component.motplog.MotpLogActionType;

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
 * 
 * @see TFB-SA-共通業務辦法.docx - 2.2.4.行動動態密碼驗證(MOTP驗證)
 */
// @formatter:on
@Service
public class MotpAuthService {

    @Autowired
    private MotpSecurityResource motpSecurityResource;

    @Autowired
    private MotpLogHelper motpLogHelper;

    /**
     * 發送推播OTP
     * 
     * @param user
     *            使用者
     * @param initData
     *            驗證初始資料
     * @param keepData
     *            驗證流程暫存資料
     * @return
     */
    public void sendMOTP(AIBankUser user, MotpAuthInitData initData, MotpAuthKeepData keepData) {

        // 發送推播OTP
        CreatePushOtpRequest createPushOtpRequest = new CreatePushOtpRequest();
        createPushOtpRequest.setCustId(user.getCustId());
        createPushOtpRequest.setUserId(user.getUserId());
        createPushOtpRequest.setCompanyKind(user.getCompanyKind());
        createPushOtpRequest.setUidDup(user.getUidDup());
        createPushOtpRequest.setDeviceIxd(initData.getDeviceIxd());
        createPushOtpRequest.setTaskIxd(initData.getTaskIxd());
        createPushOtpRequest.setTitle(initData.getTitle());
        createPushOtpRequest.setMessage(initData.getMessage());
        createPushOtpRequest.setCustInfo(initData.getCustInfo());
        createPushOtpRequest.setTxFactor(initData.getTxFactor());
        createPushOtpRequest.setTxSeedType(initData.getTxSeedType());
        createPushOtpRequest.setClientIp(initData.getClientIp());
        CreatePushOtpResponse createPushOtpResponse = motpSecurityResource.createPushOtp(createPushOtpRequest);

        motpLogHelper.saveLog(MotpLogActionType.GENERATE_MOTP, user, "MotpAuthService", "sendMOTP", "SUCCESS", null, null);
        // 設置暫存
        keepData.setInitData(initData);
        keepData.setCreatePushOtpResponse(createPushOtpResponse);
    }

    /**
     * 驗證推播OTP
     * 
     * @param user
     *            使用者
     * @param verifyData
     *            前端上送驗證資料
     * @param keepData
     *            驗證流程暫存資料
     * 
     */
    public void validateMOTP(AIBankUser user, MotpAuthVerifyData verifyData, MotpAuthKeepData keepData) {

        // 驗證推播OTP
        VerifyPushOtpRequest verifyPushOtpRequest = new VerifyPushOtpRequest();
        verifyPushOtpRequest.setUserInputOtp(verifyData.getUserOtp());
        verifyPushOtpRequest.setMotpTransDataKey(keepData.getCreatePushOtpResponse().getMotpTransDataKey());
        verifyPushOtpRequest.setMotpVerifyCarrierKey(keepData.getCreatePushOtpResponse().getMotpVerifyCarrierKey());
        verifyPushOtpRequest.setAccount(keepData.getCreatePushOtpResponse().getAccount());
        verifyPushOtpRequest.setGroup(keepData.getCreatePushOtpResponse().getGroup());
        VerifyPushOtpResponse verifyPushOtpResponse = motpSecurityResource.verifyPushOtp(verifyPushOtpRequest);

        motpLogHelper.saveLog(MotpLogActionType.VALIDATE_MOTP, user, "MotpAuthService", "sendMOTP", "SUCCESS", null, null);

        // 設置暫存
        keepData.setVerifyData(verifyData);
        keepData.setVerifyPushOtpResponse(verifyPushOtpResponse);
    }

}
