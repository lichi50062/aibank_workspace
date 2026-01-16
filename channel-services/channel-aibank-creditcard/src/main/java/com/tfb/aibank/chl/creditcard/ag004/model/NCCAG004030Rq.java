package com.tfb.aibank.chl.creditcard.ag004.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NCCAG004030Rq.java
* 
* <p>Description:刷卡通知設定</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/14, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCAG004030Rq implements RqData {

    /** 鍵值 */
    private String cardKey;

    /** 開 true / 關 false */
    private boolean radio;

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

    /**
     * @return the radio
     */
    public boolean isRadio() {
        return radio;
    }

    /**
     * @param radio
     *            the radio to set
     */
    public void setRadio(boolean radio) {
        this.radio = radio;
    }

}
