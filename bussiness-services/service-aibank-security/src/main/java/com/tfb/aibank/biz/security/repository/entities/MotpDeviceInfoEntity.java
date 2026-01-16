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
package com.tfb.aibank.biz.security.repository.entities;

import java.io.Serializable;
import java.util.Date;

import com.tfb.aibank.biz.security.services.motp.type.MotpDeviceStatus;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)MotpDeviceInfoEntity.java
 * 
 * <p>Description:MOTP裝置綁定資訊 - Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/10, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "MOTP_DEVICE_INFO")
public class MotpDeviceInfoEntity implements Serializable {

    private static final long serialVersionUID = -1902854929138733949L;

    /** MOTP裝置綁定鍵值 */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOTP_DEVICE_INFO_SEQ")
    @SequenceGenerator(name = "MOTP_DEVICE_INFO_SEQ", sequenceName = "MOTP_DEVICE_INFO_SEQ", allocationSize = 1)
    @Column(name = "MOTP_DEVICE_KEY")
    private Integer motpDeviceKey;

    /** 公司鍵值 */
    @Basic
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    /** 使用者鍵值 */
    @Basic
    @Column(name = "USER_KEY")
    private Integer userKey;

    /** 行動裝置鍵值 */
    @Basic
    @Column(name = "DEVICE_INFO_KEY")
    private Integer deviceInfoKey;

    /** 行銀裝置ID */
    @Basic
    @Column(name = "DEVICE_UUID")
    private String deviceUuid;

    /**
     * 推播ID 供全景推播使用（應為DVEICE_ID）
     */
    @Basic
    @Column(name = "PUSH_ID")
    private String pushId;

    /**
     * 綁定帳戶名稱 對應MOTP PROFILＥ名稱 (應為COMPANY_UID)
     */
    @Basic
    @Column(name = "ACCOUNT_ID")
    private String accountId;

    /** 載具序號 */
    @Basic
    @Column(name = "SN")
    private String sn;

    /**
     * 手機端用戶ID 對應全景addProfile後取得之ClientID
     */
    @Basic
    @Column(name = "CLIENT_ID")
    private String clientId;

    /**
     * MOTP裝置ID 對應全景addProfile後取得之DeviceID
     */
    @Basic
    @Column(name = "MACHINE_CODE")
    private String machineCode;

    /**
     * 使用狀態 INIT:未啟用/ ENABLE:已啟用/ DISABLE:已失效
     */
    @Basic
    @Column(name = "ENABLE")
    @Enumerated(EnumType.STRING)
    private MotpDeviceStatus enable;

    /** 綁定群組 */
    @Basic
    @Column(name = "MOTP_GROUP")
    private String group;

    /** 裝置代碼 */
    @Basic
    @Column(name = "MODEL")
    private String model;

    /** 客戶IP */
    @Basic
    @Column(name = "CLIENT_IP")
    private String clientIp;

    /** 裝置作業系統 */
    @Basic
    @Column(name = "DEVICE_PLATFORM")
    private String devicePlatform;

    /** 裝置作業系統版本 */
    @Basic
    @Column(name = "DEVICE_PLATFORM_VERSION")
    private String devicePlatformVersion;

    /** 綁定推播OTP日期 */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 註銷方式 0:原裝置刪除 / 1:行銀非綁定裝置 / 2:網銀 / 3:系統註銷
     */
    @Basic
    @Column(name = "DEFEAT_TYPE")
    private String defeatType;

    /**
     * 註銷描述
     */
    @Basic
    @Column(name = "DEFEAT_DESC")
    private String defeatDesc;

    /** 註銷裝置日期 */
    @Basic
    @Column(name = "DEFEAT_TIME")
    private Date defeatTime;

    /**
     * 視訊認證鍵值，如果此筆MOTP綁定有做視訊驗證，則此欄位有值
     */
    @Basic
    @Column(name = "VIDEO_AUTH_KEY")
    private Integer videoAuthKey;

    /**
     * 視訊認證，因為什麼原因而做視訊驗證
     */
    @Basic
    @Column(name = "VIDEO_AUTH_APPLY_REASON")
    private String videoAuthApplyReason;

    /**
     * 完成MOTP綁定時間
     */
    @Basic
    @Column(name = "COMPLETE_TIME")
    private Date completeTime;

    /**
     * 供關聯MOTP_LOG_DATA使用
     */
    @Basic
    @Column(name = "SESSION_ID")
    private String sessionId;

    /**
     * MOTP綁定時約定條款版本
     */
    @Basic
    @Column(name = "MOTP_TERMS_VER")
    private String motpTermsVer;

