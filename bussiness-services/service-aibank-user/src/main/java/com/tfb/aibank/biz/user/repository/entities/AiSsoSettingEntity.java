/**
 * 
 */
package com.tfb.aibank.biz.user.repository.entities;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@formatter:off
/**
* @(#)AiSsoSettingEntity.java
* 
* <p>Description:AI Bank身分認證平台串接設定 Entity</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/01/02, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on

@Entity
@Table(name = "AI_SSO_SETTING")
public class AiSsoSettingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 驗證方式
     */
    @Basic
    @Column(name = "AUTH_TYPE")
    private String authType;

    /**
     * 目標/來源通路
     */
    @Basic
    @Column(name = "CHANNEL_ID")
    private String channelId;

    /**
     * 目標平台鍵值
     */
    @Id
    @Column(name = "CHANNEL_KEY")
    private String channelKey;

    /**
     * 目標/來源名稱
     */
    @Basic
    @Column(name = "CHANNEL_NAME")
    private String channelName;

    /**
     * 平台保留參數
     */
    @Basic
    @Column(name = "CUSTOME_PARAM")
    private String customeParam;

    /**
     * 目標/來源
     */
    @Basic
    @Column(name = "FUNC")
    private String func;

    /**
     * 備註
     */
    @Basic
    @Column(name = "MEMO")
    private String memo;

    /**
     * Header顯示方式
     */
    @Basic
    @Column(name = "MODULE_TYPE")
    private String moduleType;

    /**
     * 開啟方式
     */
    @Basic
    @Column(name = "OPEN_TYPE")
    private String openType;

    /**
     * 外開URL
     */
    @Basic
    @Column(name = "OPEN_URL")
    private String openUrl;

    /**
     * 是否透過身分認證平台
     */
    @Basic
    @Column(name = "SSO_FLAG")
    private String ssoFlag;

    /**
     * 內崁參數
     */
    @Basic
    @Column(name = "MODULE_PARAM")
    private String moduleParam;

    /**
     * 傳入登入資料開關
     */
    @Basic
    @Column(name = "LOGIN_DATA_FLAG")
    private String loginDataFlag;
    /**
     * 取得驗證方式
     * 
     * @return String 驗證方式
     */
    public String getAuthType() {
        return this.authType;
    }

    /**
     * 設定驗證方式
     * 
     * @param authType
     *            要設定的驗證方式
     */
    public void setAuthType(String authType) {
        this.authType = authType;
    }

    /**
     * 取得目標/來源通路
     * 
     * @return String 目標/來源通路
     */
    public String getChannelId() {
        return this.channelId;
    }

    /**
     * 設定目標/來源通路
     * 
     * @param channelId
     *            要設定的目標/來源通路
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * 取得目標平台鍵值
     * 
     * @return String 目標平台鍵值
     */
    public String getChannelKey() {
        return this.channelKey;
    }

    /**
     * 設定目標平台鍵值
     * 
     * @param channelKey
     *            要設定的目標平台鍵值
     */
    public void setChannelKey(String channelKey) {
        this.channelKey = channelKey;
    }

    /**
     * 取得目標/來源名稱
     * 
     * @return String 目標/來源名稱
     */
    public String getChannelName() {
        return this.channelName;
    }

    /**
     * 設定目標/來源名稱
     * 
     * @param channelName
     *            要設定的目標/來源名稱
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * 取得平台保留參數
     * 
     * @return String 平台保留參數
     */
    public String getCustomeParam() {
        return this.customeParam;
    }

    /**
     * 設定平台保留參數
     * 
     * @param customeParam
     *            要設定的平台保留參數
     */
    public void setCustomeParam(String customeParam) {
        this.customeParam = customeParam;
    }

    /**
     * 取得目標/來源
     * 
     * @return String 目標/來源
     */
    public String getFunc() {
        return this.func;
    }

    /**
     * 設定目標/來源
     * 
     * @param func
     *            要設定的目標/來源
     */
    public void setFunc(String func) {
        this.func = func;
    }

    /**
     * 取得備註
     * 
     * @return String 備註
     */
    public String getMemo() {
        return this.memo;
    }

    /**
     * 設定備註
     * 
     * @param memo
     *            要設定的備註
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 取得Header顯示方式
     * 
     * @return String Header顯示方式
     */
    public String getModuleType() {
        return this.moduleType;
    }

    /**
     * 設定Header顯示方式
     * 
     * @param moduleType
     *            要設定的Header顯示方式
     */
    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    /**
     * 取得開啟方式
     * 
     * @return String 開啟方式
     */
    public String getOpenType() {
        return this.openType;
    }

    /**
     * 設定開啟方式
     * 
     * @param openType
     *            要設定的開啟方式
     */
    public void setOpenType(String openType) {
        this.openType = openType;
    }

    /**
     * 取得外開URL
     * 
     * @return String 外開URL
     */
    public String getOpenUrl() {
        return this.openUrl;
    }

    /**
     * 設定外開URL
     * 
     * @param openUrl
     *            要設定的外開URL
     */
    public void setOpenUrl(String openUrl) {
        this.openUrl = openUrl;
    }

    /**
     * 取得是否透過身分認證平台
     * 
     * @return String 是否透過身分認證平台
     */
    public String getSsoFlag() {
        return this.ssoFlag;
    }

    /**
     * 設定是否透過身分認證平台
     * 
     * @param ssoFlag
     *            要設定的是否透過身分認證平台
     */
    public void setSsoFlag(String ssoFlag) {
        this.ssoFlag = ssoFlag;
    }

    /**
     * @return the moduleParam
     */
    public String getModuleParam() {
        return moduleParam;
    }

    /**
     * @param moduleParam
     *            the moduleParam to set
     */
    public void setModuleParam(String moduleParam) {
        this.moduleParam = moduleParam;
    }
    /**
     * 
     * @return
     */
    public String getLoginDataFlag() {
        return loginDataFlag;
    }
    /**
     * 
     * @param loginDataFlag
     */
    public void setLoginDataFlag(String loginDataFlag) {
        this.loginDataFlag = loginDataFlag;
    }

}
