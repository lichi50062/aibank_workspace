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
package com.tfb.aibank.chl.system.ot009.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NSTOT009000Rs.java
 * 
 * <p>Description:裝置綁定授權查詢</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/11, John	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT009000Rs implements RsData {

    /**
     * 授權狀態
     */
    private boolean isAlreadyAuth;

    /**
     * 需要OTP
     */
    private boolean isNeedOtp;

    /**
     * @return the isAlreadyAuth
     */
    public boolean isAlreadyAuth() {
        return isAlreadyAuth;
    }

    /**
     * @param isAlreadyAuth
     *            the isAlreadyAuth to set
     */
    public void setAlreadyAuth(boolean isAlreadyAuth) {
        this.isAlreadyAuth = isAlreadyAuth;
    }

    /**
     * @return the isNeedOtp
     */
    public boolean isNeedOtp() {
        return isNeedOtp;
    }

    /**
     * @param isNeedOtp
     *            the isNeedOtp to set
     */
    public void setNeedOtp(boolean isNeedOtp) {
        this.isNeedOtp = isNeedOtp;
    }

}
