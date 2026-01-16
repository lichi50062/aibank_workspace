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
 * @(#)TwcaApiQueryVerifyResultCertParams.java
 * 
 * <p>Description:台網 QueryVerifyResult CertParams</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/16, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TwcaApiQueryVerifyResultCertParams {

    /** 憑證. */
    @SerializedName("X509Cert")
    private String x509Cert;

    /** 憑證 CN. */
    @SerializedName("CertCN")
    private String certCN;

    /** 憑證序號. */
    @SerializedName("CertSN")
    private String certSN;

    /** 憑證生效日期. */
    @SerializedName("CertNotBefore")
    private String certNotBefore;

    /** 憑證截止日期. */
    @SerializedName("CertNotAfter")
    private String certNotAfter;

    /**
     * @return {@link #x509Cert}
     */
    public String getX509Cert() {
        return x509Cert;
    }

    /**
     * @param x509Cert
     *            {@link #x509Cert}
     */
    public void setX509Cert(String x509Cert) {
        this.x509Cert = x509Cert;
    }

    /**
     * @return {@link #certCN}
     */
    public String getCertCN() {
        return certCN;
    }

    /**
     * @param certCN
     *            {@link #certCN}
     */
    public void setCertCN(String certCN) {
        this.certCN = certCN;
    }

    /**
     * @return {@link #certSN}
     */
    public String getCertSN() {
        return certSN;
    }

    /**
     * @param certSN
     *            {@link #certSN}
     */
    public void setCertSN(String certSN) {
        this.certSN = certSN;
    }

    /**
     * @return {@link #certNotBefore}
     */
    public String getCertNotBefore() {
        return certNotBefore;
    }

    /**
     * @param certNotBefore
     *            {@link #certNotBefore}
     */
    public void setCertNotBefore(String certNotBefore) {
        this.certNotBefore = certNotBefore;
    }

    /**
     * @return {@link #certNotAfter}
     */
    public String getCertNotAfter() {
        return certNotAfter;
    }

    /**
     * @param certNotAfter
     *            {@link #certNotAfter}
     */
    public void setCertNotAfter(String certNotAfter) {
        this.certNotAfter = certNotAfter;
    }

}
