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
 * @(#)HighestAmt.java
 *
 * <p>Description:消費分析 OAuth API 消費金額彙總資訊查詢 DataList</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/07/11 Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class HighestAmt {

    /** 交易日期 */
    private String date;
    
    /** 商店名稱 */
    private String merchant;

    /** 交易金額 */
    private BigDecimal txnNtdAmt;

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the merchant
     */
    public String getMerchant() {
        return this.merchant;
    }

    /**
     * @param merchant
     *            the merchant to set
     */
    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    /**
     * @return the txnNtdAmt
     */
    public BigDecimal getTxnNtdAmt() {
        return this.txnNtdAmt;
    }

    /**
     * @param txnNtdAmt
     *            the txnNtdAmt to set
     */
    public void setTxnNtdAmt(BigDecimal txnNtdAmt) {
        this.txnNtdAmt = txnNtdAmt;
    }
}
