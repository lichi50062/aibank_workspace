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

import java.util.ArrayList;
import java.util.List;

//@formatter:off
/**
* @(#)NCCQU008Output.java
* 
* <p>Description: NCCQU008Output 物件</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/02/07, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCCQU008Output {

    /**
     * 每月預估金額
     */
    private NCCQU008MonthlyEstimate monthlyEstimate;

    /** 分期方案 */
    private List<NCCQU008InsInterestSection> insInterestSection = new ArrayList<>();

    /** 其他分期方案 */
    private List<NCCQU008InsInterestSection> otherInsInterestSection = new ArrayList<>();

    public NCCQU008MonthlyEstimate getMonthlyEstimate() {
        return monthlyEstimate;
    }

    public void setMonthlyEstimate(NCCQU008MonthlyEstimate monthlyEstimate) {
        this.monthlyEstimate = monthlyEstimate;
    }

    public List<NCCQU008InsInterestSection> getInsInterestSection() {
        return insInterestSection;
    }

    public void setInsInterestSection(List<NCCQU008InsInterestSection> insInterestSection) {
        this.insInterestSection = insInterestSection;
    }

    public List<NCCQU008InsInterestSection> getOtherInsInterestSection() {
        return otherInsInterestSection;
    }

    public void setOtherInsInterestSection(List<NCCQU008InsInterestSection> otherInsInterestSection) {
        this.otherInsInterestSection = otherInsInterestSection;
    }

}
