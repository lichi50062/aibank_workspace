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
package com.tfb.aibank.chl.creditcard.qu011.model;

// @formatter:off
/**
 * @(#)NCCQU011CreditCard.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/14, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU011CreditCard {

    /** 卡號 */
    private String cardNo;

    /** 卡片名稱 */
    private String cardName;

    /** 識別資訊 */
    private String cardKey;

    /** 持卡類別，分為正卡、正卡項下附卡、附卡三種 */
    private String cardHoldType;

    /** 是否為「正卡項下附卡」 */
    private boolean isSecondaryUnderPrimaryCard;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardKey() {
        return cardKey;
    }

    public void setCardKey(String cardKey) {
        this.cardKey = cardKey;
    }

    public String getCardHoldType() {
        return cardHoldType;
    }

    public void setCardHoldType(String cardHoldType) {
        this.cardHoldType = cardHoldType;
    }

    public boolean isSecondaryUnderPrimaryCard() {
        return isSecondaryUnderPrimaryCard;
    }

    public void setSecondaryUnderPrimaryCard(boolean isSecondaryUnderPrimaryCard) {
        this.isSecondaryUnderPrimaryCard = isSecondaryUnderPrimaryCard;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

}
