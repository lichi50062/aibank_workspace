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

import java.math.BigDecimal;

// @formatter:off
/**
* @(#)CategoryStats.java
* 
* <p>Description:消費分析 OAuth API 各消費類別金額查詢 Response</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/06/28 Warren Lin
* <ol>
*  <li>初版</li>
* </ol>
*/
// @formatter:on
public class CategoryStats {

    /** 資料年月 */
    private String yearMonth;
    
    /** 消費類別 */
    private String category;

    /** 消費金額 */
    private BigDecimal txnAmt;

    /**
     * @return the yearMonth
     */
    public String getYearMonth() {
        return yearMonth;
    }

    /**
     * @param yearMonth
     *            the yearMonth to set
     */
    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * @param category
     *            the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the txnAmt
     */
    public BigDecimal getTxnAmt() {
        return this.txnAmt;
    }

    /**
     * @param txnAmt
     *            the txnAmt to set
     */
    public void setTxnAmt(BigDecimal txnAmt) {
        this.txnAmt = txnAmt;
    }
}
