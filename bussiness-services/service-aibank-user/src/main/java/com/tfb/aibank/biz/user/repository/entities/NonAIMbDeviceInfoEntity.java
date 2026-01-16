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
package com.tfb.aibank.biz.user.repository.entities;

// @formatter:off
/**
 * @(#)NonAIMbDeviceInfoEntity.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/29, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * 行動裝置設定檔 Entity
 * 
 * @author $author$
 * 
 */
@Entity
@Table(name = "MB_DEVICE_INFO")
public class NonAIMbDeviceInfoEntity {

    /**
     * 行動裝置設定檔鍵值
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MB_DEVICE_INFO_SEQ")
    @SequenceGenerator(name = "MB_DEVICE_INFO_SEQ", sequenceName = "MB_DEVICE_INFO_SEQ", allocationSize = 1)
    @Column(name = "DEVICE_INFO_KEY")
    private Integer deviceInfoKey;

    /**
     * 行動裝置UUID
     */
    @Basic
    @Column(name = "DEVICE_UUID")
    private String deviceUuId;

    /**
     * 公司鍵值
     */
    @Basic
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    /**
     * 使用者鍵值
     */
    @Basic
    @Column(name = "USER_KEY")
    private Integer userKey;

    /**
     * 裝置資訊
     */
    @Basic
    @Column(name = "MODEL")
    private String model;

    /**
     * 裝置作業系統
     */
    @Basic
    @Column(name = "DEVICE_PLATFORM")
    private String devicePlatform;

    /**
     * 裝置作業系統版本
     */
    @Basic
    @Column(name = "DEVICE_PLATFORM_VERSION")
    private String devicePlatformVersion;

    /**
     * 暱稱
     */
    @Basic
    @Column(name = "DEVICE_ALIAS")
    private String deviceAlias;

    /**
     * 簡易登入 1: 已授權 0或空白: 未授權
     */
    @Basic
    @Column(name = "LOGIN_FLAG")
    private Integer loginFlag;

    /**
     * 簡易登入授權日期
     */
    @Basic
    @Column(name = "LOGIN_AUTH_DATE")
    private Date loginAuthDate;

    /**
     * 訊息通知1: 已授權0或空白:未授權
     */
    @Basic
    @Column(name = "NOTIFY_FLAG")
    private Integer notifyFlag;

    /**
     * 訊息通知授權日期
     */
    @Basic
    @Column(name = "NOTIFY_AUTH_DATE")
    private Date notifyAuthDate;

    /**
     * 通知聲音設定 0:未設定(無聲音) 1:全天有聲音 2:0900~2100有聲音 3:21:00~09:00有聲音
     */
    @Basic
    @Column(name = "NOTIFY_SOUND")
    private Integer notifySound;

    /**
     * 來源IP
     */
    @Basic
    @Column(name = "CLIENT_IP")
    private String clientIp;

    /**
     * 重要訊息設定密碼
     */
    @Basic
    @Column(name = "MSG_PASSWORD")
    private String msgPassword;

    /**
     * 是否啟用 0:不啟用 1:啟用
     */
    @Basic
    @Column(name = "ENABLE")
    private Integer enable;

    /**
     * 建立時間
     */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 更新時間
     */
    @Basic
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /** 電文異常時壓入, EB552157 : 1, CEW323 : 2 ; 無:0 */
    @Basic
    @Column(name = "ERROR_FLAG")
    private Integer errorFlag;

    /**
     * 登入身份別 0:一般會員登入, 1:信用卡會員登入
     */
    @Basic
    @Column(name = "LOGIN_TYPE")
    private Integer loginType;

    /**
     * 無卡提款 1:已授權 0或空白:未授權
     */
    @Basic
    @Column(name = "WITHDRAWAL_FLAG")
    private Integer withdrawalFlag;

    /**
     * 行動裝置UUID
     */
    @Basic
    @Column(name = "DEVICE_UUID_OLD")
    private String deviceUuIdOld;

