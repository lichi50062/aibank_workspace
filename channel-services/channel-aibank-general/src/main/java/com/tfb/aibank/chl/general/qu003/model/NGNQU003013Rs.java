package com.tfb.aibank.chl.general.qu003.model;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NGNQU003012Rs.java
 * 
 * <p>Description:優惠 012 點擊數</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/14
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNQU003013Rs implements RsData {

    private Map<String, Integer> promotionClickCountMap;

    public Map<String, Integer> getPromotionClickCountMap() {
        return promotionClickCountMap;
    }

    public void setPromotionClickCountMap(Map<String, Integer> promotionClickCountMap) {
        this.promotionClickCountMap = promotionClickCountMap;
    }
}
