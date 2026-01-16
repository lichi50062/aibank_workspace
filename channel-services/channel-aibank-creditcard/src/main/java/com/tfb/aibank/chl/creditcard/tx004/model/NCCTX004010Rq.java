package com.tfb.aibank.chl.creditcard.tx004.model;

import com.ibm.tw.ibmb.base.model.RqData;
import org.springframework.stereotype.Component;

//@formatter:off
/**
* @(#)NCCQU014010Rq.java
* 
* <p>Description:NCCTX004_道路救援登錄 RQ</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/24
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCTX004010Rq implements RqData {
    /**
     * 進入功能時外部傳入的卡號
     */
    private String cardKey;

    public String getCardKey() {
        return cardKey;
    }

    public void setCardKey(String cardKey) {
        this.cardKey = cardKey;
    }
}
