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
package com.tfb.aibank.common.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// @formatter:off
/**
 * @(#)ServiceBase.java
 * 
 * <p>Description:服務據點物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/17, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ServiceBase {
    /** 據點名稱 */
    private String name;
    /** 緯度 */
    private BigDecimal latitude;
    /** 經度 */
    private BigDecimal longitude;
    /** 距離 */
    private BigDecimal distance;
    /** 範圍內距離圓心的距離 */
    private BigDecimal radiusDistance;
    /** 地址 */
    private String address;
    /** 服務項目標籤 */
    private List<String> tabTypes = new ArrayList<>();
    /** 服務據點類型 */
    private Integer serviceType;
    /** 地區 */
    private String area;
    /** 分區 */
    private String areaDetail;
    /** 分行代號 */
    private String branchCode;
    /** 提醒說明 */
    private String desc;
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the distance
     */
    public BigDecimal getDistance() {
        return distance;
    }
    
    /**
     * @param distance
     *            the distance to set
     */
    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }
    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * @return the tabTypes
     */
    public List<String> getTabTypes() {
        return tabTypes;
    }

    /**
     * @param tabTypes
     *            the tabTypes to set
     */
    public void setTabTypes(List<String> tabTypes) {
        this.tabTypes = tabTypes;
    }

    /**
     * @return the serviceType
     */
    public Integer getServiceType() {
        return serviceType;
    }

    /**
     * @param serviceType
     *            the serviceType to set
     */
    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
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
     * @return the branchCode
     */
    public String getBranchCode() {
        return branchCode;
    }

    /**
     * @param branchCode
     *            the branchCode to set
     */
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }
    
    /**
     * @param desc
     *            the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
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

    /**
     * @return the radiusDistance
     */
    public BigDecimal getRadiusDistance() {
        return radiusDistance;
    }

    /**
     * @param radiusDistance
     *            the radiusDistance to set
     */
    public void setRadiusDistance(BigDecimal radiusDistance) {
        this.radiusDistance = radiusDistance;
    }


}
