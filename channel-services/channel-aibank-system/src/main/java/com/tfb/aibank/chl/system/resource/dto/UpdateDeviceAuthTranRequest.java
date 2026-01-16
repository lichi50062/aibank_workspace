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
package com.tfb.aibank.chl.system.resource.dto;

// @formatter:off
/**
 * @(#)UpdateDeviceAuthTranRequest.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/30, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class UpdateDeviceAuthTranRequest {

    /** 裝置授權交易鍵值 */
    private String txKey;

    /** 裝置代號 */
    String deviceId;

    /** 授權狀態 */
    String status;

    /**
     * @return the txKey
     */
    public String getTxKey() {
        return txKey;
    }

    /**
     * @param txKey
     *            the txKey to set
     */
    public void setTxKey(String txKey) {
        this.txKey = txKey;
    }

    /**
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId
     *            the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
