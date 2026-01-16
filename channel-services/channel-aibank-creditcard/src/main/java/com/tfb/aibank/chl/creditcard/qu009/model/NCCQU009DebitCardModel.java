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
 * @(#)NCCQU009DebitCardModel.java
 * 
 * <p>Description:[簽帳卡資料]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/19, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU009DebitCardModel {
    /** 卡名 */
    private String cardName;
    /** 顯示卡號 */
    private String displayCardNo;
    /** 卡號 所有卡片時為空 */
    private String cardNo;
    /** 合計消費 */
    private String totalExpenditure;
    /** 平均消費 */
    private String averageExpenditure;

    /** 平均消費 */
    private BigDecimal averageExpenditureOrigin;
    /** 卡面 */
    private String cardPicPath;

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
     * @return the displayCardNo
     */
    public String getDisplayCardNo() {
        return displayCardNo;
    }

    /**
     * @param displayCardNo
     *            the displayCardNo to set
     */
    public void setDisplayCardNo(String displayCardNo) {
        this.displayCardNo = displayCardNo;
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
     * @return the totalExpenditure
     */
    public String getTotalExpenditure() {
        return totalExpenditure;
    }

    /**
     * @param totalExpenditure
     *            the totalExpenditure to set
     */
    public void setTotalExpenditure(String totalExpenditure) {
        this.totalExpenditure = totalExpenditure;
    }

    /**
     * @return the averageExpenditure
     */
    public String getAverageExpenditure() {
        return averageExpenditure;
    }

    /**
     * @param averageExpenditure
     *            the averageExpenditure to set
     */
    public void setAverageExpenditure(String averageExpenditure) {
        this.averageExpenditure = averageExpenditure;
    }

    /**
     * @return the averageExpenditureOrigin
     */
    public BigDecimal getAverageExpenditureOrigin() {
        return averageExpenditureOrigin;
    }

    /**
     * @param averageExpenditureOrigin
     *            the averageExpenditureOrigin to set
     */
    public void setAverageExpenditureOrigin(BigDecimal averageExpenditureOrigin) {
        this.averageExpenditureOrigin = averageExpenditureOrigin;
    }

    /**
     * @return the cardPicPath
     */
    public String getCardPicPath() {
        return cardPicPath;
    }

    /**
     * @param cardPicPath
     *            the cardPicPath to set
     */
    public void setCardPicPath(String cardPicPath) {
        this.cardPicPath = cardPicPath;
    }

}
