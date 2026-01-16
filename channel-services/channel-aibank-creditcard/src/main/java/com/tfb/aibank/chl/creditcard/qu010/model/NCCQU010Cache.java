package com.tfb.aibank.chl.creditcard.qu010.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tfb.aibank.chl.creditcard.resource.dto.CategoryStats;
import com.tfb.aibank.chl.creditcard.resource.dto.ChargesStats;

// @formatter:off
/**
 * @(#)NCCQU010Cache.java
 * 
 * <p>Description:消費分析 輸出專用物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/25, Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU010Cache {

    /** 近半年分析年月 */
    private List<String> latestSixMonthAnalysisYearMonths = new ArrayList<>();

    /** 近一年分析年月 */
    private List<String> latestYearAnalysisYearMonths = new ArrayList<>();

    /** 消費趨勢 */
    private NCCQU010CardSummary trendSummary;

    /** 消費佔比 */
    private NCCQU010CardSummary ratioSummary;

    /** 消費分佈 */
    private NCCQU010CardSummary distributionSummary;

    /** 近2年交易彙整資料 */
    private List<ChargesStats> latestTwoYearConsumptionData = new ArrayList<>();

    /** 近半年消費平均 */
    private BigDecimal latestSixMonthConsumptionAvg;

    /** 近一年消費平均 */
    private BigDecimal latestYearConsumptionAvg;

    /** 近2年消費類別資料 */
    private Map<String, List<CategoryStats>> latestTwoYearCategoryData = new HashMap<>();

    /** 各月份各消費類別資料 */
    private List<NCCQU010MonthCategoryData> monthCategoryData = new ArrayList<>();

    /** 各月份各類別消費明細資料 */
    private NCCQU010MonthDetailData monthDetailData;

    /** 整年消費明細搜尋資料 */
    private List<NCCQU010AnnualDetailData> annualDetails = new ArrayList<>();

    /** 分析起始月 */
    private String currentAnalysisMonth;

    /** 分析起始月 */
    private String startYearMonth;

    /** 分析結束月 */
    private String endYearMonth;

    /** 剛好一年前的起始月 */
    private String lastYearMonth;

    /** 筆數上限 */
    private int recordLimit;

    /** 趨勢牌卡卡別 */
    private String cardType;

    /** 查詢結果: 0: 查詢成功, 1: 查詢失敗 3:沒有資料 4:成功,但還有資料 */
    private int queryResult;

    /**
     * @return the latestSixMonthAnalysisYearMonths
     */
    public List<String> getLatestSixMonthAnalysisYearMonths() {
        return latestSixMonthAnalysisYearMonths;
    }

    /**
     * @param latestSixMonthAnalysisYearMonths
     *            the latestSixMonthAnalysisYearMonths to set
     */
    public void setLatestSixMonthAnalysisYearMonths(List<String> latestSixMonthAnalysisYearMonths) {
        this.latestSixMonthAnalysisYearMonths = latestSixMonthAnalysisYearMonths;
    }

    /**
     * @return the latestYearAnalysisYearMonths
     */
    public List<String> getLatestYearAnalysisYearMonths() {
        return latestYearAnalysisYearMonths;
    }

    /**
     * @param latestYearAnalysisYearMonths
     *            the latestYearAnalysisYearMonths to set
     */
    public void setLatestYearAnalysisYearMonths(List<String> latestYearAnalysisYearMonths) {
        this.latestYearAnalysisYearMonths = latestYearAnalysisYearMonths;
    }

    /**
     * @return the trendSummary
     */
    public NCCQU010CardSummary getTrendSummary() {
        return trendSummary;
    }

    /**
     * @param trendSummary
     *            the trendSummary to set
     */
    public void setTrendSummary(NCCQU010CardSummary trendSummary) {
        this.trendSummary = trendSummary;
    }

    /**
     * @return the ratioSummary
     */
    public NCCQU010CardSummary getRatioSummary() {
        return ratioSummary;
    }

    /**
     * @param ratioSummary
     *            the ratioSummary to set
     */
    public void setRatioSummary(NCCQU010CardSummary ratioSummary) {
        this.ratioSummary = ratioSummary;
    }

    /**
     * @return the distributionSummary
     */
    public NCCQU010CardSummary getDistributionSummary() {
        return distributionSummary;
    }

    /**
     * @param distributionSummary
     *            the distributionSummary to set
     */
    public void setDistributionSummary(NCCQU010CardSummary distributionSummary) {
        this.distributionSummary = distributionSummary;
    }

    /**
     * @return the latestTwoYearConsumptionData
     */
    public List<ChargesStats> getLatestTwoYearConsumptionData() {
        return latestTwoYearConsumptionData;
    }

    /**
     * @param latestTwoYearConsumptionData
     *            the latestTwoYearConsumptionData to set
     */
    public void setLatestTwoYearConsumptionData(List<ChargesStats> latestTwoYearConsumptionData) {
        this.latestTwoYearConsumptionData = latestTwoYearConsumptionData;
    }

    /**
     * @return the latestSixMonthConsumptionAvg
     */
    public BigDecimal getLatestSixMonthConsumptionAvg() {
        return latestSixMonthConsumptionAvg;
    }

    /**
     * @param latestSixMonthConsumptionAvg
     *            the latestSixMonthConsumptionAvg to set
     */
    public void setLatestSixMonthConsumptionAvg(BigDecimal latestSixMonthConsumptionAvg) {
        this.latestSixMonthConsumptionAvg = latestSixMonthConsumptionAvg;
    }

    /**
     * @return the latestYearConsumptionAvg
     */
    public BigDecimal getLatestYearConsumptionAvg() {
        return latestYearConsumptionAvg;
    }

    /**
     * @param latestYearConsumptionAvg
     *            the latestYearConsumptionAvg to set
     */
    public void setLatestYearConsumptionAvg(BigDecimal latestYearConsumptionAvg) {
        this.latestYearConsumptionAvg = latestYearConsumptionAvg;
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
     * @return the monthDetailData
     */
    public NCCQU010MonthDetailData getMonthDetailData() {
        return monthDetailData;
    }

    /**
     * @param monthDetailData
     *            the monthDetailData to set
     */
    public void setMonthDetailData(NCCQU010MonthDetailData monthDetailData) {
        this.monthDetailData = monthDetailData;
    }

    /**
     * @return the annualDetails
     */
    public List<NCCQU010AnnualDetailData> getAnnualDetails() {
        return annualDetails;
    }

    /**
     * @param annualDetails
     *            the annualDetails to set
     */
    public void setAnnualDetails(List<NCCQU010AnnualDetailData> annualDetails) {
        this.annualDetails = annualDetails;
    }

    /**
     * @return the currentAnalysisMonth
     */
    public String getCurrentAnalysisMonth() {
        return currentAnalysisMonth;
    }

    /**
     * @param currentAnalysisMonth
     *            the currentAnalysisMonth to set
     */
    public void setCurrentAnalysisMonth(String currentAnalysisMonth) {
        this.currentAnalysisMonth = currentAnalysisMonth;
    }

    /**
     * @return the startYearMonth
     */
    public String getStartYearMonth() {
        return startYearMonth;
    }

    /**
     * @param startYearMonth
     *            the startYearMonth to set
     */
    public void setStartYearMonth(String startYearMonth) {
        this.startYearMonth = startYearMonth;
    }

    /**
     * @return the endYearMonth
     */
    public String getEndYearMonth() {
        return endYearMonth;
    }

    /**
     * @param endYearMonth
     *            the endYearMonth to set
     */
    public void setEndYearMonth(String endYearMonth) {
        this.endYearMonth = endYearMonth;
    }

    /**
     * @return the recordLimit
     */
    public int getRecordLimit() {
        return recordLimit;
    }

    /**
     * @param recordLimit
     *            the recordLimit to set
     */
    public void setRecordLimit(int recordLimit) {
        this.recordLimit = recordLimit;
    }

    /**
     * @return the latestTwoYearCategoryData
     */
    public Map<String, List<CategoryStats>> getLatestTwoYearCategoryData() {
        return latestTwoYearCategoryData;
    }

    /**
     * @param latestTwoYearCategoryData
     *            the latestTwoYearCategoryData to set
     */
    public void setLatestTwoYearCategoryData(Map<String, List<CategoryStats>> latestTwoYearCategoryData) {
        this.latestTwoYearCategoryData = latestTwoYearCategoryData;
    }

    /**
     * @return the cardType
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * @param cardType
     *            the cardType to set
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

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
     * @return the lastYearMonth
     */
    public String getLastYearMonth() {
        return lastYearMonth;
    }

    /**
     * @param lastYearMonth
     *            the lastYearMonth to set
     */
    public void setLastYearMonth(String lastYearMonth) {
        this.lastYearMonth = lastYearMonth;
    }

}
