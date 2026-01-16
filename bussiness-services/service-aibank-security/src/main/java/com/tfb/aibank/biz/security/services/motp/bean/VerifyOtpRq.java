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
 * @(#)VerifyOtpRq.java
 * 
 * <p>Description:全景MOTP - API介接服務 - VerifyOtp RQ</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/10, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class VerifyOtpRq extends MotpRq {

    /** 使用者名稱 */
    private String account;

    /** 群組名稱 */
    private String group;

    /** 挑戰碼內容 */
    private String challenge;

    /** 挑戰碼模式 */
    private String CRmode;

    /** 一次性密碼 */
    private String otp;

    /** log備註 */
    private String logMsg;

    /** 保留欄位 */
    private String flag;

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

    /**
     * @return the challenge
     */
    public String getChallenge() {
        return challenge;
    }

    /**
     * @param challenge
     *            the challenge to set
     */
    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    /**
     * @return the cRmode
     */
    public String getCRmode() {
        return CRmode;
    }

    /**
     * @param cRmode
     *            the cRmode to set
     */
    public void setCRmode(String cRmode) {
        CRmode = cRmode;
    }

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

    /**
     * @return the logMsg
     */
    public String getLogMsg() {
        return logMsg;
    }

    /**
     * @param logMsg
     *            the logMsg to set
     */
    public void setLogMsg(String logMsg) {
        this.logMsg = logMsg;
    }

    /**
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag
     *            the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

}
