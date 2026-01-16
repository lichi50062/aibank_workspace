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

// @formatter:off
/**
 * @(#)MotpAuthVerifyData.java
 * 
 * <p>Description:MOTP驗證服務 - 前端上送驗證資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/14, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class MotpAuthVerifyData implements Serializable {

    private static final long serialVersionUID = 8717437186807328993L;

    /** 使用者輸入OTP */
    private String userOtp;

    /**
     * @return the userOtp
     */
    public String getUserOtp() {
        return userOtp;
    }

    /**
     * @param userOtp
     *            the userOtp to set
     */
    public void setUserOtp(String userOtp) {
        this.userOtp = userOtp;
    }

}
