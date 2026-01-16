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
package com.tfb.aibank.chl.component.notification.model;

// @formatter:off
/**
 * @(#)CreateOtpRecordResponse.java
 * 
 * <p>Description:建立OTP發送紀錄 - Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CreateOtpRecordResponse {

    /**
     * OTP log key
     */
    private Integer otpKey;

    /**
     * @return the otpKey
     */
    public Integer getOtpKey() {
        return otpKey;
    }

    /**
     * @param otpKey
     *            the otpKey to set
     */
    public void setOtpKey(Integer otpKey) {
        this.otpKey = otpKey;
    }

}
