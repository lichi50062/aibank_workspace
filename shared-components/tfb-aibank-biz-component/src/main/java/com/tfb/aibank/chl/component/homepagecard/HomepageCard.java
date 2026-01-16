/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.homepagecard;

import java.io.Serializable;
import java.util.Date;

// @formatter:off
/**
 * @(#)HomepageCard.java
 * 
 * <p>Description:首頁牌卡設定檔</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class HomepageCard implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 牌卡Key
     */
    private Integer cardKey;

    /**
     * 牌卡編號
     */
    private Integer cardId;

    /**
     * 牌卡應用0-首頁1-交易面
     */
    private Integer cardUsed;

    /**
     * 牌卡應用交易 [TASK_ID] 若CARD_USED=2，才寫入此欄位
     */
    private String cardUsedTaskId;

    /**
     * HOMEPAGE：首頁牌卡、 HOMEPAGE_SETTING：首頁牌卡設定 AD_CARD1：廣告小卡1 AD_CARD2：廣告小卡2 TASK_CARD：交易內牌卡
     */
    private String cardTemplate;

    /**
     * 牌卡名稱
     */
    private String cardName;

    /**
     * 預設順序
     */
    private Integer cardSort;

    /**
     * 牌卡底圖路徑
     */
    private String cardBg;

    /**
     * 牌卡小圖路徑
     */
    private String cardIcon;

    /**
     * 牌卡說明
     */
    private String cardDesc;

    /**
     * 牌卡目的(目標URL)
     */
    private String cardTarget;

    /**
     * 功能導頁帶的參數
     */
    private String cardParam;

    /**
     * 牌卡說明-折合
     */
    private String cardFold;

    /**
     * 顯示註記
     */
    private Integer showFlag;

    /**
     * 是否有查詢權限
     */
    private Integer query;

    /**
     * 客群
     */
    private String taGroup;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 更新時間
     */
    private Date updateTime;

    /**
     * 上架時間
     */
    private Date startTime;

    /**
     * 下架時間
     */
    private Date endTime;

    /**
     * 客群機制
     */
    private int taType;

    /**
     * 動態順序依據 程式執行時塞值排序用
     */
    private Integer cardSortDynamic;

    /**
     * 線上申請fromNo
     */
    private String fromNoApplyOnline = "";

    /**
     * 專人回電fromNo
     */
    private String fromNoCallback = "";

    /**
     * 廣告牌卡版本
     */
    private String cardVersion;

    /**
     * 牌卡目的二
     */
    private String cardTarget2;

    /**
     * @return the cardKey
     */
    public Integer getCardKey() {
        return cardKey;
    }

    /**
     * @param cardKey
     *            the cardKey to set
     */
    public void setCardKey(Integer cardKey) {
        this.cardKey = cardKey;
    }

    /**
     * @return the cardId
     */
    public Integer getCardId() {
        return cardId;
    }

    /**
     * @param cardId
     *            the cardId to set
     */
    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    /**
     * @return the cardUsed
     */
    public Integer getCardUsed() {
        return cardUsed;
    }

    /**
     * @param cardUsed
     *            the cardUsed to set
     */
    public void setCardUsed(Integer cardUsed) {
        this.cardUsed = cardUsed;
    }

    /**
     * @return the cardUsedTaskId
     */
    public String getCardUsedTaskId() {
        return cardUsedTaskId;
    }

    /**
     * @param cardUsedTaskId
     *            the cardUsedTaskId to set
     */
    public void setCardUsedTaskId(String cardUsedTaskId) {
        this.cardUsedTaskId = cardUsedTaskId;
    }

    /**
     * @return the cardTemplate
     */
    public String getCardTemplate() {
        return cardTemplate;
    }

    /**
     * @param cardTemplate
     *            the cardTemplate to set
     */
    public void setCardTemplate(String cardTemplate) {
        this.cardTemplate = cardTemplate;
    }

    /**
     * @return the cardName
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * @param cardName
     *            the cardName to set
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * @return the cardSort
     */
    public Integer getCardSort() {
        return cardSort;
    }

    /**
     * @param cardSort
     *            the cardSort to set
     */
    public void setCardSort(Integer cardSort) {
        this.cardSort = cardSort;
    }

    /**
     * @return the cardBg
     */
    public String getCardBg() {
        return cardBg;
    }

    /**
     * @param cardBg
     *            the cardBg to set
     */
    public void setCardBg(String cardBg) {
        this.cardBg = cardBg;
    }

    /**
     * @return the cardIcon
     */
    public String getCardIcon() {
        return cardIcon;
    }

    /**
     * @param cardIcon
     *            the cardIcon to set
     */
    public void setCardIcon(String cardIcon) {
        this.cardIcon = cardIcon;
    }

    /**
     * @return the cardTarget
     */
    public String getCardTarget() {
        return cardTarget;
    }

    /**
     * @param cardTarget
     *            the cardTarget to set
     */
    public void setCardTarget(String cardTarget) {
        this.cardTarget = cardTarget;
    }

    /**
     * @return the cardParam
     */
    public String getCardParam() {
        return cardParam;
    }

    /**
     * @param cardParam
     *            the cardParam to set
     */
    public void setCardParam(String cardParam) {
        this.cardParam = cardParam;
    }

    /**
     * @return the cardFold
     */
    public String getCardFold() {
        return cardFold;
    }

    /**
     * @param cardFold
     *            the cardFold to set
     */
    public void setCardFold(String cardFold) {
        this.cardFold = cardFold;
    }

    /**
     * @return the showFlag
     */
    public Integer getShowFlag() {
        return showFlag;
    }

    /**
     * @param showFlag
     *            the showFlag to set
     */
    public void setShowFlag(Integer showFlag) {
        this.showFlag = showFlag;
    }

    /**
     * @return the query
     */
    public Integer getQuery() {
        return query;
    }

    /**
     * @param query
     *            the query to set
     */
    public void setQuery(Integer query) {
        this.query = query;
    }

    /**
     * @return the taGroup
     */
    public String getTaGroup() {
        return taGroup == null ? "" : taGroup;
    }

    /**
     * @param taGroup
     *            the taGroup to set
     */
    public void setTaGroup(String taGroup) {
        this.taGroup = taGroup;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     *            the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the taType
     */
    public int getTaType() {
        return taType;
    }

    /**
     * @return the cardEvent
     */
    public CardEvent getCardEvent() {
        String _taGroup = this.taGroup == null ? "" : this.taGroup;
        return new CardEvent(this.taType, _taGroup, this.cardTemplate, this.cardKey);
    }

    /**
     * @param taType
     *            the taType to set
     */
    public void setTaType(int taType) {
        this.taType = taType;
    }

    /**
     * @return the cardDesc
     */
    public String getCardDesc() {
        return cardDesc;
    }

    /**
     * @param cardDesc
     *            the cardDesc to set
     */
    public void setCardDesc(String cardDesc) {
        this.cardDesc = cardDesc;
    }

    public Integer getCardSortDynamic() {
        return cardSortDynamic;
    }

    public void setCardSortDynamic(Integer cardSortDynamic) {
        this.cardSortDynamic = cardSortDynamic;
    }

    public String getFromNoApplyOnline() {
        return fromNoApplyOnline;
    }

    public void setFromNoApplyOnline(String fromNoApplyOnline) {
        this.fromNoApplyOnline = fromNoApplyOnline;
    }

    public String getFromNoCallback() {
        return fromNoCallback;
    }

    public void setFromNoCallback(String fromNoCallback) {
        this.fromNoCallback = fromNoCallback;
    }

    public String getCardVersion() {
        return cardVersion;
    }

    public void setCardVersion(String cardVersion) {
        this.cardVersion = cardVersion;
    }

    public String getCardTarget2() {
        return cardTarget2;
    }

    public void setCardTarget2(String cardTarget2) {
        this.cardTarget2 = cardTarget2;
    }

    @Override
    public String toString() {
        return "Homepage{" + "taGroup='" + taGroup + '\'' + ", cardTemplate='" + cardTemplate + '\'' + ", cardKey=" + cardKey + ", cardDesc='" + cardDesc + '\'' + ", showFlag=" + showFlag + ", query=" + query + ", createTime=" + createTime + ", updateTime=" + updateTime + ", startTime=" + startTime + ", endTime=" + endTime + ", taType=" + taType + ", cardSortDynamic=" + cardSortDynamic + ", fromNoApplyOnline='" + fromNoApplyOnline + '\'' + ", fromNoCallback='" + fromNoCallback + '\'' + ", cardVersion='" + cardVersion + '\'' + ", cardTarget2='" + cardTarget2 + '\'' + '}';
    }

}
