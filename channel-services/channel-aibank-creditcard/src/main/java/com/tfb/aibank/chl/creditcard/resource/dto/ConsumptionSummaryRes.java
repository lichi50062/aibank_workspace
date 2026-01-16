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
 * @(#)ConsumptionSummaryRes.java
 *
 * <p>Description:消費分析 消費金額彙總資訊查詢 Response</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/07/11 Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ConsumptionSummaryRes {

    /** 最高金額資料 */
    private HighestAmt highestAmt;

    /** 交易彙整資料 */
    private List<ChargesStats> chargesStats = new ArrayList<>();

    /** 查詢結果: 0: 查詢成功, 1: 查詢失敗 3:沒有資料 4:成功,但還有資料 */
    private int queryResult;

    /**
     * @return the highestAmt
     */
    public HighestAmt getHighestAmt() {
        return highestAmt;
    }

    /**
     * @param highestAmt
     *            the highestAmt to set
     */
    public void setHighestAmt(HighestAmt highestAmt) {
        this.highestAmt = highestAmt;
    }

    /**
     * @return the chargeStats
     */
    public List<ChargesStats> getChargeStats() {
        return chargesStats;
    }

    /**
     * @param chargesStats
     *            the chargeStats to set
     */
    public void setChargeStats(List<ChargesStats> chargesStats) {
        this.chargesStats = chargesStats;
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
