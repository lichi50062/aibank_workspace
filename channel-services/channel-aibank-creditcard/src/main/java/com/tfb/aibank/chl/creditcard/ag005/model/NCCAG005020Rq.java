package com.tfb.aibank.chl.creditcard.ag005.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCAG005020Rq.java
 * 
 * <p>Description:保費權益設定 020 設定編輯頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/28, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG005020Rq implements RqData {

    /** 選擇的卡片索引值 */
    private int cardIdx;

    public int getCardIdx() {
        return cardIdx;
    }

    public void setCardIdx(int cardIdx) {
        this.cardIdx = cardIdx;
    }

}
