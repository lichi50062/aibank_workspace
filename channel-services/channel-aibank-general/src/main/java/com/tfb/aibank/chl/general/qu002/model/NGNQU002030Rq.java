package com.tfb.aibank.chl.general.qu002.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NGNQU002030Rq.java
 * 
 * <p>Description:服務據點 030 搜尋結果頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/18, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNQU002030Rq implements RqData {
    private String keyword;
    private String area;
    private String areaDetail;
    private String serviceType;
    private List<Boolean> tagTypes;
    /** 裝置 緯度 */
    private BigDecimal latitude;
    /** 裝置 經度 */
    private BigDecimal longitude;
    /**
     * @return the keyword
     */
    public String getKeyword() {
        return keyword;
    }
    
    /**
     * @param keyword
     *            the keyword to set
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }
    
    /**
     * @param area
     *            the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }
    /**
     * @return the areaDetail
     */
    public String getAreaDetail() {
        return areaDetail;
    }
    
    /**
     * @param areaDetail
     *            the areaDetail to set
     */
    public void setAreaDetail(String areaDetail) {
        this.areaDetail = areaDetail;
    }
    /**
     * @return the serviceType
     */
    public String getServiceType() {
        return serviceType;
    }
    
    /**
     * @param serviceType
     *            the serviceType to set
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    /**
     * @return the tagTypes
     */
    public List<Boolean> getTagTypes() {
        return tagTypes;
    }
    
    /**
     * @param tagTypes
     *            the tagTypes to set
     */
    public void setTagTypes(List<Boolean> tagTypes) {
        this.tagTypes = tagTypes;
    }

    /**
     * @return the latitude
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     *            the latitude to set
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     *            the longitude to set
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

}