    /**
     * @return the motpDeviceKey
     */
    public Integer getMotpDeviceKey() {
        return motpDeviceKey;
    }

    /**
     * @param motpDeviceKey
     *            the motpDeviceKey to set
     */
    public void setMotpDeviceKey(Integer motpDeviceKey) {
        this.motpDeviceKey = motpDeviceKey;
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
     * @return the deviceInfoKey
     */
    public Integer getDeviceInfoKey() {
        return deviceInfoKey;
    }

    /**
     * @param deviceInfoKey
     *            the deviceInfoKey to set
     */
    public void setDeviceInfoKey(Integer deviceInfoKey) {
        this.deviceInfoKey = deviceInfoKey;
    }

    /**
     * @return the deviceUuid
     */
    public String getDeviceUuid() {
        return deviceUuid;
    }

    /**
     * @param deviceUuid
     *            the deviceUuid to set
     */
    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    /**
     * @return the pushId
     */
    public String getPushId() {
        return pushId;
    }

    /**
     * @param pushId
     *            the pushId to set
     */
    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    /**
     * @return the accountId
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @param accountId
     *            the accountId to set
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * @return the sn
     */
    public String getSn() {
        return sn;
    }

    /**
     * @param sn
     *            the sn to set
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * @return the clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId
     *            the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the machineCode
     */
    public String getMachineCode() {
        return machineCode;
    }

    /**
     * @param machineCode
     *            the machineCode to set
     */
    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    /**
     * @return the enable
     */
    public MotpDeviceStatus getEnable() {
        return enable;
    }

    /**
     * @param enable
     *            the enable to set
     */
    public void setEnable(MotpDeviceStatus enable) {
        this.enable = enable;
    }

    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * @param group
     *            the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model
     *            the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the clientIp
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * @param clientIp
     *            the clientIp to set
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * @return the devicePlatform
     */
    public String getDevicePlatform() {
        return devicePlatform;
    }

    /**
     * @param devicePlatform
     *            the devicePlatform to set
     */
    public void setDevicePlatform(String devicePlatform) {
        this.devicePlatform = devicePlatform;
    }

    /**
     * @return the devicePlatformVersion
     */
    public String getDevicePlatformVersion() {
        return devicePlatformVersion;
    }

    /**
     * @param devicePlatformVersion
     *            the devicePlatformVersion to set
     */
    public void setDevicePlatformVersion(String devicePlatformVersion) {
        this.devicePlatformVersion = devicePlatformVersion;
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
     * @return the defeatType
     */
    public String getDefeatType() {
        return defeatType;
    }

    /**
     * @param defeatType
     *            the defeatType to set
     */
    public void setDefeatType(String defeatType) {
        this.defeatType = defeatType;
    }

    /**
     * @return the defeatDesc
     */
    public String getDefeatDesc() {
        return defeatDesc;
    }

    /**
     * @param defeatDesc
     *            the defeatDesc to set
     */
    public void setDefeatDesc(String defeatDesc) {
        this.defeatDesc = defeatDesc;
    }

    /**
     * @return the defeatTime
     */
    public Date getDefeatTime() {
        return defeatTime;
    }

    /**
     * @param defeatTime
     *            the defeatTime to set
     */
    public void setDefeatTime(Date defeatTime) {
        this.defeatTime = defeatTime;
    }

    /**
     * @return the videoAuthKey
     */
    public Integer getVideoAuthKey() {
        return videoAuthKey;
    }

    /**
     * @param videoAuthKey
     *            the videoAuthKey to set
     */
    public void setVideoAuthKey(Integer videoAuthKey) {
        this.videoAuthKey = videoAuthKey;
    }

    /**
     * @return the videoAuthApplyReason
     */
    public String getVideoAuthApplyReason() {
        return videoAuthApplyReason;
    }

    /**
     * @param videoAuthApplyReason
     *            the videoAuthApplyReason to set
     */
    public void setVideoAuthApplyReason(String videoAuthApplyReason) {
        this.videoAuthApplyReason = videoAuthApplyReason;
    }

    /**
     * @return the completeTime
     */
    public Date getCompleteTime() {
        return completeTime;
    }

    /**
     * @param completeTime
     *            the completeTime to set
     */
    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId
     *            the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @return the motpTermsVer
     */
    public String getMotpTermsVer() {
        return motpTermsVer;
    }

    /**
     * @param motpTermsVer
     *            the motpTermsVer to set
     */
    public void setMotpTermsVer(String motpTermsVer) {
        this.motpTermsVer = motpTermsVer;
    }

}
