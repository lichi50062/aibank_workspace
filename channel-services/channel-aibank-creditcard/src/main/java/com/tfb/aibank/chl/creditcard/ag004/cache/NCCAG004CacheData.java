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
package com.tfb.aibank.chl.creditcard.ag004.cache;

import java.util.List;

import com.tfb.aibank.chl.creditcard.ag004.model.NCCAG004Repeat;
import com.tfb.aibank.chl.model.creditcard.CreditCard;

// @formatter:off
/**
 * @(#)NCCAG004CacheData.java
 * 
 * <p>Description:刷卡通知設定</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/14, John Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCAG004CacheData {

    /** 初始email */
    private String email;

    /** 卡號 */
    private List<CreditCard> allCreditCards;

    /** 卡號 */
    private List<NCCAG004Repeat> cardList;

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the allCreditCards
     */
    public List<CreditCard> getAllCreditCards() {
        return allCreditCards;
    }

    /**
     * @param allCreditCards
     *            the allCreditCards to set
     */
    public void setAllCreditCards(List<CreditCard> allCreditCards) {
        this.allCreditCards = allCreditCards;
    }

    /**
     * @return the cardList
     */
    public List<NCCAG004Repeat> getCardList() {
        return cardList;
    }

    /**
     * @param cardList
     *            the cardList to set
     */
    public void setCardList(List<NCCAG004Repeat> cardList) {
        this.cardList = cardList;
    }

}
