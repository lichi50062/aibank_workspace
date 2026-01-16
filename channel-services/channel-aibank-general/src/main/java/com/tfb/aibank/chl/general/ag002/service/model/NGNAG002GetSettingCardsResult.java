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
package com.tfb.aibank.chl.general.ag002.service.model;

import java.util.List;

import com.tfb.aibank.chl.general.ag002.model.NGNAG002CardData;

// @formatter:off
/**
 * @(#)NGNAG002GetSettingCardsResult.java
 * 
 * <p>Description:取得畫面牌卡設定資料 - Result</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NGNAG002GetSettingCardsResult {

    /** 已設定牌卡 */
    private List<NGNAG002CardData> cards;

    /** 其他牌卡 */
    private List<NGNAG002CardData> otherCards;

    /** 是否為未登入但有綁定免登速查 */
    private boolean isNotLoginBind;

    /**
     * @return the cards
     */
    public List<NGNAG002CardData> getCards() {
        return cards;
    }

    /**
     * @param cards
     *            the cards to set
     */
    public void setCards(List<NGNAG002CardData> cards) {
        this.cards = cards;
    }

    /**
     * @return the otherCards
     */
    public List<NGNAG002CardData> getOtherCards() {
        return otherCards;
    }

    /**
     * @param otherCards
     *            the otherCards to set
     */
    public void setOtherCards(List<NGNAG002CardData> otherCards) {
        this.otherCards = otherCards;
    }

    /**
     * @return the isNotLoginBind
     */
    public boolean isNotLoginBind() {
        return isNotLoginBind;
    }

    /**
     * @param isNotLoginBind
     *            the isNotLoginBind to set
     */
    public void setNotLoginBind(boolean isNotLoginBind) {
        this.isNotLoginBind = isNotLoginBind;
    }

}
