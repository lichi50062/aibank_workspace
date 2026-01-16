package com.tfb.aibank.chl.creditcard.ag007.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCAG007020Rq.java
 * 
 * <p>Description:一鍵綁定行動支付 020 判斷信用卡卡號是否可綁定</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/12, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG007020Rq implements RqData {

    /** 信用卡卡號 Key */
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
