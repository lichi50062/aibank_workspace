/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.creditcard.qu009.model;

import java.math.BigDecimal;

// @formatter:off
/**
 * @(#)NCCQU009PurchaseDetail.java
 * 
 * <p>Description:消費明細</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/19, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU009PurchaseDetail {

    /** 消費日期 MM/DD */
    private String purchaseDate;

    /** 消費說明 */
    private String purchaseDesc;

    /** 消費金額 */
    private String purchaseAmt;

    /** 消費金額 */
    private BigDecimal purchaseOriginAmt;

    /** 請款日 YYYY/MM/DD */
    private String postDate;

    /** 卡號 */
    private String cardNo;

    /** 卡號隱碼 */
    private String cardDisplay;

    /** 卡名 */
    private String cardName;

    /** 支付方式 */
    private String payType;

    /** 虛擬卡號末四碼 */
    private String vCardNo;

    /** 外幣金額/幣別 {正負號}+{幣別名稱}+<空白>+{外幣金額} */
    private String curAmt;

    /** 海外消費 */
    private Boolean isFxCur;

    /** 行動支付 */
    private Boolean isMobliePay;

    /**
     * @return the purchaseDate
     */
    public String getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * @param purchaseDate
     *            the purchaseDate to set
     */
    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * @return the purchaseDesc
     */
    public String getPurchaseDesc() {
        return purchaseDesc;
    }

    /**
     * @param purchaseDesc
     *            the purchaseDesc to set
     */
    public void setPurchaseDesc(String purchaseDesc) {
        this.purchaseDesc = purchaseDesc;
    }

    /**
     * @return the purchaseAmt
     */
    public String getPurchaseAmt() {
        return purchaseAmt;
    }

    /**
     * @param purchaseAmt
     *            the purchaseAmt to set
     */
    public void setPurchaseAmt(String purchaseAmt) {
        this.purchaseAmt = purchaseAmt;
    }

    /**
     * @return the postDate
     */
    public String getPostDate() {
        return postDate;
    }

    /**
     * @param postDate
     *            the postDate to set
     */
    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    /**
     * @return the cardNo
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * @param cardNo
     *            the cardNo to set
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * @return the cardDisplay
     */
    public String getCardDisplay() {
        return cardDisplay;
    }

    /**
     * @param cardDisplay
     *            the cardDisplay to set
     */
    public void setCardDisplay(String cardDisplay) {
        this.cardDisplay = cardDisplay;
    }

    /**
     * @return the cardName
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * @param cardName
     *            the cardName to set
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * @return the payType
     */
    public String getPayType() {
        return payType;
    }

    /**
     * @param payType
     *            the payType to set
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * @return the vCardNo
     */
    public String getvCardNo() {
        return vCardNo;
    }

    /**
     * @param vCardNo
     *            the vCardNo to set
     */
    public void setvCardNo(String vCardNo) {
        this.vCardNo = vCardNo;
    }

    /**
     * @return the curAmt
     */
    public String getCurAmt() {
        return curAmt;
    }

    /**
     * @param curAmt
     *            the curAmt to set
     */
    public void setCurAmt(String curAmt) {
        this.curAmt = curAmt;
    }

    /**
     * @return the isFxCur
     */
    public Boolean getIsFxCur() {
        return isFxCur;
    }

    /**
     * @param isFxCur
     *            the isFxCur to set
     */
    public void setIsFxCur(Boolean isFxCur) {
        this.isFxCur = isFxCur;
    }

    /**
     * @return the isMobliePay
     */
    public Boolean getIsMobliePay() {
        return isMobliePay;
    }

    /**
     * @param isMobliePay
     *            the isMobliePay to set
     */
    public void setIsMobliePay(Boolean isMobliePay) {
        this.isMobliePay = isMobliePay;
    }

    /**
     * @return the purchaseOriginAmt
     */
    public BigDecimal getPurchaseOriginAmt() {
        return purchaseOriginAmt;
    }

    /**
     * @param purchaseOriginAmt
     *            the purchaseOriginAmt to set
     */
    public void setPurchaseOriginAmt(BigDecimal purchaseOriginAmt) {
        this.purchaseOriginAmt = purchaseOriginAmt;
    }

}
