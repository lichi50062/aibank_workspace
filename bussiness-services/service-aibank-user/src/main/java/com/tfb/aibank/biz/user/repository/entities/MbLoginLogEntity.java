/*
 * =========================================================================== IBM Confidential AIS Source Materials
 *
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.user.repository.entities;

import java.io.Serializable;
import java.util.Date;

import com.tfb.aibank.biz.user.repository.entities.support.MbLoginLogEntityListener;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

//@formatter:off
/**
* @(#)MbLoginLogEntity.java
* 行動登入紀錄資料檔
* <p>Description:[程式說明]</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/12/02, John Chang 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Entity
@Table(name = "MB_LOGIN_LOG")
@EntityListeners(MbLoginLogEntityListener.class)
public class MbLoginLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 交易存取記錄鍵值 Reference to SEQ_B2C_ACCESS_LOG_KEY
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "B2C_ACCESS_LOG_SEQ")
    @SequenceGenerator(name = "B2C_ACCESS_LOG_SEQ", sequenceName = "B2C_ACCESS_LOG_SEQ", allocationSize = 100)
    @Column(name = "ACCESS_LOG_KEY")
    private Integer accessLogKey;

    /**
     * 存取時間
     */
    @Basic
    @Column(name = "ACCESS_TIME")
    private Date accessTime;

    /**
     * NATIVE CODE版本
     */
    @Basic
    @Column(name = "APP_VERSION")
    private String appVersion;

    /**
     * 通路 4：行銀
     */
    @Basic
    @Column(name = "CHANNEL_ID")
    private String channelId;

    /**
     * 登入IP
     */
    @Basic
    @Column(name = "CLIENT_IP")
    private String clientIp;

    /**
     * 公司鍵值
     */
    @Basic
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    /**
     * 公司統編
     */
    @Basic
    @Column(name = "COMPANY_UID")
    private String companyUid;

    /**
     * 裝置ID
     */
    @Basic
    @Column(name = "DEVICE_ID")
    private String deviceId;

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
     * 錯誤代碼
     */
    @Basic
    @Column(name = "ERROR_CODE")
    private String errorCode;

    /**
     * 錯誤訊息描述
     */
    @Basic
    @Column(name = "ERROR_DESC")
    private String errorDesc;

    /**
     * 錯誤系統代碼
     */
    @Basic
    @Column(name = "ERROR_SYSTEM_ID")
    private String errorSystemId;

    /**
     * 來源頁面
     */
    @Basic
    @Column(name = "FROM_PAGE_URL")
    private String fromPageUrl;

    /**
     * 語系
     */
    @Basic
    @Column(name = "LOCALE")
    private String locale;

    /**
     * 登入記錄鍵值
     */
    @Basic
    @Column(name = "LOGIN_LOG_KEY")
    private Integer loginLogKey;

    /** 
     * 
     */
    @Basic
    @Column(name = "MENU_ID")
    private String menuId;

    /**
     * 裝置名稱
     */
    @Basic
    @Column(name = "MODEL")
    private String model;

    /**
     * 用戶代碼
     */
    @Basic
    @Column(name = "NAME_CODE")
    private String nameCode;

    /**
     * 國別代碼2碼
     */
    @Basic
    @Column(name = "NATION_CODE")
    private String nationCode;

    /**
     * 國別說明
     */
    @Basic
    @Column(name = "NATION_NAME")
    private String nationName;

    /**
     * 網路 1:wifi 2:mobile(3G or 4G) 3.both
     */
    @Basic
    @Column(name = "NETWORK")
    private Integer network;

    /**
     * 通知鍵值
     */
    @Basic
    @Column(name = "NOTIFICATION_KEY")
    private Integer notificationKey;

    /**
     * 頁面名稱
     */
    @Basic
    @Column(name = "PAGE_URL")
    private String pageUrl;

    /**
     * GET 參數 HTTP GET Query String
     */
    @Basic
    @Column(name = "QUERY_STRING")
    private String queryString;

    /**
     * Resource CODE版本
     */
    @Basic
    @Column(name = "RES_VERSION")
    private String resVersion;

    /**
     * 交易結果 0: 正常 1: 警告 2: 失敗 9: 待確認
     */
    @Basic
    @Column(name = "RESULT_CODE")
    private Integer resultCode;

    /**
     * 通知交易註記 1:交易記錄查詢時需提供檢視按鈕
     */
    @Basic
    @Column(name = "TASK_FLAG")
    private Integer taskFlag;

    /**
     * 交易代號
     */
    @Basic
    @Column(name = "TASK_ID")
    private String taskId;

    /**
     * 使用者鍵值
     */
    @Basic
    @Column(name = "USER_KEY")
    private Integer userKey;

    /**
     * 登入時的使用者代號 效能考量
     */
    @Basic
    @Column(name = "USER_UUID")
    private String userUuid;

    /**
     * 取得交易存取記錄鍵值 Reference to SEQ_B2C_ACCESS_LOG_KEY
     * 
     * @return Integer 交易存取記錄鍵值 Reference to SEQ_B2C_ACCESS_LOG_KEY
     */
    public Integer getAccessLogKey() {
        return this.accessLogKey;
    }

    /**
     * 設定交易存取記錄鍵值 Reference to SEQ_B2C_ACCESS_LOG_KEY
     * 
     * @param accessLogKey
     *            要設定的交易存取記錄鍵值 Reference to SEQ_B2C_ACCESS_LOG_KEY
     */
    public void setAccessLogKey(Integer accessLogKey) {
        this.accessLogKey = accessLogKey;
    }

    /**
     * 取得存取時間
     * 
     * @return Date 存取時間
     */
    public Date getAccessTime() {
        return this.accessTime;
    }

    /**
     * 設定存取時間
     * 
     * @param accessTime
     *            要設定的存取時間
     */
    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    /**
     * 取得NATIVE CODE版本
     * 
     * @return String NATIVE CODE版本
     */
    public String getAppVersion() {
        return this.appVersion;
    }

    /**
     * 設定NATIVE CODE版本
     * 
     * @param appVersion
     *            要設定的NATIVE CODE版本
     */
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    /**
     * 取得通路 4：行銀
     * 
     * @return String 通路 4：行銀
     */
    public String getChannelId() {
        return this.channelId;
    }

    /**
     * 設定通路 4：行銀
     * 
     * @param channelId
     *            要設定的通路 4：行銀
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * 取得登入IP
     * 
     * @return String 登入IP
     */
    public String getClientIp() {
        return this.clientIp;
    }

    /**
     * 設定登入IP
     * 
     * @param clientIp
     *            要設定的登入IP
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * 取得公司鍵值
     * 
     * @return Integer 公司鍵值
     */
    public Integer getCompanyKey() {
        return this.companyKey;
    }

    /**
     * 設定公司鍵值
     * 
     * @param companyKey
     *            要設定的公司鍵值
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * 取得公司統編
     * 
     * @return String 公司統編
     */
    public String getCompanyUid() {
        return this.companyUid;
    }

    /**
     * 設定公司統編
     * 
     * @param companyUid
     *            要設定的公司統編
     */
    public void setCompanyUid(String companyUid) {
        this.companyUid = companyUid;
    }

    /**
     * 取得裝置ID
     * 
     * @return String 裝置ID
     */
    public String getDeviceId() {
        return this.deviceId;
    }

    /**
     * 設定裝置ID
     * 
     * @param deviceId
     *            要設定的裝置ID
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 取得裝置作業系統
     * 
     * @return String 裝置作業系統
     */
    public String getDevicePlatform() {
        return this.devicePlatform;
    }

    /**
     * 設定裝置作業系統
     * 
     * @param devicePlatform
     *            要設定的裝置作業系統
     */
    public void setDevicePlatform(String devicePlatform) {
        this.devicePlatform = devicePlatform;
    }

    /**
     * 取得裝置作業系統版本
     * 
     * @return String 裝置作業系統版本
     */
    public String getDevicePlatformVersion() {
        return this.devicePlatformVersion;
    }

    /**
     * 設定裝置作業系統版本
     * 
     * @param devicePlatformVersion
     *            要設定的裝置作業系統版本
     */
    public void setDevicePlatformVersion(String devicePlatformVersion) {
        this.devicePlatformVersion = devicePlatformVersion;
    }

    /**
     * 取得錯誤代碼
     * 
     * @return String 錯誤代碼
     */
    public String getErrorCode() {
        return this.errorCode;
    }

    /**
     * 設定錯誤代碼
     * 
     * @param errorCode
     *            要設定的錯誤代碼
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 取得錯誤訊息描述
     * 
     * @return String 錯誤訊息描述
     */
    public String getErrorDesc() {
        return this.errorDesc;
    }

    /**
     * 設定錯誤訊息描述
     * 
     * @param errorDesc
     *            要設定的錯誤訊息描述
     */
    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    /**
     * 取得錯誤系統代碼
     * 
     * @return String 錯誤系統代碼
     */
    public String getErrorSystemId() {
        return this.errorSystemId;
    }

    /**
     * 設定錯誤系統代碼
     * 
     * @param errorSystemId
     *            要設定的錯誤系統代碼
     */
    public void setErrorSystemId(String errorSystemId) {
        this.errorSystemId = errorSystemId;
    }

    /**
     * 取得來源頁面
     * 
     * @return String 來源頁面
     */
    public String getFromPageUrl() {
        return this.fromPageUrl;
    }

    /**
     * 設定來源頁面
     * 
     * @param fromPageUrl
     *            要設定的來源頁面
     */
    public void setFromPageUrl(String fromPageUrl) {
        this.fromPageUrl = fromPageUrl;
    }

    /**
     * 取得語系
     * 
     * @return String 語系
     */
    public String getLocale() {
        return this.locale;
    }

    /**
     * 設定語系
     * 
     * @param locale
     *            要設定的語系
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * 取得登入記錄鍵值
     * 
     * @return Integer 登入記錄鍵值
     */
    public Integer getLoginLogKey() {
        return this.loginLogKey;
    }

    /**
     * 設定登入記錄鍵值
     * 
     * @param loginLogKey
     *            要設定的登入記錄鍵值
     */
    public void setLoginLogKey(Integer loginLogKey) {
        this.loginLogKey = loginLogKey;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getMenuId() {
        return this.menuId;
    }

    /**
     * 設定
     * 
     * @param menuId
     *            要設定的
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * 取得裝置名稱
     * 
     * @return String 裝置名稱
     */
    public String getModel() {
        return this.model;
    }

    /**
     * 設定裝置名稱
     * 
     * @param model
     *            要設定的裝置名稱
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 取得用戶代碼
     * 
     * @return String 用戶代碼
     */
    public String getNameCode() {
        return this.nameCode;
    }

    /**
     * 設定用戶代碼
     * 
     * @param nameCode
     *            要設定的用戶代碼
     */
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    /**
     * 取得國別代碼2碼
     * 
     * @return String 國別代碼2碼
     */
    public String getNationCode() {
        return this.nationCode;
    }

    /**
     * 設定國別代碼2碼
     * 
     * @param nationCode
     *            要設定的國別代碼2碼
     */
    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    /**
     * 取得國別說明
     * 
     * @return String 國別說明
     */
    public String getNationName() {
        return this.nationName;
    }

    /**
     * 設定國別說明
     * 
     * @param nationName
     *            要設定的國別說明
     */
    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    /**
     * 取得網路 1:wifi 2:mobile(3G or 4G) 3.both
     * 
     * @return Integer 網路 1:wifi 2:mobile(3G or 4G) 3.both
     */
    public Integer getNetwork() {
        return this.network;
    }

    /**
     * 設定網路 1:wifi 2:mobile(3G or 4G) 3.both
     * 
     * @param network
     *            要設定的網路 1:wifi 2:mobile(3G or 4G) 3.both
     */
    public void setNetwork(Integer network) {
        this.network = network;
    }

    /**
     * 取得通知鍵值
     * 
     * @return Integer 通知鍵值
     */
    public Integer getNotificationKey() {
        return this.notificationKey;
    }

    /**
     * 設定通知鍵值
     * 
     * @param notificationKey
     *            要設定的通知鍵值
     */
    public void setNotificationKey(Integer notificationKey) {
        this.notificationKey = notificationKey;
    }

    /**
     * 取得頁面名稱
     * 
     * @return String 頁面名稱
     */
    public String getPageUrl() {
        return this.pageUrl;
    }

    /**
     * 設定頁面名稱
     * 
     * @param pageUrl
     *            要設定的頁面名稱
     */
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    /**
     * 取得GET 參數 HTTP GET Query String
     * 
     * @return String GET 參數 HTTP GET Query String
     */
    public String getQueryString() {
        return this.queryString;
    }

    /**
     * 設定GET 參數 HTTP GET Query String
     * 
     * @param queryString
     *            要設定的GET 參數 HTTP GET Query String
     */
    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    /**
     * 取得Resource CODE版本
     * 
     * @return String Resource CODE版本
     */
    public String getResVersion() {
        return this.resVersion;
    }

    /**
     * 設定Resource CODE版本
     * 
     * @param resVersion
     *            要設定的Resource CODE版本
     */
    public void setResVersion(String resVersion) {
        this.resVersion = resVersion;
    }

    /**
     * 取得交易結果 0: 正常 1: 警告 2: 失敗 9: 待確認
     * 
     * @return Integer 交易結果 0: 正常 1: 警告 2: 失敗 9: 待確認
     */
    public Integer getResultCode() {
        return this.resultCode;
    }

    /**
     * 設定交易結果 0: 正常 1: 警告 2: 失敗 9: 待確認
     * 
     * @param resultCode
     *            要設定的交易結果 0: 正常 1: 警告 2: 失敗 9: 待確認
     */
    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * 取得通知交易註記 1:交易記錄查詢時需提供檢視按鈕
     * 
     * @return Integer 通知交易註記 1:交易記錄查詢時需提供檢視按鈕
     */
    public Integer getTaskFlag() {
        return this.taskFlag;
    }

    /**
     * 設定通知交易註記 1:交易記錄查詢時需提供檢視按鈕
     * 
     * @param taskFlag
     *            要設定的通知交易註記 1:交易記錄查詢時需提供檢視按鈕
     */
    public void setTaskFlag(Integer taskFlag) {
        this.taskFlag = taskFlag;
    }

    /**
     * 取得交易代號
     * 
     * @return String 交易代號
     */
    public String getTaskId() {
        return this.taskId;
    }

    /**
     * 設定交易代號
     * 
     * @param taskId
     *            要設定的交易代號
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * 取得使用者鍵值
     * 
     * @return Integer 使用者鍵值
     */
    public Integer getUserKey() {
        return this.userKey;
    }

    /**
     * 設定使用者鍵值
     * 
     * @param userKey
     *            要設定的使用者鍵值
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    /**
     * 取得登入時的使用者代號 效能考量
     * 
     * @return String 登入時的使用者代號 效能考量
     */
    public String getUserUuid() {
        return this.userUuid;
    }

    /**
     * 設定登入時的使用者代號 效能考量
     * 
     * @param userUuid
     *            要設定的登入時的使用者代號 效能考量
     */
    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }
}
