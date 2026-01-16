package com.tfb.aibank.chl.creditcard.qu008.model;

import com.ibm.tw.ibmb.base.model.RsData;

import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NCCQU008012Rs.java
 * 
 * <p>Description:信用卡分期管理 012 每月預估金額</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/02/07, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU008012Rs implements RsData {

    /**
     * 每月預估金額
     */
    private NCCQU008MonthlyEstimate monthlyEstimate;

    public NCCQU008MonthlyEstimate getMonthlyEstimate() {
        return monthlyEstimate;
    }

    public void setMonthlyEstimate(NCCQU008MonthlyEstimate monthlyEstimate) {
        this.monthlyEstimate = monthlyEstimate;
    }
}
