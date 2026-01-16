/**
 * 
 */
package com.tfb.aibank.biz.user.services.sso.model;

import com.ibm.tw.commons.util.StringUtils;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)DoFastValidateUserRequest.java 
* 
* <p>Description:SSO/快速身份認證</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 2024/01/17, JohnChang
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on
public class DoFastValidateUserRequest {

    /** 身分證字號 */
    @Schema(description = "身分證字號")
    private String custId;

    /** 使用者代號 */
    @Schema(description = "使用者代號")
    private String userId;

    /** 公司類型 */
    @Schema(description = "公司類型")
    private int companyKind;

    /** 誤別碼 */
    @Schema(description = "誤別碼")
    private String uidDup;

    /** 登入身份 */
    @Schema(description = "登入身份")
    String loginType;

    /** 裝置代號 */
    @Schema(description = "裝置代號")
    String deviceId;

    /** 0:密碼輸入, 1:手勢 */
    @Schema(description = "0:密碼輸入, 1:手勢")
    String pwType;

    /** 指紋/顏值 登入的 challenge */
    @Schema(description = "指紋/顏值 登入的 challenge")
    String challenge;

    /** 是否是指紋、顏值登入？ Y:是，N:否 */
    @Schema(description = "是否是指紋、顏值登入？ Y:是，N:否")
    String IsSignatureLogin;

    /** 目標平台鍵值 */
    @Schema(description = "目標平台鍵值")
    private String channelKey;

    /** 密碼 */
    @Schema(description = "密碼")
    private String secret;

    /** 啟用時間因子 */
    @Schema(description = "啟用時間因子")
    private Boolean pwdWithTime;

    /** 需要建立 Token */
    @Schema(description = "需要建立Token")
    private boolean needGenerateToken;

    // Token種類
    @Schema(description = "Token種類")
    private String tokenType;

    /**
     * 是否為快登
     * 
     * @return
     */
    public boolean isQuickLogin() {
        return StringUtils.equalsIgnoreCase("Y", this.getIsSignatureLogin());
    }

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
    public int getCompanyKind() {
        return companyKind;
    }

    /**
     * @param companyKind
     *            the companyKind to set
     */
    public void setCompanyKind(int companyKind) {
        this.companyKind = companyKind;
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
     * @return the channelKey
     */
    public String getChannelKey() {
        return channelKey;
    }

    /**
     * @param channelKey
     *            the channelKey to set
     */
    public void setChannelKey(String channelKey) {
        this.channelKey = channelKey;
    }

    /**
     * 
     * @return
     */
    public String getSecret() {
        return secret;
    }

    /**
     * 
     * @param sxcrxt
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * 
     * @return
     */
    public Boolean getPwdWithTime() {
        return pwdWithTime;
    }

    /**
     * 
     * @param pwdWithTime
     */
    public void setPwdWithTime(Boolean pwdWithTime) {
        this.pwdWithTime = pwdWithTime;
    }

    /**
     * 
     * @return
     */
    public boolean isNeedGenerateToken() {
        return needGenerateToken;
    }

    /**
     * 
     * @param needGenerateToken
     */
    public void setNeedGenerateToken(boolean needGenerateToken) {
        this.needGenerateToken = needGenerateToken;
    }

    /**
     * 
     * @return
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * 
     * @param tokenType
     */
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

}
