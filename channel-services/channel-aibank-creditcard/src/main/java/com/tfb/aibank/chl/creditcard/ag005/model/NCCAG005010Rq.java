package com.tfb.aibank.chl.creditcard.ag005.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCAG005010Rq.java
 * 
 * <p>Description:保費權益設定 010 保費權益信用卡清單</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/28, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG005010Rq implements RqData {

    /** 信用卡識別碼(唯一值) */
    private String cardKey;

    /** 前次完成設定的信用卡鍵值 */
    private String selectedCardKey;

    public String getCardKey() {
        return cardKey;
    }

    public void setCardKey(String cardKey) {
        this.cardKey = cardKey;
    }

    public String getSelectedCardKey() {
        return selectedCardKey;
    }

    public void setSelectedCardKey(String selectedCardKey) {
        this.selectedCardKey = selectedCardKey;
    }

}
