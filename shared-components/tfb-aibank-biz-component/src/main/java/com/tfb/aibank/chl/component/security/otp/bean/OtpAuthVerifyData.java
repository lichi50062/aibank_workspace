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
package com.tfb.aibank.chl.component.security.otp.bean;

import java.io.Serializable;

// @formatter:off
/**
 * @(#)OtpAuthVerifyData.java
 * 
 * <p>Description:OTP驗證服務 - 前端上送驗證資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class OtpAuthVerifyData implements Serializable {

    private static final long serialVersionUID = 8818163426083693255L;

    /** 前端回傳的交易代碼 */
    private String userEncTxCode;

    /** 前端回傳的交易生成碼雜湊值 */
    private String userTxHash;

    /** 前端上送OTP值 */
    private String userOtp;

    /**
     * @return the userEncTxCode
     */
    public String getUserEncTxCode() {
        return userEncTxCode;
    }

    /**
     * @param userEncTxCode
     *            the userEncTxCode to set
     */
    public void setUserEncTxCode(String userEncTxCode) {
        this.userEncTxCode = userEncTxCode;
    }

    /**
     * @return the userTxHash
     */
    public String getUserTxHash() {
        return userTxHash;
    }

    /**
     * @param userTxHash
     *            the userTxHash to set
     */
    public void setUserTxHash(String userTxHash) {
        this.userTxHash = userTxHash;
    }

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
