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
package com.tfb.aibank.biz.component.twcaapi.model;

// @formatter:off
/**
 * @(#)CertInfo.java
 * 
 * <p>Description:憑證資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/07, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TwcaApiCertInfo {

    /** 憑證 CN. */
    private String cn;

    /** 憑證序號. */
    private String serial;

    /** 憑證生效日期. */
    private String notBefore;

    /** 憑證截止日期. */
    private String notAfter;

    /** 憑證申請時間. */
    private String applyDate;

    /** 憑證狀態. */
    private String state;

    /** 憑證狀態訊息. */
    private String stateMessage;

    /**
     * @return {@link #cn}
     */
    public String getCn() {
        return cn;
    }

    /**
     * @param cn
     *            {@link #cn}
     */
    public void setCn(String cn) {
        this.cn = cn;
    }

    /**
     * @return {@link #serial}
     */
    public String getSerial() {
        return serial;
    }

    /**
     * @param serial
     *            {@link #serial}
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

    /**
     * @return {@link #notBefore}
     */
    public String getNotBefore() {
        return notBefore;
    }

    /**
     * @param notBefore
     *            {@link #notBefore}
     */
    public void setNotBefore(String notBefore) {
        this.notBefore = notBefore;
    }

    /**
     * @return {@link #notAfter}
     */
    public String getNotAfter() {
        return notAfter;
    }

    /**
     * @param notAfter
     *            {@link #notAfter}
     */
    public void setNotAfter(String notAfter) {
        this.notAfter = notAfter;
    }

    /**
     * @return {@link #applyDate}
     */
    public String getApplyDate() {
        return applyDate;
    }

    /**
     * @param applyDate
     *            {@link #applyDate}
     */
    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    /**
     * @return {@link #state}
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     *            {@link #state}
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return {@link #stateMessage}
     */
    public String getStateMessage() {
        return stateMessage;
    }

    /**
     * @param stateMessage
     *            {@link #stateMessage}
     */
    public void setStateMessage(String stateMessage) {
        this.stateMessage = stateMessage;
    }

}
