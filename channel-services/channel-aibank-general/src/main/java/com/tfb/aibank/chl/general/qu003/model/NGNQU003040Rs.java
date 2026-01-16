package com.tfb.aibank.chl.general.qu003.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.chl.general.resource.dto.Promotion;

// @formatter:off
/**
 * @(#)NGNQU003040Rs.java
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
public class NGNQU003040Rs implements RsData {

    /**
     * 優惠活動清單
     */
    private List<Promotion> promotions;

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }
}