    /**
     * 快速登入密碼類型0：文字密碼 1：手勢密碼
     */
    @Basic
    @Column(name = "LOGIN_PASSWD_TYPE")
    private String loginPasswdType;

    /**
     * LUCKYPAY註記 0:未授權 1:已授權
     */
    @Basic
    @Column(name = "LUCKYPAY_FLAG")
    private Integer luckyPayFlag;

    /**
     * 速速查註記 0:未授權 1:已授權
     */
    @Basic
    @Column(name = "QSEARCH_FLAG")
    private Integer qSearchFlag;

    /**
     * 邦你記註記 0:未授權 1:已授權
     */
    @Basic
    @Column(name = "LEDGER_FLAG")
    private Integer ledgerFlag;

    /**
     * LUCKYPAY上次狀態更新時間
     */
    @Basic
    @Column(name = "LUCKYPAY_UPDATE_TIME")
    private Date luckyPayUpdateTime;

    /**
     * LUCKYPAY目前是否啟用 0:不啟用 1:啟用
     */
    @Basic
    @Column(name = "LUCKYPAY_ENABLE")
    private Integer luckyPayEnable;

    /**
     * [提高非約轉 新增]提高非約轉註記 0:未授權 1:已授權
     */
    @Basic
    @Column(name = "RAISE_TRANSFER_FLAG")
    private Integer raiseTransferFlag;

    /**
     * [提高非約轉 新增]提高非約轉註記申請日期
     */
    @Basic
    @Column(name = "RAISE_TRANSFER_DATE")
    private Date raiseTransferDate;

    /**
     * 是否變更過密碼，Y:是
     */
    @Basic
    @Column(name = "CHG_PWD_USERID_FLAG")
    private String chgPwdUseridFlag;

    /**
     * 變更密碼時間
     */
    @Basic
    @Column(name = "CHG_PWD_USERID_DATE")
    private Date chgPwdUseridDate;

    /**
     * 申請MOTP註記 0:未授權 1:已授權
     */
    @Basic
    @Column(name = "MOTP_FLAG")
    private Integer motpFlag;

    /**
     * MOTP註記申請日期
     */
    @Basic
    @Column(name = "MOTP_FLAG_DATE")
    private Date motpFlagDate;

    /**
     * 手機號碼轉帳1: 已授權0或空白:未授權
     */
    @Basic
    @Column(name = "PHONE_TRANSFER_FLAG")
    private Integer phoneTransferFlag;

    /**
     * 手機號碼轉帳授權日期
     */
    @Basic
    @Column(name = "PHONE_TRANSFER_AUTH_DATE")
    private Date phoneTransferAuthDate;

    /**
     * 取得 行動裝置設定檔鍵值
     *
     * @return deviceInfoKey
     */
    public Integer getDeviceInfoKey() {
        return deviceInfoKey;
    }

    /**
     * 設定 行動裝置設定檔鍵值
     *
     * @param deviceInfoKey
     */
    public void setDeviceInfoKey(Integer deviceInfoKey) {
        this.deviceInfoKey = deviceInfoKey;
    }

    /**
     * 取得 行動裝置UUID
     *
     * @return deviceUuId
     */
    public String getDeviceUuId() {
        return deviceUuId;
    }

    /**
     * 設定 行動裝置UUID
     *
     * @param deviceUuId
     */
    public void setDeviceUuId(String deviceUuId) {
        this.deviceUuId = deviceUuId;
    }

    /**
     * 取得 公司鍵值
     *
     * @return companyKey
     */
    public Integer getCompanyKey() {
        return companyKey;
    }

    /**
     * 設定 公司鍵值
     *
     * @param companyKey
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * 取得 使用者鍵值
     *
     * @return userKey
     */
    public Integer getUserKey() {
        return userKey;
    }

    /**
     * 設定 使用者鍵值
     *
     * @param userKey
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    /**
     * 取得 裝置資訊
     *
     * @return model
     */
    public String getModel() {
        return model;
    }

