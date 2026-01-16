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
 * @(#)NSTOT016011Rs.java
 * 
 * <p>Description:雙重驗證登入API 011 更新資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/27, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT016011Rs implements RsData {

    /**
     * 雙重驗證 間隔秒數
     */
    private Integer checkSeconds;

    public Integer getCheckSeconds() {
        return checkSeconds;
    }

    public void setCheckSeconds(Integer checkSeconds) {
        this.checkSeconds = checkSeconds;
    }
}
