/**
 * 
 */
package com.tfb.aibank.biz.user.repository.entities;

import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

//@formatter:off
/**
* @(#)MbGestureProfileEntity.java
* 行動手勢基本資料檔
* <p>Description:[程式說明]</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, John Chang 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Entity
@Table(name = "AIBANK_GESTURE_PROFILE")
public class MbGestureProfileEntity {

    /**
     * 行動手勢鍵值
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MB_GESTURE_PROFILE_SEQ")
    @SequenceGenerator(name = "MB_GESTURE_PROFILE_SEQ", sequenceName = "MB_GESTURE_PROFILE_SEQ", allocationSize = 1)
    @Column(name = "GESTURE_PROFILE_KEY")
    private Integer gestureProfileKey;

    /**
     * 行動裝置設定檔鍵值
     */
    @Basic
    @Column(name = "DEVICE_INFO_KEY")
    private Integer deviceInfoKey;

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
     * 手勢密碼SHA-256
     */
    @Basic
    @Column(name = "PASSWORD")
    private String password;

    /**
     * 手勢密碼變異係數
     */
    @Basic
    @Column(name = "COEFFICIENT")
    private String coefficient;
    /**
     * 狀態
     */
    @Basic
    @Column(name = "STATUS")
    private Integer status;

    /**
     * 密碼錯誤次數
     */
    @Basic
    @Column(name = "PWD_ERROR_COUNT")
    private Integer pwdErrorCount;

    /**
     * 密碼錯誤時間
     */
    @Basic
    @Column(name = "PWD_ERROR_TIME")
    private Date pwdErrorTime;

    /**
     * 密碼變更時間
     */
    @Basic
    @Column(name = "PWD_CHANGE_TIME")
    private Date pwdChangeTime;

    /**
     * 建立時間
     */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 取得 gestureProfileKey
     *
     * @return Integer
     */
    public Integer getGestureProfileKey() {
        return gestureProfileKey;
    }

    /**
     * 設定 gestureProfileKey
     *
     * @param gestureProfileKey
     */
    public void setGestureProfileKey(Integer gestureProfileKey) {
        this.gestureProfileKey = gestureProfileKey;
    }

    /**
     * 取得 deviceInfoKey
     *
     * @return Integer
     */
    public Integer getDeviceInfoKey() {
        return deviceInfoKey;
    }

    /**
     * 設定 deviceInfoKey
     *
     * @param deviceInfoKey
     */
    public void setDeviceInfoKey(Integer deviceInfoKey) {
        this.deviceInfoKey = deviceInfoKey;
    }

    /**
     * 取得 companyKey
     *
     * @return Integer
     */
    public Integer getCompanyKey() {
        return companyKey;
    }

    /**
     * 設定 companyKey
     *
     * @param companyKey
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * 取得 userKey
     *
     * @return Integer
     */
    public Integer getUserKey() {
        return userKey;
    }

    /**
     * 設定 userKey
     *
     * @param userKey
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    /**
     * 取得 pass-word
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * 設定 pass-word
     *
     * @param pass-word
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 取得 status
     *
     * @return String
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 設定 status
     *
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 取得 pwdErrorCount
     *
     * @return String
     */
    public Integer getPwdErrorCount() {
        return pwdErrorCount;
    }

    /**
     * 設定 pwdErrorCount
     *
     * @param pwdErrorCount
     */
    public void setPwdErrorCount(Integer pwdErrorCount) {
        this.pwdErrorCount = pwdErrorCount;
    }

    /**
     * 取得 pwdErrorTime
     *
     * @return String
     */
    public Date getPwdErrorTime() {
        return pwdErrorTime;
    }

    /**
     * 設定 pwdErrorTime
     *
     * @param pwdErrorTime
     */
    public void setPwdErrorTime(Date pwdErrorTime) {
        this.pwdErrorTime = pwdErrorTime;
    }

    /**
     * 取得 pwdChangeTime
     *
     * @return Date
     */
    public Date getPwdChangeTime() {
        return pwdChangeTime;
    }

    /**
     * 設定 pwdChangeTime
     *
     * @param pwdChangeTime
     */
    public void setPwdChangeTime(Date pwdChangeTime) {
        this.pwdChangeTime = pwdChangeTime;
    }

    /**
     * 取得 createTime
     *
     * @return Date
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 設定 createTime
     *
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得 coefficient
     *
     * @return String
     */
    public String getCoefficient() {
        return coefficient;
    }

    /**
     * 設定 coefficient
     *
     * @param coefficient
     */
    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient;
    }

}
