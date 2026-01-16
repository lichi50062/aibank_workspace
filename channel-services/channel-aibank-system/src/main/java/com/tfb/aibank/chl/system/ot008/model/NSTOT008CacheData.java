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
package com.tfb.aibank.chl.system.ot008.model;

// @formatter:off
/**
 * @(#)NSTOT008CacheData.java
 * 
 * <p>Description:裝置綁定</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/02, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NSTOT008CacheData {
    /**
     * 綁定方式 0 - 純綁定
     * 
     * 1-含無卡提款授權
     * 
     * 2-含手機門號轉帳授權
     * 
     * 3-含變更轉帳額度授權
     */
    private int authType;

    /**
     * @return the authType
     */
    public int getAuthType() {
        return authType;
    }

    /**
     * @param authType
     *            the authType to set
     */
    public void setAuthType(int authType) {
        this.authType = authType;
    }

}
