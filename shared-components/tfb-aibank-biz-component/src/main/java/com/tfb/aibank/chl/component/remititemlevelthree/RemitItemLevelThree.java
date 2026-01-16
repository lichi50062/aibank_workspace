package com.tfb.aibank.chl.component.remititemlevelthree;

import java.util.Date;

import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)RemitItemLevelThree.java
 *
 * <p>Description:[匯款性質細項說明 Model]</p>
 *
 * <p>Modify History:</p>
 * <ol>v1.0, 2023/7/2,
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class RemitItemLevelThree {

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 大項鍵值
     */
    private Integer levelOneKey;

    /**
     * 匯款性質排序
     */
    private Integer remitSort;

    /**
     * 細項鍵值
     */
    private Integer levelThreeKey;

    /**
     * 中項鍵值
     */
    private Integer levelTwoKey;

    /**
     * 語系
     */
    private String locale;

    /**
     * 匯款性質代碼
     */
    private String remitCode;

    /**
     * 匯款性質說明
     */
    private String remitDescription;

    /**
     * 匯款性質名稱
     */
    private String remitName;

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
     *
     */
    private String remitCodeO;

    /**
     * 大額換匯申報
     */
    private boolean largeRemitItem;

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
     * @return Integer 大項鍵值
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
     * 取得細項鍵值
     *
     * @return Integer 細項鍵值
     */
    public Integer getLevelThreeKey() {
        return this.levelThreeKey;
    }

    /**
     * 設定細項鍵值
     *
     * @param levelThreeKey
     *            要設定的細項鍵值
     */
    public void setLevelThreeKey(Integer levelThreeKey) {
        this.levelThreeKey = levelThreeKey;
    }

    /**
     * 取得中項鍵值
     *
     * @return Integer 中項鍵值
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

    /**
     * 取得 remitSort
     *
     * @return 傳回 remitSort。
     */
    public Integer getRemitSort() {
        return remitSort;
    }

    /**
     * 設定 remitSort
     *
     * @param remitSort
     *            要設定的 remitSort。
     */
    public void setRemitSort(Integer remitSort) {
        this.remitSort = remitSort;
    }

    public String getRemitCodeO() {
        return remitCodeO;
    }

    public void setRemitCodeO(String remitCodeO) {
        this.remitCodeO = remitCodeO;
    }

    public boolean isLargeRemitItem() {
        return largeRemitItem;
    }

    public void setLargeRemitItem(boolean largeRemitItem) {
        this.largeRemitItem = largeRemitItem;
    }

    /**
     * 取得 remitCodeValue
     */
    public String getRemitCodeValue() {
        if (StringUtils.isBlank(this.remitValue)) {
            return this.remitCode;
        }
        else {
            return this.remitCode + '-' + this.remitValue;
        }
    }

}
