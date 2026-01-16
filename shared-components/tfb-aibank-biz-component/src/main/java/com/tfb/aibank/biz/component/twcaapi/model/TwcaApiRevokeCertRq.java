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
 * @(#)TwcaApiRevokeRq.java
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
public class TwcaApiRevokeCertRq extends TwcaApiRq {

    /** 應用平台代號. */
    @SerializedName("BusinessNo")
    private String businessNo;

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

}
