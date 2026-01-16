package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.Date;


/**
 * 縣市.
 */
public class CityModel {

    /**
     * 縣市代碼.
     */
    private String cityCode1;

    /**
     * 縣市代號2.
     */
    private String cityCode2;

    /**
     * The Locale.
     */
    private String locale;

    /**
     * 縣市名稱.
     */
    private String cityName;

    /**
     * 排序.
     */
    private Integer citySort;

    /**
     * 建立時間.
     */
    private Date createTime;

    /**
     * 更新時間.
     */
    private Date updateTime;


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
     * Gets city code 2.
     *
     * @return the city code 2
     */
    public String getCityCode2() {
        return cityCode2;
    }

    /**
     * Sets city code 2.
     *
     * @param cityCode2
     *            the city code 2
     */
    public void setCityCode2(String cityCode2) {
        this.cityCode2 = cityCode2;
    }

    /**
     * Gets locale.
     *
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Sets locale.
     *
     * @param locale
     *            the locale
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * Gets city name.
     *
     * @return the city name
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Sets city name.
     *
     * @param cityName
     *            the city name
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * Gets city sort.
     *
     * @return the city sort
     */
    public Integer getCitySort() {
        return citySort;
    }

    /**
     * Sets city sort.
     *
     * @param citySort
     *            the city sort
     */
    public void setCitySort(Integer citySort) {
        this.citySort = citySort;
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
