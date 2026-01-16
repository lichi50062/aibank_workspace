package com.tfb.aibank.biz.component.videoauthapi.model;

// @formatter:off
/**
 * @(#)VideoAuthUserData.java
 * 
 * <p>Description:[數存提高非約轉 userdata]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/18, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class VideoAuthUserData {

    /**
     * 申請來源
     */
    private String channel;

    /**
     * AES加密的身分證字號
     */
    private String uuId;

    /**
     * 裝置uuid App回傳 deviceId Web回傳 提供空字串
     */
    private String deviceId;

    /**
     * 裝置資訊 / 系統資訊 App回傳 裝置資訊 (iPhone13,4) Web回傳 (Windows/MacOS)
     */
    private String device;

    /**
     * App回傳 系統版本號(iOS 15) Web回傳 瀏覽器版本(Chrome版本100.0.4896.75)
     */
    private String os;

    /**
     * App回傳 裝置資訊 可以不用傳(提供空字串) Web回傳 用戶IP位置
     */
    private String clientIp;

    /**
     * App回傳 App版本號 Web回傳 Browser版本號
     */
    private String appVersion;

    /**
     * 系統判斷業務別使用
     */
    private String serviceNo;

    /**
     * 待確認email來源
     */
    private String email;

    /**
     * 使用者代碼
     */
    private String userId;

    /**
     * 數三提升帳戶資訊
     */
    private String acNo;

    /**
     * 註記通知手機號碼
     */
    private String mobile;

    public VideoAuthUserData() {
        super();
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
     * @return the acNo
     */
    public String getAcNo() {
        return acNo;
    }

    /**
     * @param acNo
     *            the acNo to set
     */
    public void setAcNo(String acNo) {
        this.acNo = acNo;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     *            the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * @return the uuId
     */
    public String getUuId() {
        return uuId;
    }

    /**
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @return the device
     */
    public String getDevice() {
        return device;
    }

    /**
     * @return the os
     */
    public String getOs() {
        return os;
    }

    /**
     * @return the clientIp
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * @return the appVersion
     */
    public String getAppVersion() {
        return appVersion;
    }

    /**
     * @return the serviceNo
     */
    public String getServiceNo() {
        return serviceNo;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param channel
     *            the channel to set
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * @param uuId
     *            the uuId to set
     */
    public void setUuId(String uuId) {
        this.uuId = uuId;
    }

    /**
     * @param deviceId
     *            the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @param device
     *            the device to set
     */
    public void setDevice(String device) {
        this.device = device;
    }

    /**
     * @param os
     *            the os to set
     */
    public void setOs(String os) {
        this.os = os;
    }

    /**
     * @param clientIp
     *            the clientIp to set
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * @param appVersion
     *            the appVersion to set
     */
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    /**
     * @param serviceNo
     *            the serviceNo to set
     */
    public void setServiceNo(String serviceNo) {
        this.serviceNo = serviceNo;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
