package com.tfb.aibank.chl.session.vo;

// @formatter:off
/**
 * @(#)GeoIPRangeDataVo.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/03, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class GeoIPRangeDataVo {

    /**
     * IP範圍結束位置
     */
    private Long endIp;

    /**
     * 位置代碼 (GEOIP_LOCATION_DATA.LOCATION_ID)
     */
    private Integer locationId;

    /**
     * IP範圍起始位置
     */
    private Long startIp;

    /**
     * @return the endIp
     */
    public Long getEndIp() {
        return endIp;
    }

    /**
     * @param endIp
     *            the endIp to set
     */
    public void setEndIp(Long endIp) {
        this.endIp = endIp;
    }

    /**
     * @return the locationId
     */
    public Integer getLocationId() {
        return locationId;
    }

    /**
     * @param locationId
     *            the locationId to set
     */
    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    /**
     * @return the startIp
     */
    public Long getStartIp() {
        return startIp;
    }

    /**
     * @param startIp
     *            the startIp to set
     */
    public void setStartIp(Long startIp) {
        this.startIp = startIp;
    }

}
