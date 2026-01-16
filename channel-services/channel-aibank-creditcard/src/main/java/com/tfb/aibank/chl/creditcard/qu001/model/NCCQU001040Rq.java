package com.tfb.aibank.chl.creditcard.qu001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NCCQU001040Rq.java
* 
* <p>Description:信用卡總覽 歷史帳單頁 RQ</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCQU001040Rq implements RqData {
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
