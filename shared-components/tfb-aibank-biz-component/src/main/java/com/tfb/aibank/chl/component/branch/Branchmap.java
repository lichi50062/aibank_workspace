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
package com.tfb.aibank.chl.component.branch;

import java.io.Serializable;
import java.util.Locale;

// @formatter:off
/**
 * @(#)BranchmapEntity.java
 * 
 * <p>Description:服務據點檔</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/23, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class Branchmap implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 服務據點鍵值 */
    private Integer branchmapKey;

    /** 地址 */
    private String address;

    /** 地區 */
    private String area;

    /** 分區 */
    private String areaDetail;

    /** 分行代號 */
    private String branchCode;

    /** 英文分行地址 */
    private String enAddress;

    /** 英文分行名稱 */
    private String enName;

    /** 緯度 */
    private String latitude;

    /** 經度 */
    private String longitude;

    /** 據點名稱 */
    private String name;

    /** 服務據點類型 */
    private Integer serviceType;

    /** 標籤類型 */
    private String tagType;

    /**
     * 取得地址
     * 
     * @return String 地址
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * 取得地址
     * 
     * @return String 地址
     */
    public String getAddress(Locale locale) {
        if (locale == Locale.US) {
            return this.enAddress;
        }
        else {
            return this.address;
        }
    }

    /**
     * 設定地址
     * 
     * @param address
     *            要設定的地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 取得地區
     * 
     * @return String 地區
     */
    public String getArea() {
        return this.area;
    }

    /**
     * 設定地區
     * 
     * @param area
     *            要設定的地區
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 取得分區
     * 
     * @return String 分區
     */
    public String getAreaDetail() {
        return this.areaDetail;
    }

    /**
     * 設定分區
     * 
     * @param areaDetail
     *            要設定的分區
     */
    public void setAreaDetail(String areaDetail) {
        this.areaDetail = areaDetail;
    }

    /**
     * 取得分行代號
     * 
     * @return String 分行代號
     */
    public String getBranchCode() {
        return this.branchCode;
    }

    /**
     * 設定分行代號
     * 
     * @param branchCode
     *            要設定的分行代號
     */
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    /**
     * 取得服務據點鍵值
     * 
     * @return int 服務據點鍵值
     */
    public Integer getBranchmapKey() {
        return this.branchmapKey;
    }

    /**
     * 設定服務據點鍵值
     * 
     * @param branchmapKey
     *            要設定的服務據點鍵值
     */
    public void setBranchmapKey(Integer branchmapKey) {
        this.branchmapKey = branchmapKey;
    }

    /**
     * 取得英文分行地址
     * 
     * @return String 英文分行地址
     */
    public String getEnAddress() {
        return this.enAddress;
    }

    /**
     * 設定英文分行地址
     * 
     * @param enAddress
     *            要設定的英文分行地址
     */
    public void setEnAddress(String enAddress) {
        this.enAddress = enAddress;
    }

    /**
     * 取得英文分行名稱
     * 
     * @return String 英文分行名稱
     */
    public String getEnName() {
        return this.enName;
    }

    /**
     * 設定英文分行名稱
     * 
     * @param enName
     *            要設定的英文分行名稱
     */
    public void setEnName(String enName) {
        this.enName = enName;
    }

    /**
     * 取得緯度
     * 
     * @return String 緯度
     */
    public String getLatitude() {
        return this.latitude;
    }

    /**
     * 設定緯度
     * 
     * @param latitude
     *            要設定的緯度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 取得經度
     * 
     * @return String 經度
     */
    public String getLongitude() {
        return this.longitude;
    }

    /**
     * 設定經度
     * 
     * @param longitude
     *            要設定的經度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 取得據點名稱
     * 
     * @param locale
     * 
     * @return String 據點名稱
     */
    public String getName() {
        return this.name;
    }

    /**
     * 取得據點名稱
     * 
     * @param locale
     * 
     * @return String 據點名稱
     */
    public String getName(Locale locale) {
        if (locale == Locale.US) {
            return this.enName;
        }
        else {
            return this.name;
        }
    }

    /**
     * 設定據點名稱
     * 
     * @param name
     *            要設定的據點名稱
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 取得服務據點類型
     * 
     * @return boolean 服務據點類型
     */
    public Integer getServiceType() {
        return this.serviceType;
    }

    /**
     * 設定服務據點類型
     * 
     * @param serviceType
     *            要設定的服務據點類型
     */
    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * 取得標籤類型
     * 
     * @return String 標籤類型
     */
    public String getTagType() {
        return this.tagType;
    }

    /**
     * 設定標籤類型
     * 
     * @param tagType
     *            要設定的標籤類型
     */
    public void setTagType(String tagType) {
        this.tagType = tagType;
    }
}
