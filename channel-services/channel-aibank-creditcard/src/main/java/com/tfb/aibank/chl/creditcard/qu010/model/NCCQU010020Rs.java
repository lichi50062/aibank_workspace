package com.tfb.aibank.chl.creditcard.qu010.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.annotations.FormatDate;
import com.ibm.tw.ibmb.base.model.RsData;
import com.ibm.tw.ibmb.model.LabelValueBean;

// @formatter:off
/**
 * @(#)NCCQU010020Rs.java
 * 
 * <p>Description:消費分析 020 趨勢分析頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/25, Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU010020Rs implements RsData {

    /** 查詢結果: 0: 查詢成功, 1: 查詢失敗 */
    private int queryResult;

    /** 時間區間清單 */
    private List<LabelValueBean> periods;

    /** 預設時間區間 */
    private int defaultPeriod = 0;

    /** 近半年平均消費金額 */
    private String latestSixMonthAvgAmtDisplay;

    /** 近一年平均消費金額 */
    private String latestYearAvgAmtDisplay;

    /** 近半年By月份消費匯總資料 */
    private List<NCCQU010MonthConsumptionData> latestSixMonthConsumptionData = new ArrayList<>();

    /** 近一年By月份消費匯總資料 */
    private List<NCCQU010MonthConsumptionData> latestYearMonthConsumptionData = new ArrayList<>();

    /** 交易日期 */
    @FormatDate(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date txnDateTime;

    /** 條件式版位是否啟用 */
    private boolean conditionalMessageEnabled;

    /** 今年度 */
    private String thisYear;

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
     * @return the latestSixMonthAvgAmtDisplay
     */
    public String getLatestSixMonthAvgAmtDisplay() {
        return latestSixMonthAvgAmtDisplay;
    }

    /**
     * @param latestSixMonthAvgAmtDisplay
     *            the latestSixMonthAvgAmtDisplay to set
     */
    public void setLatestSixMonthAvgAmtDisplay(String latestSixMonthAvgAmtDisplay) {
        this.latestSixMonthAvgAmtDisplay = latestSixMonthAvgAmtDisplay;
    }

    /**
     * @return the latestYearAvgAmtDisplay
     */
    public String getLatestYearAvgAmtDisplay() {
        return latestYearAvgAmtDisplay;
    }

    /**
     * @param latestYearAvgAmtDisplay
     *            the latestYearAvgAmtDisplay to set
     */
    public void setLatestYearAvgAmtDisplay(String latestYearAvgAmtDisplay) {
        this.latestYearAvgAmtDisplay = latestYearAvgAmtDisplay;
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
     * @return the txnDateTime
     */
    public Date getTxnDateTime() {
        return txnDateTime;
    }

    /**
     * @param txnDateTime
     *            the txnDateTime to set
     */
    public void setTxnDateTime(Date txnDateTime) {
        this.txnDateTime = txnDateTime;
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
     * @return the thisYear
     */
    public String getThisYear() {
        return thisYear;
    }

    /**
     * @param thisYear
     *            the thisYear to set
     */
    public void setThisYear(String thisYear) {
        this.thisYear = thisYear;
    }

    /**
     * @return the defaultPeriod
     */
    public int getDefaultPeriod() {
        return defaultPeriod;
    }

    /**
     * @param defaultPeriod
     *            the defaultPeriod to set
     */
    public void setDefaultPeriod(int defaultPeriod) {
        this.defaultPeriod = defaultPeriod;
    }

}
