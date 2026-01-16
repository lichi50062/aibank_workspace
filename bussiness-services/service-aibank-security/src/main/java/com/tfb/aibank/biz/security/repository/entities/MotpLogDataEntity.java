/*
 * =========================================================================== IBM Confidential AIS Source Materials
 *
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.security.repository.entities;

import java.io.Serializable;
import java.util.Date;

import com.tfb.aibank.biz.security.repository.entities.support.MotpLogDataEntityListener;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * Entity
 * 
 * @author $author$
 */
@Entity
@EntityListeners(MotpLogDataEntityListener.class)
@Table(name = "MOTP_LOG_DATA")
public class MotpLogDataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 
     * 
     */
    @Basic
    @Column(name = "ACCESS_LOG_KEY")
    private String accessLogKey;

    /** 
     * 
     */
    @Basic
    @Column(name = "ACTION")
    private String action;

    /** 
     * 
     */
    @Basic
    @Column(name = "CHANNEL")
    private String channel;

    /** 
     * 
     */
    @Basic
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    /** 
     * 
     */
    @Basic
    @Column(name = "COMPANY_UID")
    private String companyUid;

    /** 
     * 
     */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /** 
     * 
     */
    @Basic
    @Column(name = "DEVICE_UUID")
    private String deviceUuid;

    /** 
     * 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOTP_LOG_DATA_SEQ")
    @SequenceGenerator(name = "MOTP_LOG_DATA_SEQ", sequenceName = "MOTP_LOG_DATA_SEQ", allocationSize = 1)
    @Column(name = "MOTP_LOG_KEY")
    private Integer motpLogKey;

    /** 
     * 
     */
    @Basic
    @Column(name = "RQ_DATA")
    private String rqData;

    /** 
     * 
     */
    @Basic
    @Column(name = "RS_DATA")
    private String rsData;

    /** 
     * 
     */
    @Basic
    @Column(name = "SERVICE_METHOD")
    private String serviceMethod;

    /** 
     * 
     */
    @Basic
    @Column(name = "SERVICE_NAME")
    private String serviceName;

    /** 
     * 
     */
    @Basic
    @Column(name = "SESSION_ID")
    private String sessionId;

    /** 
     * 
     */
    @Basic
    @Column(name = "STATUS")
    private String status;

    /** 
     * 
     */
    @Basic
    @Column(name = "STATUS_DESC")
    private String statusDesc;

    /** 
     * 
     */
    @Basic
    @Column(name = "TYPE")
    private String type;

    /** 
     * 
     */
    @Basic
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /** 
     * 
     */
    @Basic
    @Column(name = "USER_KEY")
    private Integer userKey;

    /**
     * 取得
     * 
     * @return String
     */
    public String getAccessLogKey() {
        return this.accessLogKey;
    }

    /**
     * 設定
     * 
     * @param accessLogKey
     *            要設定的
     */
    public void setAccessLogKey(String accessLogKey) {
        this.accessLogKey = accessLogKey;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getAction() {
        return this.action;
    }

    /**
     * 設定
     * 
     * @param action
     *            要設定的
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getChannel() {
        return this.channel;
    }

    /**
     * 設定
     * 
     * @param channel
     *            要設定的
     */
    public void setChannel(String channel) {
        this.channel = channel;
    }

    /**
     * 取得
     * 
     * @return Integer
     */
    public Integer getCompanyKey() {
        return this.companyKey;
    }

    /**
     * 設定
     * 
     * @param companyKey
     *            要設定的
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getCompanyUid() {
        return this.companyUid;
    }

    /**
     * 設定
     * 
     * @param companyUid
     *            要設定的
     */
    public void setCompanyUid(String companyUid) {
        this.companyUid = companyUid;
    }

    /**
     * 取得
     * 
     * @return Date
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 設定
     * 
     * @param createTime
     *            要設定的
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getDeviceUuid() {
        return this.deviceUuid;
    }

    /**
     * 設定
     * 
     * @param deviceUuid
     *            要設定的
     */
    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    /**
     * 取得
     * 
     * @return Integer
     */
    public Integer getMotpLogKey() {
        return this.motpLogKey;
    }

    /**
     * 設定
     * 
     * @param motpLogKey
     *            要設定的
     */
    public void setMotpLogKey(Integer motpLogKey) {
        this.motpLogKey = motpLogKey;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getRqData() {
        return this.rqData;
    }

    /**
     * 設定
     * 
     * @param rqData
     *            要設定的
     */
    public void setRqData(String rqData) {
        this.rqData = rqData;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getRsData() {
        return this.rsData;
    }

    /**
     * 設定
     * 
     * @param rsData
     *            要設定的
     */
    public void setRsData(String rsData) {
        this.rsData = rsData;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getServiceMethod() {
        return this.serviceMethod;
    }

    /**
     * 設定
     * 
     * @param serviceMethod
     *            要設定的
     */
    public void setServiceMethod(String serviceMethod) {
        this.serviceMethod = serviceMethod;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getServiceName() {
        return this.serviceName;
    }

    /**
     * 設定
     * 
     * @param serviceName
     *            要設定的
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getSessionId() {
        return this.sessionId;
    }

    /**
     * 設定
     * 
     * @param sessionId
     *            要設定的
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * 設定
     * 
     * @param status
     *            要設定的
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getStatusDesc() {
        return this.statusDesc;
    }

    /**
     * 設定
     * 
     * @param statusDesc
     *            要設定的
     */
    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getType() {
        return this.type;
    }

    /**
     * 設定
     * 
     * @param type
     *            要設定的
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 取得
     * 
     * @return Date
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 設定
     * 
     * @param updateTime
     *            要設定的
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 取得
     * 
     * @return Integer
     */
    public Integer getUserKey() {
        return this.userKey;
    }

    /**
     * 設定
     * 
     * @param userKey
     *            要設定的
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }
}
