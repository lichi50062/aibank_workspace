/*
 * ===========================================================================
 *
 * IBM Confidential
 *
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.ArrayList;
import java.util.List;

// @formatter:off
/**
* @(#)CategoryRes.java
* 
* <p>Description:消費分析 各消費類別金額查詢 Response</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/06/28 Warren Lin
* <ol>
*  <li>初版</li>
* </ol>
*/
// @formatter:on
public class CategoryRes {

    /** 各消費類別金額資料 */
    private List<CategoryStats> categoryStats = new ArrayList<>();

    /** 查詢結果: 0: 查詢成功, 1: 查詢失敗 */
    private int queryResult;

    /**
     * @return the categoryStats
     */
    public List<CategoryStats> getCategoryStats() {
        return categoryStats;
    }

    /**
     * @param categoryStats
     *            the categoryStats to set
     */
    public void setCategoryStats(List<CategoryStats> categoryStats) {
        this.categoryStats = categoryStats;
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

}
