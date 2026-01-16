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
package com.tfb.aibank.biz.security.services.motp.bean;

// @formatter:off
/**
 * @(#)PushMessageRs.java
 * 
 * <p>Description:全景MOTP - API介接服務 - PushMessage RS</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/10, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class PushMessageDataRs extends MotpRs {

    /** otp */
    private String otp;

    /**
     * @return the otp
     */
    public String getOtp() {
        return otp;
    }

    /**
     * @param otp
     *            the otp to set
     */
    public void setOtp(String otp) {
        this.otp = otp;
    }

}
