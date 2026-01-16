package com.tfb.aibank.chl.creditcard.ag001.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCAG001010Rs.java
*
* <p>Description:預借現金密碼設定密碼設定 功能首頁</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCAG001010Rs implements RsData {

    private boolean isHasPrimary;

    private boolean isHasSupp;

    /**
     * 信用卡清單
     */
    private List<NCCAG001CardInfo> cards;

    /**
     * @return the cards
     */
    public List<NCCAG001CardInfo> getCards() {
        return cards;
    }

    /**
     * @param cards
     *            the cards to set
     */
    public void setCards(List<NCCAG001CardInfo> cards) {
        this.cards = cards;
    }

    /**
     * @return the isHasPrimary
     */
    public boolean isHasPrimary() {
        return isHasPrimary;
    }

    /**
     * @param isHasPrimary
     *            the isHasPrimary to set
     */
    public void setHasPrimary(boolean isHasPrimary) {
        this.isHasPrimary = isHasPrimary;
    }

    /**
     * @return the isHasSupp
     */
    public boolean isHasSupp() {
        return isHasSupp;
    }

    /**
     * @param isHasSupp
     *            the isHasSupp to set
     */
    public void setHasSupp(boolean isHasSupp) {
        this.isHasSupp = isHasSupp;
    }

}
