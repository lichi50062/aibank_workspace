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

// @formatter:off
/**
* @(#)NCCQU010AnnualDetailData.java
* 
* <p>Description:消費分析 OAuth API 整年消費明細搜尋 Response</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/06/28 Warren Lin
* <ol>
*  <li>初版</li>
* </ol>
*/
// @formatter:on
public class NCCQU010AnnualDetailData {

    /** 顯示交易日期(yyyy/MM/dd) */
    private Date txDate;
    
    /** 顯示交易日期(MM/dd) */
    private String txDateDisplay;

    /** 顯示交易日期(yyyy/MM) */
    private String txDateYYYYMM;

    /** 月份數字 */
    private String monthNum;

    /** 顯示月份 */
    private String monthDisplay;

    /** 交易年 */
    private String txYear;

    /** 顯示項目名稱 */
    private String itemName;

    /** 消費金額 */
    private BigDecimal txnNtdAmt;

    /** 顯示金額 */
    private String txAmtDisplay;

    /** 信用卡支付資訊 */
    private NCCQU010PaymentInfo paymentInfo;
    
    /**
     * @return the txDate
     */
    public Date getTxDate() {
        return txDate;
    }

    /**
     * Set the txDate
     *
     * @param txDate
     */
    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    /**
     * @return the txDateDisplay
     */
    public String getTxDateDisplay() {
        return txDateDisplay;
    }

    /**
     * Set the txDateDisplay
     *
     * @param txDateDisplay
     */
    public void setTxDateDisplay(String txDateDisplay) {
        this.txDateDisplay = txDateDisplay;
    }

    /**
     * @return the txDateYYYYMM
     */
    public String getTxDateYYYYMM() {
        return txDateYYYYMM;
    }

    /**
     * Set the txDateYYYYMM
     *
     * @param txDateYYYYMM
     */
    public void setTxDateYYYYMM(String txDateYYYYMM) {
        this.txDateYYYYMM = txDateYYYYMM;
    }

    /**
     * @return the txYear
     */
    public String getTxYear() {
        return txYear;
    }

    /**
     * Set the txYear
     *
     * @param txYear
     */
    public void setTxYear(String txYear) {
        this.txYear = txYear;
    }

    /**
     * @return the monthDisplay
     */
    public String getMonthDisplay() {
        return monthDisplay;
    }

    /**
     * Set the monthDisplay
     *
     * @param monthDisplay
     */
    public void setMonthDisplay(String monthDisplay) {
        this.monthDisplay = monthDisplay;
    }

    /**
     * @return the monthNum
     */
    public String getMonthNum() {
        return monthNum;
    }

    /**
     * Set the monthNum
     *
     * @param monthNum
     */
    public void setMonthNum(String monthNum) {
        this.monthNum = monthNum;
    }

    /**
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Set the itemName
     *
     * @param itemName
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    /**
     * @return the txnNtdAmt
     */
    public BigDecimal getTxnNtdAmt() {
        return txnNtdAmt;
    }

    /**
     * Set the txnNtdAmt
     *
     * @param txnNtdAmt
     */
    public void setTxnNtdAmt(BigDecimal txnNtdAmt) {
        this.txnNtdAmt = txnNtdAmt;
    }

    /**
     * @return the txAmtDisplay
     */
    public String getTxAmtDisplay() {
        return txAmtDisplay;
    }

    /**
     * Set the txAmtDisplay
     *
     * @param txAmtDisplay
     */
    public void setTxAmtDisplay(String txAmtDisplay) {
        this.txAmtDisplay = txAmtDisplay;
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
}
