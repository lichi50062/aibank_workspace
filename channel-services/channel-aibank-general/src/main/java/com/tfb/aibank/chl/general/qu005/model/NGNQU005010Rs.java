package com.tfb.aibank.chl.general.qu005.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.annotations.FormatDate;
import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NGNQU005010Rs.java
 * 
 * <p>Description:帳戶安全中心 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/04, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNQU005010Rs implements RsData {

    /** 變更密碼日期 */
    @FormatDate(pattern = "yyyy/MM/dd")
    private Date pwChgDate;
    /** 半年內是否變更密碼 */
    private boolean changePwdHalfYear;
    /** 半年內是否沿用舊密碼 */
    private boolean keepOldPwdHalfYear;
    /** 月份 */
    private int month;
    /** 客戶Email */
    private String userEmail;
    /** 是否已設置Email */
    private boolean emailExists;
    /** Email格式是否正確 */
    private boolean emailValid;
    /** 是否已啟用OTP */
    private boolean enableMOTP;
    /** 是否已啟用FIDO快速登入 */
    private boolean enableFIDO;
    /** Face ID、Touch ID、生物辨識 */
    private String biometricsDesc;
    /** 是否啟用雙重驗證登入 */
    private boolean twoStepFlag;
    /** 是否已綁定裝置 */
    private boolean deviceBinding;
    /** 裝置名稱 */
    private String deviceName;
    /** APP是否為最近版本 */
    private boolean appIsLatestVersion;
    /** APP版本 from APP */
    private String appVersion;
    /** APP版本 from DB */
    private String dbVersion;
    /** 手機作業系統 */
    private String mobileOS;
    /** 是否為一般會員 */
    private boolean isGeneral;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean isEmailExists() {
        return emailExists;
    }

    public void setEmailExists(boolean emailExists) {
        this.emailExists = emailExists;
    }

    public boolean isEmailValid() {
        return emailValid;
    }

    public void setEmailValid(boolean emailValid) {
        this.emailValid = emailValid;
    }

    public boolean isChangePwdHalfYear() {
        return changePwdHalfYear;
    }

    public void setChangePwdHalfYear(boolean changePwdHalfYear) {
        this.changePwdHalfYear = changePwdHalfYear;
    }

    public boolean isKeepOldPwdHalfYear() {
        return keepOldPwdHalfYear;
    }

    public void setKeepOldPwdHalfYear(boolean keepOldPwdHalfYear) {
        this.keepOldPwdHalfYear = keepOldPwdHalfYear;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public boolean isEnableFIDO() {
        return enableFIDO;
    }

    public void setEnableFIDO(boolean enableFIDO) {
        this.enableFIDO = enableFIDO;
    }

    public String getBiometricsDesc() {
        return biometricsDesc;
    }

    public void setBiometricsDesc(String biometricsDesc) {
        this.biometricsDesc = biometricsDesc;
    }

    /**
     * @return {@link #twoStepFlag}
     */
    public boolean isTwoStepFlag() {
        return twoStepFlag;
    }

    /**
     * @param twoStepFlag
     *            {@link #twoStepFlag}
     */
    public void setTwoStepFlag(boolean twoStepFlag) {
        this.twoStepFlag = twoStepFlag;
    }

    public boolean isDeviceBinding() {
        return deviceBinding;
    }

    public void setDeviceBinding(boolean deviceBinding) {
        this.deviceBinding = deviceBinding;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public boolean isAppIsLatestVersion() {
        return appIsLatestVersion;
    }

    public void setAppIsLatestVersion(boolean appIsLatestVersion) {
        this.appIsLatestVersion = appIsLatestVersion;
    }

    public String getMobileOS() {
        return mobileOS;
    }

    public void setMobileOS(String mobileOS) {
        this.mobileOS = mobileOS;
    }

    public boolean isEnableMOTP() {
        return enableMOTP;
    }

    public void setEnableMOTP(boolean enableMOTP) {
        this.enableMOTP = enableMOTP;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public boolean isGeneral() {
        return isGeneral;
    }

    public void setGeneral(boolean isGeneral) {
        this.isGeneral = isGeneral;
    }

    public Date getPwChgDate() {
        return pwChgDate;
    }

    public void setPwChgDate(Date pwChgDate) {
        this.pwChgDate = pwChgDate;
    }

    public String getDbVersion() {
        return dbVersion;
    }

    public void setDbVersion(String dbVersion) {
        this.dbVersion = dbVersion;
    }

}
