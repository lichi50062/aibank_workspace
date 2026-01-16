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

import java.util.Date;

import com.ibm.tw.ibmb.annotations.FormatDate;

// @formatter:off
/**
* @(#)NCCQU010PaymentInfo.java
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
public class NCCQU010PaymentInfo {

    /** 入帳日期 */
    @FormatDate
    private Date accountingDate;
    
    /** 信用卡 */
    private String card;

    /** 卡號末4碼 */
    private String lastFourDigits;

    /** 支付方式 */
    private String payMethod;

    /** 虛擬卡末四碼 */
    private String virtualCardLast4Digits;

    /** 外幣金額 */
    private String foreignAmtDisplay;

    /**
     * @return the accountingDate
     */
    public Date getAccountingDate() {
        return accountingDate;
    }

    /**
     * @param accountingDate
     *            the accountingDate to set
     */
    public void setAccountingDate(Date accountingDate) {
        this.accountingDate = accountingDate;
    }

    /**
     * @return the card
     */
    public String getCard() {
        return this.card;
    }

    /**
     * @param card
     *            the card to set
     */
    public void setCard(String card) {
        this.card = card;
    }

    /**
     * @return the lastFourDigits
     */
    public String getLastFourDigits() {
        return this.lastFourDigits;
    }

    /**
     * @param lastFourDigits
     *            the lastFourDigits to set
     */
    public void setLastFourDigits(String lastFourDigits) {
        this.lastFourDigits = lastFourDigits;
    }

    /**
     * @return the payMethod
     */
    public String getPayMethod() {
        return this.payMethod;
    }

    /**
     * @param payMethod
     *            the payMethod to set
     */
    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    /**
     * @return the virtualCardLast4Digits
     */
    public String getVirtualCardLast4Digits() {
        return this.virtualCardLast4Digits;
    }

    /**
     * @param virtualCardLast4Digits
     *            the virtualCardLast4Digits to set
     */
    public void setVirtualCardLast4Digits(String virtualCardLast4Digits) {
        this.virtualCardLast4Digits = virtualCardLast4Digits;
    }

    /**
     * @return the foreignAmtDisplay
     */
    public String getForeignAmtDisplay() {
        return this.foreignAmtDisplay;
    }

    /**
     * @param foreignAmtDisplay
     *            the foreignAmtDisplay to set
     */
    public void setForeignAmtDisplay(String foreignAmtDisplay) {
        this.foreignAmtDisplay = foreignAmtDisplay;
    }
}
