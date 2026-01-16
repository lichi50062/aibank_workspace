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

import java.util.Date;
import java.util.List;

import com.ibm.tw.ibmb.annotations.FormatDate;

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
public class Promotion {
    /**
     * 來自信用卡優惠網
     */
    private boolean fromCreditCard;
    /**
     * 鍵值
     */
    private String promotionKey;

    /**
     * 活動類別
     */
    private String category = "";

    /**
     * 活動標題
     */
    private String title;

    /**
     * 活動副標
     */
    private String subtitle;

    /**
     * 活動內容
     */
    private String content;

    /**
     * 活動注意事項
     */
    private String remarkContent;

    /**
     * 精選優惠圖
     */
    private String squarePicture;

    /**
     * 活動細節圖
     */
    private String bannerPicture;

    /**
     * 起始日期
     */
    private String startTime;

    /**
     * 起始日期(MillionSeconds)
     */
    private long startTimeMs;

    /**
     * 結束日期
     */
    private String endTime;

    /**
     * 結束日期(MillionSeconds)
     */
    private long endTimeMs;

    /**
     * 上架日期
     */
    private Date upTime;

    /**
     * 下架日期
     */
    private Date downTime;

    /**
     * 行事曆提醒時間
     */
    private String calenderReminder;

    /**
     * 引導按鈕1名稱
     */
    private String buttonName1;

    /**
     * 引導按鈕1連結
     */
    private String buttonLink1;

    /**
     * 引導按鈕2名稱
     */
    private String buttonName2;

    /**
     * 引導按鈕2連結
     */
    private String buttonLink2;

    /**
     * 活動標籤
     */
    private String tags = "";

    /**
     * 是否為主打活動
     */
    private Integer isBanner;

    /**
     * 適用客群
     */
    private Integer taType;

    /**
     * Unica名單檔名
     */
    private String taGroup;

    /**
     * U行銷組合代碼
     */
    private String treatmentCode;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 更新時間
     */
    private Date updateTime;

    /**
     * 是否顯示活動登錄 信用卡活動登錄
     */
    private String isRegister;

    /**
     * 是否連到專屬網址 信用卡活動登錄
     */
    private String isOtherLink;
    /**
     * 專屬網址 信用卡活動登錄
     */
    private String otherLink;

    /**
     * 是否顯示活動連結按鈕 信用卡活動登錄
     */
    private String isShowLink;

    // 延伸欄位

    /**
     * 是否收藏
     */
    private boolean isFavorite;

    /**
     * 加入蒐藏時間
     */
    private Date addfavoriteTime;

    /**
     * 點擊次數
     */
    private int count = -1;

    /**
     * 到期日
     * A.	若為信用卡優惠網資料，則取暫存的CARD_PROMOTION.END_TIME。
     * B.	若為AI BANK後台維護資料，則取PROMOTION.DOWN_TIME。
     */
    @FormatDate
    private Date finishTime;

    /**
     * 上下架Tag
     */
    private PromotionTimeTag promotionTimeTag;

    /**
     * 活動已結束
     */
    private boolean promotionEnded = false;

    /**
     * 活動結束日至系統日的日期天數，
     * 已結束的活動才會放值
     */
    private int daysFromEndTime = -1;

    /**
     * 此活動歸屬分類Keys
     */
    private List<String> categoryKeys;

    /**
     * 此活動歸屬分類
     */
    private List<PromotionCategory> categoryList;

    /**
     * 此活動歸屬分類警語
     */
    private List<String> categoryReminderList;

    /**
     * 活動是否結束排序seq
     */
    private int sortSeqPromoAlive = 0;

    /**
     * 活動「主」分類
     */
    private String categoryMain = "";

    private String shareUrl;

    public boolean isFromCreditCard() {
        return fromCreditCard;
    }

    public void setFromCreditCard(boolean fromCreditCard) {
        this.fromCreditCard = fromCreditCard;
    }

    public String getPromotionKey() {
        return promotionKey;
    }

