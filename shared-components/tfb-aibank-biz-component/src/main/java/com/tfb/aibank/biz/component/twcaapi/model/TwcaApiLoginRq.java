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
package com.tfb.aibank.biz.component.twcaapi.model;

import com.google.gson.annotations.SerializedName;

// @formatter:off
/**
 * @(#)TwcaApiLoginRq.java
 * 
 * <p>Description:台網 Login</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/08, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TwcaApiLoginRq extends TwcaApiRq {

    /** 應用平台代號. */
    @SerializedName("BusinessNo")
    private String businessNo;

    /** API 版本. */
    @SerializedName("ApiVersion")
    private String apiVersion;

    /** HashKey 組別. */
    @SerializedName("HashKeyNo")
    private String hashKeyNo;

    /** 驗證編號. */
    @SerializedName("VerifyNo")
    private String verifyNo;

    /** 應用平台接收驗章結果以繼續處理交易之網頁介面. */
    @SerializedName("ReturnURL")
    private String returnURL;

    /** 應用平台自訂字串. */
    @SerializedName("ReturnParams")
    private String returnParams;

    /** InputParams Json. */
    @SerializedName("InputParams")
    private String inputParams;

    /**
     * @return {@link #businessNo}
     */
    public String getBusinessNo() {
        return businessNo;
    }

    /**
     * @param businessNo
     *            {@link #businessNo}
     */
    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    /**
     * @return {@link #apiVersion}
     */
    public String getApiVersion() {
        return apiVersion;
    }

    /**
     * @param apiVersion
     *            {@link #apiVersion}
     */
    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    /**
     * @return {@link #hashKeyNo}
     */
    public String getHashKeyNo() {
        return hashKeyNo;
    }

    /**
     * @param hashKeyNo
     *            {@link #hashKeyNo}
     */
    public void setHashKeyNo(String hashKeyNo) {
        this.hashKeyNo = hashKeyNo;
    }

    /**
     * @return {@link #verifyNo}
     */
    public String getVerifyNo() {
        return verifyNo;
    }

    /**
     * @param verifyNo
     *            {@link #verifyNo}
     */
    public void setVerifyNo(String verifyNo) {
        this.verifyNo = verifyNo;
    }

    /**
     * @return {@link #returnURL}
     */
    public String getReturnURL() {
        return returnURL;
    }

    /**
     * @param returnURL
     *            {@link #returnURL}
     */
    public void setReturnURL(String returnURL) {
        this.returnURL = returnURL;
    }

    /**
     * @return {@link #returnParams}
     */
    public String getReturnParams() {
        return returnParams;
    }

    /**
     * @param returnParams
     *            {@link #returnParams}
     */
    public void setReturnParams(String returnParams) {
        this.returnParams = returnParams;
    }

    /**
     * @return {@link #inputParams}
     */
    public String getInputParams() {
        return inputParams;
    }

    /**
     * @param inputParams
     *            {@link #inputParams}
     */
    public void setInputParams(String inputParams) {
        this.inputParams = inputParams;
    }

}
