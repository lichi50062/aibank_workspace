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

import java.util.List;

// @formatter:off
/**
 * @(#)CreateOtpUserRq.java
 * 
 * <p>Description:全景MOTP - API介接服務 - CreateOTPUser RQ</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/10, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CreateOtpUserRq extends MotpRq {

    /** OTP API帳號 */
    private String opAcct;

    /** OTP API密碼 */
    private String opPwd;

    /** 使用者名稱 */
    private String account;

    /** 裝置型態 */
    private String deviceType;

    /** 群組名稱 */
    private List<String> group;

    /** 帳號描述 */
    private String desc;

    /** log備註 */
    private String logMsg;

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
     * @return the deviceType
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * @param deviceType
     *            the deviceType to set
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * @return the group
     */
    public List<String> getGroup() {
        return group;
    }

    /**
     * @param group
     *            the group to set
     */
    public void setGroup(List<String> group) {
        this.group = group;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc
     *            the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
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

}
