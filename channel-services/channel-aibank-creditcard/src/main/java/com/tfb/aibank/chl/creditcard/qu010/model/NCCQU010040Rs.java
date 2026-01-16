package com.tfb.aibank.chl.creditcard.qu010.model;

import java.util.List;

import com.ibm.tw.ibmb.base.model.RsData;

import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NCCQU010040Rs.java
 * 
 * <p>Description:消費分析 040 月曆消費分析頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/25, Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU010040Rs implements RsData {

    /** 查詢結果: 0: 查詢成功, 1: 查詢失敗, 2: 無資料 */
    private int queryResult;

    /** 各月份各類別消費明細資料 */
    private NCCQU010MonthDetailData monthDetailData;

    /** 指定分析月 */
    private String selectedYearMonth;

    /** 開始年月 */
    private String startYearMonth;

    /** 結束年月 */
    private String endYearMonth;

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
     *            set the startYearMonth
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
     *            set the endYearMonth
     */
    public void setEndYearMonth(String endYearMonth) {
        this.endYearMonth = endYearMonth;
    }
}
