package com.tfb.aibank.biz.pushlistener.repository.aib.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)FirstDownloadRecordEntity.java
 * 
 * <p>Description:首次開啟APP紀錄 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "FIRST_DOWNLOAD_RECORD")
public class FirstDownloadRecordEntity implements Serializable {

    private static final long serialVersionUID = 868148866109643030L;

    /**
     * 資料鍵值
     */
    @Id
    @Column(name = "FIRST_DOWNLOAD_RECORD_KEY")
    private Integer firstDownloadRecordKey;

    /**
     * 行動裝置UUID
     */
    @Basic
    @Column(name = "DEVICE_UUID")
    private String deviceUuid;

    /**
     * 首次開啟APP時間
     */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 行動裝置作業系統
     */
    @Basic
    @Column(name = "PLATFORM")
    private String platform;

    /**
     * 已發送推播，0:未發送、1:已發送
     */
    @Basic
    @Column(name = "PUSH_FLAG")
    private Integer pushFlag;

    /**
     * 登入時間
     */
    @Basic
    @Column(name = "LOGIN_TIME")
    private Date loginTime;

    /**
     * 推播 Token
     */
    @Column(name = "PUSH_TOKEN")
    private String pushToken;

    public Integer getFirstDownloadRecordKey() {
        return firstDownloadRecordKey;
    }

    public void setFirstDownloadRecordKey(Integer firstDownloadRecordKey) {
        this.firstDownloadRecordKey = firstDownloadRecordKey;
    }

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getPushFlag() {
        return pushFlag;
    }

    public void setPushFlag(Integer pushFlag) {
        this.pushFlag = pushFlag;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

}
