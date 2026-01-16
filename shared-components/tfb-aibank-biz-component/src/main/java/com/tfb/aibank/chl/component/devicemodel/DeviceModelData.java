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
package com.tfb.aibank.chl.component.devicemodel;

import java.util.Date;

// @formatter:off
/**
 * @(#)DeviceModelData.java
 * 
 * <p>Description:行動裝置型號對應表資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/13, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class DeviceModelData {

    /** 裝置型號代碼 */
    private String deviceModel;

    /** 廠牌 */
    private String brand;

    /** 廠牌名稱 */
    private String marketingName;

    /** 建立時間 */
    private Date createTime;

    /** 更新時間 */
    private Date updateTime;

    /**
     * @return the deviceModel
     */
    public String getDeviceModel() {
        return deviceModel;
    }

    /**
     * @param deviceModel
     *            the deviceModel to set
     */
    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand
     *            the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return the marketingName
     */
    public String getMarketingName() {
        return marketingName;
    }

    /**
     * @param marketingName
     *            the marketingName to set
     */
    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     *            the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
