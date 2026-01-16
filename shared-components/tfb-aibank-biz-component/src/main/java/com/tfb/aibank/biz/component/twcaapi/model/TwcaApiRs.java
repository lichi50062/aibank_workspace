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
 * @(#)TwcaApiRs.java
 * 
 * <p>Description:台網api rs</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/08, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public abstract class TwcaApiRs {

    /** 結果代碼. */
    @SerializedName("ReturnCode")
    private String returnCode;

    /** 錯誤訊息. */
    @SerializedName("ReturnCodeDesc")
    private String returnCodeDesc;

    /** 驗證碼. */
    @SerializedName("IdentifyNo")
    private String identifyNo;

    /** 驗證. */
    public abstract String getIdentifyData(String hashKey);

    /**
     * @return {@link #returnCode}
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * @param returnCode
     *            {@link #returnCode}
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * @return {@link #returnCodeDesc}
     */
    public String getReturnCodeDesc() {
        return returnCodeDesc;
    }

    /**
     * @param returnCodeDesc
     *            {@link #returnCodeDesc}
     */
    public void setReturnCodeDesc(String returnCodeDesc) {
        this.returnCodeDesc = returnCodeDesc;
    }

    /**
     * @return {@link #identifyNo}
     */
    public String getIdentifyNo() {
        return identifyNo;
    }

    /**
     * @param identifyNo
     *            {@link #identifyNo}
     */
    public void setIdentifyNo(String identifyNo) {
        this.identifyNo = identifyNo;
    }

}
