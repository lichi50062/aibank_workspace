/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.creditcard.qu008.model;

import java.util.List;

// @formatter:off
/**
 * @(#) NCCQU008InsInterestSection.java
 * 
 * <p>Description:分期利息區塊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/04, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU008InsInterestSection {

    /**
     * 存期 ex: 分3期
     */
    private String period;

    /**
     * 存期 ex: 3 純數字
     */
    private Integer periodOrigin;

    /**
     * 是否最熱門
     */
    private Boolean isMostPopular;

    /**
     * 是否最輕鬆
     */
    private Boolean isMostEasy;

    /**
     * 利息相關描述
     */
    private String interestDesc;

    /** 利息計算結果 */
    private List<NCCQU008InterestCalResult> interestCalResults;

    /**
     * @return the period
     */
    public String getPeriod() {
        return period;
    }

    /**
     * @param period
     *            the period to set
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * @return the periodOrigin
     */
    public Integer getPeriodOrigin() {
        return periodOrigin;
    }

    /**
     * @param periodOrigin
     *            the periodOrigin to set
     */
    public void setPeriodOrigin(Integer periodOrigin) {
        this.periodOrigin = periodOrigin;
    }

    /**
     * @return the isMostPopular
     */
    public Boolean getIsMostPopular() {
        return isMostPopular;
    }

    /**
     * @param isMostPopular
     *            the isMostPopular to set
     */
    public void setIsMostPopular(Boolean isMostPopular) {
        this.isMostPopular = isMostPopular;
    }

    /**
     * @return the isMostEasy
     */
    public Boolean getIsMostEasy() {
        return isMostEasy;
    }

    /**
     * @param isMostEasy
     *            the isMostEasy to set
     */
    public void setIsMostEasy(Boolean isMostEasy) {
        this.isMostEasy = isMostEasy;
    }

    /**
     * @return the interestDesc
     */
    public String getInterestDesc() {
        return interestDesc;
    }

    /**
     * @param interestDesc
     *            the interestDesc to set
     */
    public void setInterestDesc(String interestDesc) {
        this.interestDesc = interestDesc;
    }

    /**
     * @return the interestCalResults
     */
    public List<NCCQU008InterestCalResult> getInterestCalResults() {
        return interestCalResults;
    }

    /**
     * @param interestCalResults
     *            the interestCalResults to set
     */
    public void setInterestCalResults(List<NCCQU008InterestCalResult> interestCalResults) {
        this.interestCalResults = interestCalResults;
    }

}
