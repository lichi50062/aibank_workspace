package com.tfb.aibank.biz.pushlistener.repository.aib.entities;

import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)MbDevicePushInfoEntity.java
 * 
 * <p>Description:行動推播設定檔 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/12/28, Horance Chou
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Cacheable(false)
@Entity
@Table(name = "MB_DEVICE_PUSH_INFO")
public class MbDevicePushInfoEntity {

    /**
     * 行動裝置設定檔鍵值
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEVICE_INFO_KEY")
    private Integer deviceInfoKey;

    /**
     * 行動裝置UUID
     */
    @Basic
    @Column(name = "DEVICE_UUID")
    private String deviceUuId;

    /**
     * 裝置作業系統
     */
    @Basic
    @Column(name = "DEVICE_PLATFORM")
    private String devicePlatform;

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
     * 推播Token
     */
    @Basic
    @Column(name = "PUSH_TOKEN")
    private String pushToken;

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
     * @return the pushToken
     */
    public String getPushToken() {
        return pushToken;
    }

    /**
     * @param pushToken
     *            the pushToken to set
     */
    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public String getDevicePlatform() {
        return devicePlatform;
    }

    public void setDevicePlatform(String devicePlatform) {
        this.devicePlatform = devicePlatform;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
