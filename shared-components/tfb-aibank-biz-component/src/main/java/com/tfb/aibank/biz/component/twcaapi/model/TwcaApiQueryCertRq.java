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
 * @(#)TwcaApiQueryCertRq.java
 * 
 * <p>Description:台網 QueryCert</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/08, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TwcaApiQueryCertRq extends TwcaApiRq {

    /** 應用平台代號. */
    @SerializedName("BusinessNo")
    private String businessNo;

    /** HashKey 組別. */
    @SerializedName("HashKeyNo")
    private String hashKeyNo;

    /** 顧客身分證號或統一編號. */
    @SerializedName("MemberNo")
    private String memberNo;

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

}
