/**
 * 
 */
package com.tfb.aibank.chl.general.resource.dto;

//@formatter:off
/**
* @(#)AddMbDeviceInfoRequest.java
* 
* <p>Description:新增 MB_DEVICE_INFO Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/16, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class AddMbDeviceInfoRequest {

    /** 使用者ID */
    private String custId;

    /** 使用者代號 */
    private String userId;

    /** 公司類型 */
    private Integer companyKind;

    /** [誤別碼] 公司統編或身份證字號誤別碼 */
    private String uidDup;

    /**
     * 驗證編號
     */
    private String verifyNo;

    /**
     * 通行證
     */
    private String token;

    /**
     * 進行註冊
     */
    private boolean isRegisterDevice;

    /**
     * 裝置編號
     */
    private String deviceId;

    /**
     * 裝置資訊
     */
    private String model;

    /**
     * 裝置作業系統
     */
    private String devicePlatform;

    /**
     * 裝置作業系統版本
     */
    private String devicePlatformVersion;

    /**
     * 裝置暱稱
     */
    private String deviceAlias;

    /**
     * 客戶端IP
     */
    private String ip;

    /**
     * 會員型態 0:一般會員 1:信用卡會員
     */
    private int loginType;

    /**
     * 快速登入密碼類型
     */
    private int loginPasswordType;

    /**
     * 推播TOKEN
     */
    private String pushToken;

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
    public Integer getCompanyKind() {
        return companyKind;
    }

    /**
     * @param companyKind
     *            the companyKind to set
     */
    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

    /**
     * @return the verifyNo
     */
    public String getVerifyNo() {
        return verifyNo;
    }

    /**
     * @param verifyNo
     *            the verifyNo to set
     */
    public void setVerifyNo(String verifyNo) {
        this.verifyNo = verifyNo;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     *            the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the isRegisterDevice
     */
    public boolean isRegisterDevice() {
        return isRegisterDevice;
    }

    /**
     * @param isRegisterDevice
     *            the isRegisterDevice to set
     */
    public void setRegisterDevice(boolean isRegisterDevice) {
        this.isRegisterDevice = isRegisterDevice;
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
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model
     *            the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the devicePlatform
     */
    public String getDevicePlatform() {
        return devicePlatform;
    }

    /**
     * @param devicePlatform
     *            the devicePlatform to set
     */
    public void setDevicePlatform(String devicePlatform) {
        this.devicePlatform = devicePlatform;
    }

    /**
     * @return the devicePlatformVersion
     */
    public String getDevicePlatformVersion() {
        return devicePlatformVersion;
    }

    /**
     * @param devicePlatformVersion
     *            the devicePlatformVersion to set
     */
    public void setDevicePlatformVersion(String devicePlatformVersion) {
        this.devicePlatformVersion = devicePlatformVersion;
    }

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
     * @return the loginType
     */
    public int getLoginType() {
        return loginType;
    }

    /**
     * @param loginType
     *            the loginType to set
     */
    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    /**
     * @return the loginPasswordType
     */
    public int getLoginPasswordType() {
        return loginPasswordType;
    }

    /**
     * @param loginPasswordType
     *            the loginPasswordType to set
     */
    public void setLoginPasswordType(int loginPasswordType) {
        this.loginPasswordType = loginPasswordType;
    }

    /**
     * @return the pushToken
     */
    public String getPushToken() {
        return pushToken;
    }

    /**
     * @param pushToken
     *            the pushToken to set
     */
    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
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

}