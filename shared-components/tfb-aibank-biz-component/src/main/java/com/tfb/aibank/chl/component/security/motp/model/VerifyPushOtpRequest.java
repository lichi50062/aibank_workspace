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
package com.tfb.aibank.chl.component.security.motp.model;

// @formatter:off
/**
 * @(#)VerifyPushOtpRequest.java
 * 
 * <p>Description:驗證推播OTP - Request</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/16, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class VerifyPushOtpRequest {

    /** 使用者輸入OTP */
    private String userInputOtp;

    /** 交易認證資訊鍵值 */
    private Integer motpTransDataKey;

    /** 交易驗證載具資訊鍵值 */
    private Integer motpVerifyCarrierKey;

    /** 使用者名稱 */
    private String account;

    /** 群組名稱 */
    private String group;

    /**
     * @return the userInputOtp
     */
    public String getUserInputOtp() {
        return userInputOtp;
    }

    /**
     * @param userInputOtp
     *            the userInputOtp to set
     */
    public void setUserInputOtp(String userInputOtp) {
        this.userInputOtp = userInputOtp;
    }

    /**
     * @return the motpTransDataKey
     */
    public Integer getMotpTransDataKey() {
        return motpTransDataKey;
    }

    /**
     * @param motpTransDataKey
     *            the motpTransDataKey to set
     */
    public void setMotpTransDataKey(Integer motpTransDataKey) {
        this.motpTransDataKey = motpTransDataKey;
    }

    /**
     * @return the motpVerifyCarrierKey
     */
    public Integer getMotpVerifyCarrierKey() {
        return motpVerifyCarrierKey;
    }

    /**
     * @param motpVerifyCarrierKey
     *            the motpVerifyCarrierKey to set
     */
    public void setMotpVerifyCarrierKey(Integer motpVerifyCarrierKey) {
        this.motpVerifyCarrierKey = motpVerifyCarrierKey;
    }

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     *            the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * @param group
     *            the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }

}
