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

import java.util.Locale;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)Notify.java
 * 
 * <p>Description:通知資料共用欄位</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "通知作業資料共用欄位")
public abstract class Notify {

    /** 公司鍵值 */
    @Schema(description = "公司鍵值")
    protected Integer companyKey;

    /** 使用者鍵值 */
    @Schema(description = "使用者鍵值")
    protected Integer userKey;

    /** 使用者代號 */
    @Schema(description = "使用者代號")
    protected String userId;

    /** 交易代號 */
    @Schema(description = "交易代號")
    protected String txId;

    /** 主檔鍵值 */
    @Schema(description = "主檔鍵值")
    protected Integer masterKey;

    /** 明細鍵值 */
    @Schema(description = "明細鍵值")
    protected Integer detailKey;

    /** 通知類別 */
    @Schema(description = "通知類別")
    protected Integer notifyType;

    /** 是否成功 0:未送, 1:成功, 2:失敗 */
    @Schema(description = "是否成功 0:未送, 1:成功, 2:失敗")
    protected Integer success = 0;

    /** email 交易類型 */
    @Schema(description = "交易類型")
    protected String txType;

    /** 交易來源 1：網銀；2：行銀 3：AIBank */
    @Schema(description = "交易來源 1：網銀；2：行銀；3：AIBank")
    protected String txSource;

    /** 紀錄 NOTIFICATION primary key */
    @Schema(description = "紀錄 NOTIFICATION primary key")
    protected Integer notificationKey;

    /** 語系 */
    @Schema(description = "語系")
    protected String locale = Locale.TAIWAN.toString();

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public Integer getMasterKey() {
        return masterKey;
    }

    public void setMasterKey(Integer masterKey) {
        this.masterKey = masterKey;
    }

    public Integer getDetailKey() {
        return detailKey;
    }

    public void setDetailKey(Integer detailKey) {
        this.detailKey = detailKey;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getTxSource() {
        return txSource;
    }

    public void setTxSource(String txSource) {
        this.txSource = txSource;
    }

    public String getTxType() {
        return txType;
    }

    public void setTxType(String txType) {
        this.txType = txType;
    }

    public Integer getNotificationKey() {
        return notificationKey;
    }

    public void setNotificationKey(Integer notificationKey) {
        this.notificationKey = notificationKey;
    }

    public Integer getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(Integer notifyType) {
        this.notifyType = notifyType;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

}