    public void setPromotionKey(String promotionKey) {
        this.promotionKey = promotionKey;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemarkContent() {
        return remarkContent;
    }

    public void setRemarkContent(String remarkContent) {
        this.remarkContent = remarkContent;
    }

    public String getSquarePicture() {
        return squarePicture;
    }

    public void setSquarePicture(String squarePicture) {
        this.squarePicture = squarePicture;
    }

    public String getBannerPicture() {
        return bannerPicture;
    }

    public void setBannerPicture(String bannerPicture) {
        this.bannerPicture = bannerPicture;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public Date getDownTime() {
        return downTime;
    }

    public void setDownTime(Date downTime) {
        this.downTime = downTime;
    }

    public String getCalenderReminder() {
        return calenderReminder;
    }

    public void setCalenderReminder(String calenderReminder) {
        this.calenderReminder = calenderReminder;
    }

    public String getButtonName1() {
        return buttonName1;
    }

    public void setButtonName1(String buttonName1) {
        this.buttonName1 = buttonName1;
    }

    public String getButtonLink1() {
        return buttonLink1;
    }

    public void setButtonLink1(String buttonLink1) {
        this.buttonLink1 = buttonLink1;
    }

    public String getButtonName2() {
        return buttonName2;
    }

    public void setButtonName2(String buttonName2) {
        this.buttonName2 = buttonName2;
    }

    public String getButtonLink2() {
        return buttonLink2;
    }

    public void setButtonLink2(String buttonLink2) {
        this.buttonLink2 = buttonLink2;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getIsBanner() {
        return isBanner;
    }

    public void setIsBanner(Integer isBanner) {
        this.isBanner = isBanner;
    }

    public Integer getTaType() {
        return taType;
    }

    public void setTaType(Integer taType) {
        this.taType = taType;
    }

    public String getTaGroup() {
        return taGroup;
    }

    public void setTaGroup(String taGroup) {
        this.taGroup = taGroup;
    }

    public String getTreatmentCode() {
        return treatmentCode;
    }

    public void setTreatmentCode(String treatmentCode) {
        this.treatmentCode = treatmentCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsRegister() {
        return isRegister;
    }

    public void setIsRegister(String isRegister) {
        this.isRegister = isRegister;
    }

    public String getIsOtherLink() {
        return isOtherLink;
    }

    public void setIsOtherLink(String isOtherLink) {
        this.isOtherLink = isOtherLink;
    }

    public String getOtherLink() {
        return otherLink;
    }

    public void setOtherLink(String otherLink) {
        this.otherLink = otherLink;
    }

    public String getIsShowLink() {
        return isShowLink;
    }

    public void setIsShowLink(String isShowLink) {
        this.isShowLink = isShowLink;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public PromotionTimeTag getPromotionTimeTag() {
        return promotionTimeTag;
    }

    public void setPromotionTimeTag(PromotionTimeTag promotionTimeTag) {
        this.promotionTimeTag = promotionTimeTag;
    }

    public long getEndTimeMs() {
        return endTimeMs;
    }

    public void setEndTimeMs(long endTimeMs) {
        this.endTimeMs = endTimeMs;
    }

    public long getStartTimeMs() {
        return startTimeMs;
    }

    public void setStartTimeMs(long startTimeMs) {
        this.startTimeMs = startTimeMs;
    }

    public List<String> getCategoryKeys() {
        return categoryKeys;
    }

    public void setCategoryKeys(List<String> categoryKeys) {
        this.categoryKeys = categoryKeys;
    }

    public List<PromotionCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<PromotionCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public List<String> getCategoryReminderList() {
        return categoryReminderList;
    }

    public void setCategoryReminderList(List<String> categoryReminderList) {
        this.categoryReminderList = categoryReminderList;
    }

    public boolean isPromotionEnded() {
        return promotionEnded;
    }

    public void setPromotionEnded(boolean promotionEnded) {
        this.promotionEnded = promotionEnded;
    }

    public int getSortSeqPromoAlive() {
        return sortSeqPromoAlive;
    }

    public void setSortSeqPromoAlive(int sortSeqPromoAlive) {
        this.sortSeqPromoAlive = sortSeqPromoAlive;
    }

    public Date getAddfavoriteTime() {
        return addfavoriteTime;
    }

    public void setAddfavoriteTime(Date addfavoriteTime) {
        this.addfavoriteTime = addfavoriteTime;
    }

    public int getDaysFromEndTime() {
        return daysFromEndTime;
    }

    public void setDaysFromEndTime(int daysFromEndTime) {
        this.daysFromEndTime = daysFromEndTime;
    }

    public String getCategoryMain() {
        return categoryMain;
    }

    public void setCategoryMain(String categoryMain) {
        this.categoryMain = categoryMain;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }
}
