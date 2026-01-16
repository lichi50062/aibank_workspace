package com.tfb.aibank.chl.creditcard.resource.dto;

// @formatter:off
/**
 * @(#)ExecuteChangeCcUserPinRequest.java
 * 
 * <p>Description:信用卡變更密碼</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/03, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ExecuteChangeCcUserPinRequest {

    /** 身份證字號 */
    private String uid;

    /** 使用者代號 */
    private String uuid;

    /** 使用者密碼 */
    private String secrxt;

    /** 新使用者密碼 */
    private String newSecrxt;

    /** 裝置代號 */
    private String deviceId;

    /** 客戶端IP */
    private String clientIP;

    /** 平台 */
    private String platform;

    /** 目前SessionID */
    private String currentSessionId;

    private String serverId;

    private String nameCode;

    /**
     * Gets name code.
     *
     * @return the name code
     */
    public String getNameCode() {
        return nameCode;
    }

    /**
     * Sets name code.
     *
     * @param nameCode
     *            the name code
     */
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    /**
     * @return the newSecrxt
     */
    public String getNewSecrxt() {
        return newSecrxt;
    }

    /**
     * @param newSecrxt
     *            the newSecrxt to set
     */
    public void setNewSecrxt(String newSecrxt) {
        this.newSecrxt = newSecrxt;
    }

    /**
     * @return the serverId
     */
    public String getServerId() {
        return serverId;
    }

    /**
     * @param serverId
     *            the serverId to set
     */
    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    /**
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     *            the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     *            the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the paswod
     */
    public String getSecrxt() {
        return secrxt;
    }

    /**
     * @param paswod
     *            the paswod to set
     */
    public void setSecrxt(String secrxt) {
        this.secrxt = secrxt;
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
     * @return the clientIP
     */
    public String getClientIP() {
        return clientIP;
    }

    /**
     * @param clientIP
     *            the clientIP to set
     */
    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @param platform
     *            the platform to set
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * @return the currentSessionId
     */
    public String getCurrentSessionId() {
        return currentSessionId;
    }

    /**
     * @param currentSessionId
     *            the currentSessionId to set
     */
    public void setCurrentSessionId(String currentSessionId) {
        this.currentSessionId = currentSessionId;
    }

}
