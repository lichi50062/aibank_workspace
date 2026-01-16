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
package com.tfb.aibank.chl.component.security.motp.model;

import java.io.Serializable;

// @formatter:off
/**
 * @(#)CreatePushOtpResponse.java
 * 
 * <p>Description:發送推播OTP - Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/14, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CreatePushOtpResponse implements Serializable {

    private static final long serialVersionUID = 8304717234277908445L;

    /** 裝置暱稱 */
    private String deviceAlias;

    /** 交易代碼 */
    private String txCode;

    /** 彈跳視窗內容 */
    private String custInfo;

    /** 與MOTP綁定為相同裝置 */
    private boolean sameDevice;

    /** 手機端用戶ID 對應全景addProfile後取得之ClientID */
    private String clientId;

    /** 挑戰碼 */
    private String challenge;

    /** 有效時間(秒) */
    private int expireTime;

    /** 交易認證資訊鍵值 */
    private Integer motpTransDataKey;

    /** 交易驗證載具資訊鍵值 */
    private Integer motpVerifyCarrierKey;

    /** 使用者名稱 */
    private String account;

    /** 群組名稱 */
    private String group;

    /**
     * @return the deviceAlias
     */
    public String getDeviceAlias() {
        return deviceAlias;
    }

    /**
     * @param deviceAlias
     *            the deviceAlias to set
     */
    public void setDeviceAlias(String deviceAlias) {
        this.deviceAlias = deviceAlias;
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
     * @return the sameDevice
     */
    public boolean isSameDevice() {
        return sameDevice;
    }

    /**
     * @param sameDevice
     *            the sameDevice to set
     */
    public void setSameDevice(boolean sameDevice) {
        this.sameDevice = sameDevice;
    }

    /**
     * @return the clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId
     *            the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
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
     * @return the expireTime
     */
    public int getExpireTime() {
        return expireTime;
    }

    /**
     * @param expireTime
     *            the expireTime to set
     */
    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * @return the motpTransDataKey
     */
    public Integer getMotpTransDataKey() {
        return motpTransDataKey;
    }

    /**
     * @param motpTransDataKey
     *            the motpTransDataKey to set
     */
    public void setMotpTransDataKey(Integer motpTransDataKey) {
        this.motpTransDataKey = motpTransDataKey;
    }

    /**
     * @return the motpVerifyCarrierKey
     */
    public Integer getMotpVerifyCarrierKey() {
        return motpVerifyCarrierKey;
    }

    /**
     * @param motpVerifyCarrierKey
     *            the motpVerifyCarrierKey to set
     */
    public void setMotpVerifyCarrierKey(Integer motpVerifyCarrierKey) {
        this.motpVerifyCarrierKey = motpVerifyCarrierKey;
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

}