    /**
     * 設定 裝置資訊
     *
     * @param model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 取得 裝置作業系統
     *
     * @return devicePlatform
     */
    public String getDevicePlatform() {
        return devicePlatform;
    }

    /**
     * 設定 裝置作業系統
     *
     * @param devicePlatform
     */
    public void setDevicePlatform(String devicePlatform) {
        this.devicePlatform = devicePlatform;
    }

    /**
     * 取得 裝置作業系統版本
     *
     * @return devicePlatformVersion
     */
    public String getDevicePlatformVersion() {
        return devicePlatformVersion;
    }

    /**
     * 設定 裝置作業系統版本
     *
     * @param devicePlatformVersion
     */
    public void setDevicePlatformVersion(String devicePlatformVersion) {
        this.devicePlatformVersion = devicePlatformVersion;
    }

    /**
     * 取得 暱稱
     *
     * @return deviceAlias
     */
    public String getDeviceAlias() {
        return deviceAlias;
    }

    /**
     * 設定 暱稱
     *
     * @param deviceAlias
     */
    public void setDeviceAlias(String deviceAlias) {
        this.deviceAlias = deviceAlias;
    }

    /**
     * 取得 簡易登入 1: 已授權 0或空白: 未授權
     *
     * @return loginFlag
     */
    public Integer getLoginFlag() {
        return loginFlag;
    }

    /**
     * 設定 簡易登入 1: 已授權 0或空白: 未授權
     *
     * @param loginFlag
     */
    public void setLoginFlag(Integer loginFlag) {
        this.loginFlag = loginFlag;
    }

    /**
     * 取得 簡易登入授權日期
     *
     * @return loginAuthDate
     */
    public Date getLoginAuthDate() {
        return loginAuthDate;
    }

    /**
     * 設定 簡易登入授權日期
     *
     * @param loginAuthDate
     */
    public void setLoginAuthDate(Date loginAuthDate) {
        this.loginAuthDate = loginAuthDate;
    }

    /**
     * 取得 訊息通知1: 已授權0或空白:未授權
     *
     * @return notifyFlag
     */
    public Integer getNotifyFlag() {
        return notifyFlag;
    }

    /**
     * 設定 訊息通知1: 已授權0或空白:未授權
     *
     * @param notifyFlag
     */
    public void setNotifyFlag(Integer notifyFlag) {
        this.notifyFlag = notifyFlag;
    }

    /**
     * 取得 訊息通知授權日期
     *
     * @return notifyAuthDate
     */
    public Date getNotifyAuthDate() {
        return notifyAuthDate;
    }

    /**
     * 設定 訊息通知授權日期
     *
     * @param notifyAuthDate
     */
    public void setNotifyAuthDate(Date notifyAuthDate) {
        this.notifyAuthDate = notifyAuthDate;
    }

    /**
     * 取得 通知聲音設定 0:未設定(無聲音) 1:全天有聲音 2:0900~2100有聲音 3:21:00~09:00有聲音
     *
     * @return notifySound
     */
    public Integer getNotifySound() {
        return notifySound;
    }

    /**
     * 設定 通知聲音設定 0:未設定(無聲音) 1:全天有聲音 2:0900~2100有聲音 3:21:00~09:00有聲音
     *
     * @param notifySound
     */
    public void setNotifySound(Integer notifySound) {
        this.notifySound = notifySound;
    }

    /**
     * 取得 來源IP
     *
     * @return clientIp
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * 設定 來源IP
     *
     * @param clientIp
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * 取得 是否啟用 0:不啟用 1:啟用
     *
     * @return enable
     */
    public Integer getEnable() {
        return enable;
    }

    /**
     * 設定 是否啟用 0:不啟用 1:啟用
     *
     * @param enable
     */
    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    /**
     * 取得 建立時間
     *
     * @return createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 設定 建立時間
     *
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得 更新時間
     *
     * @return updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 設定 更新時間
     *
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 取得 重要訊息設定密碼
     * 
     * @return
     */
    public String getMsgPassword() {
        return msgPassword;
    }

