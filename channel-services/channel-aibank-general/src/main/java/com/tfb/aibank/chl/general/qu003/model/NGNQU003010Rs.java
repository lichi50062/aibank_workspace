package com.tfb.aibank.chl.general.qu003.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.chl.general.resource.dto.Promotion;
import com.tfb.aibank.chl.general.resource.dto.PromotionCategory;

// @formatter:off
/**
 * @(#)NGNQU003010Rs.java
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
public class NGNQU003010Rs implements RsData {
    /**
     * 優惠活動清單
     */
    private List<Promotion> promotions;

    /**
     * 優惠活動分類
     */
    private List<PromotionCategory> promotionCategories;

    /**
     * 您可能會喜歡區塊(banner)
     */
    private List<Promotion> banners;

    /** 信用卡會員登入 */
    private boolean ccMemberLogin;

    /** 任務牆Banner Level */
    private String missionBannerLevel;

    /** 任務牆 活動資格 */
    private String missionActivityQualification;

    /** 任務牆完成數量 */
    private int missionCompleteCount;

    /** 任務牆利率 */
    private BigDecimal missionRate;

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

    public String getMissionActivityQualification() {
        return missionActivityQualification;
    }

    public void setMissionActivityQualification(String missionActivityQualification) {
        this.missionActivityQualification = missionActivityQualification;
    }

    public String getMissionBannerLevel() {
        return missionBannerLevel;
    }

    public void setMissionBannerLevel(String missionBannerLevel) {
        this.missionBannerLevel = missionBannerLevel;
    }

    public int getMissionCompleteCount() {
        return missionCompleteCount;
    }

    public void setMissionCompleteCount(int missionCompleteCount) {
        this.missionCompleteCount = missionCompleteCount;
    }

    public BigDecimal getMissionRate() {
        return missionRate;
    }

    public void setMissionRate(BigDecimal missionRate) {
        this.missionRate = missionRate;
    }

    public boolean isCcMemberLogin() {
        return ccMemberLogin;
    }

    public void setCcMemberLogin(boolean ccMemberLogin) {
        this.ccMemberLogin = ccMemberLogin;
    }
}
