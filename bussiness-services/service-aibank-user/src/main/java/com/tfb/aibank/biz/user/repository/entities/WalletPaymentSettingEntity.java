package com.tfb.aibank.biz.user.repository.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 錢包功能設定(密碼)
 *
 * @author Alex.Zhao
 */
@Entity
@Table(name = "WALLET_PAYMENT_SETTING")
public class WalletPaymentSettingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 付款密碼設定鍵 */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WALLET_PAYMENT_SETTING_SEQ")
    @SequenceGenerator(name = "WALLET_PAYMENT_SETTING_SEQ", sequenceName = "WALLET_PAYMENT_SETTING_SEQ", allocationSize = 1)
    @Column(name = "WALLET_PAYMENT_SETTING_KEY")
    private Integer walletPaymentSettingKey;

    /** 行動裝置設定鍵值 */
    @Basic
    @Column(name = "DEVICE_UUID", length = 64,  nullable = false)
    private String deviceUuid;

    /** 公司鍵值 */
    @Basic
    @Column(name = "COMPANY_KEY", nullable = false)
    private Integer companyKey;

    /** 使用者鍵值 */
    @Basic
    @Column(name = "USER_KEY", nullable = false)
    private Integer userKey;

    /** 密碼 (SHA-256 加密) */
    @Basic
    @Column(name = "PASSWORD", length = 512)
    private String password;

    /** 建立時間 */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /** 密碼變更時間 */
    @Basic
    @Column(name = "PWD_CHANGE_TIME")
    private Date pwdChangeTime;

    /** 密碼錯誤次數 */
    @Basic
    @Column(name = "PWD_ERROR_COUNT")
    private Integer pwdErrorCount;

    /** 密碼錯誤時間 */
    @Basic
    @Column(name = "PWD_ERROR_TIME")
    private Date pwdErrorTime;

    /** 使用快登驗證 */
    @Basic
    @Column(name = "FAST_LOGIN_AUTH")
    private Boolean fastLoginAuth;

    public Integer getWalletPaymentSettingKey() {
        return walletPaymentSettingKey;
    }

    public void setWalletPaymentSettingKey(Integer walletPaymentSettingKey) {
        this.walletPaymentSettingKey = walletPaymentSettingKey;
    }

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPwdChangeTime() {
        return pwdChangeTime;
    }

    public void setPwdChangeTime(Date pwdChangeTime) {
        this.pwdChangeTime = pwdChangeTime;
    }

    public Integer getPwdErrorCount() {
        return pwdErrorCount;
    }

    public void setPwdErrorCount(Integer pwdErrorCount) {
        this.pwdErrorCount = pwdErrorCount;
    }

    public Date getPwdErrorTime() {
        return pwdErrorTime;
    }

    public void setPwdErrorTime(Date pwdErrorTime) {
        this.pwdErrorTime = pwdErrorTime;
    }

    public Boolean getFastLoginAuth() {
        return fastLoginAuth;
    }

    public void setFastLoginAuth(Boolean fastLoginAuth) {
        this.fastLoginAuth = fastLoginAuth;
    }
}
