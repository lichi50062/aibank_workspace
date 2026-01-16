package com.tfb.aibank.chl.creditcard.qu010.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.tfb.aibank.chl.creditcard.resource.dto.CategoryDetailResRep;
import com.tfb.aibank.chl.creditcard.resource.dto.CategoryStats;
import com.tfb.aibank.chl.creditcard.resource.dto.ChargesStats;
import com.tfb.aibank.chl.model.creditcard.CreditCard;

// @formatter:off
/**
 * @(#)NCCQU010Input.java
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
public class NCCQU010Input {

    /** 指定分析月 */
    private String selectedYearMonth;

    /** 分析起始月 */
    private String startYearMonth;

    /** 分析結束月 */
    private String endYearMonth;

    /** 指定消費類別 */
    private String categoryGroup;

    /** 消費明細類別 */
    private String itemCategory;

    /** 近半年分析年月 */
    private List<String> latestSixMonthYearMonths = new ArrayList<>();
    
    /** 近一年分析年月 */
    private List<String> latestYearAnalysisYearMonths = new ArrayList<>();
    
    /** Credit cards */
    private List<CreditCard> creditCards;

    /** 近半年消費平均 */
    private BigDecimal latestSixMonthConsumptionAvg;

    /** 近一年消費平均 */
    private BigDecimal latestYearConsumptionAvg;

    /** 近2年交易彙整資料 */
    private List<ChargesStats> latestTwoYearConsumptionData = new ArrayList<>();

    /** 近半年消費類別資料 */
    private List<CategoryStats> latestSixMonthCategoryData = new ArrayList<>();

    /** 近一年消費類別資料 */
    private List<CategoryStats> latestYearCategoryData = new ArrayList<>();

    /** 近2年消費類別資料 */
    private List<CategoryStats> latestTwoYearCategoryData = new ArrayList<>();

    /** 各消費明細資料 */
    private List<CategoryDetailResRep> categoryDetails = new ArrayList<>();

    /** 語系設定 **/
    private Locale locale;
    
    /** 搜尋關鍵字 */
    private String searchKeyword;
    
    /** 分頁 */
    private Integer skip;

    /**
     * @return the selectedYearMonth
     */
    public String getSelectedYearMonth() {
        return selectedYearMonth;
    }

    /**
     * @param selectedYearMonth
     *            the selectedYearMonth to set
     */
    public void setSelectedYearMonth(String selectedYearMonth) {
        this.selectedYearMonth = selectedYearMonth;
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
     * @return the itemCategory
     */
    public String getItemCategory() {
        return itemCategory;
    }

    /**
     * @param itemCategory
     *            the itemCategory to set
     */
    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
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
     * @return the creditCards
     */
    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    /**
     * @param creditCards
     *            the creditCards to set
     */
    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
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
     * @return the locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * @param locale
     * Set the locale
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    /**
     * @return the searchKeyword
     */
    public String getSearchKeyword() {
        return searchKeyword;
    }

    /**
     * @param searchKeyword
     *            the searchKeyword to set
     */
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    /**
     * @return the skip
     */
    public Integer getSkip() {
        return skip;
    }

    /**
     * @param skip
     *            the skip to set
     */
    public void setSkip(Integer skip) {
        this.skip = skip;
    }
}
