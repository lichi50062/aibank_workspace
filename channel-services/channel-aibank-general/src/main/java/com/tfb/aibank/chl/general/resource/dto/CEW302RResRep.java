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
package com.tfb.aibank.chl.general.resource.dto;

import java.math.BigDecimal;

// @formatter:off
/**
 * @(#)CEW302RResRep.java
 * 
 * <p>Description:CEW302R 信用卡卡片總覽</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/29, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CEW302RResRep {

    /** 卡號 */
    private String cardNo;

    /** 正附卡別 */
    private String cardRelationship;

    /** 到期日 */
    private String expireDay;

    /** 國內預借現金可用餘額 */
    private BigDecimal domesticCashAdvanceBalanceAvailable;

    /** 卡片狀態 */
    private String cardStatus;

    /** 卡片種類 */
    private String cardType;

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
     * @return the cardRelationship
     */
    public String getCardRelationship() {
        return cardRelationship;
    }

    /**
     * @param cardRelationship
     *            the cardRelationship to set
     */
    public void setCardRelationship(String cardRelationship) {
        this.cardRelationship = cardRelationship;
    }

    /**
     * @return the expireDay
     */
    public String getExpireDay() {
        return expireDay;
    }

    /**
     * @param expireDay
     *            the expireDay to set
     */
    public void setExpireDay(String expireDay) {
        this.expireDay = expireDay;
    }

    /**
     * @return the domesticCashAdvanceBalanceAvailable
     */
    public BigDecimal getDomesticCashAdvanceBalanceAvailable() {
        return domesticCashAdvanceBalanceAvailable;
    }

    /**
     * @param domesticCashAdvanceBalanceAvailable
     *            the domesticCashAdvanceBalanceAvailable to set
     */
    public void setDomesticCashAdvanceBalanceAvailable(BigDecimal domesticCashAdvanceBalanceAvailable) {
        this.domesticCashAdvanceBalanceAvailable = domesticCashAdvanceBalanceAvailable;
    }

    /**
     * @return the cardStatus
     */
    public String getCardStatus() {
        return cardStatus;
    }

    /**
     * @param cardStatus
     *            the cardStatus to set
     */
    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    /**
     * @return the cardType
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * @param cardType
     *            the cardType to set
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

}
