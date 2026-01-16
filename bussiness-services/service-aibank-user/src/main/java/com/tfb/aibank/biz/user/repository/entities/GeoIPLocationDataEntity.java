package com.tfb.aibank.biz.user.repository.entities;

import java.math.BigDecimal;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)GeoIPLocationDataEntity.java
 * 
 * <p>Description:GEO IP LITE CITY IP位置城市對應表 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "GEOIP_LOCATION_DATA")
public class GeoIPLocationDataEntity {

    /**
     * 區碼(US)
     */
    @Basic
    @Column(name = "AREA_CODE")
    private String areaCode;

    /**
     * 城市名稱
     */
    @Basic
    @Column(name = "CITY_NAME")
    private String cityName;

    /**
     * 國碼
     */
    @Basic
    @Column(name = "COUNTRY_CODE")
    private String countryCode;

    /**
     * 國碼
     */
    @Basic
    @Column(name = "COUNTRY_NAME")
    private String countryName;

    /**
     * 緯度
     */
    @Basic
    @Column(name = "LATITUDE")
    private BigDecimal latitude;

    /**
     * LOCATION ID
     */
    @Id
    @Column(name = "LOCATION_ID")
    private Integer locationId;

    /**
     * 經度
     */
    @Basic
    @Column(name = "LONGITUDE")
    private BigDecimal longitude;

    /**
     * 都會區代碼(US)
     */
    @Basic
    @Column(name = "METRO_CODE")
    private Integer metroCode;

    /**
     * 郵遞區號
     */
    @Basic
    @Column(name = "POSTAL_CODE")
    private String postalCode;

    /**
     * 區碼
     */
    @Basic
    @Column(name = "REGION_CODE")
    private String regionCode;

    /**
     * 取得區碼(US)
     * 
     * @return String 區碼(US)
     */
    public String getAreaCode() {
        return this.areaCode;
    }

    /**
     * 設定區碼(US)
     * 
     * @param areaCode
     *            要設定的區碼(US)
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * 取得城市名稱
     * 
     * @return String 城市名稱
     */
    public String getCityName() {
        return this.cityName;
    }

    /**
     * 設定城市名稱
     * 
     * @param cityName
     *            要設定的城市名稱
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * 取得國碼
     * 
     * @return String 國碼
     */
    public String getCountryCode() {
        return this.countryCode;
    }

    /**
     * 設定國碼
     * 
     * @param countryCode
     *            要設定的國碼
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * 取得 countryName
     * 
     * @return 傳回 countryName。
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * 設定 countryName
     * 
     * @param countryName
     *            要設定的 countryName。
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * 取得緯度
     * 
     * @return BigDecimal 緯度
     */
    public BigDecimal getLatitude() {
        return this.latitude;
    }

    /**
     * 設定緯度
     * 
     * @param latitude
     *            要設定的緯度
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * 取得LOCATION ID
     * 
     * @return Integer LOCATION ID
     */
    public Integer getLocationId() {
        return this.locationId;
    }

    /**
     * 設定LOCATION ID
     * 
     * @param locationId
     *            要設定的LOCATION ID
     */
    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    /**
     * 取得經度
     * 
     * @return BigDecimal 經度
     */
    public BigDecimal getLongitude() {
        return this.longitude;
    }

    /**
     * 設定經度
     * 
     * @param longitude
     *            要設定的經度
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * 取得都會區代碼(US)
     * 
     * @return Integer 都會區代碼(US)
     */
    public Integer getMetroCode() {
        return this.metroCode;
    }

    /**
     * 設定都會區代碼(US)
     * 
     * @param metroCode
     *            要設定的都會區代碼(US)
     */
    public void setMetroCode(Integer metroCode) {
        this.metroCode = metroCode;
    }

    /**
     * 取得郵遞區號
     * 
     * @return String 郵遞區號
     */
    public String getPostalCode() {
        return this.postalCode;
    }

    /**
     * 設定郵遞區號
     * 
     * @param postalCode
     *            要設定的郵遞區號
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * 取得區碼
     * 
     * @return String 區碼
     */
    public String getRegionCode() {
        return this.regionCode;
    }

    /**
     * 設定區碼
     * 
     * @param regionCode
     *            要設定的區碼
     */
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
}
