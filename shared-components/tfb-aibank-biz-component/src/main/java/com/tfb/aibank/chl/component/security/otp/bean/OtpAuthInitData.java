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
import java.util.Date;

// @formatter:off
/**
 * @(#)OtpAuthInitData.java
 * 
 * <p>Description:OTP驗證服務 - 驗證初始資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class OtpAuthInitData implements Serializable {

    private static final long serialVersionUID = 8818163426083693255L;

    /** 是否已經由交易電文發送OTP */
    private boolean txnSendFlag = false;
    /** 交易電文發送OTP交易代碼 */
    private String txCode;
    /** 交易電文發送OTP有效時間 */
    private Date expireTime;

    /** 交易代號 */
    private String taskId;

    /** 交易名稱 */
    private String taskName;

    /** OTP使用手機號碼 */
    private String otpMobile;

    /** OTP訊息 */
    private String sendMessage;

    /** 交易因子 */
    private String txFactors;

    /** 語系 */
    private String locale;

    /** 是否做寄送otp */
    private Boolean isSendOtp = true;

    /**
     * @return the txnSendFlag
     */
    public boolean isTxnSendFlag() {
        return txnSendFlag;
    }

    /**
     * @param txnSendFlag
     *            the txnSendFlag to set
     */
    public void setTxnSendFlag(boolean txnSendFlag) {
        this.txnSendFlag = txnSendFlag;
    }

    /**
     * @return the txCode
     */
    public String getTxCode() {
        return txCode;
    }

    /**
     * @param txCode
     *            the txCode to set
     */
    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }

    /**
     * @return the expireTime
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * @param expireTime
     *            the expireTime to set
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * @param taskId
     *            the taskId to set
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the taskName
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * @param taskName
     *            the taskName to set
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * @return the otpMobile
     */
    public String getOtpMobile() {
        return otpMobile;
    }

    /**
     * @param otpMobile
     *            the otpMobile to set
     */
    public void setOtpMobile(String otpMobile) {
        this.otpMobile = otpMobile;
    }

    /**
     * @return the sendMessage
     */
    public String getSendMessage() {
        return sendMessage;
    }

    /**
     * @param sendMessage
     *            the sendMessage to set
     */
    public void setSendMessage(String sendMessage) {
        this.sendMessage = sendMessage;
    }

    /**
     * @return the txFactors
     */
    public String getTxFactors() {
        return txFactors;
    }

    /**
     * @param txFactors
     *            the txFactors to set
     */
    public void setTxFactors(String txFactors) {
        this.txFactors = txFactors;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @param locale
     *            the locale to set
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * @return the isSendOtp
     */
    public Boolean getIsSendOtp() {
        return isSendOtp;
    }

    /**
     * @param isSendOtp
     *            the isSendOtp to set
     */
    public void setIsSendOtp(Boolean isSendOtp) {
        this.isSendOtp = isSendOtp;
    }

}
