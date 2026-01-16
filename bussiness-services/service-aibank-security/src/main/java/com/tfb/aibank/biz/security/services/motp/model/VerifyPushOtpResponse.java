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
package com.tfb.aibank.biz.security.services.motp.model;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)VerifyPushOtpResponse.java
 * 
 * <p>Description:驗證推播OTP - Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/16, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "驗證推播OTP - Response")
public class VerifyPushOtpResponse {

    /** 驗證推播OTP結果類型 */
    @Schema(description = "驗證推播OTP結果類型")
    private String motpVerifyResultType;

    /** MOTP API回覆代號 */
    @Schema(description = "MOTP API回覆代號")
    private String code;

    /** MOTP API回覆訊息 */
    @Schema(description = "MOTP API回覆訊息")
    private String message;

    /**
     * @return the motpVerifyResultType
     */
    public String getMotpVerifyResultType() {
        return motpVerifyResultType;
    }

    /**
     * @param motpVerifyResultType
     *            the motpVerifyResultType to set
     */
    public void setMotpVerifyResultType(String motpVerifyResultType) {
        this.motpVerifyResultType = motpVerifyResultType;
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
