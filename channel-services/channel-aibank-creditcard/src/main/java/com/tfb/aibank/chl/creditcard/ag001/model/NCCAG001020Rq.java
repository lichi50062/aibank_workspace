package com.tfb.aibank.chl.creditcard.ag001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NCCAG001020Rq.java
*
* <p>Description:預借現金密碼設定 結果頁</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCAG001020Rq implements RqData {

    /**
     * 卡片Index
     */
    private String cardKey;

    /** cvv */
    private String cvv;

    /** 密碼 */
    private String pinBlock;

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
     * @return the cvv
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * @param cvv
     *            the cvv to set
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    /**
     * @return the pinBlock
     */
    public String getPinBlock() {
        return pinBlock;
    }

    /**
     * @param pinBlock
     *            the pinBlock to set
     */
    public void setPinBlock(String pinBlock) {
        this.pinBlock = pinBlock;
    }

}
