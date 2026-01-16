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
 * @(#)RemitItemLevelOne.java
 * 
 * <p>Description:匯款性質大項說明</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/07/01, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class RemitItemLevelOne implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 資料鍵值
     */
    private Integer levelOneKey;

    /**
     * 國別
     */
    private String country;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 幣別
     */
    private String curr;

    /**
     * 受款人身分別
     */
    private String inType;

    /**
     * 匯款性質大項說明
     */
    private String levelOneDescription;

    /**
     * 匯款性質大項名稱
     */
    private String levelOneName;

    /**
     * 匯款性質大項代號
     */
    private Integer levelOneNo;

    /**
     * 語系
     */
    private String locale;

    /**
     * 匯款人身分別
     */
    private String outType;

    /**
     * 更新時間
     */
    private Date updateTime;

    /**
     * 取得國別
     * 
     * @return String 國別
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * 設定國別
     * 
     * @param country
     *            要設定的國別
     */
    public void setCountry(String country) {
        this.country = country;
    }

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
     * 取得幣別
     * 
     * @return String 幣別
     */
    public String getCurr() {
        return this.curr;
    }

    /**
     * 設定幣別
     * 
     * @param curr
     *            要設定的幣別
     */
    public void setCurr(String curr) {
        this.curr = curr;
    }

    /**
     * 取得受款人身分別
     * 
     * @return String 受款人身分別
     */
    public String getInType() {
        return this.inType;
    }

    /**
     * 設定受款人身分別
     * 
     * @param inType
     *            要設定的受款人身分別
     */
    public void setInType(String inType) {
        this.inType = inType;
    }

    /**
     * 取得匯款性質大項說明
     * 
     * @return String 匯款性質大項說明
     */
    public String getLevelOneDescription() {
        return this.levelOneDescription;
    }

    /**
     * 設定匯款性質大項說明
     * 
     * @param levelOneDescription
     *            要設定的匯款性質大項說明
     */
    public void setLevelOneDescription(String levelOneDescription) {
        this.levelOneDescription = levelOneDescription;
    }

    /**
     * 取得資料鍵值
     * 
     * @return int 資料鍵值
     */
    public Integer getLevelOneKey() {
        return this.levelOneKey;
    }

    /**
     * 設定資料鍵值
     * 
     * @param levelOneKey
     *            要設定的資料鍵值
     */
    public void setLevelOneKey(Integer levelOneKey) {
        this.levelOneKey = levelOneKey;
    }

    /**
     * 取得匯款性質大項名稱
     * 
     * @return String 匯款性質大項名稱
     */
    public String getLevelOneName() {
        return this.levelOneName;
    }

    /**
     * 設定匯款性質大項名稱
     * 
     * @param levelOneName
     *            要設定的匯款性質大項名稱
     */
    public void setLevelOneName(String levelOneName) {
        this.levelOneName = levelOneName;
    }

    /**
     * 取得匯款性質大項代號
     * 
     * @return int 匯款性質大項代號
     */
    public Integer getLevelOneNo() {
        return this.levelOneNo;
    }

    /**
     * 設定匯款性質大項代號
     * 
     * @param levelOneNo
     *            要設定的匯款性質大項代號
     */
    public void setLevelOneNo(Integer levelOneNo) {
        this.levelOneNo = levelOneNo;
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
     * 取得匯款人身分別
     * 
     * @return String 匯款人身分別
     */
    public String getOutType() {
        return this.outType;
    }

    /**
     * 設定匯款人身分別
     * 
     * @param outType
     *            要設定的匯款人身分別
     */
    public void setOutType(String outType) {
        this.outType = outType;
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
