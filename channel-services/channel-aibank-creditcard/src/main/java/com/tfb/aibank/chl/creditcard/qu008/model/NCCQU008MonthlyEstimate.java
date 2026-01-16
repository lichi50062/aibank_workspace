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
package com.tfb.aibank.chl.creditcard.qu008.model;

import java.math.BigDecimal;
import java.util.List;

//@formatter:off
/**
* @(#)NCCQU008MonthlyEstimate.java
* 
* <p>Description: 每月預估金額</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/02/07, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCCQU008MonthlyEstimate {

    /**
     * 總金額
     */
    private BigDecimal totalAmt;

    /**
     * 總金額顯示用
     */
    private String totalAmtDisplay;

    /**
     * 各月分期消費明細
     */
    private List<NCCQU008MonthlyEstimateDetail> details;

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    public String getTotalAmtDisplay() {
        return totalAmtDisplay;
    }

    public void setTotalAmtDisplay(String totalAmtDisplay) {
        this.totalAmtDisplay = totalAmtDisplay;
    }

    public List<NCCQU008MonthlyEstimateDetail> getDetails() {
        return details;
    }

    public void setDetails(List<NCCQU008MonthlyEstimateDetail> details) {
        this.details = details;
    }
}
