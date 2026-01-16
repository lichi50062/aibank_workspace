package com.tfb.aibank.chl.creditcard.qu001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCQU001021Rs.java
* 
* <p>Description:信用卡總覽 卡片管理頁 更新卡片暱稱 RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, Alex PY Lis
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCQU001024Rs implements RsData {
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
