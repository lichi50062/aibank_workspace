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
package com.tfb.aibank.biz.security.services.motp.model;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)CreatePushOtpRequest.java
 * 
 * <p>Description:發送推播OTP - Request</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/14, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "發送推播OTP - Request")
public class CreatePushOtpRequest {

    /** 身分證字號 */
    @Schema(description = "身分證字號")
    private String custId;

    /** 使用者代號 */
    @Schema(description = "使用者代號")
    private String userId;

    /** 公司類型 */
    @Schema(description = "公司類型")
    private int companyKind;

    /** 誤別碼 */
    @Schema(description = "誤別碼")
    private String uidDup;

    /** 裝置ID */
    @Schema(description = "裝置ID")
    private String deviceIxd;

    /** 交易代號 */
    @Schema(description = "交易代號")
    private String taskIxd;

    /** 推播訊息標題 */
    @Schema(description = "推播訊息標題")
    private String title;

    /** 推播訊息內容 */
    @Schema(description = "推播訊息內容")
    private String message;

    /** 彈跳視窗內容 */
    @Schema(description = "彈跳視窗內容")
    private String custInfo;

    /** 交易參數因子 */
    @Schema(description = "交易參數因子")
    private String txFactor;

    /** 交易因子類型 */
    @Schema(description = "交易因子類型")
    private String txSeedType;

    /** CLIENT IP */
    @Schema(description = "CLIENT IP")
    private String clientIp;

    /**
     * @return the custId
     */
    public String getCustId() {
        return custId;
    }

    /**
     * @param custId
     *            the custId to set
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the companyKind
     */
    public int getCompanyKind() {
        return companyKind;
    }

    /**
     * @param companyKind
     *            the companyKind to set
     */
    public void setCompanyKind(int companyKind) {
        this.companyKind = companyKind;
    }

    /**
     * @return the uidDup
     */
    public String getUidDup() {
        return uidDup;
    }

    /**
     * @param uidDup
     *            the uidDup to set
     */
    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }

    /**
     * @return the deviceIxd
     */
    public String getDeviceIxd() {
        return deviceIxd;
    }

    /**
     * @param deviceIxd
     *            the deviceIxd to set
     */
    public void setDeviceIxd(String deviceIxd) {
        this.deviceIxd = deviceIxd;
    }

    /**
     * @return the taskIxd
     */
    public String getTaskIxd() {
        return taskIxd;
    }

    /**
     * @param taskIxd
     *            the taskIxd to set
     */
    public void setTaskIxd(String taskIxd) {
        this.taskIxd = taskIxd;
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
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
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
     * @return the txFactor
     */
    public String getTxFactor() {
        return txFactor;
    }

    /**
     * @param txFactor
     *            the txFactor to set
     */
    public void setTxFactor(String txFactor) {
        this.txFactor = txFactor;
    }

    /**
     * @return the txSeedType
     */
    public String getTxSeedType() {
        return txSeedType;
    }

    /**
     * @param txSeedType
     *            the txSeedType to set
     */
    public void setTxSeedType(String txSeedType) {
        this.txSeedType = txSeedType;
    }

    /**
     * @return the clientIp
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * @param clientIp
     *            the clientIp to set
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

}
