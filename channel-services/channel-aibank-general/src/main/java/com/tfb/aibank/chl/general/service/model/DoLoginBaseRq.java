package com.tfb.aibank.chl.general.service.model;

/**
 * // @formatter:off
/**
 * @(#)DoLoginBaseRq.java
 * 
 * <p>Description:登入OT001,OT005 共用 登入RQ</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/31, bensonlin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on 
public abstract class DoLoginBaseRq {

    /**
     * 統編/身分證字號
     */
    private String uid;

    /**
     * 使用者代碼
     */
    private String uuid;

    /**
     * 密碼
     */
    private String secret;

    /**
     * 密碼型態 0:密碼輸入, 1:手勢
     */
    private String pwType;

    /**
     * 登入身份
     */
    private String loginType;

    /**
     * 指紋、顏值登入的驗證 =Y
     */
    private String isSignatureLogin;

    /**
     * 生物辨識比對值
     */
    private String challenge;

    /** 首次登入 */
    private String firstLogin;

    /**
     * 確認重複登入
     */
    private boolean force;

    /**
     * 接續登入
     */
    private boolean continueLogin;

    /**
     * 變更密碼期限且接續登入
     */
    private boolean undatePWDnLogin;

    /** Push Token */
    private String pushToken;

    /** 是否首登 */
    private boolean isFirstLogin;

    /**
     * User Agent
     */
    private String agent;
    /** 螢幕寬 */
    private String screenWidth;
    /** 螢幕高 */
    private String screenHeight;

    /**
     * E2EE給前端uid/uuid加密時，是否帶入之時間因子
     */
    private boolean isUidUuidNeedWithTime;

    /**
     * E2EE給前端PWD加密時，是否帶入之時間因子
     */
    private boolean isPwdNeedWithTime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getPwType() {
        return pwType;
    }

    public void setPwType(String pwType) {
        this.pwType = pwType;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getIsSignatureLogin() {
        return isSignatureLogin;
    }

    public void setIsSignatureLogin(String isSignatureLogin) {
        this.isSignatureLogin = isSignatureLogin;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(String firstLogin) {
        this.firstLogin = firstLogin;
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    public boolean isContinueLogin() {
        return continueLogin;
    }

    public void setContinueLogin(boolean continueLogin) {
        this.continueLogin = continueLogin;
    }

    public boolean isUndatePWDnLogin() {
        return undatePWDnLogin;
    }

    public void setUndatePWDnLogin(boolean undatePWDnLogin) {
        this.undatePWDnLogin = undatePWDnLogin;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public boolean isFirstLogin() {
        return isFirstLogin;
    }

    public void setFirstLogin(boolean isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(String screenWidth) {
        this.screenWidth = screenWidth;
    }

    public String getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(String screenHeight) {
        this.screenHeight = screenHeight;
    }

    public boolean isUidUuidNeedWithTime() {
        return isUidUuidNeedWithTime;
    }

    public void setUidUuidNeedWithTime(boolean isUidUuidNeedWithTime) {
        this.isUidUuidNeedWithTime = isUidUuidNeedWithTime;
    }

    public boolean isPwdNeedWithTime() {
        return isPwdNeedWithTime;
    }

    public void setPwdNeedWithTime(boolean isPwdNeedWithTime) {
        this.isPwdNeedWithTime = isPwdNeedWithTime;
    }
    
    
    
}
