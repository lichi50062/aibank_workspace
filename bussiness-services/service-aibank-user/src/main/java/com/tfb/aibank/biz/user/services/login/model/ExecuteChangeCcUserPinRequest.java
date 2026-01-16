package com.tfb.aibank.biz.user.services.login.model;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)ExecuteUserLoginRequest.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ExecuteChangeCcUserPinRequest {

    @Schema(description = "身份證字號")
    String uid;

    @Schema(description = "使用者代號")
    String uuid;

    @Schema(description = "使用者密碼")
    String secrxt;

    @Schema(description = "新密碼")
    String newSecrxt;

    @Schema(description = "裝置代號")
    String deviceId;

    @Schema(description = "客戶端IP")
    String clientIP;

    @Schema(description = "平台")
    String platform;

    @Schema(description = "裝置資訊")
    DeviceInfo deviceInfo;

    @Schema(description = "目前SessionID")
    String currentSessionId;

    @Schema(description = "serverId")
    String serverId;

    @Schema(description = "使用者代碼")
    String nameCode;

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
     * @return the uuixd
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuixd
     *            the uuixd to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the secrxt
     */
    public String getSecrxt() {
        return secrxt;
    }

    /**
     * @param secrxt
     *            the secrxt to set
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
     * @return the deviceInfo
     */
    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    /**
     * @param deviceInfo
     *            the deviceInfo to set
     */
    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
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

}
