package com.tfb.aibank.chl.general.qu003.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NGNQU003010Rq.java
 * 
 * <p>Description:優惠 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/14,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNQU003011Rq implements RqData {
    /**
     * 優惠活動Key
     */
    private String promotionKey;

    /**
     * 作業項目=> 1: 加收藏, 2:刪除收藏
     * */
    private int actionType;

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public String getPromotionKey() {
        return promotionKey;
    }

    public void setPromotionKey(String promotionKey) {
        this.promotionKey = promotionKey;
    }
}
