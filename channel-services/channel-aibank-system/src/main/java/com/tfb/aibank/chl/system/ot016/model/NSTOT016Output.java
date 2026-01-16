/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.system.ot016.model;

// @formatter:off
/**
 * @(#)NSTOT016Output.java
 * 
 * <p>Description:雙重驗證登入API 輸出專用物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/27, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NSTOT016Output {

    /**
     * 更新成功
     */
    private Boolean updateSuccess;

    public Boolean getUpdateSuccess() {
        return updateSuccess;
    }

    public void setUpdateSuccess(Boolean updateSuccess) {
        this.updateSuccess = updateSuccess;
    }
}
