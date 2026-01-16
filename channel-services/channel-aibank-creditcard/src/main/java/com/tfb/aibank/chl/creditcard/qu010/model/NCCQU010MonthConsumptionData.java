package com.tfb.aibank.chl.creditcard.qu010.model;

import java.math.BigDecimal;

// @formatter:off
/**
 * @(#)MonthConsumptionData.java
 * 
 * <p>Description:消費分析 輸出專用物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/04, Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU010MonthConsumptionData {
    
    /** 消費年月 */
    private String yearMonth;

    /** 顯示消費月 */
    private String yearMonthDisplay;

    /** 消費月 */
    private int month;

    /** 比近半年平均 */
    private BigDecimal latestHalfYearRatio;

    /** 比近1年平均 */
    private BigDecimal latestYearRatio;

    /** 比去年同月 */
    private BigDecimal yearOnYearRatio;

    /** 消費總額 */
    private BigDecimal consumptionAmt;

    /** 顯示比近半年平均 */
    private String latestHalfYearAvgRatioDisplay;

    /** 顯示比近1年平均 */
    private String latestYearAvgRatioDisplay;
    
    /** 顯示比去年同月 */
    private String yearOnYearRatioDisplay;

    /** 顯示消費總額 */
    private String consumptionAmtDisplay;

    /**
     * @return the yearMonth
     */
    public String getYearMonth() {
        return yearMonth;
    }

    /**
     * @param yearMonth
     *            the yearMonth to set
     */
    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    /**
     * @return the yearMonthDisplay
     */
    public String getYearMonthDisplay() {
        return yearMonthDisplay;
    }

    /**
     * @param yearMonthDisplay
     *            the yearMonthDisplay to set
     */
    public void setYearMonthDisplay(String yearMonthDisplay) {
        this.yearMonthDisplay = yearMonthDisplay;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month
     *            the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return the latestHalfYearRatio
     */
    public BigDecimal getLatestHalfYearRatio() {
        return latestHalfYearRatio;
    }

    /**
     * @param latestHalfYearRatio
     *            the latestHalfYearRatio to set
     */
    public void setLatestHalfYearRatio(BigDecimal latestHalfYearRatio) {
        this.latestHalfYearRatio = latestHalfYearRatio;
    }

    /**
     * @return the latestYearRatio
     */
    public BigDecimal getLatestYearRatio() {
        return latestYearRatio;
    }

    /**
     * @param latestYearRatio
     *            the latestYearRatio to set
     */
    public void setLatestYearRatio(BigDecimal latestYearRatio) {
        this.latestYearRatio = latestYearRatio;
    }

    /**
     * @return the yearOnYearRatio
     */
    public BigDecimal getYearOnYearRatio() {
        return yearOnYearRatio;
    }

    /**
     * @param yearOnYearRatio
     *            the yearOnYearRatio to set
     */
    public void setYearOnYearRatio(BigDecimal yearOnYearRatio) {
        this.yearOnYearRatio = yearOnYearRatio;
    }
    
    /**
     * @return the latestHalfYearAvgRatioDisplay
     */
    public String getLatestHalfYearAvgRatioDisplay() {
        return latestHalfYearAvgRatioDisplay;
    }

    /**
     * @param latestHalfYearAvgRatioDisplay
     *            the latestHalfYearAvgRatioDisplay to set
     */
    public void setLatestHalfYearAvgRatioDisplay(String latestHalfYearAvgRatioDisplay) {
        this.latestHalfYearAvgRatioDisplay = latestHalfYearAvgRatioDisplay;
    }

    /**
     * @return the latestYearAvgRatioDisplay
     */
    public String getLatestYearAvgRatioDisplay() {
        return latestYearAvgRatioDisplay;
    }

    /**
     * @param latestYearAvgRatioDisplay
     *            the latestYearAvgRatioDisplay to set
     */
    public void setLatestYearAvgRatioDisplay(String latestYearAvgRatioDisplay) {
        this.latestYearAvgRatioDisplay = latestYearAvgRatioDisplay;
    }

    /**
     * @return the yearOnYearRatioDisplay
     */
    public String getYearOnYearRatioDisplay() {
        return yearOnYearRatioDisplay;
    }

    /**
     * @param yearOnYearRatioDisplay
     *            the yearOnYearRatioDisplay to set
     */
    public void setYearOnYearRatioDisplay(String yearOnYearRatioDisplay) {
        this.yearOnYearRatioDisplay = yearOnYearRatioDisplay;
    }

    /**
     * @return the consumptionAmtDisplay
     */
    public String getConsumptionAmtDisplay() {
        return consumptionAmtDisplay;
    }

    /**
     * @param consumptionAmtDisplay
     *            the consumptionAmtDisplay to set
     */
    public void setConsumptionAmtDisplay(String consumptionAmtDisplay) {
        this.consumptionAmtDisplay = consumptionAmtDisplay;
    }

    /**
     * @return the consumptionAmt
     */
    public BigDecimal getConsumptionAmt() {
        return consumptionAmt;
    }

    /**
     * @param consumptionAmt
     *            the consumptionAmt to set
     */
    public void setConsumptionAmt(BigDecimal consumptionAmt) {
        this.consumptionAmt = consumptionAmt;
    }
}
