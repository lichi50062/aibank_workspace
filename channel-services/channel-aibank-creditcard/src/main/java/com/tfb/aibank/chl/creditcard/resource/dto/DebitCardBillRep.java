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
package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;
import java.util.Date;

// @formatter:off
/**
 * @(#)DebitCardBillRep.java
 * 
 * <p>Description:查詢簽帳金融卡帳單明細/p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/21, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class DebitCardBillRep {
    /** 消費日期 CclAr1 */
    private Date purchaseDate;

    /** 消費說明 CclAr2 */
    private String purchaseDesc;

    /** 請款日 CclAr3 */
    private Date postDate;

    /** 幣別 CclAr4 */
    private String cur;

    /** 外幣折算日 CclAr5 */
    private Date fxCurCalDate;

    /** 外幣金額 CclAr6 */
    private BigDecimal curAmt;

    /** 台幣金額 CclAr7 */
    private BigDecimal twdAmt;

    /** 卡號 */
    private String cardNo;

    /** 正負號 CclAr8 */
    private String sign;

    /** 虛擬卡類別 CclTOKNTYP */
    private String vCardType;

    /** 虛擬卡號 CclTOKN */
    private String vCardNo;

    /** 不足扣 */
    private String underWithHolding;

    /**
     * @return the purchaseDate
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * @param purchaseDate
     *            the purchaseDate to set
     */
    public void setPurchaseDate(Date purchaseDate) {
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
     * @return the postDate
     */
    public Date getPostDate() {
        return postDate;
    }

    /**
     * @param postDate
     *            the postDate to set
     */
    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    /**
     * @return the cur
     */
    public String getCur() {
        return cur;
    }

    /**
     * @param cur
     *            the cur to set
     */
    public void setCur(String cur) {
        this.cur = cur;
    }

    /**
     * @return the fxCurCalDate
     */
    public Date getFxCurCalDate() {
        return fxCurCalDate;
    }

    /**
     * @param fxCurCalDate
     *            the fxCurCalDate to set
     */
    public void setFxCurCalDate(Date fxCurCalDate) {
        this.fxCurCalDate = fxCurCalDate;
    }

    /**
     * @return the curAmt
     */
    public BigDecimal getCurAmt() {
        return curAmt;
    }

    /**
     * @param curAmt
     *            the curAmt to set
     */
    public void setCurAmt(BigDecimal curAmt) {
        this.curAmt = curAmt;
    }

    /**
     * @return the twdAmt
     */
    public BigDecimal getTwdAmt() {
        return twdAmt;
    }

    /**
     * @param twdAmt
     *            the twdAmt to set
     */
    public void setTwdAmt(BigDecimal twdAmt) {
        this.twdAmt = twdAmt;
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
     * @return the sign
     */
    public String getSign() {
        return sign;
    }

    /**
     * @param sign
     *            the sign to set
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * @return the vCardType
     */
    public String getvCardType() {
        return vCardType;
    }

    /**
     * @param vCardType
     *            the vCardType to set
     */
    public void setvCardType(String vCardType) {
        this.vCardType = vCardType;
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
     * @return the underWithHolding
     */
    public String getUnderWithHolding() {
        return underWithHolding;
    }

    /**
     * @param underWithHolding
     *            the underWithHolding to set
     */
    public void setUnderWithHolding(String underWithHolding) {
        this.underWithHolding = underWithHolding;
    }

}
