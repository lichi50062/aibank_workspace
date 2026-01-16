package com.tfb.aibank.chl.creditcard.qu010.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ibm.tw.ibmb.model.LabelValueBean;
import com.tfb.aibank.chl.creditcard.resource.dto.CategoryDetailResRep;
import com.tfb.aibank.chl.creditcard.resource.dto.CategoryRes;
import com.tfb.aibank.chl.creditcard.resource.dto.CategoryStats;
import com.tfb.aibank.chl.creditcard.resource.dto.ChargesStats;
import com.tfb.aibank.chl.creditcard.resource.dto.ConsumptionSummaryRes;

// @formatter:off
/**
 * @(#)NCCQU010Output.java
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
public class NCCQU010Output {

    /** 消費趨勢 */
    private NCCQU010CardSummary trendSummary;

    /** 消費佔比 */
    private NCCQU010CardSummary ratioSummary;

    /** 消費分佈 */
    private NCCQU010CardSummary distributionSummary;

    /** 失敗標題 */
    private String errorTitle;

    /** 失敗訊息 */
    private String errorMessage;

    /** 信用卡推薦 */
    private NCCQU010CreditCardRecommendation creditCardRecommendation;

    /** 分析起始月 */
    private String startYearMonth;

    /** 分析結束月 */
    private String endYearMonth;

    /** 剛好一年前的起始月 */
    private String lastYearMonth;

    /** 近半年分析年月 */
    private List<String> latestSixMonthYearMonths;

    /** 近一年分析年月 */
    private List<String> latestYearAnalysisYearMonths;

    /** 消費金額彙總資訊查詢 */
    private ConsumptionSummaryRes consumptionSummary;

    /** 各消費類別金額資訊查詢 */
    private CategoryRes categoryConsumption;

    /** 各消費類別金額資訊查詢類別 */
    private String categoryGroup;

    /** 近半年消費金額彙總資料 */
    private List<ChargesStats> latestSixMonthConsumptionSummaryData = new ArrayList<>();

    /** 近一年消費金額彙總資料 */
    private List<ChargesStats> latestYearConsumptionSummaryData = new ArrayList<>();

    /** 近2年消費金額彙總資料 */
    private List<ChargesStats> latestTwoYearConsumptionData = new ArrayList<>();

    /** 近半年平均消費金額 */
    private BigDecimal latestSixMonthConsumptionAvg;

    /** 近一年平均消費金額 */
    private BigDecimal latestYearConsumptionAvg;

    /** 近半年By月份消費匯總資料 */
    private List<NCCQU010MonthConsumptionData> latestSixMonthConsumptionData;

    /** 近一年By月份消費匯總資料 */
    private List<NCCQU010MonthConsumptionData> latestYearMonthConsumptionData;

    /** 時間區間清單 */
    private List<LabelValueBean> periods;

    /** 消費類別清單 */
    private List<LabelValueBean> consumptionCategories;

    /** 分析月清單 */
    private List<NCCQU010DateInfo> yearMonths;

    /** 近半年消費類別資料 */
    private List<CategoryStats> latestSixMonthCategoryData = new ArrayList<>();

    /** 近一年消費類別資料 */
    private List<CategoryStats> latestYearCategoryData = new ArrayList<>();

    /** 近2年消費類別資料 */
    private List<CategoryStats> latestTwoYearCategoryData = new ArrayList<>();

    /** 各月份消各費類別資料 */
    private List<NCCQU010MonthCategoryData> monthCategoryData;

    /** 指定月份指定類別消費明細資料 */
    private NCCQU010MonthDetailData monthDetailData;

    /** 040 指定類別消費明細資料: 0 成功, 1 失敗, 2 無資料 */
    private Integer queryResult;

    /** 各消費明細資料 */
    private List<CategoryDetailResRep> categoryDetails = new ArrayList<>();

    /** 整年消費明細搜尋資料 */
    private List<NCCQU010AnnualDetailData> annualDetails = new ArrayList<>();

    /** 有跨去年 */
    private Boolean hasLastYear;

    /** 顯示今年 */
    private String currentYearDisplay;

    /** 顯示去年 */
    private String lastYearDisplay;

    /** 消費金額加總 */
    private String txnAmtSumDisplay;

    /** 筆數上限 */
    private int recordLimit;

    /** 條件式版位是否啟用 */
    private boolean conditionalMessageEnabled;

    /**
     * @return the trendSummary
     */
    public NCCQU010CardSummary getTrendSummary() {
        if (trendSummary == null) {
            trendSummary = new NCCQU010CardSummary();
        }
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
        if (ratioSummary == null) {
            ratioSummary = new NCCQU010CardSummary();
        }
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
        if (distributionSummary == null) {
            distributionSummary = new NCCQU010CardSummary();
        }
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
     * @return the errorTitle
     */
    public String getErrorTitle() {
        return errorTitle;
    }

    /**
     * @param errorTitle
     *            the errorTitle to set
     */
    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage
     *            the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the creditCardRecommendation
     */
    public NCCQU010CreditCardRecommendation getCreditCardRecommendation() {
        return creditCardRecommendation;
    }

    /**
     * @param homepageCard
     *            the creditCardRecommendation to set
     */
    public void setCreditCardRecommendation(NCCQU010CreditCardRecommendation homepageCard) {
        this.creditCardRecommendation = homepageCard;
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
     * @return the latestSixMonthYearMonths
     */
    public List<String> getLatestSixMonthYearMonths() {
        return latestSixMonthYearMonths;
    }

    /**
     * @param latestSixMonthYearMonths
     *            the latestSixMonthYearMonths to set
     */
    public void setLatestSixMonthYearMonths(List<String> latestSixMonthYearMonths) {
        this.latestSixMonthYearMonths = latestSixMonthYearMonths;
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
     * @return the consumptionSummary
     */
    public ConsumptionSummaryRes getConsumptionSummary() {
        return consumptionSummary;
    }

    /**
     * @param consumptionSummary
     *            the consumptionSummary to set
     */
    public void setConsumptionSummary(ConsumptionSummaryRes consumptionSummary) {
        this.consumptionSummary = consumptionSummary;
    }

    /**
     * @return the categoryConsumption
     */
    public CategoryRes getCategoryConsumption() {
        return categoryConsumption;
    }

    /**
     * @param categoryConsumption
     *            the categoryConsumption to set
     */
    public void setCategoryConsumption(CategoryRes categoryConsumption) {
        this.categoryConsumption = categoryConsumption;
    }

    /**
     * @return the latestSixMonthConsumptionSummaryData
     */
    public List<ChargesStats> getLatestSixMonthConsumptionSummaryData() {
        return latestSixMonthConsumptionSummaryData;
    }

    /**
     * @param latestSixMonthConsumptionSummaryData
     *            the latestSixMonthConsumptionSummaryData to set
     */
    public void setLatestSixMonthConsumptionSummaryData(List<ChargesStats> latestSixMonthConsumptionSummaryData) {
        this.latestSixMonthConsumptionSummaryData = latestSixMonthConsumptionSummaryData;
    }

    /**
     * @return the latestYearConsumptionSummaryData
     */
    public List<ChargesStats> getLatestYearConsumptionSummaryData() {
        return latestYearConsumptionSummaryData;
    }

    /**
     * @param latestYearConsumptionSummaryData
     *            the latestYearConsumptionSummaryData to set
     */
    public void setLatestYearConsumptionSummaryData(List<ChargesStats> latestYearConsumptionSummaryData) {
        this.latestYearConsumptionSummaryData = latestYearConsumptionSummaryData;
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
     * @return the latestSixMonthConsumptionData
     */
    public List<NCCQU010MonthConsumptionData> getLatestSixMonthConsumptionData() {
        return latestSixMonthConsumptionData;
    }

    /**
     * @param latestSixMonthConsumptionData
     *            the latestSixMonthConsumptionData to set
     */
    public void setLatestSixMonthConsumptionData(List<NCCQU010MonthConsumptionData> latestSixMonthConsumptionData) {
        this.latestSixMonthConsumptionData = latestSixMonthConsumptionData;
    }

    /**
     * @return the latestYearMonthConsumptionData
     */
    public List<NCCQU010MonthConsumptionData> getLatestYearMonthConsumptionData() {
        return latestYearMonthConsumptionData;
    }

    /**
     * @param latestYearMonthConsumptionData
     *            the latestYearMonthConsumptionData to set
     */
    public void setLatestYearMonthConsumptionData(List<NCCQU010MonthConsumptionData> latestYearMonthConsumptionData) {
        this.latestYearMonthConsumptionData = latestYearMonthConsumptionData;
    }

    /**
     * @return the periods
     */
    public List<LabelValueBean> getPeriods() {
        return periods;
    }

    /**
     * @param periods
     *            the periods to set
     */
    public void setPeriods(List<LabelValueBean> periods) {
        this.periods = periods;
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
     * @return the yearMonths
     */
    public List<NCCQU010DateInfo> getYearMonths() {
        return yearMonths;
    }

    /**
     * @param yearMonths
     *            the yearMonths to set
     */
    public void setYearMonths(List<NCCQU010DateInfo> yearMonths) {
        this.yearMonths = yearMonths;
    }

    /**
     * @return the latestSixMonthCategoryData
     */
    public List<CategoryStats> getLatestSixMonthCategoryData() {
        return latestSixMonthCategoryData;
    }

    /**
     * @param latestSixMonthCategoryData
     *            the latestSixMonthCategoryData to set
     */
    public void setLatestSixMonthCategoryData(List<CategoryStats> latestSixMonthCategoryData) {
        this.latestSixMonthCategoryData = latestSixMonthCategoryData;
    }

    /**
     * @return the latestYearCategoryData
     */
    public List<CategoryStats> getLatestYearCategoryData() {
        return latestYearCategoryData;
    }

    /**
     * @param latestYearCategoryData
     *            the latestYearCategoryData to set
     */
    public void setLatestYearCategoryData(List<CategoryStats> latestYearCategoryData) {
        this.latestYearCategoryData = latestYearCategoryData;
    }

    /**
     * @return the latestTwoYearCategoryData
     */
    public List<CategoryStats> getLatestTwoYearCategoryData() {
        return latestTwoYearCategoryData;
    }

    /**
     * @param latestTwoYearCategoryData
     *            the latestTwoYearCategoryData to set
     */
    public void setLatestTwoYearCategoryData(List<CategoryStats> latestTwoYearCategoryData) {
        this.latestTwoYearCategoryData = latestTwoYearCategoryData;
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
     * @return the queryResult
     */
    public Integer getQueryResult() {
        return queryResult;
    }

    /**
     * @param queryResult
     *            the queryResult to set
     */
    public void setQueryResult(Integer queryResult) {
        this.queryResult = queryResult;
    }

    /**
     * @return the categoryDetails
     */
    public List<CategoryDetailResRep> getCategoryDetails() {
        return categoryDetails;
    }

    /**
     * @param categoryDetails
     *            the categoryDetails to set
     */
    public void setCategoryDetails(List<CategoryDetailResRep> categoryDetails) {
        this.categoryDetails = categoryDetails;
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
     * @return the txnAmtSumDisplay
     */
    public String getTxnAmtSumDisplay() {
        return this.txnAmtSumDisplay;
    }

    /**
     * @param txnAmtSumDisplay
     *            the txnAmtSumDisplay to set
     */
    public void setTxnAmtSumDisplay(String txnAmtSumDisplay) {
        this.txnAmtSumDisplay = txnAmtSumDisplay;
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

    /**
     * @return the categoryGroup
     */
    public String getCategoryGroup() {
        return categoryGroup;
    }

    /**
     * @param categoryGroup
     *            the categoryGroup to set
     */
    public void setCategoryGroup(String categoryGroup) {
        this.categoryGroup = categoryGroup;
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
