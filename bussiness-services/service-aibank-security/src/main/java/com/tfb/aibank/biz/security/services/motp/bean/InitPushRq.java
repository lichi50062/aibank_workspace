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
 * @(#)InitPushRq.java
 * 
 * <p>Description:全景MOTP - API介接服務 - InitPush RQ</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/10, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class InitPushRq extends MotpRq {

    /** OTP API帳號 */
    private String opAcct;

    /** OTP API密碼 */
    private String opPwd;

    /** 使用者名稱 */
    private String account;

    /** 裝置類型 */
    private String device;

    /** 保留欄位 */
    private String flag;

    /**
     * @return the opAcct
     */
    public String getOpAcct() {
        return opAcct;
    }

    /**
     * @param opAcct
     *            the opAcct to set
     */
    public void setOpAcct(String opAcct) {
        this.opAcct = opAcct;
    }

    /**
     * @return the opPwd
     */
    public String getOpPwd() {
        return opPwd;
    }

    /**
     * @param opPwd
     *            the opPwd to set
     */
    public void setOpPwd(String opPwd) {
        this.opPwd = opPwd;
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
     * @return the device
     */
    public String getDevice() {
        return device;
    }

    /**
     * @param device
     *            the device to set
     */
    public void setDevice(String device) {
        this.device = device;
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
