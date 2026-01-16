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
package com.tfb.aibank.chl.general.ag004.model.vo;

// @formatter:off
/**
 * @(#)FxCurrencyVo.java
 * 
 * <p>Description:外幣資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/14, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FxCurrencyVo {

    /** 外幣名稱 */
    private String curName;

    /** 外幣代號 */
    private String curCode;

    /** 外幣排序碼 */
    private String curIndex;

    /** 外幣顯示區塊 0是首頁顯示 1是其他幣別 */
    private String displayArea;

    /** ---------------前端新增-------------------- */
    /** 首頁顯示排序碼 */
    private String homeDisplayIndex;

    /**
     * @return the curName
     */
    public String getCurName() {
        return curName;
    }

    /**
     * @param curName
     *            the curName to set
     */
    public void setCurName(String curName) {
        this.curName = curName;
    }

    /**
     * @return the curCode
     */
    public String getCurCode() {
        return curCode;
    }

    /**
     * @param curCode
     *            the curCode to set
     */
    public void setCurCode(String curCode) {
        this.curCode = curCode;
    }

    /**
     * @return the curIndex
     */
    public String getCurIndex() {
        return curIndex;
    }

    /**
     * @param curIndex
     *            the curIndex to set
     */
    public void setCurIndex(String curIndex) {
        this.curIndex = curIndex;
    }

    /**
     * @return the displayArea
     */
    public String getDisplayArea() {
        return displayArea;
    }

    /**
     * @param displayArea
     *            the displayArea to set
     */
    public void setDisplayArea(String displayArea) {
        this.displayArea = displayArea;
    }

    /**
     * @return the homeDisplayIndex
     */
    public String getHomeDisplayIndex() {
        return homeDisplayIndex;
    }

    /**
     * @param homeDisplayIndex
     *            the homeDisplayIndex to set
     */
    public void setHomeDisplayIndex(String homeDisplayIndex) {
        this.homeDisplayIndex = homeDisplayIndex;
    }

}
