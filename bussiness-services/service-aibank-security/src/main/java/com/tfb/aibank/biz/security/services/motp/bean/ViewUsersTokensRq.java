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
 * @(#)ViewUsersTokensRq.java
 * 
 * <p>Description:全景MOTP - API介接服務 - ViewUsersTokens RQ</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/10, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ViewUsersTokensRq extends MotpRq {

    /** OTP API帳號 */
    private String opAcct;

    /** OTP API密碼 */
    private String opPwd;

    /** 使用者名稱 */
    private String account;

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
