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
package com.tfb.aibank.chl.system.resource.dto;

//@formatter:off
/**
* @(#)AIBankTxnResultPageRequest.java
* 
* <p>Description:交易結果頁記錄 - Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/01/17, MP
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class AibankTxnResultpage {

    /** companyKey */
    private Integer companyKey;

    /** userKey */
    private Integer userKey;

    /** b2cAccessLog traceId */
    private String traceId;

    /** 結果頁HTML In String */
    private String resData;

    /** PageId -> FromPage value in chl request */
    private String pageId;

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getResData() {
        return resData;
    }

    public void setResData(String resData) {
        this.resData = resData;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
}
