package com.tfb.aibank.chl.creditcard.ag001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCAG001020Rs.java
*
* <p>Description:預借現金密碼設定密碼設定 結果頁</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCAG001020Rs implements RsData {

    /**
     * 卡名
     */
    private String cardName;

    /** 交易時間 */
    private String hostTxTime;

    /**
     * @return the cardName
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * @param cardName
     *            the cardName to set
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * @return the hostTxTime
     */
    public String getHostTxTime() {
        return hostTxTime;
    }

    /**
     * @param hostTxTime
     *            the hostTxTime to set
     */
    public void setHostTxTime(String hostTxTime) {
        this.hostTxTime = hostTxTime;
    }

}
