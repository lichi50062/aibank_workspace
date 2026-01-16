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
package com.tfb.aibank.chl.general.ot011.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NGNOT011010Rs.java
 * 
 * <p>Description:LINE官方帳號綁定 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/18, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNOT011010Rs implements RsData {

    /** 是否已綁定 */
    private boolean isBind;

    /** 申請綁定URL */
    private String applyUrl;

    /**
     * @return the isBind
     */
    public boolean isBind() {
        return isBind;
    }

    /**
     * @param isBind
     *            the isBind to set
     */
    public void setBind(boolean isBind) {
        this.isBind = isBind;
    }

    /**
     * @return the applyUrl
     */
    public String getApplyUrl() {
        return applyUrl;
    }

    /**
     * @param applyUrl
     *            the applyUrl to set
     */
    public void setApplyUrl(String applyUrl) {
        this.applyUrl = applyUrl;
    }

}
