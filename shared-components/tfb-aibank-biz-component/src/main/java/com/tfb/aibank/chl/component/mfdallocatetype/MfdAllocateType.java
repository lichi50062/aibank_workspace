/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.mfdallocatetype;

import java.io.Serializable;
import java.util.Date;

// @formatter:off
/**
 * @(#)MfdAllocateType.java
 * 
 * <p>Description:基金配息方式</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/10, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class MfdAllocateType implements Serializable {

    private static final long serialVersionUID = 375126041769457592L;

    /** 配息類別 1：除息 2：除權 3：除息或除權 */
    private String allocateType;

    /** 配息類別名稱 */
    private String allocateTypeName;

    /** 語系 */
    private String locale;

    /** 建立時間 */
    private Date createTime;

    /** 更新時間 */
    private Date updateTime;

    /**
     * 取得配息類別 1：除息 2：除權 3：除息或除權
     * 
     * @return String 配息類別 1：除息 2：除權 3：除息或除權
     */
    public String getAllocateType() {
        return this.allocateType;
    }

    /**
     * 設定配息類別 1：除息 2：除權 3：除息或除權
     * 
     * @param allocateType
     *            要設定的配息類別 1：除息 2：除權 3：除息或除權
     */
    public void setAllocateType(String allocateType) {
        this.allocateType = allocateType;
    }

    /**
     * 取得配息類別名稱
     * 
     * @return String 配息類別名稱
     */
    public String getAllocateTypeName() {
        return this.allocateTypeName;
    }

    /**
     * 設定配息類別名稱
     * 
     * @param allocateTypeName
     *            要設定的配息類別名稱
     */
    public void setAllocateTypeName(String allocateTypeName) {
        this.allocateTypeName = allocateTypeName;
    }

    /**
     * 取得建立時間
     * 
     * @return Date 建立時間
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 設定建立時間
     * 
     * @param createTime
     *            要設定的建立時間
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得語系
     * 
     * @return String 語系
     */
    public String getLocale() {
        return this.locale;
    }

    /**
     * 設定語系
     * 
     * @param locale
     *            要設定的語系
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * 取得更新時間
     * 
     * @return Date 更新時間
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 設定更新時間
     * 
     * @param updateTime
     *            要設定的更新時間
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
