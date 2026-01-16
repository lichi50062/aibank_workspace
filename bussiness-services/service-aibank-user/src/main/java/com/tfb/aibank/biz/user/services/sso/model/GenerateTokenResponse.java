/**
 * 
 */
package com.tfb.aibank.biz.user.services.sso.model;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)GenerateTokenResponse.java 
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
public class GenerateTokenResponse {

    // Status
    // 2:SSO Auth
    // 1:Non-SSO-AUTH
    // 0:NOT-FOUND
    @Schema(description = "Status")
    private String status;

    /**
     * Token
     */
    @Schema(description = "Token")
    private String token;

    /**
     * 來源通路
     */
    @Schema(description = "來源通路")
    private String sourceFrom;

    /**
     * 目標通路
     */
    @Schema(description = "目標通路")
    private String channelId;

    /**
     * 目標
     */
    @Schema(description = "目標")
    private String func;

    /**
     * 平台使用者代碼
     */
    @Schema(description = "平台使用者代碼")
    private String txId;

    /**
     * 平台保留參數
     */
    @Schema(description = "平台保留參數")
    private String customParam;

    /**
     * 開啟方式
     */
    @Schema(description = "外開開啟方式")
    private String openType;

    /**
     * 是否透過身分認證平台
     */
    @Schema(description = "是否透過身分認證平台")
    private String ssoFlag;

    /**
     * 外開URL
     */
    @Schema(description = "外開URL")
    private String openUrl;

    /**
     * Header顯示方式
     */
    @Schema(description = "Header顯示方式")
    private String moduleType;
    
    /**
     * 平台名稱
     */
    @Schema(description = "平台名稱")
    private String channelName;
    
    /**
     * 內崁參數
     */
    @Schema(description = "內崁參數")
    private String moduleParam;

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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
     * @return the sourceFrom
     */
    public String getSourceFrom() {
        return sourceFrom;
    }

    /**
     * @param sourceFrom
     *            the sourceFrom to set
     */
    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    /**
     * @return the channelId
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * @param channelId
     *            the channelId to set
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * @return the func
     */
    public String getFunc() {
        return func;
    }

    /**
     * @param func
     *            the func to set
     */
    public void setFunc(String func) {
        this.func = func;
    }

    /**
     * @return the txId
     */
    public String getTxId() {
        return txId;
    }

    /**
     * @param txId
     *            the txId to set
     */
    public void setTxId(String txId) {
        this.txId = txId;
    }

    /**
     * @return the customParam
     */
    public String getCustomParam() {
        return customParam;
    }

    /**
     * @param customParam
     *            the customParam to set
     */
    public void setCustomParam(String customParam) {
        this.customParam = customParam;
    }

    /**
     * @return the openType
     */
    public String getOpenType() {
        return openType;
    }

    /**
     * @param openType
     *            the openType to set
     */
    public void setOpenType(String openType) {
        this.openType = openType;
    }

    /**
     * @return the ssoFlag
     */
    public String getSsoFlag() {
        return ssoFlag;
    }

    /**
     * @param ssoFlag
     *            the ssoFlag to set
     */
    public void setSsoFlag(String ssoFlag) {
        this.ssoFlag = ssoFlag;
    }

    /**
     * @return the openUrl
     */
    public String getOpenUrl() {
        return openUrl;
    }

    /**
     * @param openUrl
     *            the openUrl to set
     */
    public void setOpenUrl(String openUrl) {
        this.openUrl = openUrl;
    }

    /**
     * @return the moduleType
     */
    public String getModuleType() {
        return moduleType;
    }

    /**
     * @param moduleType
     *            the moduleType to set
     */
    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    /**
     * @return the channelName
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * @param channelName the channelName to set
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * @return the moduleParam
     */
    public String getModuleParam() {
        return moduleParam;
    }

    /**
     * @param moduleParam the moduleParam to set
     */
    public void setModuleParam(String moduleParam) {
        this.moduleParam = moduleParam;
    }

}
