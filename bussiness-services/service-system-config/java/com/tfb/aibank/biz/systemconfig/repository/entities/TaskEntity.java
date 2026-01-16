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
package com.tfb.aibank.biz.systemconfig.repository.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Date;

// @formatter:off
/**
 * @(#)TaskEntity.java
 * 
 * <p>Description:交易設定 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "TASK")
public class TaskEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 此交易是否需要控管 0：不控管 1：控管 (預設)
     */
    @Basic
    @Column(name = "ACCESS_CONTROL_FLAG")
    private int accessControlFlag;

    /**
     * 交易群組代碼
     */
    @Basic
    @Column(name = "APP_ID")
    private String appId;

    /**
     * 業務分類代碼
     */
    @Basic
    @Column(name = "BIZ_ID")
    private String bizId;

    /**
     * 顯示方式
     */
    @Basic
    @Column(name = "DISPLAY_TYPE")
    private int displayType;

    /**
     * 重覆submit 或 back 時要導到的頁面, 若為NULL則導回本交易首頁
     */
    @Basic
    @Column(name = "REDIRECT_URL")
    private String redirectUrl;

    /**
     * 是否啟用 0：否 1：是
     */
    @Basic
    @Column(name = "STATUS")
    private int status;

    /**
     * 是否支援未登入之操作
     */
    @Basic
    @Column(name = "SUPPORT_GUEST_OP_FLAG")
    private int supportGuestOpFlag;

    /**
     * 交易暫停說明
     */
    @Basic
    @Column(name = "SUSPEND_DESC")
    private String suspendDesc;

    /**
     * 暫停結束時間
     */
    @Basic
    @Column(name = "SUSPEND_END_TIME")
    private Date suspendEndTime;

    /**
     * 暫停起始時間
     */
    @Basic
    @Column(name = "SUSPEND_START_TIME")
    private Date suspendStartTime;

    /**
     * 交易功能代號
     */
    @Id
    @Column(name = "TASK_ID")
    private String taskId;

    /**
     * 交易逾時設定 (0~9999)
     */
    @Basic
    @Column(name = "TASK_TIMEOUT")
    private Integer taskTimeout;

    /**
     * 交易放行安控機制
     * <ul>
     * <li>0：表示無須安控驗證</li>
     * <li>1：表示使用簡訊OTP進行安控驗證</li>
     * <li>2：表示使用MOTP進行安控驗證</li>
     * <li>3：表示可同時使用簡訊OTP以及MOTP進行安控驗證</li>
     * </ul>
     */
    @Basic
    @Column(name = "SECURITY_TYPES")
    private int securityTypes;

    /**
     * OTP使用類型
     * <ul>
     * <li>1：表示要使用臨櫃申請OTP註記進行判斷</li>
     * <li>2：表示要使用CIF留存的行動電話有無值進行判斷</li>
     * <li>3：表示要用AS400留存的行動電話有無值進行判斷</li>
     * </ul>
     */
    @Basic
    @Column(name = "SECURITY_OTP_TYPES")
    private int securityOtpTypes;

    /**
     * IOS最低支援版本
     */
    @Basic
    @Column(name = "IOS_MIN_VERSION")
    private String iosMinVersion;

    /**
     * ANDROID最低支援版本
     */
    @Basic
    @Column(name = "ANDROID_MIN_VERSION")
    private String androidMinVersion;

    /**
     * 可被記錄(0：不可1：可)
     */
    @Basic
    @Column(name = "CAN_BE_RECORD")
    private int canBeRecord;

    /**
     * 取得此交易是否需要控管 0：不控管 1：控管 (預設)
     * 
     * @return int 此交易是否需要控管 0：不控管 1：控管 (預設)
     */
    public int getAccessControlFlag() {
        return this.accessControlFlag;
    }

    /**
     * 設定此交易是否需要控管 0：不控管 1：控管 (預設)
     * 
     * @param accessControlFlag
     *            要設定的此交易是否需要控管 0：不控管 1：控管 (預設)
     */
    public void setAccessControlFlag(int accessControlFlag) {
        this.accessControlFlag = accessControlFlag;
    }

    /**
     * 取得交易群組代碼
     * 
     * @return String 交易群組代碼
     */
    public String getAppId() {
        return this.appId;
    }

    /**
     * 設定交易群組代碼
     * 
     * @param appId
     *            要設定的交易群組代碼
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * 取得業務分類代碼
     * 
     * @return String 業務分類代碼
     */
    public String getBizId() {
        return this.bizId;
    }

    /**
     * 設定業務分類代碼
     * 
     * @param bizId
     *            要設定的業務分類代碼
     */
    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    /**
     * 取得顯示方式
     * 
     * @return int 顯示方式
     */
    public int getDisplayType() {
        return this.displayType;
    }

    /**
     * 設定顯示方式
     * 
     * @param displayType
     *            要設定的顯示方式
     */
    public void setDisplayType(int displayType) {
        this.displayType = displayType;
    }

    /**
     * 取得重覆submit 或 back 時要導到的頁面, 若為NULL則導回本交易首頁
     * 
     * @return String 重覆submit 或 back 時要導到的頁面, 若為NULL則導回本交易首頁
     */
    public String getRedirectUrl() {
        return this.redirectUrl;
    }

    /**
     * 設定重覆submit 或 back 時要導到的頁面, 若為NULL則導回本交易首頁
     * 
     * @param redirectUrl
     *            要設定的重覆submit 或 back 時要導到的頁面, 若為NULL則導回本交易首頁
     */
    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    /**
     * 取得是否啟用 0：否 1：是
     * 
     * @return int 是否啟用 0：否 1：是
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * 設定是否啟用 0：否 1：是
     * 
     * @param status
     *            要設定的是否啟用 0：否 1：是
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * 取得是否支援未登入之操作
     * 
     * @return int 是否支援未登入之操作
     */
    public int getSupportGuestOpFlag() {
        return this.supportGuestOpFlag;
    }

    /**
     * 設定是否支援未登入之操作
     * 
     * @param supportGuestOpFlag
     *            要設定的是否支援未登入之操作
     */
    public void setSupportGuestOpFlag(int supportGuestOpFlag) {
        this.supportGuestOpFlag = supportGuestOpFlag;
    }

    /**
     * 取得交易暫停說明
     * 
     * @return String 交易暫停說明
     */
    public String getSuspendDesc() {
        return this.suspendDesc;
    }

    /**
     * 設定交易暫停說明
     * 
     * @param suspendDesc
     *            要設定的交易暫停說明
     */
    public void setSuspendDesc(String suspendDesc) {
        this.suspendDesc = suspendDesc;
    }

    /**
     * 取得暫停結束時間
     * 
     * @return Date 暫停結束時間
     */
    public Date getSuspendEndTime() {
        return this.suspendEndTime;
    }

    /**
     * 設定暫停結束時間
     * 
     * @param suspendEndTime
     *            要設定的暫停結束時間
     */
    public void setSuspendEndTime(Date suspendEndTime) {
        this.suspendEndTime = suspendEndTime;
    }

    /**
     * 取得暫停起始時間
     * 
     * @return Date 暫停起始時間
     */
    public Date getSuspendStartTime() {
        return this.suspendStartTime;
    }

    /**
     * 設定暫停起始時間
     * 
     * @param suspendStartTime
     *            要設定的暫停起始時間
     */
    public void setSuspendStartTime(Date suspendStartTime) {
        this.suspendStartTime = suspendStartTime;
    }

    /**
     * 取得交易功能代號
     * 
     * @return String 交易功能代號
     */
    public String getTaskId() {
        return this.taskId;
    }

    /**
     * 設定交易功能代號
     * 
     * @param taskId
     *            要設定的交易功能代號
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * 取得交易逾時設定 (0~9999)
     * 
     * @return int 交易逾時設定 (0~9999)
     */
    public Integer getTaskTimeout() {
        return this.taskTimeout;
    }

    /**
     * 設定交易逾時設定 (0~9999)
     * 
     * @param taskTimeout
     *            要設定的交易逾時設定 (0~9999)
     */
    public void setTaskTimeout(Integer taskTimeout) {
        this.taskTimeout = taskTimeout;
    }

    /**
     * @return the securityTypes
     */
    public int getSecurityTypes() {
        return securityTypes;
    }

    /**
     * @param securityTypes
     *            the securityTypes to set
     */
    public void setSecurityTypes(int securityTypes) {
        this.securityTypes = securityTypes;
    }

    /**
     * @return the securityOtpTypes
     */
    public int getSecurityOtpTypes() {
        return securityOtpTypes;
    }

    /**
     * @param securityOtpTypes
     *            the securityOtpTypes to set
     */
    public void setSecurityOtpTypes(int securityOtpTypes) {
        this.securityOtpTypes = securityOtpTypes;
    }

    /**
     * @return the iosMinVersion
     */
    public String getIosMinVersion() {
        return iosMinVersion;
    }

    /**
     * @param iosMinVersion
     *            the iosMinVersion to set
     */
    public void setIosMinVersion(String iosMinVersion) {
        this.iosMinVersion = iosMinVersion;
    }

    /**
     * @return the androidMinVersion
     */
    public String getAndroidMinVersion() {
        return androidMinVersion;
    }

    /**
     * @param androidMinVersion
     *            the androidMinVersion to set
     */
    public void setAndroidMinVersion(String androidMinVersion) {
        this.androidMinVersion = androidMinVersion;
    }

    public int getCanBeRecord() {
        return canBeRecord;
    }

    public void setCanBeRecord(int canBeRecord) {
        this.canBeRecord = canBeRecord;
    }
}
