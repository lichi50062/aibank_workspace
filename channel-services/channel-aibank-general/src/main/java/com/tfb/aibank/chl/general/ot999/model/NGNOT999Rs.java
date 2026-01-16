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
package com.tfb.aibank.chl.general.ot999.model;

import java.util.List;

// @formatter:off
/**
 * @(#)NGNOT999Rs.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/15, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NGNOT999Rs {

    /**
     * 行銀快速身份認證 狀態碼
     */
    private String statusCode;

    /**
     * 行銀快速身份認證 CallBack Url
     */
    private String callBackUrl;

    /**
     * 開啟方式
     */
    private String openType;

    /**
     * 內崁參數
     */
    private String moduleParam;

    /**
     * 網域白名單
     */
    private List<String> whiteListWhenOpenUrl;

    /** Header顯示方式 */
    private String moduleType;
    
     

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
     * @return the callBackUrl
     */
    public String getCallBackUrl() {
        return callBackUrl;
    }

    /**
     * @param callBackUrl
     *            the callBackUrl to set
     */
    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
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
     * @return the whiteListWhenOpenUrl
     */
    public List<String> getWhiteListWhenOpenUrl() {
        return whiteListWhenOpenUrl;
    }

    /**
     * @param whiteListWhenOpenUrl
     *            the whiteListWhenOpenUrl to set
     */
    public void setWhiteListWhenOpenUrl(List<String> whiteListWhenOpenUrl) {
        this.whiteListWhenOpenUrl = whiteListWhenOpenUrl;
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
