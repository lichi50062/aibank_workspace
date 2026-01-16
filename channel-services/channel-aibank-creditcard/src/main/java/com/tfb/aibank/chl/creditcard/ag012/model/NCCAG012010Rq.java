package com.tfb.aibank.chl.creditcard.ag012.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCAG012010Rq.java
 * 
 * <p>Description:信用卡交易設定 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/11, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG012010Rq implements RqData {

    /** 卡片鍵值 */
    private String cardKey;

    /**
     * @return the cardKey
     */
    public String getCardKey() {
        return cardKey;
    }

    /**
     * @param cardKey
     *            the cardKey to set
     */
    public void setCardKey(String cardKey) {
        this.cardKey = cardKey;
    }

}
