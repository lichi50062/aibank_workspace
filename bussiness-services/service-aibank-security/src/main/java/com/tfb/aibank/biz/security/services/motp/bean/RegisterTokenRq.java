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
 * @(#)RegisterTokenRq.java
 * 
 * <p>Description:全景MOTP - API介接服務 - RegisterToken RQ</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/10, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class RegisterTokenRq extends MotpRq {

    /** OTP API帳號 */
    private String opAcct;

    /** OTP API密碼 */
    private String opPwd;

    /** 使用者名稱 */
    private String account;

    /** 載具序號 */
    private String token_num;

    /** 機器碼 */
    private String machineCode;

    /** 推播ID */
    private String push_id;

    /** 推播手機類型 */
    private String mobile_type;

    /** log備註 */
    private String logMsg;

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
     * @return the token_num
     */
    public String getToken_num() {
        return token_num;
    }

    /**
     * @param token_num
     *            the token_num to set
     */
    public void setToken_num(String token_num) {
        this.token_num = token_num;
    }

    /**
     * @return the machineCode
     */
    public String getMachineCode() {
        return machineCode;
    }

    /**
     * @param machineCode
     *            the machineCode to set
     */
    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    /**
     * @return the push_id
     */
    public String getPush_id() {
        return push_id;
    }

    /**
     * @param push_id
     *            the push_id to set
     */
    public void setPush_id(String push_id) {
        this.push_id = push_id;
    }

    /**
     * @return the mobile_type
     */
    public String getMobile_type() {
        return mobile_type;
    }

    /**
     * @param mobile_type
     *            the mobile_type to set
     */
    public void setMobile_type(String mobile_type) {
        this.mobile_type = mobile_type;
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
