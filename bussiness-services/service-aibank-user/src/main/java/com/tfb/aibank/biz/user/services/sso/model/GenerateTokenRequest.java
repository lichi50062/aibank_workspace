/**
 * 
 */
package com.tfb.aibank.biz.user.services.sso.model;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)GenerateTokenRequest.java 
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
public class GenerateTokenRequest {

    /** 身分證字號 */
    @Schema(description = "身分證字號")
    private String custId;

    /** 使用者代碼 */
    @Schema(description = "使用者代碼")
    private String userId;

    /** 誤別碼 */
    @Schema(description = "誤別碼")
    private String uidDup;

    /** 公司類型 */
    @Schema(description = "公司類型")
    private Integer companyKind;

    /** 目標平台鍵值 */
    @Schema(description = "目標平台鍵值")
    private String channelKey;

    /** 公司類型 */
    @Schema(description = "公司類型")
    private String nameCode;
    
    /** Token類型 */
    @Schema(description = "Token類型 ")
    private String type;
    
    /** 外轉內SSO */
    @Schema(description = "由外轉內SSO ")
    private boolean fromFastLogin;
    /**SSO 下行電文 */
    @Schema(description = "SSO登入下行電文 ")
    private LoginData loginData;
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
     * @return the nameCode
     */
    public String getNameCode() {
        return nameCode;
    }

    /**
     * @param nameCode
     *            the nameCode to set
     */
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     */
    public boolean isFromFastLogin() {
        return fromFastLogin;
    }
    
    /**
     * 
     * @param fromFastLogin
     */
    public void setFromFastLogin(boolean fromFastLogin) {
        this.fromFastLogin = fromFastLogin;
    }
    /**
     * 
     * @return
     */
    public LoginData getLoginData() {
        return loginData;
    }
    /**
     * 
     * @param loginData
     */
    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    } 

}
