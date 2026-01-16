/*
 * ===========================================================================
 *
 * IBM Confidential
 *
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.general.resource.dto;

import java.util.List;

// @formatter:off
/**
 * @(#)Promotion.java
 * 
 * <p>Description:[優惠活動]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/14, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class PromotionListResponse {
    /**
     * 優惠活動清單
     */
    private List<Promotion> promotions;

    /**
     * 您可能會喜歡區塊(banner)
     */
    private List<Promotion> banners;

    /**
     * 優惠活動分類
     */
    private List<PromotionCategory> promotionCategories;

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public List<PromotionCategory> getPromotionCategories() {
        return promotionCategories;
    }

    public void setPromotionCategories(List<PromotionCategory> promotionCategories) {
        this.promotionCategories = promotionCategories;
    }

    public List<Promotion> getBanners() {
        return banners;
    }

    public void setBanners(List<Promotion> banners) {
        this.banners = banners;
    }
}
