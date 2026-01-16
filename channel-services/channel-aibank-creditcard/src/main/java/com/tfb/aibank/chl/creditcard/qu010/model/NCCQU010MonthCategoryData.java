package com.tfb.aibank.chl.creditcard.qu010.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// @formatter:off
/**
 * @(#)NCCQU010MonthCategoryData.java
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
public class NCCQU010MonthCategoryData extends NCCQU010MonthConsumptionData {

    /** 消費類別 */
    private String categoryGroup;

    /** 近半年消費金額加總 */
    private BigDecimal latestHalfYearConsumptionSum;

    /** 近一年消費金額加總 */
    private BigDecimal latestYearConsumptionSum;

    /** 類別佔比 */
    private List<NCCQU010CategoryRatioData> categoryRatioData = new ArrayList<>();

    /** 消費類別明細資料 */
    private List<NCCQU010CategoryDetailData> categoryDetailData = new ArrayList<>();

    /** 剩餘項目數量 */
    private String otherItemCountDisplay;

    /** 剩餘項目佔比 */
    private String otherRatioDisplay;

    /** 金額是否有負值 */
    private Boolean hasMinusAmt = false;

    /** 與近6個月平均值比對 */
    private String lastSixMonthAverageRatioDisplay;

    /** 與去年同月比對 */
    private String lastYearRatioDisplay;

    /**
     * @return the categoryGroup
     */
    public String getCategoryGroup() {
        return this.categoryGroup;
    }

    /**
     * @param categoryGroup
     *            the categoryGroup to set
     */
    public void setCategoryGroup(String categoryGroup) {
        this.categoryGroup = categoryGroup;
    }

    /**
     * @return the latestHalfYearConsumptionSum
     */
    public BigDecimal getLatestHalfYearConsumptionSum() {
        return latestHalfYearConsumptionSum;
    }

    /**
     * @param latestHalfYearConsumptionSum
     *            the latestHalfYearConsumptionSum to set
     */
    public void setLatestHalfYearConsumptionSum(BigDecimal latestHalfYearConsumptionSum) {
        this.latestHalfYearConsumptionSum = latestHalfYearConsumptionSum;
    }

    /**
     * @return the latestYearConsumptionSum
     */
    public BigDecimal getLatestYearConsumptionSum() {
        return latestYearConsumptionSum;
    }

    /**
     * @param latestYearConsumptionSum
     *            the latestYearConsumptionSum to set
     */
    public void setLatestYearConsumptionSum(BigDecimal latestYearConsumptionSum) {
        this.latestYearConsumptionSum = latestYearConsumptionSum;
    }

    /**
     * @return the categoryRatioData
     */
    public List<NCCQU010CategoryRatioData> getCategoryRatioData() {
        return this.categoryRatioData;
    }

    /**
     * @param categoryRatioData
     *            the categoryRatioData to set
     */
    public void setCategoryRatioData(List<NCCQU010CategoryRatioData> categoryRatioData) {
        this.categoryRatioData = categoryRatioData;
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
     * @return the otherItemCountDisplay
     */
    public String getOtherItemCountDisplay() {
        return this.otherItemCountDisplay;
    }

    /**
     * @param otherItemCountDisplay
     *            the otherItemCountDisplay to set
     */
    public void setOtherItemCountDisplay(String otherItemCountDisplay) {
        this.otherItemCountDisplay = otherItemCountDisplay;
    }

    /**
     * @return the otherRatioDisplay
     */
    public String getOtherRatioDisplay() {
        return this.otherRatioDisplay;
    }

    /**
     * @param otherRatioDisplay
     *            the otherRatioDisplay to set
     */
    public void setOtherRatioDisplay(String otherRatioDisplay) {
        this.otherRatioDisplay = otherRatioDisplay;
    }

    /**
     * 
     * @return the hasMinusAmt
     */
    public Boolean getHasMinusAmt() {
        return hasMinusAmt;
    }

    /**
     * @param hasMinusAmt
     *            the hasMinusAmt to set
     */
    public void setHasMinusAmt(Boolean hasMinusAmt) {
        this.hasMinusAmt = hasMinusAmt;
    }

    /**
     * @return the lastSixMonthAverageRatioDisplay
     */
    public String getLastSixMonthAverageRatioDisplay() {
        return lastSixMonthAverageRatioDisplay;
    }

    /**
     * @param lastSixMonthAverageRatioDisplay
     *            the lastSixMonthAverageRatioDisplay to set
     */
    public void setLastSixMonthAverageRatioDisplay(String lastSixMonthAverageRatioDisplay) {
        this.lastSixMonthAverageRatioDisplay = lastSixMonthAverageRatioDisplay;
    }

    /**
     * @return the lastYearRatioDisplay
     */
    public String getLastYearRatioDisplay() {
        return lastYearRatioDisplay;
    }

    /**
     * @param lastYearRatioDisplay
     *            the lastYearRatioDisplay to set
     */
    public void setLastYearRatioDisplay(String lastYearRatioDisplay) {
        this.lastYearRatioDisplay = lastYearRatioDisplay;
    }

}
