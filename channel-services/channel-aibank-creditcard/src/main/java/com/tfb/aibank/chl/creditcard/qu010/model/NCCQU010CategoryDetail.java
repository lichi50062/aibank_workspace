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

// @formatter:off
/**
* @(#)NCCQU010CategoryDetail.java
* 
* <p>Description:消費分析 OAuth API 各類別消費明細查詢 Response</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/06/28 Warren Lin
* <ol>
*  <li>初版</li>
* </ol>
*/
// @formatter:on
public class NCCQU010CategoryDetail {

    /** 是否有後續資料 */
    private boolean hasNextPage;

    /** 消費日期 */
    private String date;

    /** 入帳日期 */
    private String accountingDate;

    /** 商店名稱 */
    private String merchant;

    /** 消費金額 */
    private String txnNtdAmt;

    /** 卡別名稱 */
    private String card;

    /** 卡號末4碼 */
    private String lastFourDigits;

    /** 消費方式 */
    private String payChannel;

    /** 虛擬卡號末4碼 */
    private String vDigits;

    /** 外幣金額 */
    private String txnAmt;

    /** 幣別 */
    private String txnCurrency;

    /**
     * @return the hasNextPage
     */
    public boolean getHasNextPage() {
        return hasNextPage;
    }

    /**
     * @param hasNextPage
     *            the hasNextPage to set
     */
    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return this.date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the accountingDate
     */
    public String getAccountingDate() {
        return this.accountingDate;
    }

    /**
     * @param accountingDate
     *            the accountingDate to set
     */
    public void setAccountingDate(String accountingDate) {
        this.accountingDate = accountingDate;
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
    public String getTxnNtdAmt() {
        return this.txnNtdAmt;
    }

    /**
     * @param txnNtdAmt
     *            the txnNtdAmt to set
     */
    public void setTxnNtdAmt(String txnNtdAmt) {
        this.txnNtdAmt = txnNtdAmt;
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
     * @return the payChannel
     */
    public String getPayChannel() {
        return this.payChannel;
    }

    /**
     * @param payChannel
     *            the payChannel to set
     */
    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    /**
     * @return the vDigits
     */
    public String getvDigits() {
        return this.vDigits;
    }

    /**
     * @param vDigits
     *            the vDigits to set
     */
    public void setvDigits(String vDigits) {
        this.vDigits = vDigits;
    }

    /**
     * @return the txnAmt
     */
    public String getTxnAmt() {
        return this.txnAmt;
    }

    /**
     * @param txnAmt
     *            the txnAmt to set
     */
    public void setTxnAmt(String txnAmt) {
        this.txnAmt = txnAmt;
    }

    /**
     * @return the txnCurrency
     */
    public String getTxnCurrency() {
        return this.txnCurrency;
    }

    /**
     * @param txnCurrency
     *            the txnCurrency to set
     */
    public void setTxnCurrency(String txnCurrency) {
        this.txnCurrency = txnCurrency;
    }
}
