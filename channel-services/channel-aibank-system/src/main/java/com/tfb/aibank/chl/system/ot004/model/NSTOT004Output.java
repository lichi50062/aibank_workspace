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
package com.tfb.aibank.chl.system.ot004.model;

import java.util.List;

import com.tfb.aibank.chl.system.resource.dto.Announcement;

// @formatter:off
/**
 * @(#)NSTOT004Output.java
 * 
 * <p>Description:NSTOT004 專用輸出物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/26, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NSTOT004Output {

    /** 系統公告資料 */
    private List<Announcement> annoList;

    /** 執行結果 */
    private Boolean execResult = Boolean.FALSE;

    /** 個股商品分時走勢圖URL */
    private String etfDataURL;

    public List<Announcement> getAnnoList() {
        return annoList;
    }

    public void setAnnoList(List<Announcement> annoList) {
        this.annoList = annoList;
    }

    public Boolean getExecResult() {
        return execResult;
    }

    public void setExecResult(Boolean execResult) {
        this.execResult = execResult;
    }

    public String getEtfDataURL() {
        return etfDataURL;
    }

    public void setEtfDataURL(String etfDataURL) {
        this.etfDataURL = etfDataURL;
    }

}
