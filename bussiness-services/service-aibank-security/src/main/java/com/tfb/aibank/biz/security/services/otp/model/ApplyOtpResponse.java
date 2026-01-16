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
package com.tfb.aibank.biz.security.services.otp.model;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)ApplyOtpResponse.java
 * 
 * <p>Description:申請OTP - Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/18, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "申請OTP - Response")
public class ApplyOtpResponse {

    /** 是否成功 */
    @Schema(description = "是否成功")
    private boolean isSuccess;

    /**
     * @return the isSuccess
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * @param isSuccess
     *            the isSuccess to set
     */
    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

}
