package com.tfb.aibank.chl.creditcard.ag008.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCAG008010Rq.java
 * 
 * <p>Description:信用卡開卡 首頁 RS</p>
 * 
 * <p>Modify History:</p>
 * <ol>
 * v1.0, 2023/05/22, Evan Wang
 *  <li>初版</li>
 * </ol>
 *
 */
//@formatter:on

@Component
public class NCCAG008010Rq implements RqData {

    /** 識別資訊(唯一值) */
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
