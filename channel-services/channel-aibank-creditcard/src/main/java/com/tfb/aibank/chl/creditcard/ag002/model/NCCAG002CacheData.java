package com.tfb.aibank.chl.creditcard.ag002.model;

import com.tfb.aibank.chl.creditcard.resource.dto.RecommendInfo;

import java.io.Serializable;

// @formatter:off
/**
 * @(#)NPYTX001CacheData.java
 *
 * <p>Description:信用卡電子帳單設定 Cache 物件</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2025/07/15, Peter
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCAG002CacheData implements Serializable {

    private static final long serialVersionUID = -2319589902129778482L;

    RecommendInfo recommendInfo;

    public RecommendInfo getRecommendInfo() {
        return recommendInfo;
    }

    public void setRecommendInfo(RecommendInfo recommendInfo) {
        this.recommendInfo = recommendInfo;
    }
}
