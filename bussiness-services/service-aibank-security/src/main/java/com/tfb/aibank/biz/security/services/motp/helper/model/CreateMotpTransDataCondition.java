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
package com.tfb.aibank.biz.security.services.motp.helper.model;

import com.tfb.aibank.biz.security.services.motp.type.MotpTxCarrierType;

// @formatter:off
/**
 * @(#)CreateMotpTransDataCondition.java
 * 
 * <p>Description:產生MOTP認證資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/15, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CreateMotpTransDataCondition {

    /** MOTP裝置綁定鍵值 */
    private Integer motpDeviceKey;

    /** 公司鍵值 */
    private Integer companyKey;

    /** 使用者鍵值 */
    private Integer userKey;

    /** 裝置ID */
    private String deviceIxd;

    /** 交易代號 */
    private String taskIxd;

    /** 推播訊息標題 */
    private String title;

    /** 推播訊息內容 */
    private String message;

    /** 彈跳視窗內容 */
    private String custInfo;

    /** 交易參數因子 */
    private String txFactor;

    /** 交易因子類型 */
    private String txSeedType;

    /** CLIENT IP */
    private String clientIp;

    /** MOTP使用載具類型 */
    private MotpTxCarrierType motpTxCarrierType;

    /**
     * @return the motpDeviceKey
     */
    public Integer getMotpDeviceKey() {
        return motpDeviceKey;
    }

    /**
     * @param motpDeviceKey
     *            the motpDeviceKey to set
     */
    public void setMotpDeviceKey(Integer motpDeviceKey) {
        this.motpDeviceKey = motpDeviceKey;
    }

    /**
     * @return the companyKey
     */
    public Integer getCompanyKey() {
        return companyKey;
    }

    /**
     * @param companyKey
     *            the companyKey to set
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * @return the userKey
     */
    public Integer getUserKey() {
        return userKey;
    }

    /**
     * @param userKey
     *            the userKey to set
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
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

    /**
     * @return the motpTxCarrierType
     */
    public MotpTxCarrierType getMotpTxCarrierType() {
        return motpTxCarrierType;
    }

    /**
     * @param motpTxCarrierType
     *            the motpTxCarrierType to set
     */
    public void setMotpTxCarrierType(MotpTxCarrierType motpTxCarrierType) {
        this.motpTxCarrierType = motpTxCarrierType;
    }

}
