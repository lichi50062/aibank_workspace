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
package com.tfb.aibank.chl.component.contactus;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)ContactUs.java
 * 
 * <p>Description:聯繫我們</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/22, alexlee	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "聯繫我們")
public class ContactUs {
    /** 語系 */
    @Schema(description = "語系")
    private String locale;
    /** 裝置作業系統 */
    @Schema(description = "裝置作業系統")
    private int deviceOs;
    /** 服務項目 */
    @Schema(description = "服務項目")
    private String serviceItem;
    /** 說明文字 */
    @Schema(description = "說明文字")
    private String infoText;
    /** 服務電話/連結 */
    @Schema(description = "服務電話/連結")
    private String serviceData;
    /** 服務類型 */
    @Schema(description = "服務類型")
    private int serviceType;
    /** 顯示狀態 */
    @Schema(description = "顯示狀態")
    private int displayFlag;
    /** 真人客服註記 */
    @Schema(description = "真人客服註記")
    private int realPerson;
    /** 服務排序 */
    @Schema(description = "服務排序")
    private int serviceSort;
    /** 建立時間 */
    @Schema(description = "建立時間")
    private Date createTime;
    /** 更新時間 */
    @Schema(description = "更新時間")
    private Date updateTime;

    @Schema(description = "電話號碼及營業時間")
    private String dispInfoText;

    /**
     * @return the dispInfoText
     */
    public String getDispInfoText() {
        return dispInfoText;
    }

    /**
     * @param dispInfoText
     *            the dispInfoText to set
     */
    public void setDispInfoText(String dispInfoText) {
        this.dispInfoText = dispInfoText;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @param locale
     *            the locale to set
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * @return the deviceOs
     */
    public int getDeviceOs() {
        return deviceOs;
    }

    /**
     * @param deviceOs
     *            the deviceOs to set
     */
    public void setDeviceOs(int deviceOs) {
        this.deviceOs = deviceOs;
    }

    /**
     * @return the serviceItem
     */
    public String getServiceItem() {
        return serviceItem;
    }

    /**
     * @param serviceItem
     *            the serviceItem to set
     */
    public void setServiceItem(String serviceItem) {
        this.serviceItem = serviceItem;
    }

    /**
     * @return the infoText
     */
    public String getInfoText() {
        return infoText;
    }

    /**
     * @param infoText
     *            the infoText to set
     */
    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    /**
     * @return the serviceData
     */
    public String getServiceData() {
        return serviceData;
    }

    /**
     * @param serviceData
     *            the serviceData to set
     */
    public void setServiceData(String serviceData) {
        this.serviceData = serviceData;
    }

    /**
     * @return the serviceType
     */
    public int getServiceType() {
        return serviceType;
    }

    /**
     * @param serviceType
     *            the serviceType to set
     */
    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * @return the displayFlag
     */
    public int getDisplayFlag() {
        return displayFlag;
    }

    /**
     * @param displayFlag
     *            the displayFlag to set
     */
    public void setDisplayFlag(int displayFlag) {
        this.displayFlag = displayFlag;
    }

    /**
     * @return the realPerson
     */
    public int getRealPerson() {
        return realPerson;
    }

    /**
     * @param realPerson
     *            the realPerson to set
     */
    public void setRealPerson(int realPerson) {
        this.realPerson = realPerson;
    }

    /**
     * @return the serviceSort
     */
    public int getServiceSort() {
        return serviceSort;
    }

    /**
     * @param serviceSort
     *            the serviceSort to set
     */
    public void setServiceSort(int serviceSort) {
        this.serviceSort = serviceSort;
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
