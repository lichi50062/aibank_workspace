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
 * @(#)GetSsoSettingResponse.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/29, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class GetSsoSettingResponse {

    // Status
    // 2:SSO Auth
    // 1:Non-SSO-AUTH
    // 0:NOT-FOUND
    @Schema(description = "Status")
    private String status;

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
     * 內崁參數
     */
    @Schema(description = "內崁參數")
    private String moduleParam;

    /**
     * Header顯示方式
     */
    @Schema(description = "Header顯示方式")
    private String moduleType;

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

}
