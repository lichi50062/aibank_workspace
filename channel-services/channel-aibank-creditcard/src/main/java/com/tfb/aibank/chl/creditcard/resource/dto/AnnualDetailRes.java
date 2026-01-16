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
* @(#)AnnualDetailRes.java
* 
* <p>Description:消費分析 整年消費明細搜尋 Response</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/06/28 Warren Lin
* <ol>
*  <li>初版</li>
* </ol>
*/
// @formatter:on
public class AnnualDetailRes {

    /** 整年消費明細資料 */
    private List<AnnualDetailResRep> annualDetails = new ArrayList<>();

    /** 查詢結果: 0: 查詢成功, 1: 查詢失敗 */
    private int queryResult;

    /**
     * @return the annualDetails
     */
    public List<AnnualDetailResRep> getAnnualDetails() {
        return annualDetails;
    }

    /**
     * Set the annualDetails
     *
     * @param annualDetails
     */
    public void setAnnualDetails(List<AnnualDetailResRep> annualDetails) {
        this.annualDetails = annualDetails;
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
