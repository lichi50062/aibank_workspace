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
package com.tfb.aibank.biz.security.services.motp.helper.model;

import com.tfb.aibank.common.type.MotpVerifyResultType;

// @formatter:off
/**
 * @(#)VerifyPushOtpResult.java
 * 
 * <p>Description:驗證推播OTP</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/16, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class VerifyPushOtpResult {

    /** MOTP交易驗證結果 */
    private MotpVerifyResultType verifyResult;

    /** MOTP API回覆代號 */
    private String code;

    /** MOTP API回覆訊息 */
    private String message;

    /**
     * @return the verifyResult
     */
    public MotpVerifyResultType getVerifyResult() {
        return verifyResult;
    }

    /**
     * @param verifyResult
     *            the verifyResult to set
     */
    public void setVerifyResult(MotpVerifyResultType verifyResult) {
        this.verifyResult = verifyResult;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
