/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.security.repository.entities;

import java.util.Date;

import com.tfb.aibank.biz.security.repository.entities.pk.TrustDeviceEntityPk;

import jakarta.persistence.Basic;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)TrustDeviceEntity.java
 * 
 * <p>Description:[信任裝置記錄檔]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/19, benson	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Cacheable(false)
@Entity
@Table(name = "TRUST_DEVICE")
@IdClass(TrustDeviceEntityPk.class)
public class TrustDeviceEntity {

    /** 公司鍵值 */
    @Id
    @Basic
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    /** 使用者鍵值 */
    @Id
    @Basic
    @Column(name = "USER_KEY")
    private Integer userKey;

    /** 裝置鍵值 */
    @Id
    @Basic
    @Column(name = "DEVICE_ID")
    private String deviceId;

    /**
     * 裝置類型<br>
     * WEB：網頁 APP：Fubon+
     */
    @Basic
    @Column(name = "DEVICE_TYPE")
    private String deviceType;

    /** 裝置名稱 */
    @Basic
    @Column(name = "DEVICE_MODEL")
    private String deviceName;

    /** OS */
    @Basic
    @Column(name = "OS")
    private String os;

    /** IP */
    @Basic
    @Column(name = "IP")
    private String ip;

    /** 瀏覽器類型 */
    @Basic
    @Column(name = "BROWSER_TYPE")
    private String browserType;

    /** 建立時間 */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    public TrustDeviceEntity() {
    }

    /**
     * @return the companyKey
     */
    public Integer getCompanyKey() {
        return companyKey;
    }

    /**
     * @param companyKey
     *            the companyKey to set
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * @return the userKey
     */
    public Integer getUserKey() {
        return userKey;
    }

    /**
     * @param userKey
     *            the userKey to set
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    /**
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId
     *            the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     *            the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
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
     * @return the deviceType
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * @param deviceType
     *            the deviceType to set
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * @return the deviceName
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * @param deviceName
     *            the deviceName to set
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * @return the os
     */
    public String getOs() {
        return os;
    }

    /**
     * @param os
     *            the os to set
     */
    public void setOs(String os) {
        this.os = os;
    }

    /**
     * @return the browserType
     */
    public String getBrowserType() {
        return browserType;
    }

    /**
     * @param browserType
     *            the browserType to set
     */
    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }

}
