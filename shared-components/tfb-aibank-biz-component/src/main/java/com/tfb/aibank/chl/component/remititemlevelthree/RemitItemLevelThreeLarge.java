package com.tfb.aibank.chl.component.remititemlevelthree;

import java.util.Date;

// @formatter:off
/**
 * @(#)RemitItemLevelThreeModel.java
 *
 * <p>Description:[匯款性質細項說明 Model]</p>
 *
 * <p>Modify History:</p>
 * <ol>v1.0, 2023/7/2,
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class RemitItemLevelThreeLarge {
    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 細項鍵值
     */
    private int levelThreeKey;

    /**
     * 語系
     */
    private String locale;

    /**
     * 匯款性質代碼
     */
    private String remitCode;

    /** 
     * 
     */
    private String remitCodeO;

    /**
     * 匯款性質說明
     */
    private String remitDescription;

    /**
     * 匯款性質名稱
     */
    private String remitName;

    /**
     * 匯款性質排序
     */
    private int remitSort;

    /**
     * 匯款性質類型
     */
    private String remitType;

    /**
     * 匯款性質
     */
    private String remitValue;

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
     * 取得細項鍵值
     * 
     * @return int 細項鍵值
     */
    public int getLevelThreeKey() {
        return this.levelThreeKey;
    }

    /**
     * 設定細項鍵值
     * 
     * @param levelThreeKey
     *            要設定的細項鍵值
     */
    public void setLevelThreeKey(int levelThreeKey) {
        this.levelThreeKey = levelThreeKey;
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
     * 取得匯款性質代碼
     * 
     * @return String 匯款性質代碼
     */
    public String getRemitCode() {
        return this.remitCode;
    }

    /**
     * 設定匯款性質代碼
     * 
     * @param remitCode
     *            要設定的匯款性質代碼
     */
    public void setRemitCode(String remitCode) {
        this.remitCode = remitCode;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getRemitCodeO() {
        return this.remitCodeO;
    }

    /**
     * 設定
     * 
     * @param remitCodeO
     *            要設定的
     */
    public void setRemitCodeO(String remitCodeO) {
        this.remitCodeO = remitCodeO;
    }

    /**
     * 取得匯款性質說明
     * 
     * @return String 匯款性質說明
     */
    public String getRemitDescription() {
        return this.remitDescription;
    }

    /**
     * 設定匯款性質說明
     * 
     * @param remitDescription
     *            要設定的匯款性質說明
     */
    public void setRemitDescription(String remitDescription) {
        this.remitDescription = remitDescription;
    }

    /**
     * 取得匯款性質名稱
     * 
     * @return String 匯款性質名稱
     */
    public String getRemitName() {
        return this.remitName;
    }

    /**
     * 設定匯款性質名稱
     * 
     * @param remitName
     *            要設定的匯款性質名稱
     */
    public void setRemitName(String remitName) {
        this.remitName = remitName;
    }

    /**
     * 取得匯款性質排序
     * 
     * @return int 匯款性質排序
     */
    public int getRemitSort() {
        return this.remitSort;
    }

    /**
     * 設定匯款性質排序
     * 
     * @param remitSort
     *            要設定的匯款性質排序
     */
    public void setRemitSort(int remitSort) {
        this.remitSort = remitSort;
    }

    /**
     * 取得匯款性質類型
     * 
     * @return String 匯款性質類型
     */
    public String getRemitType() {
        return this.remitType;
    }

    /**
     * 設定匯款性質類型
     * 
     * @param remitType
     *            要設定的匯款性質類型
     */
    public void setRemitType(String remitType) {
        this.remitType = remitType;
    }

    /**
     * 取得匯款性質
     * 
     * @return String 匯款性質
     */
    public String getRemitValue() {
        return this.remitValue;
    }

    /**
     * 設定匯款性質
     * 
     * @param remitValue
     *            要設定的匯款性質
     */
    public void setRemitValue(String remitValue) {
        this.remitValue = remitValue;
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
