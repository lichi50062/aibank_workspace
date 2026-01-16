package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.Date;

/**
 * 鄉鎮區.
 */
public class CityAreaModel {

    /**
     * The Area key.
     */
    private Integer areaKey;

    /**
     * 縣市代號1.
     */
    private String cityCode1;

    /**
     * 鄉鎮市代號.
     */
    private String areaCode;

    /**
     * 排序.
     */
    private Integer areaSort;


    /**
     * locale.
     */
    private String locale;

    /**
     * 區域名稱.
     */
    private String areaName;

    /**
     * 建立時間.
     */
    private Date createTime;

    /**
     * 更新時間.
     */
    private Date updateTime;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * Gets area key.
     *
     * @return the area key
     */
    public Integer getAreaKey() {
        return areaKey;
    }

    /**
     * Sets area key.
     *
     * @param areaKey
     *            the area key
     */
    public void setAreaKey(Integer areaKey) {
        this.areaKey = areaKey;
    }

    /**
     * Gets city code 1.
     *
     * @return the city code 1
     */
    public String getCityCode1() {
        return cityCode1;
    }

    /**
     * Sets city code 1.
     *
     * @param cityCode1
     *            the city code 1
     */
    public void setCityCode1(String cityCode1) {
        this.cityCode1 = cityCode1;
    }

    /**
     * Gets area code.
     *
     * @return the area code
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * Sets area code.
     *
     * @param areaCode
     *            the area code
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * Gets area sort.
     *
     * @return the area sort
     */
    public Integer getAreaSort() {
        return areaSort;
    }

    /**
     * Sets area sort.
     *
     * @param areaSort
     *            the area sort
     */
    public void setAreaSort(Integer areaSort) {
        this.areaSort = areaSort;
    }

    /**
     * Gets create time.
     *
     * @return the create time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Sets create time.
     *
     * @param createTime
     *            the create time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * Gets update time.
     *
     * @return the update time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * Sets update time.
     *
     * @param updateTime
     *            the update time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
