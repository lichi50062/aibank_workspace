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
package com.tfb.aibank.chl.general.resource.dto;

import java.util.Date;

// @formatter:off
/**
 * @(#)AppVersion.java
 * 
 * <p>Description:AI Bank APP版本 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AppVersion {

    /**
     * 生效時間
     */
    private Date activeTime;

    /**
     * 版本號
     */
    private String appVer;

    /**
     * 資料鍵值
     */
    private Integer appVersionKey;

    /**
     * 作業系統類別，IOS、ANDROID
     */
    private String category;

    /**
     * 取得生效時間
     * 
     * @return Date 生效時間
     */
    public Date getActiveTime() {
        return this.activeTime;
    }

    /**
     * 設定生效時間
     * 
     * @param activeTime
     *            要設定的生效時間
     */
    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    /**
     * 取得版本號
     * 
     * @return String 版本號
     */
    public String getAppVer() {
        return this.appVer;
    }

    /**
     * 設定版本號
     * 
     * @param appVer
     *            要設定的版本號
     */
    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }

    /**
     * 取得資料鍵值
     * 
     * @return int 資料鍵值
     */
    public Integer getAppVersionKey() {
        return this.appVersionKey;
    }

    /**
     * 設定資料鍵值
     * 
     * @param appVersionKey
     *            要設定的資料鍵值
     */
    public void setAppVersionKey(Integer appVersionKey) {
        this.appVersionKey = appVersionKey;
    }

    /**
     * 取得作業系統類別，IOS、ANDROID
     * 
     * @return String 作業系統類別，IOS、ANDROID
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * 設定作業系統類別，IOS、ANDROID
     * 
     * @param category
     *            要設定的作業系統類別，IOS、ANDROID
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
