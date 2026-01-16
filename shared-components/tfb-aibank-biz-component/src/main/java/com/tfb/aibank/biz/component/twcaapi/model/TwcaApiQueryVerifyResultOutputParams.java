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
 * @(#)TwcaApiQueryVerifyResultOutputParams.java
 * 
 * <p>Description:台網 QueryVerifyResult OutputParams</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/16, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TwcaApiQueryVerifyResultOutputParams {

    /** 應用平台代號. */
    @SerializedName("Action")
    private String action;

    /** 應用平台代號. */
    @SerializedName("SelectType")
    private String selectType;

    /** 應用平台代號. */
    @SerializedName("VerifyTime")
    private String verifyTime;

    /** 應用平台代號. */
    @SerializedName("Plaintext")
    private String plaintext;

    /** 應用平台代號. */
    @SerializedName("CertParams")
    private TwcaApiQueryVerifyResultCertParams certParams;

    /**
     * @return {@link #action}
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action
     *            {@link #action}
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return {@link #selectType}
     */
    public String getSelectType() {
        return selectType;
    }

    /**
     * @param selectType
     *            {@link #selectType}
     */
    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    /**
     * @return {@link #verifyTime}
     */
    public String getVerifyTime() {
        return verifyTime;
    }

    /**
     * @param verifyTime
     *            {@link #verifyTime}
     */
    public void setVerifyTime(String verifyTime) {
        this.verifyTime = verifyTime;
    }

    /**
     * @return {@link #plaintext}
     */
    public String getPlaintext() {
        return plaintext;
    }

    /**
     * @param plaintext
     *            {@link #plaintext}
     */
    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    /**
     * @return {@link #certParams}
     */
    public TwcaApiQueryVerifyResultCertParams getCertParams() {
        return certParams;
    }

    /**
     * @param certParams
     *            {@link #certParams}
     */
    public void setCertParams(TwcaApiQueryVerifyResultCertParams certParams) {
        this.certParams = certParams;
    }

}
