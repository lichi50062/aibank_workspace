package com.tfb.aibank.chl.general.resource.vo.twofactor;

/**
 * // @formatter:off
/**
 * @(#)AppInfoVo.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/16, bensonlin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
public class AppInfoVo {
    

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
    /**
     * 語系
     */
    private String locale;

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getBrowserType() {
        return browserType;
    }

    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIsAllow() {
        return isAllow;
    }

    public void setIsAllow(String isAllow) {
        this.isAllow = isAllow;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
    
    
}
