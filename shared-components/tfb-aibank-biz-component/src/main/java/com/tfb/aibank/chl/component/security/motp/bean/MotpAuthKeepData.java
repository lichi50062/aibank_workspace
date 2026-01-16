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
package com.tfb.aibank.chl.component.security.motp.bean;

import java.io.Serializable;

import com.tfb.aibank.chl.component.security.motp.model.CreatePushOtpResponse;
import com.tfb.aibank.chl.component.security.motp.model.VerifyPushOtpResponse;

// @formatter:off
/**
 * @(#)MotpAuthKeepData.java
 * 
 * <p>Description:MOTP驗證服務 - 驗證流程暫存資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/14, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class MotpAuthKeepData implements Serializable {

    private static final long serialVersionUID = -1864379278754981718L;

    /** 驗證初始資料 */
    private MotpAuthInitData initData;

    /** 發送推播OTP - Response */
    private CreatePushOtpResponse createPushOtpResponse;

    /** 前端上送驗證資料 */
    private MotpAuthVerifyData verifyData;

    /** 驗證推播OTP - Response */
    private VerifyPushOtpResponse verifyPushOtpResponse;

    /**
     * @return the initData
     */
    public MotpAuthInitData getInitData() {
        return initData;
    }

    /**
     * @param initData
     *            the initData to set
     */
    public void setInitData(MotpAuthInitData initData) {
        this.initData = initData;
    }

    /**
     * @return the createPushOtpResponse
     */
    public CreatePushOtpResponse getCreatePushOtpResponse() {
        return createPushOtpResponse;
    }

    /**
     * @param createPushOtpResponse
     *            the createPushOtpResponse to set
     */
    public void setCreatePushOtpResponse(CreatePushOtpResponse createPushOtpResponse) {
        this.createPushOtpResponse = createPushOtpResponse;
    }

    /**
     * @return the verifyData
     */
    public MotpAuthVerifyData getVerifyData() {
        return verifyData;
    }

    /**
     * @param verifyData
     *            the verifyData to set
     */
    public void setVerifyData(MotpAuthVerifyData verifyData) {
        this.verifyData = verifyData;
    }

    /**
     * @return the verifyPushOtpResponse
     */
    public VerifyPushOtpResponse getVerifyPushOtpResponse() {
        return verifyPushOtpResponse;
    }

    /**
     * @param verifyPushOtpResponse
     *            the verifyPushOtpResponse to set
     */
    public void setVerifyPushOtpResponse(VerifyPushOtpResponse verifyPushOtpResponse) {
        this.verifyPushOtpResponse = verifyPushOtpResponse;
    }

}
