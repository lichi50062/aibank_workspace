/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2025.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.remititemlevelthree;

import java.io.Serializable;
import java.util.Date;

// @formatter:off
/**
 * @(#)RemitItemLevelTwo.java
 * 
 * <p>Description:匯款性質中項說明</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/07/01, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class RemitItemLevelTwo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 中項鍵值
     */
    private Integer levelTwoKey;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 大項鍵值
     */
    private Integer levelOneKey;

    /**
     * 匯款性質中項說明
     */
    private String levelTwoDescription;

    /**
     * 匯款性質中項名稱
     */
    private String levelTwoName;

    /**
     * 匯款性質中項代號
     */
    private Integer levelTwoNo;

    /**
     * 語系
     */
    private String locale;

    /**
     * 更新時間
     */
    private Date updateTime;

    /**
     * 取得建立時間
     * 
     * @return Date 建立時間
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 設定建立時間
     * 
     * @param createTime
     *            要設定的建立時間
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得大項鍵值
     * 
     * @return int 大項鍵值
     */
    public Integer getLevelOneKey() {
        return this.levelOneKey;
    }

    /**
     * 設定大項鍵值
     * 
     * @param levelOneKey
     *            要設定的大項鍵值
     */
    public void setLevelOneKey(Integer levelOneKey) {
        this.levelOneKey = levelOneKey;
    }

    /**
     * 取得匯款性質中項說明
     * 
     * @return String 匯款性質中項說明
     */
    public String getLevelTwoDescription() {
        return this.levelTwoDescription;
    }

    /**
     * 設定匯款性質中項說明
     * 
     * @param levelTwoDescription
     *            要設定的匯款性質中項說明
     */
    public void setLevelTwoDescription(String levelTwoDescription) {
        this.levelTwoDescription = levelTwoDescription;
    }

    /**
     * 取得中項鍵值
     * 
     * @return int 中項鍵值
     */
    public Integer getLevelTwoKey() {
        return this.levelTwoKey;
    }

    /**
     * 設定中項鍵值
     * 
     * @param levelTwoKey
     *            要設定的中項鍵值
     */
    public void setLevelTwoKey(Integer levelTwoKey) {
        this.levelTwoKey = levelTwoKey;
    }

    /**
     * 取得匯款性質中項名稱
     * 
     * @return String 匯款性質中項名稱
     */
    public String getLevelTwoName() {
        return this.levelTwoName;
    }

    /**
     * 設定匯款性質中項名稱
     * 
     * @param levelTwoName
     *            要設定的匯款性質中項名稱
     */
    public void setLevelTwoName(String levelTwoName) {
        this.levelTwoName = levelTwoName;
    }

    /**
     * 取得匯款性質中項代號
     * 
     * @return int 匯款性質中項代號
     */
    public Integer getLevelTwoNo() {
        return this.levelTwoNo;
    }

    /**
     * 設定匯款性質中項代號
     * 
     * @param levelTwoNo
     *            要設定的匯款性質中項代號
     */
    public void setLevelTwoNo(Integer levelTwoNo) {
        this.levelTwoNo = levelTwoNo;
    }

    /**
     * 取得語系
     * 
     * @return String 語系
     */
    public String getLocale() {
        return this.locale;
    }

    /**
     * 設定語系
     * 
     * @param locale
     *            要設定的語系
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * 取得更新時間
     * 
     * @return Date 更新時間
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 設定更新時間
     * 
     * @param updateTime
     *            要設定的更新時間
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
