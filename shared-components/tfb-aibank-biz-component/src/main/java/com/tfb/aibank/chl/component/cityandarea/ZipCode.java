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
package com.tfb.aibank.chl.component.cityandarea;

import java.io.Serializable;
import java.util.Date;

// @formatter:off
/**
 * @(#)ZipcodeModel.java
 * 
 * <p>Description:郵遞區號資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/12, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ZipCode implements Serializable {

    private static final long serialVersionUID = -650390966455263147L;

    /** 資料鍵值 */
    private Integer zipKey;

    /** 地區名稱 */
    private String areaName;

    /** 縣市代號1 */
    private String cityCode1;

    /** 建立時間 */
    private Date createTime;

    /** 語系 */
    private String locale;

    /** 更新時間 */
    private Date updateTime;

    /** 郵遞區號 */
    private String zipcode;

    public Integer getZipKey() {
        return zipKey;
    }

    public void setZipKey(Integer zipKey) {
        this.zipKey = zipKey;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCityCode1() {
        return cityCode1;
    }

    public void setCityCode1(String cityCode1) {
        this.cityCode1 = cityCode1;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

}
