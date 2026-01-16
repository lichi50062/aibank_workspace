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
* @(#)CategoryDetailRes.java
* 
* <p>Description:消費分析 各類別消費明細查詢 Response</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/06/28 Warren Lin
* <ol>
*  <li>初版</li>
* </ol>
*/
// @formatter:on
public class CategoryDetailRes {

    /** 整年消費明細資料 */
    private List<CategoryDetailResRep> categoryDetails = new ArrayList<>();

    /** 查詢結果: 0: 查詢成功, 1: 查詢失敗 */
    private int queryResult;

    /**
     * @return the categoryDetails
     */
    public List<CategoryDetailResRep> getCategoryDetails() {
        return categoryDetails;
    }

    /**
     * Set the categoryDetails
     *
     * @param categoryDetails
     */
    public void setCategoryDetails(List<CategoryDetailResRep> categoryDetails) {
        this.categoryDetails = categoryDetails;
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
