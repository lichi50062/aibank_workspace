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
 * @(#)TwcaApiQueryVerifyResultRq.java
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
public class TwcaApiQueryVerifyResultRq extends TwcaApiRq {

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

    /** 顧客身分證號或統一編號. */
    @SerializedName("MemberNo")
    private String memberNo;

    /** 通行證. */
    @SerializedName("Token")
    private String token;

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
     * @return {@link #memberNo}
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * @param memberNo
     *            {@link #memberNo}
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    /**
     * @return {@link #token}
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     *            {@link #token}
     */
    public void setToken(String token) {
        this.token = token;
    }

}
