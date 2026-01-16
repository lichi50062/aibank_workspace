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

import java.util.List;

import com.google.gson.annotations.SerializedName;

// @formatter:off
/**
 * @(#)TwcaApiQueryCertRs.java
 * 
 * <p>Description:台網 QueryCert</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/07, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TwcaApiQueryCertRs extends TwcaApiRs {

    /** 應用平台代號. */
    @SerializedName("BusinessNo")
    private String businessNo;

    /** HashKey 組別. */
    @SerializedName("HashKeyNo")
    private String hashKeyNo;

    /** 顧客身分證號或統一編號. */
    @SerializedName("MemberNo")
    private String memberNo;

    /** 憑證清單資訊 Json. */
    @SerializedName("CertInfoList")
    private String certInfoListJsonData;

    /** 憑證清單資訊. */
    private List<TwcaApiCertInfo> certInfoList;

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
     * @return {@link #certInfoListJsonData}
     */
    public String getCertInfoListJsonData() {
        return certInfoListJsonData;
    }

    /**
     * @param certInfoListJsonData
     *            {@link #certInfoListJsonData}
     */
    public void setCertInfoListJsonData(String certInfoListJsonData) {
        this.certInfoListJsonData = certInfoListJsonData;
    }

    /**
     * @return {@link #certInfoList}
     */
    public List<TwcaApiCertInfo> getCertInfoList() {
        return certInfoList;
    }

    /**
     * @param certInfoList
     *            {@link #certInfoList}
     */
    public void setCertInfoList(List<TwcaApiCertInfo> certInfoList) {
        this.certInfoList = certInfoList;
    }

    @Override
    public String getIdentifyData(String hashKey) {
        return getBusinessNo() + getHashKeyNo() + getMemberNo() + getReturnCode() + getReturnCodeDesc() + getCertInfoListJsonData() + hashKey;
    }

}
