/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.general.ag002.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NGNAG002011Rq.java
* 
* <p>Description:首頁牌卡設定 - 更新設定 - RQ</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNAG002011Rq implements RqData {

    /** 牌卡編號 */
    private List<Integer> cardIds;

    /**
     * @return the cardIds
     */
    public List<Integer> getCardIds() {
        return cardIds;
    }

    /**
     * @param cardIds
     *            the cardIds to set
     */
    public void setCardIds(List<Integer> cardIds) {
        this.cardIds = cardIds;
    }

}
