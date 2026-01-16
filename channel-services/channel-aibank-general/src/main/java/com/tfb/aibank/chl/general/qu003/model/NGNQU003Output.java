package com.tfb.aibank.chl.general.qu003.model;

// @formatter:off
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tfb.aibank.chl.component.userdatacache.model.FinancialMgmMemberLevel;
import com.tfb.aibank.chl.general.resource.dto.CEW306RRepeat;
import com.tfb.aibank.chl.general.resource.dto.Promotion;
import com.tfb.aibank.chl.general.resource.dto.PromotionCategory;
import com.tfb.aibank.chl.general.resource.dto.RewardsProduct;
import com.tfb.aibank.chl.model.account.TransOutAccount;

/**
 * @(#)NGNQU003Output.java
 * 
 * <p>Description:優惠 輸出專用物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/14, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NGNQU003Output {
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

    /**
     * 顯示個人點數資料
     */
    private boolean showPersonalPointData;

    /**
     * 紅利點數個人資料
     */
    private List<CEW306RRepeat> cew306RRepeats;

    /**
     * 熱門紅利兌換
     */
    private List<RewardsProduct> bonusPoints;

    /**
     * 熱門哩程兌換
     */
    private List<RewardsProduct> mileagePoints;

    /**
     * 熱門福華點兌換
     */
    private List<RewardsProduct> fuhuaPoints;

    /**
     * 台幣轉出帳號
     */
    private List<TransOutAccount> transOutAcctsTW;
    /**
     * 外幣轉出帳號
     */
    private List<TransOutAccount> transOutAcctsFR;

    /** 財管會員等級 */
    private FinancialMgmMemberLevel financialMgmMemberLevel;

    /** 任務牆Banner Level */
    private String missionBannerLevel;

    /** 任務牆 活動資格 */
    private String missionActivityQualification;

    /** 任務牆完成數量 */
    private int missionCompleteCount;

    /** 任務牆利率 */
    private BigDecimal missionRate;

    private Map<String, Integer> promotionClickCountMap;
    
    //是否為高貢獻客戶
    private boolean highContributeCust;

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

    public boolean isShowPersonalPointData() {
        return showPersonalPointData;
    }

    public void setShowPersonalPointData(boolean showPersonalPointData) {
        this.showPersonalPointData = showPersonalPointData;
    }

    public List<CEW306RRepeat> getCew306RRepeats() {
        return cew306RRepeats;
    }

    public void setCew306RRepeats(List<CEW306RRepeat> cew306RRepeats) {
        this.cew306RRepeats = cew306RRepeats;
    }

    public List<RewardsProduct> getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(List<RewardsProduct> bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public List<RewardsProduct> getMileagePoints() {
        return mileagePoints;
    }

    public void setMileagePoints(List<RewardsProduct> mileagePoints) {
        this.mileagePoints = mileagePoints;
    }

    public List<RewardsProduct> getFuhuaPoints() {
        return fuhuaPoints;
    }

    public void setFuhuaPoints(List<RewardsProduct> fuhuaPoints) {
        this.fuhuaPoints = fuhuaPoints;
    }

    public List<TransOutAccount> getTransOutAcctsFR() {
        return transOutAcctsFR;
    }

    public void setTransOutAcctsFR(List<TransOutAccount> transOutAcctsFR) {
        this.transOutAcctsFR = transOutAcctsFR;
    }

    public List<TransOutAccount> getTransOutAcctsTW() {
        return transOutAcctsTW;
    }

    public void setTransOutAcctsTW(List<TransOutAccount> transOutAcctsTW) {
        this.transOutAcctsTW = transOutAcctsTW;
    }

    public FinancialMgmMemberLevel getFinancialMgmMemberLevel() {
        return financialMgmMemberLevel;
    }

    public void setFinancialMgmMemberLevel(FinancialMgmMemberLevel financialMgmMemberLevel) {
        this.financialMgmMemberLevel = financialMgmMemberLevel;
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

    public Map<String, Integer> getPromotionClickCountMap() {
        return promotionClickCountMap;
    }

    public void setPromotionClickCountMap(Map<String, Integer> promotionClickCountMap) {
        this.promotionClickCountMap = promotionClickCountMap;
    }

    public boolean isHighContributeCust() {
        return highContributeCust;
    }

    public void setHighContributeCust(boolean highContributeCust) {
        this.highContributeCust = highContributeCust;
    }
}
