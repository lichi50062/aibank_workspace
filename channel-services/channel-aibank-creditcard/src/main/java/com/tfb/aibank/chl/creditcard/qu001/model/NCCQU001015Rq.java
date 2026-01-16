package com.tfb.aibank.chl.creditcard.qu001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NCCQU001012Rq.java
* 
* <p>Description:信用卡總覽 功能首頁 取得本期未出帳消費明細 RQ</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/08, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCQU001015Rq implements RqData {
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
