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
package com.tfb.aibank.chl.component.notification.model;

import java.util.Date;

// @formatter:off
/**
 * @(#)NotificationRequest.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/10, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ResendNotificationRequest {
    /** 通知記錄鍵值 */
    private Integer notificationKey;

    /** 公司鍵值' */
    private Integer companyKey;

    /** 使用者鍵值 */
    private Integer userKey;

    /** 交易代號 */
    private String txId;

    /** 交易主檔鍵值 */
    private Integer masterKey;

    /** 交易明細檔鍵值 */
    private Integer detailKey;

    /** 通知類別 */
    private Integer notifyType;

    /** 通知資料 */
    private String notifyData;

    /** 通知建立時間 */
    private Date createTime;

    /** 通知執行時間 */
    private Date notifyTime;

    /** 是否已執行通知，0:尚未執行；1:執行成功；2:執行失敗 */
    private Integer notified;

    /** 電子郵件主旨 */
    private String emailSubject;

    /** Email (AES加密) */
    private String notifyEmail;

    /** 手機號碼 (AES加密) */
    private String notifyPhone;

    /** 是否為國內手機，0:國外手機；1:國內手機 */
    private String phoneFlag;

    /** 使用者代碼 */
    private String userId;

    /** 交易類型 */
    private String txType;

    /** 交易來源，1:網銀；2:行銀；3:AI Bank */
    private String txSource;

    public Integer getNotificationKey() {
        return notificationKey;
    }

    public void setNotificationKey(Integer notificationKey) {
        this.notificationKey = notificationKey;
    }

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDetailKey() {
        return detailKey;
    }

    public void setDetailKey(Integer detailKey) {
        this.detailKey = detailKey;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public Integer getMasterKey() {
        return masterKey;
    }

    public void setMasterKey(Integer masterKey) {
        this.masterKey = masterKey;
    }

    public Integer getNotified() {
        return notified;
    }

    public void setNotified(Integer notified) {
        this.notified = notified;
    }

    public String getNotifyData() {
        return notifyData;
    }

    public void setNotifyData(String notifyData) {
        this.notifyData = notifyData;
    }

    public String getNotifyEmail() {
        return notifyEmail;
    }

    public void setNotifyEmail(String notifyEmail) {
        this.notifyEmail = notifyEmail;
    }

    public String getNotifyPhone() {
        return notifyPhone;
    }

    public void setNotifyPhone(String notifyPhone) {
        this.notifyPhone = notifyPhone;
    }

    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    public Integer getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(Integer notifyType) {
        this.notifyType = notifyType;
    }

    public String getPhoneFlag() {
        return phoneFlag;
    }

    public void setPhoneFlag(String phoneFlag) {
        this.phoneFlag = phoneFlag;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public String getTxType() {
        return txType;
    }

    public void setTxType(String txType) {
        this.txType = txType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public String getTxSource() {
        return txSource;
    }

    public void setTxSource(String txSource) {
        this.txSource = txSource;
    }

}
