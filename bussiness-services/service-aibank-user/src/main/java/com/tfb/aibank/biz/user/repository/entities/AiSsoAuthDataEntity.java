/**
 * 
 */
package com.tfb.aibank.biz.user.repository.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@formatter:off
/**
* @(#)AiSsoAuthDataEntity.java
* 
* <p>Description:AI Bank SSO驗證資料表 Entity</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/01/02, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Entity
@Table(name = "AI_SSO_AUTH_DATA")
public class AiSsoAuthDataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 通路
     */
    @Basic
    @Column(name = "CHANNEL_ID")
    private String channelId;

    /**
     * 建立時間
     */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 有效時間
     */
    @Basic
    @Column(name = "EXPIRED_TIME")
    private Date expiredTime;

    /**
     * 會員類別
     */
    @Basic
    @Column(name = "LOGIN_TYPE")
    private String loginType;

    /**
     * 登入驗證TOKEN
     */
    @Id
    @Column(name = "TOKEN")
    private String token;

    /**
     * SSO驗證資料
     */
    @Basic
    @Column(name = "USER_DATA")
    private String userData;

    /**
     * 使用者代碼
     */
    @Basic
    @Column(name = "USER_UUID")
    private String userUuid;

    /**
     * 身分認證平台傳入識別值
     */
    @Basic
    @Column(name = "PLATFORM_USER_ACCOUNT")
    private String platformUserAccount;

    /**
     * 取得通路
     * 
     * @return String 通路
     */
    public String getChannelId() {
        return this.channelId;
    }

    /**
     * 設定通路
     * 
     * @param channelId
     *            要設定的通路
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * 取得建立時間
     * 
     * @return Date 建立時間
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 設定建立時間
     * 
     * @param createTime
     *            要設定的建立時間
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得有效時間
     * 
     * @return Date 有效時間
     */
    public Date getExpiredTime() {
        return this.expiredTime;
    }

    /**
     * 設定有效時間
     * 
     * @param expiredTime
     *            要設定的有效時間
     */
    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    /**
     * 取得會員類別
     * 
     * @return String 會員類別
     */
    public String getLoginType() {
        return this.loginType;
    }

    /**
     * 設定會員類別
     * 
     * @param loginType
     *            要設定的會員類別
     */
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    /**
     * 取得登入驗證TOKEN
     * 
     * @return String 登入驗證TOKEN
     */
    public String getToken() {
        return this.token;
    }

    /**
     * 設定登入驗證TOKEN
     * 
     * @param token
     *            要設定的登入驗證TOKEN
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 取得SSO驗證資料
     * 
     * @return String SSO驗證資料
     */
    public String getUserData() {
        return this.userData;
    }

    /**
     * 設定SSO驗證資料
     * 
     * @param userData
     *            要設定的SSO驗證資料
     */
    public void setUserData(String userData) {
        this.userData = userData;
    }

    /**
     * 取得使用者代碼
     * 
     * @return String 使用者代碼
     */
    public String getUserUuid() {
        return this.userUuid;
    }

    /**
     * 設定使用者代碼
     * 
     * @param userUuid
     *            要設定的使用者代碼
     */
    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    /**
     * @return the platformUserAccount
     */
    public String getPlatformUserAccount() {
        return platformUserAccount;
    }

    /**
     * @param platformUserAccount
     *            the platformUserAccount to set
     */
    public void setPlatformUserAccount(String platformUserAccount) {
        this.platformUserAccount = platformUserAccount;
    }

}
