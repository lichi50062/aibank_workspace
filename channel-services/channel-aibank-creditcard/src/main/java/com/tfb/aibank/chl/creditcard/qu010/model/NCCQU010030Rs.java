package com.tfb.aibank.chl.creditcard.qu010.model;

import java.util.ArrayList;
import java.util.List;

import com.ibm.tw.ibmb.base.model.RsData;
import com.ibm.tw.ibmb.model.LabelValueBean;

import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NCCQU010030Rs.java
 * 
 * <p>Description:消費分析 030 類別分析頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/25, Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU010030Rs implements RsData {

    /** 查詢結果: 0: 查詢成功, 1: 查詢失敗 */
    private int queryResult;

    /** 消費類別清單 */
    private List<LabelValueBean> consumptionCategories;

    /** 今年分析月清單 */
    private List<NCCQU010DateInfo> thisYearMonths;

    /** 去年分析月清單 */
    private List<NCCQU010DateInfo> lastYearMonths;

    /** 月份消費類別資料 */
    private List<NCCQU010MonthCategoryData> monthCategoryData = new ArrayList<>();

    /** 有跨去年 */
    private Boolean hasLastYear;

    /** 顯示今年 */
    private String currentYearDisplay;

    /** 顯示去年 */
    private String lastYearDisplay;

    /** 條件式版位是否啟用 */
    private boolean conditionalMessageEnabled;

    /**
     * @return the queryResult
     */
    public int getQueryResult() {
        return queryResult;
    }

    /**
     * @param queryResult
     *            the queryResult to set
     */
    public void setQueryResult(int queryResult) {
        this.queryResult = queryResult;
    }

    /**
     * @return the consumptionCategories
     */
    public List<LabelValueBean> getConsumptionCategories() {
        return consumptionCategories;
    }

    /**
     * @param consumptionCategories
     *            the consumptionCategories to set
     */
    public void setConsumptionCategories(List<LabelValueBean> consumptionCategories) {
        this.consumptionCategories = consumptionCategories;
    }

    /**
     * @return the thisYearMonths
     */
    public List<NCCQU010DateInfo> getThisYearMonths() {
        return thisYearMonths;
    }

    /**
     * @param thisYearMonths
     *            the thisYearMonths to set
     */
    public void setThisYearMonths(List<NCCQU010DateInfo> thisYearMonths) {
        this.thisYearMonths = thisYearMonths;
    }

    /**
     * @return the lastYearMonths
     */
    public List<NCCQU010DateInfo> getLastYearMonths() {
        return lastYearMonths;
    }

    /**
     * @param lastYearMonths
     *            the lastYearMonths to set
     */
    public void setLastYearMonths(List<NCCQU010DateInfo> lastYearMonths) {
        this.lastYearMonths = lastYearMonths;
    }

    /**
     * @return the monthCategoryData
     */
    public List<NCCQU010MonthCategoryData> getMonthCategoryData() {
        return monthCategoryData;
    }

    /**
     * @param monthCategoryData
     *            the monthCategoryData to set
     */
    public void setMonthCategoryData(List<NCCQU010MonthCategoryData> monthCategoryData) {
        this.monthCategoryData = monthCategoryData;
    }

    /**
     * @return the hasLastYear
     */
    public Boolean getHasLastYear() {
        return hasLastYear;
    }

    /**
     * @param hasLastYear
     *            the hasLastYear to set
     */
    public void setHasLastYear(Boolean hasLastYear) {
        this.hasLastYear = hasLastYear;
    }

    /**
     * @return the currentYearDisplay
     */
    public String getCurrentYearDisplay() {
        return currentYearDisplay;
    }

    /**
     * @param currentYearDisplay
     *            the currentYearDisplay to set
     */
    public void setCurrentYearDisplay(String currentYearDisplay) {
        this.currentYearDisplay = currentYearDisplay;
    }

    /**
     * @return the lastYearDisplay
     */
    public String getLastYearDisplay() {
        return lastYearDisplay;
    }

    /**
     * @param lastYearDisplay
     *            the lastYearDisplay to set
     */
    public void setLastYearDisplay(String lastYearDisplay) {
        this.lastYearDisplay = lastYearDisplay;
    }

    /**
     * @return the conditionalMessageEnabled
     */
    public boolean isConditionalMessageEnabled() {
        return conditionalMessageEnabled;
    }

    /**
     * @param conditionalMessageEnabled
     *            the conditionalMessageEnabled to set
     */
    public void setConditionalMessageEnabled(boolean conditionalMessageEnabled) {
        this.conditionalMessageEnabled = conditionalMessageEnabled;
    }
}