    /**
     * 設定 重要訊息設定密碼
     * 
     * @param msgPass-word
     */
    public void setMsgPassword(String msgPassword) {
        this.msgPassword = msgPassword;
    }

    /**
     * 取得 取得 登入身份別 0:一般會員登入, 1:信用卡會員登入
     *
     * @return loginType
     */
    public Integer getLoginType() {
        return loginType;
    }

    /**
     * 設定 取得 登入身份別 0:一般會員登入, 1:信用卡會員登入
     *
     * @param loginType
     */
    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    /**
     * 取得 電文異常時壓入, EB552157 : 1, CEW323 : 2 ; 無:0
     *
     * @return errorFlag
     */
    public Integer getErrorFlag() {
        return errorFlag;
    }

    /**
     * 設定 電文異常時壓入, EB552157 : 1, CEW323 : 2 ; 無:0
     *
     * @param errorFlag
     */
    public void setErrorFlag(Integer errorFlag) {
        this.errorFlag = errorFlag;
    }

    /**
     * @return the withdrawalFlag
     */
    public Integer getWithdrawalFlag() {
        return withdrawalFlag;
    }

    /**
     * @param withdrawalFlag
     *            the withdrawalFlag to set
     */
    public void setWithdrawalFlag(Integer withdrawalFlag) {
        this.withdrawalFlag = withdrawalFlag;
    }

    /**
     * 取得 deviceUuIdOld
     *
     * @return String
     */
    public String getDeviceUuIdOld() {
        return deviceUuIdOld;
    }

    /**
     * 設定 deviceUuIdOld
     *
     * @param deviceUuIdOld
     */
    public void setDeviceUuIdOld(String deviceUuIdOld) {
        this.deviceUuIdOld = deviceUuIdOld;
    }

    /**
     * 取得 loginPass-wdType
     *
     * @return String
     */
    public String getLoginPasswdType() {
        return loginPasswdType;
    }

    /**
     * 設定 loginPass-wdType
     *
     * @param loginPass-wdType
     */
    public void setLoginPasswdType(String loginPasswdType) {
        this.loginPasswdType = loginPasswdType;
    }

    /**
     * 取得 luckyPayFlag
     *
     * @return String
     */
    public Integer getLuckyPayFlag() {
        return luckyPayFlag;
    }

    /**
     * 設定 luckyPayFlag
     *
     * @param flagy
     */
    public void setLuckyPayFlag(Integer flagy) {
        this.luckyPayFlag = flagy;
    }

    /**
     * 取得 qSearchFlag
     *
     * @param flagy
     */
    public Integer getqSearchFlag() {
        return qSearchFlag;
    }

    /**
     * 設定 qSearchFlag
     *
     * @param flagy
     */
    public void setqSearchFlag(Integer qSearchFlag) {
        this.qSearchFlag = qSearchFlag;
    }

    /**
     * 取得 ledgerFlag
     *
     * @param flagy
     */
    public Integer getLedgerFlag() {
        return ledgerFlag;
    }

    /**
     * 設定 ledgerFlag
     *
     * @param flagy
     */
    public void setLedgerFlag(Integer ledgerFlag) {
        this.ledgerFlag = ledgerFlag;
    }

    public Date getLuckyPayUpdateTime() {
        return luckyPayUpdateTime;
    }

    public void setLuckyPayUpdateTime(Date luckyPayUpdateTime) {
        this.luckyPayUpdateTime = luckyPayUpdateTime;
    }

    public Integer getLuckyPayEnable() {
        return luckyPayEnable;
    }

    public void setLuckyPayEnable(Integer luckyPayEnable) {
        this.luckyPayEnable = luckyPayEnable;
    }

    /**
     * String raiseTransferFlag
     * 
     * @return the raiseTransferFlag
     */
    public Integer getRaiseTransferFlag() {
        return raiseTransferFlag;
    }

