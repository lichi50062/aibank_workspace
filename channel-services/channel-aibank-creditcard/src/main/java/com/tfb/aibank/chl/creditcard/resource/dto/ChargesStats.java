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
* @(#)ChargesStats.java
* 
* <p>Description:消費分析 消費金額彙總資訊查詢 DataList</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/07/11 Warren Lin
* <ol>
*  <li>初版</li>
* </ol>
*/
// @formatter:on
public class ChargesStats {

    /** 資料年月 */
    private String yearMonth;
    
    /** 交易筆數 */
    private Integer txnCnt;

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
     * @return the txnCnt
     */
    public Integer getTxnCnt() {
        return this.txnCnt;
    }

    /**
     * @param txnCnt
     *            the txnCnt to set
     */
    public void setTxnCnt(Integer txnCnt) {
        this.txnCnt = txnCnt;
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
