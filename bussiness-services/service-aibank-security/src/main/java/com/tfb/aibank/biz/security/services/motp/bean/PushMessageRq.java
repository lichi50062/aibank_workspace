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

import java.util.Map;

// @formatter:off
/**
 * @(#)PushMessageRq.java
 * 
 * <p>Description:全景MOTP - API介接服務 - PushMessage RQ</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/10, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class PushMessageRq extends MotpRq {

    /** OTP API帳號 */
    private String opAcct;

    /** OTP API密碼 */
    private String opPwd;

    /** 使用者名稱 */
    private String account;

    /** 標題 */
    private String title;

    /** 推播模式 */
    private String mode;

    /** 推播資料 */
    private String data;

    /** 挑戰碼 */
    private String challenge;

    /** 推播類型 */
    private String flag;

    /** 群組名稱 */
    private String group;

    /** 推播客製發送內容 */
    private String custInfo;

    /** 客製發送資料 */
    private String custData;

    /** 給予APP接收的訊息 */
    private Map<String, Object> appInfo;

    /**
     * 收件匣訊息
     */
    private String inboxMessage;

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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * @param mode
     *            the mode to set
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(String data) {
        this.data = data;
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
     * @return the custInfo
     */
    public String getCustInfo() {
        return custInfo;
    }

    /**
     * @param custInfo
     *            the custInfo to set
     */
    public void setCustInfo(String custInfo) {
        this.custInfo = custInfo;
    }

    /**
     * @return the custData
     */
    public String getCustData() {
        return custData;
    }

    /**
     * @param custData
     *            the custData to set
     */
    public void setCustData(String custData) {
        this.custData = custData;
    }

    /**
     * @return the appInfo
     */
    public Map<String, Object> getAppInfo() {
        return appInfo;
    }

    /**
     * @param appInfo
     *            the appInfo to set
     */
    public void setAppInfo(Map<String, Object> appInfo) {
        this.appInfo = appInfo;
    }

    /**
     * @return the inboxMessage
     */
    public String getInboxMessage() {
        return inboxMessage;
    }

    /**
     * @param inboxMessage
     *            the inboxMessage to set
     */
    public void setInboxMessage(String inboxMessage) {
        this.inboxMessage = inboxMessage;
    }

}
