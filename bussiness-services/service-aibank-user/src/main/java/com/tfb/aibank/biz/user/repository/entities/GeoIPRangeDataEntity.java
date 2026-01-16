package com.tfb.aibank.biz.user.repository.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

// @formatter:off
/**
 * @(#)GeoIPRangeDataEntity.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "GEOIP_RANGE_DATA")
@IdClass(com.tfb.aibank.biz.user.repository.entities.pk.GeoIPRangeDataEntityPk.class)
public class GeoIPRangeDataEntity {

    /**
     * IP範圍結束位置
     */
    @Id
    @Column(name = "END_IP")
    private Long endIp;

    /**
     * 位置代碼 (GEOIP_LOCATION_DATA.LOCATION_ID)
     */
    @Basic
    @Column(name = "LOCATION_ID")
    private Integer locationId;

    /**
     * IP範圍起始位置
     */
    @Id
    @Column(name = "START_IP")
    private Long startIp;

    /**
     * Location Data
     */
    @Transient
    private GeoIPLocationDataEntity locationDataEntity;

    /**
     * 取得IP範圍結束位置
     * 
     * @return Integer IP範圍結束位置
     */
    public Long getEndIp() {
        return this.endIp;
    }

    /**
     * 設定IP範圍結束位置
     * 
     * @param endIp
     *            要設定的IP範圍結束位置
     */
    public void setEndIp(Long endIp) {
        this.endIp = endIp;
    }

    /**
     * 取得位置代碼 (GEOIP_LOCATION_DATA.LOCATION_ID)
     * 
     * @return Integer 位置代碼 (GEOIP_LOCATION_DATA.LOCATION_ID)
     */
    public Integer getLocationId() {
        return this.locationId;
    }

    /**
     * 設定位置代碼 (GEOIP_LOCATION_DATA.LOCATION_ID)
     * 
     * @param locationId
     *            要設定的位置代碼 (GEOIP_LOCATION_DATA.LOCATION_ID)
     */
    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    /**
     * 取得IP範圍起始位置
     * 
     * @return Integer IP範圍起始位置
     */
    public Long getStartIp() {
        return this.startIp;
    }

    /**
     * 設定IP範圍起始位置
     * 
     * @param startIp
     *            要設定的IP範圍起始位置
     */
    public void setStartIp(Long startIp) {
        this.startIp = startIp;
    }

    /**
     * 取得 locationDataEntity
     * 
     * @return 傳回 locationDataEntity。
     */
    public GeoIPLocationDataEntity getLocationDataEntity() {
        return locationDataEntity;
    }

    /**
     * 設定 locationDataEntity
     * 
     * @param locationDataEntity
     *            要設定的 locationDataEntity。
     */
    public void setLocationDataEntity(GeoIPLocationDataEntity locationDataEntity) {
        this.locationDataEntity = locationDataEntity;
    }
}
