package com.tfb.aibank.chl.creditcard.qu010.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCQU010031Rs.java
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
public class NCCQU010031Rs implements RsData {

    /** 查詢結果: 0: 查詢成功, 1: 查詢失敗 */
    private int queryResult;

    /** 指定月份指定類別消費明細資料 */
    private NCCQU010MonthDetailData monthDetailData;

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
}
