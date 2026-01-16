package com.tfb.aibank.chl.general.qu003.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NGNQU003040Rq.java
 * 
 * <p>Description:優惠 040 我的收藏</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/14, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNQU003040Rq implements RqData {

    private String promotionKey;

    public String getPromotionKey() {
        return promotionKey;
    }

    public void setPromotionKey(String promotionKey) {
        this.promotionKey = promotionKey;
    }
}
