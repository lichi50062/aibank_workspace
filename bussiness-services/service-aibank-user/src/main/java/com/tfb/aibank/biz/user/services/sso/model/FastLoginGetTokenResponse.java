/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.user.services.sso.model;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)FastLoginGetTokenResponse.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/12, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FastLoginGetTokenResponse {
    /**
     * statusCode
     */
    @Schema(description = "statusCode")
    private String statusCode;

    @Schema(description = "statusDesc")
    private String statusDesc;

    /**
     * 登入人員資料
     */
    @Schema(description = "登入人員資料")
    private String userData;

    /**
     * 會員類別
     */
    @Schema(description = "會員類別")
    private String loginType;

    /**
     * 使用者代碼
     */
    @Schema(description = "使用者代碼")
    private String userUuid;

    /**
     * @return the statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode
     *            the statusCode to set
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the statusDesc
     */
    public String getStatusDesc() {
        return statusDesc;
    }

    /**
     * @param statusDesc
     *            the statusDesc to set
     */
    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    /**
     * @return the userData
     */
    public String getUserData() {
        return userData;
    }

    /**
     * @param userData
     *            the userData to set
     */
    public void setUserData(String userData) {
        this.userData = userData;
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
     * @return the userUuid
     */
    public String getUserUuid() {
        return userUuid;
    }

    /**
     * @param userUuid
     *            the userUuid to set
     */
    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

}
