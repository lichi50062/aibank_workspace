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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * Entity
 * 
 * @author $author$
 */
@Entity
@Table(name = "EB_LOGIN_LOG_B")
public class EbLoginLogBEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 
     * 
     */
    @Basic
    @Column(name = "CHANNEL_ID")
    private String channelId;

    /** 
     * 
     */
    @Basic
    @Column(name = "CITY_NAME")
    private String cityName;

    /** 
     * 
     */
    @Basic
    @Column(name = "CLIENT_IP")
    private String clientIp;

    /** 
     * 
     */
    @Basic
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    /** 
     * 
     */
    @Basic
    @Column(name = "COMPANY_UID")
    private String companyUid;

    /** 
     * 
     */
    @Basic
    @Column(name = "END_IP")
    private String endIp;

    /** 
     * 
     */
    @Basic
    @Column(name = "ERROR_CODE")
    private String errorCode;

    /** 
     * 
     */
    @Basic
    @Column(name = "ERROR_DESC")
    private String errorDesc;

    /** 
     * 
     */
    @Basic
    @Column(name = "ERROR_SYSTEM_ID")
    private String errorSystemId;

    /** 
     * 
     */
    @Basic
    @Column(name = "LOCATION_ID")
    private Integer locationId;

    /** 
     * 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EB_LOGIN_LOG_SEQ")
    @SequenceGenerator(name = "EB_LOGIN_LOG_SEQ", sequenceName = "EB_LOGIN_LOG_SEQ", allocationSize = 100)
    @Column(name = "LOGIN_LOG_KEY")
    private Integer loginLogKey;

    /** 
     * 
     */
    @Basic
    @Column(name = "LOGIN_TIME")
    private Date loginTime;

    /** 
     * 
     */
    @Basic
    @Column(name = "LOGOUT_TIME")
    private Date logoutTime;

    /** 
     * 
     */
    @Basic
    @Column(name = "NAME_CODE")
    private String nameCode;

    /** 
     * 
     */
    @Basic
    @Column(name = "NATION_CODE")
    private String nationCode;

    /** 
     * 
     */
    @Basic
    @Column(name = "NATION_NAME")
    private String nationName;

    /** 
     * 
     */
    @Basic
    @Column(name = "SCREEN_HEIGHT")
    private Integer screenHeight;

    /** 
     * 
     */
    @Basic
    @Column(name = "SCREEN_WIDTH")
    private Integer screenWidth;

    /** 
     * 
     */
    @Basic
    @Column(name = "SERVER_ID")
    private String serverId;

    /** 
     * 
     */
    @Basic
    @Column(name = "SESSION_ID")
    private String sessionId;

    /** 
     * 
     */
    @Basic
    @Column(name = "SIGNON_TOKEN")
    private String signonToken;

    /** 
     * 
     */
    @Basic
    @Column(name = "START_IP")
    private String startIp;

    /** 
     * 
     */
    @Basic
    @Column(name = "UID_DUP")
    private String uidDup;

    /** 
     * 
     */
    @Basic
    @Column(name = "USER_AGENT")
    private String userAgent;

    /** 
     * 
     */
    @Basic
    @Column(name = "USER_KEY")
    private Integer userKey;

    /** 
     * 
     */
    @Basic
    @Column(name = "USER_UUID")
    private String userUuid;

    /**
     * 取得
     * 
     * @return String
     */
    public String getChannelId() {
        return this.channelId;
    }

    /**
     * 設定
     * 
     * @param channelId
     *            要設定的
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getCityName() {
        return this.cityName;
    }

    /**
     * 設定
     * 
     * @param cityName
     *            要設定的
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getClientIp() {
        return this.clientIp;
    }

    /**
     * 設定
     * 
     * @param clientIp
     *            要設定的
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * 取得
     * 
     * @return Integer
     */
    public Integer getCompanyKey() {
        return this.companyKey;
    }

    /**
     * 設定
     * 
     * @param companyKey
     *            要設定的
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getCompanyUid() {
        return this.companyUid;
    }

    /**
     * 設定
     * 
     * @param companyUid
     *            要設定的
     */
    public void setCompanyUid(String companyUid) {
        this.companyUid = companyUid;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getEndIp() {
        return this.endIp;
    }

    /**
     * 設定
     * 
     * @param endIp
     *            要設定的
     */
    public void setEndIp(String endIp) {
        this.endIp = endIp;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getErrorCode() {
        return this.errorCode;
    }

    /**
     * 設定
     * 
     * @param errorCode
     *            要設定的
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getErrorDesc() {
        return this.errorDesc;
    }

    /**
     * 設定
     * 
     * @param errorDesc
     *            要設定的
     */
    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getErrorSystemId() {
        return this.errorSystemId;
    }

    /**
     * 設定
     * 
     * @param errorSystemId
     *            要設定的
     */
    public void setErrorSystemId(String errorSystemId) {
        this.errorSystemId = errorSystemId;
    }

    /**
     * 取得
     * 
     * @return Integer
     */
    public Integer getLocationId() {
        return this.locationId;
    }

    /**
     * 設定
     * 
     * @param locationId
     *            要設定的
     */
    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    /**
     * 取得
     * 
     * @return Integer
     */
    public Integer getLoginLogKey() {
        return this.loginLogKey;
    }

    /**
     * 設定
     * 
     * @param loginLogKey
     *            要設定的
     */
    public void setLoginLogKey(Integer loginLogKey) {
        this.loginLogKey = loginLogKey;
    }

    /**
     * 取得
     * 
     * @return Date
     */
    public Date getLoginTime() {
        return this.loginTime;
    }

    /**
     * 設定
     * 
     * @param loginTime
     *            要設定的
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 取得
     * 
     * @return Date
     */
    public Date getLogoutTime() {
        return this.logoutTime;
    }

    /**
     * 設定
     * 
     * @param logoutTime
     *            要設定的
     */
    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getNameCode() {
        return this.nameCode;
    }

    /**
     * 設定
     * 
     * @param nameCode
     *            要設定的
     */
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getNationCode() {
        return this.nationCode;
    }

    /**
     * 設定
     * 
     * @param nationCode
     *            要設定的
     */
    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getNationName() {
        return this.nationName;
    }

    /**
     * 設定
     * 
     * @param nationName
     *            要設定的
     */
    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    /**
     * 取得
     * 
     * @return Integer
     */
    public Integer getScreenHeight() {
        return this.screenHeight;
    }

    /**
     * 設定
     * 
     * @param screenHeight
     *            要設定的
     */
    public void setScreenHeight(Integer screenHeight) {
        this.screenHeight = screenHeight;
    }

    /**
     * 取得
     * 
     * @return Integer
     */
    public Integer getScreenWidth() {
        return this.screenWidth;
    }

    /**
     * 設定
     * 
     * @param screenWidth
     *            要設定的
     */
    public void setScreenWidth(Integer screenWidth) {
        this.screenWidth = screenWidth;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getServerId() {
        return this.serverId;
    }

    /**
     * 設定
     * 
     * @param serverId
     *            要設定的
     */
    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getSessionId() {
        return this.sessionId;
    }

    /**
     * 設定
     * 
     * @param sessionId
     *            要設定的
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getSignonToken() {
        return this.signonToken;
    }

    /**
     * 設定
     * 
     * @param signonToken
     *            要設定的
     */
    public void setSignonToken(String signonToken) {
        this.signonToken = signonToken;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getStartIp() {
        return this.startIp;
    }

    /**
     * 設定
     * 
     * @param startIp
     *            要設定的
     */
    public void setStartIp(String startIp) {
        this.startIp = startIp;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getUidDup() {
        return this.uidDup;
    }

    /**
     * 設定
     * 
     * @param uidDup
     *            要設定的
     */
    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getUserAgent() {
        return this.userAgent;
    }

    /**
     * 設定
     * 
     * @param userAgent
     *            要設定的
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * 取得
     * 
     * @return Integer
     */
    public Integer getUserKey() {
        return this.userKey;
    }

    /**
     * 設定
     * 
     * @param userKey
     *            要設定的
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getUserUuid() {
        return this.userUuid;
    }

    /**
     * 設定
     * 
     * @param userUuid
     *            要設定的
     */
    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }
}
