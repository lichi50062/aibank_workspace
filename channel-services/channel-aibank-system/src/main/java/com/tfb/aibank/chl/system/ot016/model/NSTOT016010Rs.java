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

import com.ibm.tw.ibmb.base.model.RsData;

import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NSTOT016010Rs.java
 * 
 * <p>Description:雙重驗證登入API 010 發送推播</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/27, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT016010Rs implements RsData {

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
