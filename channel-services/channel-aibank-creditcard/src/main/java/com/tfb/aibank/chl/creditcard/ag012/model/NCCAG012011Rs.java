package com.tfb.aibank.chl.creditcard.ag012.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCAG012020Rs.java
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
public class NCCAG012011Rs implements RsData {

    /** 卡片資訊集 */
    private List<NCCAG012CardCollect> cardCollects;

    /**
     * @return the cardCollects
     */
    public List<NCCAG012CardCollect> getCardCollects() {
        return cardCollects;
    }

    /**
     * @param cardCollects
     *            the cardCollects to set
     */
    public void setCardCollects(List<NCCAG012CardCollect> cardCollects) {
        this.cardCollects = cardCollects;
    }

}
