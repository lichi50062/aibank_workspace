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
* @(#)CategoryReq.java
* 
* <p>Description: 消費分析 各消費類別金額查詢 Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/06/28 Warren Lin
* <ol>
*  <li>初版</li>
* </ol>
*/
// @formatter:on
public class CategoryReq {

    /** 身份字號 */
    private String custId;

    /** 消費類別群組 */
    private String categoryGroup;
    
    /** 開始年月: YYYY-MM */
    private String startYearMonth;

    /** 結束年月: YYYY-MM */
    private String endYearMonth;

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
     * @return the categoryGroup
     */
    public String getCategoryGroup() {
        return categoryGroup;
    }

    /**
     * @param categoryGroup
     *            the categoryGroup to set
     */
    public void setCategoryGroup(String categoryGroup) {
        this.categoryGroup = categoryGroup;
    }

    /**
     * @return the startYearMonth
     */
    public String getStartYearMonth() {
        return this.startYearMonth;
    }

    /**
     * @param startYearMonth
     *            the startYearMonth to set
     */
    public void setStartYearMonth(String startYearMonth) {
        this.startYearMonth = startYearMonth;
    }

    /**
     * @return the endYearMonth
     */
    public String getEndYearMonth() {
        return this.endYearMonth;
    }

    /**
     * @param endYearMonth
     *            the endYearMonth to set
     */
    public void setEndYearMonth(String endYearMonth) {
        this.endYearMonth = endYearMonth;
    }
}
