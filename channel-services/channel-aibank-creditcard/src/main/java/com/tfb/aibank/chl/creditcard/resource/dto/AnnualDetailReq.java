/*
 * ===========================================================================
 *
 * IBM Confidential
 *
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

// @formatter:off
/**
* @(#)AnnualDetailReq.java
* 
* <p>Description:消費分析 整年消費明細搜尋 Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/06/28 Warren Lin
* <ol>
*  <li>初版</li>
* </ol>
*/
// @formatter:on
public class AnnualDetailReq {

    /** 身份字號 */
    private String custId;

    /** 年月: YYYY-MM */
    private String yearMonth;

    /** 分頁 */
    private Integer skip;

    /** 回傳筆數: 預設1000 */
    private String limit;

    /**
     * @return the custId
     */
    public String getCustId() {
        return custId;
    }

    /**
     * @param custId
     *            the custId to set
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * @return the yearMonth
     */
    public String getYearMonth() {
        return this.yearMonth;
    }

    /**
     * @param yearMonth
     *            the yearMonth to set
     */
    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    /**
     * @return the skip
     */
    public Integer getSkip() {
        return this.skip;
    }

    /**
     * @param skip
     *            the skip to set
     */
    public void setSkip(Integer skip) {
        this.skip = skip;
    }
    
    /**
     * @return the limit
     */
    public String getLimit() {
        return this.limit;
    }

    /**
     * @param limit
     *            the limit to set
     */
    public void setLimit(String limit) {
        this.limit = limit;
    }
}
