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

//@formatter:off
/**
* @(#)MbDeviceInfoEntity.java
* 
* <p>Description:行動狀態設定檔 Entity</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, Edward Tien
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Cacheable(false)
@Entity
@Table(name = "AIBANK_MB_DEVICE_INFO")
public class MbDeviceInfoEntity {

    /**
     * 行動裝置設定檔鍵值
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AIBANK_MB_DEVICE_INFO_SEQ")
    @SequenceGenerator(name = "AIBANK_MB_DEVICE_INFO_SEQ", sequenceName = "AIBANK_MB_DEVICE_INFO_SEQ", allocationSize = 1)
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
     * 裝置暱稱
     */
    @Basic
    @Column(name = "DEVICE_ALIAS")
    private String deviceAlias;

    /**
     * 是否授權簡易登入，1:已授權；0或空白:未授權
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
     * 是否授權訊息通知，1:已授權；0或空白:未授權
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
     * 訊息設定 0:未開啟訊息通知(全天不可發送) 1:已開啟訊息通知(全天可發送) 2:夜間勿擾(2100~0900不發送)
     */
    @Basic
    @Column(name = "NOTIFY_PASS")
    private Integer notifyPass;

    /**
     * 來源IP
     */
    @Basic
    @Column(name = "CLIENT_IP")
    private String clientIp;

    /**
     * 是否授權訊息密碼設定，1:已授權；0或空白:未授權
     */
    @Basic
    @Column(name = "MSG_FLAG")
    private Integer msgFlag;

    /**
     * 重要訊息設定密碼
     */
    @Basic
    @Column(name = "MSG_PASSWORD")
    private String msgPassword;

    /**
     * 是否啟用，0:不啟用；1:啟用
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

    /**
     * 登入身份別，0:一般會員登入；1:信用卡會員登入
     */
    @Basic
    @Column(name = "LOGIN_TYPE")
    private Integer loginType;

    /**
     * 訂閱電文異常註記，1:EB552157，2:CEW323:2；0:無
     */
    @Basic
    @Column(name = "ERROR_FLAG")
    private Integer errorFlag;

    /**
     * 快速登入密碼類型，0:文字密碼；1:手勢密碼；2:指紋；3:臉部；4:Android生物辨識
     */
    @Basic
    @Column(name = "LOGIN_PASSWD_TYPE")
    private Integer loginPasswdType;

    /**
     * 免登速查註記，0:不啟用；1:啟用
     */
    @Basic
    @Column(name = "QSEARCH_FLAG")
    private Integer qsearchFlag;

    /**
     * 是否變更過密碼/代碼 1:是 0:否
     */
    @Basic
    @Column(name = "CHG_PWD_USERID_FLAG")
    private Integer chgPwdUseridFlag;

    /**
     * 變更密碼時間
     */
    @Basic
    @Column(name = "CHG_PWD_USERID_DATE")
    private Date chgPwdUseridDate;

    /**
     * 申請MOTP註記，0:未授權；1:已授權
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
     * 有設定快登時，開啟APP是否直接登入，0:不直接登入；1:直接登入
     */
    @Basic
    @Column(name = "DIRECT_EZ_LOGIN_FLAG")
    private Integer directEzLoginFlag;

    /**
     * 無卡提款授權註記 0/1
     */
    @Basic
    @Column(name = "WITHDRAWAL_FLAG")
    private Integer withdrawalFlag;

    /**
     * 手機號碼收款授權註記 0/1
     */
    @Basic
    @Column(name = "PHONE_TRANSFER_FLAG")
    private Integer phoneTransferFlag;

    /**
     * 轉帳額度授權註記 0/1
     */
    @Basic
    @Column(name = "RAISE_TRANSFER_FLAG")
    private Integer raiseTransferFlag;

    /**
     * 富邦錢包授權註記 0/1
     */
    @Basic
    @Column(name = "WALLET_FLAG")
    private Integer walletFlag;

    /** 雙重驗證登入設定 */
    @Basic
    @Column(name = "TWOSTEP_FLAG")
    private Integer twostepFlag;

    /** 雙重驗證登入設定時間 */
    @Basic
    @Column(name = "TWOSTEP_FLAG_TIME")
    private Date twostepFlagTime;

    /** 信用卡/簽帳金融卡FIDO2綁定註記 0：未綁定FIDO 1：已綁定FIDO */
    @Basic
    @Column(name = "CREDIT_CARD_FIDO2_FLAG")
    private Integer creditCardFido2Flag;

    /**
     * @return the twostepFlag
     */
    public Integer getTwostepFlag() {
        return twostepFlag;
    }

    /**
     * @param twostepFlag
     *            the twostepFlag to set
     */
    public void setTwostepFlag(Integer twostepFlag) {
        this.twostepFlag = twostepFlag;
    }

    /**
     * @return the twostepFlagTime
     */
    public Date getTwostepFlagTime() {
        return twostepFlagTime;
    }

    /**
     * @param twostepFlagTime
     *            the twostepFlagTime to set
     */
    public void setTwostepFlagTime(Date twostepFlagTime) {
        this.twostepFlagTime = twostepFlagTime;
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
     * @return the deviceUuId
     */
    public String getDeviceUuId() {
        return deviceUuId;
    }

    /**
     * @param deviceUuId
     *            the deviceUuId to set
     */
    public void setDeviceUuId(String deviceUuId) {
        this.deviceUuId = deviceUuId;
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
     * @return the deviceAlias
     */
    public String getDeviceAlias() {
        return deviceAlias;
    }

    /**
     * @param deviceAlias
     *            the deviceAlias to set
     */
    public void setDeviceAlias(String deviceAlias) {
        this.deviceAlias = deviceAlias;
    }

    /**
     * @return the loginFlag
     */
    public Integer getLoginFlag() {
        return loginFlag;
    }

    /**
     * @param loginFlag
     *            the loginFlag to set
     */
    public void setLoginFlag(Integer loginFlag) {
        this.loginFlag = loginFlag;
    }

    /**
     * @return the loginAuthDate
     */
    public Date getLoginAuthDate() {
        return loginAuthDate;
    }

    /**
     * @param loginAuthDate
     *            the loginAuthDate to set
     */
    public void setLoginAuthDate(Date loginAuthDate) {
        this.loginAuthDate = loginAuthDate;
    }

    /**
     * @return the notifyFlag
     */
    public Integer getNotifyFlag() {
        return notifyFlag;
    }

    /**
     * @param notifyFlag
     *            the notifyFlag to set
     */
    public void setNotifyFlag(Integer notifyFlag) {
        this.notifyFlag = notifyFlag;
    }

    /**
     * @return the notifyAuthDate
     */
    public Date getNotifyAuthDate() {
        return notifyAuthDate;
    }

    /**
     * @param notifyAuthDate
     *            the notifyAuthDate to set
     */
    public void setNotifyAuthDate(Date notifyAuthDate) {
        this.notifyAuthDate = notifyAuthDate;
    }

    /**
     * @return the notifyPass
     */
    public Integer getNotifyPass() {
        return notifyPass;
    }

    /**
     * @param notifyPass
     *            the notifyPass to set
     */
    public void setNotifyPass(Integer notifyPass) {
        this.notifyPass = notifyPass;
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
     * @return the msgFlag
     */
    public Integer getMsgFlag() {
        return msgFlag;
    }

    /**
     * @param msgFlag
     *            the msgFlag to set
     */
    public void setMsgFlag(Integer msgFlag) {
        this.msgFlag = msgFlag;
    }

    /**
     * @return the msgPassword
     */
    public String getMsgPassword() {
        return msgPassword;
    }

    /**
     * @param msgPassword
     *            the msgPassword to set
     */
    public void setMsgPassword(String msgPassword) {
        this.msgPassword = msgPassword;
    }

    /**
     * @return the enable
     */
    public Integer getEnable() {
        return enable;
    }

    /**
     * @param enable
     *            the enable to set
     */
    public void setEnable(Integer enable) {
        this.enable = enable;
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

    /**
     * @return the loginType
     */
    public Integer getLoginType() {
        return loginType;
    }

    /**
     * @param loginType
     *            the loginType to set
     */
    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    /**
     * @return the errorFlag
     */
    public Integer getErrorFlag() {
        return errorFlag;
    }

    /**
     * @param errorFlag
     *            the errorFlag to set
     */
    public void setErrorFlag(Integer errorFlag) {
        this.errorFlag = errorFlag;
    }

    /**
     * @return the loginPasswdType
     */
    public Integer getLoginPasswdType() {
        return loginPasswdType;
    }

    /**
     * @param loginPasswdType
     *            the loginPasswdType to set
     */
    public void setLoginPasswdType(Integer loginPasswdType) {
        this.loginPasswdType = loginPasswdType;
    }

    /**
     * @return the qsearchFlag
     */
    public Integer getQsearchFlag() {
        return qsearchFlag;
    }

    /**
     * @param qsearchFlag
     *            the qsearchFlag to set
     */
    public void setQsearchFlag(Integer qsearchFlag) {
        this.qsearchFlag = qsearchFlag;
    }

    /**
     * @return the chgPwdUseridFlag
     */
    public Integer getChgPwdUseridFlag() {
        return chgPwdUseridFlag;
    }

    /**
     * @param chgPwdUseridFlag
     *            the chgPwdUseridFlag to set
     */
    public void setChgPwdUseridFlag(Integer chgPwdUseridFlag) {
        this.chgPwdUseridFlag = chgPwdUseridFlag;
    }

    /**
     * @return the chgPwdUseridDate
     */
    public Date getChgPwdUseridDate() {
        return chgPwdUseridDate;
    }

    /**
     * @param chgPwdUseridDate
     *            the chgPwdUseridDate to set
     */
    public void setChgPwdUseridDate(Date chgPwdUseridDate) {
        this.chgPwdUseridDate = chgPwdUseridDate;
    }

    /**
     * @return the motpFlag
     */
    public Integer getMotpFlag() {
        return motpFlag;
    }

    /**
     * @param motpFlag
     *            the motpFlag to set
     */
    public void setMotpFlag(Integer motpFlag) {
        this.motpFlag = motpFlag;
    }

    /**
     * @return the motpFlagDate
     */
    public Date getMotpFlagDate() {
        return motpFlagDate;
    }

    /**
     * @param motpFlagDate
     *            the motpFlagDate to set
     */
    public void setMotpFlagDate(Date motpFlagDate) {
        this.motpFlagDate = motpFlagDate;
    }

    /**
     * @return the directEzLoginFlag
     */
    public Integer getDirectEzLoginFlag() {
        return directEzLoginFlag;
    }

    /**
     * @param directEzLoginFlag
     *            the directEzLoginFlag to set
     */
    public void setDirectEzLoginFlag(Integer directEzLoginFlag) {
        this.directEzLoginFlag = directEzLoginFlag;
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
     * @return the phoneTransferFlag
     */
    public Integer getPhoneTransferFlag() {
        return phoneTransferFlag;
    }

    /**
     * @param phoneTransferFlag
     *            the phoneTransferFlag to set
     */
    public void setPhoneTransferFlag(Integer phoneTransferFlag) {
        this.phoneTransferFlag = phoneTransferFlag;
    }

    /**
     * @return the raiseTransferFlag
     */
    public Integer getRaiseTransferFlag() {
        return raiseTransferFlag;
    }

    /**
     * @param raiseTransferFlag
     *            the raiseTransferFlag to set
     */
    public void setRaiseTransferFlag(Integer raiseTransferFlag) {
        this.raiseTransferFlag = raiseTransferFlag;
    }

    /**
     * @return the walletFlag
     */
    public Integer getWalletFlag() {
        return walletFlag;
    }

    /**
     * @param walletFlag
     *            the walletFlag to set
     */
    public void setWalletFlag(Integer walletFlag) {
        this.walletFlag = walletFlag;
    }

    /**
     * @return the creditCardFido2Flag
     */
    public Integer getCreditCardFido2Flag() {
        return creditCardFido2Flag;
    }

    /**
     * @param creditCardFido2Flag
     *            the creditCardFido2Flag to set
     */
    public void setCreditCardFido2Flag(Integer creditCardFido2Flag) {
        this.creditCardFido2Flag = creditCardFido2Flag;
    }
}
