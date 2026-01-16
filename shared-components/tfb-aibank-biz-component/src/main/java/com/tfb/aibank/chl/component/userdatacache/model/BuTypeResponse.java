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
package com.tfb.aibank.chl.component.userdatacache.model;

import com.tfb.aibank.common.type.CompanyBUType;

// @formatter:off
/**
 * @(#)BuTypeResponse.java
 * 
 * <p>Description:取得OBU/DBU</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/19, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BuTypeResponse {

    /**
     * 代碼
     * 
     * @see CompanyBUType
     */
    private int buType;

    /**
     * @return the buType
     */
    public int getBuType() {
        return buType;
    }

    /**
     * @param buType
     *            the buType to set
     */
    public void setBuType(int buType) {
        this.buType = buType;
    }

}
