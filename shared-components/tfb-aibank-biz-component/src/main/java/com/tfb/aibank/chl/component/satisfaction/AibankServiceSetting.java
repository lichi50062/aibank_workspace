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
package com.tfb.aibank.chl.component.satisfaction;

import java.io.Serializable;
import java.util.Date;

// @formatter:off
/**
 * @(#)AibankServiceSettingModel.java
 * 
 * <p>Description:AI Bank滿意度問卷設定資料表</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/20, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AibankServiceSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 資料鍵值 */
    private Integer serviceKey;

    /** 語系 */
    private String locale;

    /** 頁面代號，ex: NPYTX001040 */
    private String pageId;

    /** 頁面名稱 */
    private String pageName;

    /** 建立時間 */
    private Date createTime;

    /** 更新時間 */
    private Date updateTime;

    /** 問卷開關 */
    private Integer showFlag;

    /** 問卷開始日期 */
    private Date startDate;

    /** 問卷結束日期 */
    private Date endDate;

    /** ID末碼 */
    private String userParam;

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
     * 取得頁面代號，ex: NPYTX001040
     * 
     * @return String 頁面代號，ex: NPYTX001040
     */
    public String getPageId() {
        return this.pageId;
    }

    /**
     * 設定頁面代號，ex: NPYTX001040
     * 
     * @param pageId
     *            要設定的頁面代號，ex: NPYTX001040
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    /**
     * 取得頁面名稱
     * 
     * @return String 頁面名稱
     */
    public String getPageName() {
        return this.pageName;
    }

    /**
     * 設定頁面名稱
     * 
     * @param pageName
     *            要設定的頁面名稱
     */
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    /**
     * 取得資料鍵值
     * 
     * @return 資料鍵值
     */
    public Integer getServiceKey() {
        return this.serviceKey;
    }

    /**
     * 設定資料鍵值
     * 
     * @param serviceKey
     *            要設定的資料鍵值
     */
    public void setServiceKey(Integer serviceKey) {
        this.serviceKey = serviceKey;
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

    public Integer getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(Integer showFlag) {
        this.showFlag = showFlag;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUserParam() {
        return userParam;
    }

    public void setUserParam(String userParam) {
        this.userParam = userParam;
    }
}
