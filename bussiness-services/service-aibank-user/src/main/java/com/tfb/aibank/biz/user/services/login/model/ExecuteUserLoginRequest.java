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
public class ExecuteUserLoginRequest {

    /** 身份證字號 */
    @Schema(description = "身份證字號")
    private String uid;

    /** 使用者代號 */
    @Schema(description = "使用者代號")
    private String uuid;

    /** 使用者密碼 */
    @Schema(description = "使用者密碼")
    private String secrxt;

    /** 登入身份 */
    @Schema(description = "登入身份")
    private String loginType;

    /** 金融卡登入帳號 */
    @Schema(description = "金融卡登入帳號")
    private String accountNo;

    /** 裝置代號 */
    @Schema(description = "裝置代號")
    private String deviceId;

    /** 0:密碼輸入, 1:手勢 */
    @Schema(description = "0:密碼輸入, 1:手勢")
    private String pwType;

    /** 是否是指紋、顏值登入？ Y:是，N:否 */
    @Schema(description = "是否是指紋、顏值登入？ Y:是，N:否")
    private String IsSignatureLogin;

    /** 指紋/顏值 登入的 challenge */
    @Schema(description = "指紋/顏值 登入的 challenge")
    private String challenge;

    /** 指紋/顏值 登入的 answer */
    @Schema(description = "指紋/顏值 登入的 answer")
    private String answer;

    /** 指紋/顏值 登入的數位簽章明文 */
    @Schema(description = "指紋/顏值 登入的數位簽章明文")
    private String signatureLoginParamJsonString;

    /** 指紋/顏值 登入的數位簽章 */
    @Schema(description = "指紋/顏值 登入的數位簽章")
    private String signature;

    /** 客戶端IP */
    @Schema(description = "客戶端IP")
    private String clientIP;

    /** 平台 */
    @Schema(description = "平台")
    private  String platform;

    @Schema(description = "裝置資訊")
    private DeviceInfo deviceInfo;

    @Schema(description = "目前SessionID")
    private  String currentSessionId;

    @Schema(description = "MB_DUP_LOGIN_CONFIRM")
    private boolean mdDupLoginConfirm;

    @Schema(description = "serverId")
    private String serverId;

    /**
     * User Agent
     */
    @Schema(description = "User Agent")
    private String userAgent;

    /** 螢幕寬 */
    @Schema(description = "螢幕寬")
    private int screenWidth;

    /** 螢幕高 */
    @Schema(description = "螢幕高")
    private int screenHeight;

    @Schema(description = "是否含有時間因子 [null:無實作 / false:不含時間因子 / true:有時間因子]")
    private Boolean isPwdWithTime;
    
    @Schema(description = "Locale 語系")
    private String locale;

    /** ip(國名) */
    @Schema(description = "ip(國名)")
    private String countryName;
    /** ip(國別) */
    @Schema(description = "ip(國別)")
    private String countryCode;
    /** ip(起) */
    @Schema(description = " ip(起)")
    private Long ipFrom;
    /** ip(迄) */
    @Schema(description = "p(迄)")
    private Long ipTo;
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
     * @return the loginType
     */
    public String getLoginType() {
        return loginType;
    }

    /**
     * @param loginType
     *            the loginType to set
     */
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    /**
     * @return the accountNo
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * @param accountNo
     *            the accountNo to set
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
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
     * @return the pwType
     */
    public String getPwType() {
        return pwType;
    }

    /**
     * @param pwType
     *            the pwType to set
     */
    public void setPwType(String pwType) {
        this.pwType = pwType;
    }

    /**
     * @return the isSignatureLogin
     */
    public String getIsSignatureLogin() {
        return IsSignatureLogin;
    }

    /**
     * @param isSignatureLogin
     *            the isSignatureLogin to set
     */
    public void setIsSignatureLogin(String isSignatureLogin) {
        IsSignatureLogin = isSignatureLogin;
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
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer
     *            the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * @return the signatureLoginParamJsonString
     */
    public String getSignatureLoginParamJsonString() {
        return signatureLoginParamJsonString;
    }

    /**
     * @param signatureLoginParamJsonString
     *            the signatureLoginParamJsonString to set
     */
    public void setSignatureLoginParamJsonString(String signatureLoginParamJsonString) {
        this.signatureLoginParamJsonString = signatureLoginParamJsonString;
    }

    /**
     * @return the signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * @param signature
     *            the signature to set
     */
    public void setSignature(String signature) {
        this.signature = signature;
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
     * @return the mdDupLoginConfirm
     */
    public boolean isMdDupLoginConfirm() {
        return mdDupLoginConfirm;
    }

    /**
     * @param mdDupLoginConfirm
     *            the mdDupLoginConfirm to set
     */
    public void setMdDupLoginConfirm(boolean mdDupLoginConfirm) {
        this.mdDupLoginConfirm = mdDupLoginConfirm;
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
     * @return the userAgent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * @param userAgent
     *            the userAgent to set
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * @return the screenWidth
     */
    public int getScreenWidth() {
        return screenWidth;
    }

    /**
     * @param screenWidth
     *            the screenWidth to set
     */
    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    /**
     * @return the screenHeight
     */
    public int getScreenHeight() {
        return screenHeight;
    }

    /**
     * @param screenHeight
     *            the screenHeight to set
     */
    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    /**
     * @return the isPwdWithTime
     */
    public Boolean getIsPwdWithTime() {
        if (isPwdWithTime == null)
            return false;
        return isPwdWithTime;
    }

    /**
     * @param isPwdWithTime
     *            the isPwdWithTime to set
     */
    public void setIsPwdWithTime(Boolean isPwdWithTime) {
        this.isPwdWithTime = isPwdWithTime;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Long getIpFrom() {
        return ipFrom;
    }

    public void setIpFrom(Long ipFrom) {
        this.ipFrom = ipFrom;
    }

    public Long getIpTo() {
        return ipTo;
    }

    public void setIpTo(Long ipTo) {
        this.ipTo = ipTo;
    }

}
