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
package com.tfb.aibank.biz.user.repository.entities;

import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)TwoFactorAuthEntity.java
 * 
 * <p>Description:[雙重驗證記錄檔]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/19, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Cacheable(false)
@Entity
@Table(name = "TWO_FACTOR_AUTH")
public class TwoFactorAuthEntity {

    /** 流水號 系統產生 */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TWO_FACTOR_AUTH_SEQ")
    @SequenceGenerator(name = "TWO_FACTOR_AUTH_SEQ", sequenceName = "TWO_FACTOR_AUTH_SEQ", allocationSize = 1)
    @Column(name = "SEQ")
    private Long seq;

    /** 公司鍵值 */
    @Basic
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    /** 使用者鍵值 */
    @Basic
    @Column(name = "USER_KEY")
    private Integer userKey;

    /**
     * 狀態 <br>
     * WAIT：待驗證 CANCEL：取消驗證 SUCCESS：已驗證可登入 FAIL：已驗證不可登入 TIMEOUT
     */
    @Basic
    @Column(name = "STATUS")
    private String status;

    /**
     * 驗證方式 <br>
     * DEVICE：裝置驗證 OTP：OTP驗證
     */
    @Basic
    @Column(name = "AUTH_TYPE")
    private String authType;

    /** IP */
    @Basic
    @Column(name = "IP")
    private String ip;

    /** 裝置鍵值 */
    @Basic
    @Column(name = "DEVICE_ID")
    private String deviceId;

    /** 登入位置 */
    @Basic
    @Column(name = "LOCATION")
    private String location;

    /** 建立時間 */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /** 更新時間 */
    @Basic
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    public TwoFactorAuthEntity() {
    }

    /**
     * @return the seq
     */
    public Long getSeq() {
        return seq;
    }

    /**
     * @param seq
     *            the seq to set
     */
    public void setSeq(Long seq) {
        this.seq = seq;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the authType
     */
    public String getAuthType() {
        return authType;
    }

    /**
     * @param authType
     *            the authType to set
     */
    public void setAuthType(String authType) {
        this.authType = authType;
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
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location
     *            the location to set
     */
    public void setLocation(String location) {
        this.location = location;
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
