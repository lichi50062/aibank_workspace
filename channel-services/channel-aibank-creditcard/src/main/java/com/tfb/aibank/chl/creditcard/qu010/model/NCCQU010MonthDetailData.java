package com.tfb.aibank.chl.creditcard.qu010.model;

import java.util.ArrayList;
import java.util.List;

// @formatter:off
/**
 * @(#)NCCQU010MonthDetailData.java
 * 
 * <p>Description:消費分析 輸出專用物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/04, Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU010MonthDetailData extends NCCQU010MonthConsumptionData {

    /** 消費類別 */
    private String itemCategory;

    /** 消費類別明細資料 */
    private List<NCCQU010CategoryDetailData> categoryDetailData = new ArrayList<>();

    /** 合計消費天數 */
    private String consumptionDayCountDisplay;

    /** 消費金額最高日 */
    private String highestConsumptionDateDisplay;

    /** 項目明細筆數 */
    private String detailItemCountDisplay;

    /** 是否集中在上半個月 */
    private Boolean focusOnFirstHalfOfMonth;

    /** 集中在半個月的消費總額 */
    private String halfOfMonthAmtAndRatioDisplay;

    /**
     * @return the itemCategory
     */
    public String getItemCategory() {
        return this.itemCategory;
    }

    /**
     * @param itemCategory
     *            the itemCategory to set
     */
    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }
    
    /**
     * @return the categoryDetailData
     */
    public List<NCCQU010CategoryDetailData> getCategoryDetailData() {
        return this.categoryDetailData;
    }

    /**
     * @param categoryDetailData
     *            the categoryDetailData to set
     */
    public void setCategoryDetailData(List<NCCQU010CategoryDetailData> categoryDetailData) {
        this.categoryDetailData = categoryDetailData;
    }

    /**
     * @return the consumptionDayCountDisplay
     */
    public String getConsumptionDayCountDisplay() {
        return this.consumptionDayCountDisplay;
    }

    /**
     * @param consumptionDayCountDisplay
     *            the consumptionDayCountDisplay to set
     */
    public void setConsumptionDayCountDisplay(String consumptionDayCountDisplay) {
        this.consumptionDayCountDisplay = consumptionDayCountDisplay;
    }

    /**
     * @return the highestConsumptionDateDisplay
     */
    public String getHighestConsumptionDateDisplay() {
        return this.highestConsumptionDateDisplay;
    }

    /**
     * @param highestConsumptionDateDisplay
     *            the highestConsumptionDateDisplay to set
     */
    public void setHighestConsumptionDateDisplay(String highestConsumptionDateDisplay) {
        this.highestConsumptionDateDisplay = highestConsumptionDateDisplay;
    }
    
    /**
     * @return the detailItemCountDisplay
     */
    public String getDetailItemCountDisplay() {
        return this.detailItemCountDisplay;
    }

    /**
     * @param detailItemCountDisplay
     *            the detailItemCountDisplay to set
     */
    public void setDetailItemCountDisplay(String detailItemCountDisplay) {
        this.detailItemCountDisplay = detailItemCountDisplay;
    }

    /**
     * @return the focusOnFirstHalfOfMonth
     */
    public Boolean getFocusOnFirstHalfOfMonth() {
        return this.focusOnFirstHalfOfMonth;
    }

    /**
     * @param focusOnFirstHalfOfMonth
     *            the focusOnFirstHalfOfMonth to set
     */
    public void setFocusOnFirstHalfOfMonth(Boolean focusOnFirstHalfOfMonth) {
        this.focusOnFirstHalfOfMonth = focusOnFirstHalfOfMonth;
    }

    /**
     * @return the halfOfMonthAmtAndRatioDisplay
     */
    public String getHalfOfMonthAmtAndRatioDisplay() {
        return this.halfOfMonthAmtAndRatioDisplay;
    }

    /**
     * @param halfOfMonthAmtAndRatioDisplay
     *            the halfOfMonthAmtAndRatioDisplay to set
     */
    public void setHalfOfMonthAmtAndRatioDisplay(String halfOfMonthAmtAndRatioDisplay) {
        this.halfOfMonthAmtAndRatioDisplay = halfOfMonthAmtAndRatioDisplay;
    }
}
