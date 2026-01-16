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

// @formatter:off
/**
* @(#)NCCQU010CategoryRatioData.java
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
public class NCCQU010CategoryRatioData {

    /** 類別名稱圖示 */
    private String itemImgUrl;

    /** 項目類別 */
    private String itemCategory;

    /** 項目名稱 */
    private String itemDesc;

    /** 項目類別預設順序 */
    private int itemCategoryIndex;

    /** 項目佔比 */
    private String ratioDisplay;

    /** 項目佔比 */
    private BigDecimal ratio;

    /** 消費金額 */
    private String txnAmtDisplay;

    /** 消費金額 */
    private BigDecimal txnAmt;

    /** 與平均比值 */
    private String averageRatio;

    /** 與去年比值 */
    private String lastYearRatio;

    /**
     * @return the itemImgUrl
     */
    public String getItemImgUrl() {
        return itemImgUrl;
    }

    /**
     * @param itemImgUrl
     *            the itemImgUrl to set
     */
    public void setItemImgUrl(String itemImgUrl) {
        this.itemImgUrl = itemImgUrl;
    }

    /**
     * @return the itemCategory
     */
    public String getItemCategory() {
        return itemCategory;
    }

    /**
     * @param itemCategory
     *            the itemCategory to set
     */
    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    /**
     * @return the itemDesc
     */
    public String getItemDesc() {
        return itemDesc;
    }

    /**
     * @param itemDesc
     *            the itemDesc to set
     */
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    /**
     * @return the itemCategoryIndex
     */
    public int getItemCategoryIndex() {
        return itemCategoryIndex;
    }

    /**
     * @param itemCategoryIndex
     *            the itemCategoryIndex to set
     */
    public void setItemCategoryIndex(int itemCategoryIndex) {
        this.itemCategoryIndex = itemCategoryIndex;
    }

    /**
     * @return the ratioDisplay
     */
    public String getRatioDisplay() {
        return this.ratioDisplay;
    }

    /**
     * @param ratioDisplay
     *            the ratioDisplay to set
     */
    public void setRatioDisplay(String ratioDisplay) {
        this.ratioDisplay = ratioDisplay;
    }

    /**
     * @return the ratio
     */
    public BigDecimal getRatio() {
        return this.ratio;
    }

    /**
     * @param ratio
     *            the ratio to set
     */
    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
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

    /**
     * @return the averageRatio
     */
    public String getAverageRatio() {
        return averageRatio;
    }

    /**
     * @param averageRatio
     *            the averageRatio to set
     */
    public void setAverageRatio(String averageRatio) {
        this.averageRatio = averageRatio;
    }

    /**
     * @return the lastYearRatio
     */
    public String getLastYearRatio() {
        return lastYearRatio;
    }

    /**
     * @param lastYearRatio
     *            the lastYearRatio to set
     */
    public void setLastYearRatio(String lastYearRatio) {
        this.lastYearRatio = lastYearRatio;
    }

}
