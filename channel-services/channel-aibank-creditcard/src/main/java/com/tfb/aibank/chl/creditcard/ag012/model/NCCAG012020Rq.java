package com.tfb.aibank.chl.creditcard.ag012.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCAG012020Rq.java
 * 
 * <p>Description:信用卡交易設定 020 設定頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/11, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG012020Rq implements RqData {
    /** 卡片資訊 */
    private NCCAG012CardInfo cardInfo;

    /**
     * @return the cardInfo
     */
    public NCCAG012CardInfo getCardInfo() {
        return cardInfo;
    }

    /**
     * @param cardInfo
     *            the cardInfo to set
     */
    public void setCardInfo(NCCAG012CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

}
