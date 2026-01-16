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
package com.tfb.aibank.chl.creditcard.qu010.model;

import java.math.BigDecimal;
import java.util.Date;
import com.ibm.tw.ibmb.annotations.FormatDate;

// @formatter:off
/**
* @(#)NCCQU010CategoryDetailData.java
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
public class NCCQU010CategoryDetailData {

    /** 消費日期 */
    @FormatDate(pattern = "MM/dd")
    private Date txnDate;

    /** 消費日期 */
    private String txnDateDisplay;
    
    /** 消費項目 */
    private String item;

    /** 消費金額 */
    private BigDecimal txnNtdAmt;

    /** 顯示消費金額 */
    private String txnAmtDisplay;
    
    /** 信用卡支付資訊 */
    private NCCQU010PaymentInfo paymentInfo;

    /** 消費金額加總 */
    private String txnAmtSumDisplay;

    /** 消費總筆數 */
    private String detailItemCountDisplay;

    /** 是否集中在上半個月 */
    private Boolean focusOnFirstHalfOfMonth;

    /**
     * @return the txnDate
     */
    public Date getTxnDate() {
        return txnDate;
    }

    /**
     * @param txnDate
     *            the txnDate to set
     */
    public void setTxnDate(Date txnDate) {
        this.txnDate = txnDate;
    }

    /**
     * @return the txnDateDisplay
     */
    public String getTxnDateDisplay() {
        return txnDateDisplay;
    }

    /**
     * @param txnDateDisplay
     *            the txnDateDisplay to set
     */
    public void setTxnDateDisplay(String txnDateDisplay) {
        this.txnDateDisplay = txnDateDisplay;
    }

    /**
     * @return the item
     */
    public String getItem() {
        return this.item;
    }

    /**
     * @param item
     *            the item to set
     */
    public void setItem(String item) {
        this.item = item;
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
    
    /**
     * @return the txnAmtDisplay
     */
    public String getTxnAmtDisplay() {
        return this.txnAmtDisplay;
    }

    /**
     * @param txnAmtDisplay
     *            the txnAmtDisplay to set
     */
    public void setTxnAmtDisplay(String txnAmtDisplay) {
        this.txnAmtDisplay = txnAmtDisplay;
    }

    /**
     * @return the paymentInfo
     */
    public NCCQU010PaymentInfo getPaymentInfo() {
        return this.paymentInfo;
    }

    /**
     * @param paymentInfo
     *            the paymentInfo to set
     */
    public void setPaymentInfo(NCCQU010PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    /**
     * @return the txnAmtSumDisplay
     */
    public String getTxnAmtSumDisplay() {
        return this.txnAmtSumDisplay;
    }

    /**
     * @param txnAmtSumDisplay
     *            the txnAmtSumDisplay to set
     */
    public void setTxnAmtSumDisplay(String txnAmtSumDisplay) {
        this.txnAmtSumDisplay = txnAmtSumDisplay;
    }

    /**
     * @return the detailItemCountDisplay
     */
    public String getDetailItemCountDisplay() {
        return this.detailItemCountDisplay;
    }

    /**
     * @param detailItemCountDisplay
     *            the detailItemCountDisplay to set
     */
    public void setDetailItemCountDisplay(String detailItemCountDisplay) {
        this.detailItemCountDisplay = detailItemCountDisplay;
    }

    /**
     * @return the focusOnFirstHalfOfMonth
     */
    public Boolean getFocusOnFirstHalfOfMonth() {
        return this.focusOnFirstHalfOfMonth;
    }

    /**
     * @param focusOnFirstHalfOfMonth
     *            the focusOnFirstHalfOfMonth to set
     */
    public void setFocusOnFirstHalfOfMonth(Boolean focusOnFirstHalfOfMonth) {
        this.focusOnFirstHalfOfMonth = focusOnFirstHalfOfMonth;
    }
}
