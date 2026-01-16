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
 * @(#)TwcaApiRq.java
 * 
 * <p>Description:台網api rq</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/08, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TwcaApiRq {

    /** 驗證碼. */
    @SerializedName("IdentifyNo")
    private String identifyNo;

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
