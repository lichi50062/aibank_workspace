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
 * v1.0, 2024/08/14, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNQU003010Rq implements RqData {
    /**
     * 優惠活動
     */
    private Integer promotion;

    public Integer getPromotion() {
        return promotion;
    }

    public void setPromotion(Integer promotion) {
        this.promotion = promotion;
    }
}
