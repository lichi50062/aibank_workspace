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
 * @(#)TwcaApiQueryVerifyResultRs.java
 * 
 * <p>Description:台網 QueryVerifyResult</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/14, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TwcaApiQueryVerifyResultRs extends TwcaApiRs {

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

    /** 執行結果. */
    @SerializedName("ResultCode")
    private String resultCode;

    /** outputParams Json. */
    @SerializedName("OutputParams")
    private String outputParamsJson;

    /** outputParams. */
    private TwcaApiQueryVerifyResultOutputParams outputParams;

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
     * @return {@link #resultCode}
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * @param resultCode
     *            {@link #resultCode}
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * @return {@link #outputParamsJson}
     */
    public String getOutputParamsJson() {
        return outputParamsJson;
    }

    /**
     * @param outputParamsJson
     *            {@link #outputParamsJson}
     */
    public void setOutputParamsJson(String outputParamsJson) {
        this.outputParamsJson = outputParamsJson;
    }

    /**
     * @return {@link #outputParams}
     */
    public TwcaApiQueryVerifyResultOutputParams getOutputParams() {
        return outputParams;
    }

    /**
     * @param outputParams
     *            {@link #outputParams}
     */
    public void setOutputParams(TwcaApiQueryVerifyResultOutputParams outputParams) {
        this.outputParams = outputParams;
    }

    @Override
    public String getIdentifyData(String hashKey) {
        return getBusinessNo() + getApiVersion() + getHashKeyNo() + getVerifyNo() + getResultCode() + getReturnCode() + getOutputParamsJson() + hashKey;
    }
}
