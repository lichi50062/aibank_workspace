/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.system.resource.dto;

//@formatter:off
/**
* @(#)AppInfo.java
* 
* <p>Description: AppInfo</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/05/26, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class AppInfo {

    /**
     * 寫入雙重驗證登入記錄檔的Key值
     */
    private String seq;

    /**
     * 平台 WEB：網銀 APP：Fubon+
     */
    private String channel;

    /**
     * 裝置名稱
     */
    private String deviceName;

    /**
     * 裝置鍵值
     */
    private String deviceId;

    /**
     * 瀏覽器類型
     */
    private String browserType;

    /**
     * 作業系統
     */
    private String os;

    /**
     * 登入時間
     */
    private String loginTime;

    /**
     * IP
     */
    private String ip;

    /**
     * 位置
     */
    private String location;

    /**
     * 是否可新增信任裝置 0：表示不可新增 1：表示可新增
     */
    private String isAllow;

    /**
     * 訊息樣板：TwoFactorAuth
     */
    private String templateCode;
    /***
     * 語系
     */
    private String locale;

    /**
     * @return the seq
     */
    public String getSeq() {
        return seq;
    }

    /**
     * @param seq
     *            the seq to set
     */
    public void setSeq(String seq) {
        this.seq = seq;
    }

    /**
     * @return the channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * @param channel
     *            the channel to set
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * @return the deviceName
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * @param deviceName
     *            the deviceName to set
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
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
     * @return the browserType
     */
    public String getBrowserType() {
        return browserType;
    }

    /**
     * @param browserType
     *            the browserType to set
     */
    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }

    /**
     * @return the os
     */
    public String getOs() {
        return os;
    }

    /**
     * @param os
     *            the os to set
     */
    public void setOs(String os) {
        this.os = os;
    }

    /**
     * @return the loginTime
     */
    public String getLoginTime() {
        return loginTime;
    }

    /**
     * @param loginTime
     *            the loginTime to set
     */
    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     *            the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location
     *            the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the isAllow
     */
    public String getIsAllow() {
        return isAllow;
    }

    /**
     * @param isAllow
     *            the isAllow to set
     */
    public void setIsAllow(String isAllow) {
        this.isAllow = isAllow;
    }

    /**
     * @return the templateCode
     */
    public String getTemplateCode() {
        return templateCode;
    }

    /**
     * @param templateCode
     *            the templateCode to set
     */
    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    /**
     * 
     * @return
     */
    public String getLocale() {
        return locale;
    }

    /**
     * 
     * @param locale
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

}
