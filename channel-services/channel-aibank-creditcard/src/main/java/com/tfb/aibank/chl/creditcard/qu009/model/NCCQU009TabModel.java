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
package com.tfb.aibank.chl.creditcard.qu009.model;

// @formatter:off
/**
 * @(#)NCCQU009TabModel.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/19, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU009TabModel {

    /**
     * tab 顯示 最新～月份
     */
    private String tabDisplay;
    /**
     * -1(最新) 0(本期) 1(前一期) 2(前二期) 3(前三期) 4(前四期) 5(前五期) 6(前六期)
     * 
     */
    private String tabId;

    /** 年 */
    private String year;

    /** 本期 ~ 前X期 */
    private String tabDisplayText;

    /**
     * @return the tabDisplay
     */
    public String getTabDisplay() {
        return tabDisplay;
    }

    /**
     * @param tabDisplay
     *            the tabDisplay to set
     */
    public void setTabDisplay(String tabDisplay) {
        this.tabDisplay = tabDisplay;
    }

    /**
     * @return the tabId
     */
    public String getTabId() {
        return tabId;
    }

    /**
     * @param tabId
     *            the tabId to set
     */
    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year
     *            the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the tabDisplayText
     */
    public String getTabDisplayText() {
        return tabDisplayText;
    }

    /**
     * @param tabDisplayText
     *            the tabDisplayText to set
     */
    public void setTabDisplayText(String tabDisplayText) {
        this.tabDisplayText = tabDisplayText;
    }
}
