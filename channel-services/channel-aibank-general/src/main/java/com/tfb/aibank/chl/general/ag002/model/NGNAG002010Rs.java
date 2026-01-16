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
package com.tfb.aibank.chl.general.ag002.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NGNAG002010Rs.java
* 
* <p>Description:首頁牌卡設定 - 設定頁 - RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNAG002010Rs implements RsData {

    /** 已設定牌卡 */
    private List<NGNAG002CardData> cards;

    /** 未設定牌卡 */
    private List<NGNAG002CardData> otherCards;

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

}
