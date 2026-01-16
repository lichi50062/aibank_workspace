/**
 * 
 */
package com.tfb.aibank.chl.creditcard.ag001.service;

import java.util.List;

import com.tfb.aibank.chl.model.creditcard.CreditCard;

//@formatter:off
/**
* @(#)NCCAG001CacheVo.java
*
* <p>Description:預借現金密碼設定</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/23 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCCAG001CacheVo {

    /** 有效卡片清單 */
    private List<CreditCard> cards;

    /**
     * @return the cards
     */
    public List<CreditCard> getCards() {
        return cards;
    }

    /**
     * @param cards
     *            the cards to set
     */
    public void setCards(List<CreditCard> cards) {
        this.cards = cards;
    }

}
