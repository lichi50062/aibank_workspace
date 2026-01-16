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
 * @(#)TwcaApiLoginOutputParams.java
 * 
 * <p>Description:台網 Login OutputParams</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/15, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TwcaApiLoginOutputParams {

    /** 顧客身分證號或統一編號. */
    @SerializedName("MemberNo")
    private String memberNo;

    /** 通行證. */
    @SerializedName("Token")
    private String token;

    /** 時戳(UTC Time). */
    @SerializedName("TimeStamp")
    private String timeStamp;

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

    /**
     * @return {@link #timeStamp}
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp
     *            {@link #timeStamp}
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

}