    /**
     * String raiseTransferFlag
     * 
     * @param raiseTransferFlag
     *            the raiseTransferFlag to set
     */
    public void setRaiseTransferFlag(Integer raiseTransferFlag) {
        this.raiseTransferFlag = raiseTransferFlag;
    }

    /**
     * Date raiseTransferDate
     * 
     * @return the raiseTransferDate
     */
    public Date getRaiseTransferDate() {
        return raiseTransferDate;
    }

    /**
     * Date raiseTransferDate
     * 
     * @param raiseTransferDate
     *            the raiseTransferDate to set
     */
    public void setRaiseTransferDate(Date raiseTransferDate) {
        this.raiseTransferDate = raiseTransferDate;
    }

    /**
     * 是否變更過密碼，Y:是
     * 
     * @return
     */
    public String getChgPwdUseridFlag() {
        return chgPwdUseridFlag;
    }

    /**
     * 是否變更過密碼，Y:是
     * 
     * @return
     */
    public void setChgPwdUseridFlag(String chgPwdUseridFlag) {
        this.chgPwdUseridFlag = chgPwdUseridFlag;
    }

    /**
     * 變更密碼時間
     * 
     * @return
     */
    public Date getChgPwdUseridDate() {
        return chgPwdUseridDate;
    }

    /**
     * 變更密碼時間
     * 
     * @return
     */
    public void setChgPwdUseridDate(Date chgPwdUseridDate) {
        this.chgPwdUseridDate = chgPwdUseridDate;
    }

    public Integer getMotpFlag() {
        return motpFlag;
    }

    public void setMotpFlag(Integer motpFlag) {
        this.motpFlag = motpFlag;
    }

    public Date getMotpFlagDate() {
        return motpFlagDate;
    }

    public Integer getPhoneTransferFlag() {
        return phoneTransferFlag;
    }

    public void setPhoneTransferFlag(Integer phoneTransferFlag) {
        this.phoneTransferFlag = phoneTransferFlag;
    }

    public Date getPhoneTransferAuthDate() {
        return phoneTransferAuthDate;
    }

    public void setPhoneTransferAuthDate(Date phoneTransferAuthDate) {
        this.phoneTransferAuthDate = phoneTransferAuthDate;
    }

    public void setMotpFlagDate(Date motpFlagDate) {
        this.motpFlagDate = motpFlagDate;
    }

    @Override
    public String toString() {
        return "MbDeviceInfoEntity [deviceInfoKey=" + deviceInfoKey + ", deviceUuId=" + deviceUuId + ", companyKey=" + companyKey + ", userKey=" + userKey + ", model=" + model + ", devicePlatform=" + devicePlatform + ", devicePlatformVersion=" + devicePlatformVersion + ", deviceAlias=" + deviceAlias + ", loginFlag=" + loginFlag + ", loginAuthDate=" + loginAuthDate + ", notifyFlag=" + notifyFlag + ", notifyAuthDate=" + notifyAuthDate + ", notifySound=" + notifySound + ", clientIp=" + clientIp + ", msgPassword=" + msgPassword + ", enable=" + enable + ", createTime=" + createTime + ", updateTime=" + updateTime + ", errorFlag=" + errorFlag + ", loginType=" + loginType + ", withdrawalFlag=" + withdrawalFlag + ", deviceUuIdOld=" + deviceUuIdOld + ", loginPasswdType=" + loginPasswdType + ", luckyPayFlag=" + luckyPayFlag + ", qSearchFlag=" + qSearchFlag + ", ledgerFlag=" + ledgerFlag + ", luckyPayUpdateTime=" + luckyPayUpdateTime + ", luckyPayEnable=" + luckyPayEnable + ", raiseTransferFlag=" + raiseTransferFlag + ", raiseTransferDate=" + raiseTransferDate + ", motpFlag=" + motpFlag + ", motpFlagDate=" + motpFlagDate + "]";
    }
}
