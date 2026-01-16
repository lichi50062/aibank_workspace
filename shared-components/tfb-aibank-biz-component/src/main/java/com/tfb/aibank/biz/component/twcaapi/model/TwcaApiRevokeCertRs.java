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
 * @(#)TwcaApiRevokeRs.java
 * 
 * <p>Description:台網 RevokeCert</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/14, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TwcaApiRevokeCertRs extends TwcaApiRs {

    /** 應用平台代號. */
    @SerializedName("BusinessNo")
    private String businessNo;

    /** API 版本. */
    @SerializedName("ApiVersion")
    private String apiVersion;

    /** HashKey 組別. */
    @SerializedName("HashKeyNo")
    private String hashKeyNo;

    /** 憑證序號. */
    @SerializedName("HexSerial")
    private String hexSerial;

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
     * @return {@link #hexSerial}
     */
    public String getHexSerial() {
        return hexSerial;
    }

    /**
     * @param hexSerial
     *            {@link #hexSerial}
     */
    public void setHexSerial(String hexSerial) {
        this.hexSerial = hexSerial;
    }

    @Override
    public String getIdentifyData(String hashKey) {
        return getBusinessNo() + getApiVersion() + getHashKeyNo() + getHexSerial() + getReturnCode() + getReturnCodeDesc() + hashKey;
    }

}
