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
package com.tfb.aibank.chl.session;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

// @formatter:off
/**
 * @(#)UserInfo.java
 * 
 * <p>Description:login API response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Horance Chou
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@JsonSerialize
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 統編 */
    private String corpIdNum;

    /** 使用者代號 */
    private String uid;
    /** 公司名稱 */
    private String cidName;

    /** 使用者姓名 */
    private String name;

    /** 是否使用token N-晶片卡；Y-Token */
    private String tokenFlag;

    /** 登入時間 */
    private Date loginDateTime;

    /** 登入身份別 */
    private String authType;

    /** 授權碼 */
    private String authorityCode;

    /** 公司電話 */
    private String telNum;

    /** 公司傳真 */
    private String faxNum;

    /** 本行銀行代號 */
    private String ctcbBankType;

    /** 公司email */
    private String email;

    /** 公司通訊地址 */
    private String addr;

    /** 母公司統編代碼 */
    private String pid;

    /** Session_ID */
    private String sessionID;

    /** 國家別 */
    private String country;
    /** 本國幣別 */
    private String localCur;

    /** 系統代號 */
    private String systemId;

    /** 身份別 */
    private String idType;

    /** 判斷法金身分 */
    private String segmentCode;

    /**
     * 客戶別
     */
    private String custType;

    /**
     * 獨資戶負責人身分證號
     */
    private String custID;

    /**
     * MSB客戶Flag<br/>
     * Y-在MSB客戶名單中；N-不在MSB客戶名單中
     */
    private String msbFlag;

    /**
     * 獨資戶身分確認提醒日期
     */
    private String popupDate;

    /** 取得統編 */
    public String getCorpIdNum() {
        return this.corpIdNum;
    }

    /** 設定統編 */
    public void setCorpIdNum(String corpIdNum) {
        this.corpIdNum = corpIdNum;
    }

    /** 取得使用者代號 */
    public String getUid() {
        return this.uid;
    }

    /** 設定使用者代號 */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /** 取得公司名稱 */
    public String getCidName() {
        return this.cidName;
    }

    /** 設定公司名稱 */
    public void setCidName(String cidName) {
        this.cidName = cidName;
    }

    /** 取得使用者姓名 */
    public String getName() {
        return this.name;
    }

    /** 設定使用者姓名 */
    public void setName(String name) {
        this.name = name;
    }

    /** 取得授權碼 */
    public String getAuthorityCode() {
        return this.authorityCode;
    }

    /** 設定授權碼 */
    public void setAuthorityCode(String authorityCode) {
        this.authorityCode = authorityCode;
    }

    /** 取得是否使用token N-晶片卡；Y-Token */
    public String getTokenFlag() {
        return this.tokenFlag;
    }

    /** 設定是否使用token N-晶片卡；Y-Token */
    public void setTokenFlag(String tokenFlag) {
        this.tokenFlag = tokenFlag;
    }

    /** 取得登入時間 */
    public Date getLoginDateTime() {
        return this.loginDateTime;
    }

    /** 設定登入時間 */
    public void setLoginDateTime(Date loginDateTime) {
        this.loginDateTime = loginDateTime;
    }

    /** 取得公司電話 */
    public String getTelNum() {
        return this.telNum;
    }

    /** 設定公司電話 */
    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    /** 取得公司傳真 */
    public String getFaxNum() {
        return this.faxNum;
    }

    /** 設定公司傳真 */
    public void setFaxNum(String faxNum) {
        this.faxNum = faxNum;
    }

    /** 取得公司email */
    public String getEmail() {
        return this.email;
    }

    /** 設定公司email */
    public void setEmail(String email) {
        this.email = email;
    }

    /** 取得公司通訊地址 */
    public String getAddr() {
        return this.addr;
    }

    /** 設定公司通訊地址 */
    public void setAddr(String addr) {
        this.addr = addr;
    }

    /** 取得母公司統編代碼 */
    public String getPid() {
        return this.pid;
    }

    /** 設定母公司統編代碼 */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /** 取得Session_ID */
    public String getSessionID() {
        return this.sessionID;
    }

    /** 設定Session_ID */
    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    /** 取得國家別 */
    public String getCountry() {
        return this.country;
    }

    /** 設定國家別 */
    public void setCountry(String country) {
        this.country = country;
    }

    /** 取得本行銀行代號 */
    public String getCtcbBankType() {
        return this.ctcbBankType;
    }

    /** 設定本行銀行代號 */
    public void setCtcbBankType(String ctcbBankType) {
        this.ctcbBankType = ctcbBankType;
    }

    /** 取得本國幣別 */
    public String getLocalCur() {
        return this.localCur;
    }

    /** 設定本國幣別 */
    public void setLocalCur(String localCur) {
        this.localCur = localCur;
    }

    /** 取得系統代號 */
    public String getSystemId() {
        return this.systemId;
    }

    /** 設定系統代號 */
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    /** 取得身份別 */
    public String getIdType() {
        return this.idType;
    }

    /** 設定身份別 */
    public void setIdType(String idType) {
        this.idType = idType;
    }

    /** 取得判斷法金身分 */
    public String getSegmentCode() {
        return this.segmentCode;
    }

    /** 設定判斷法金身分 */
    public void setSegmentCode(String segmentCode) {
        this.segmentCode = segmentCode;
    }

    /**
     * @return the custType
     */
    public String getCustType() {
        return custType;
    }

    /**
     * @param custType
     *            the custType to set
     */
    public void setCustType(String custType) {
        this.custType = custType;
    }

    /**
     * @return the custID
     */
    public String getCustID() {
        return custID;
    }

    /**
     * @param custID
     *            the custID to set
     */
    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getMsbFlag() {
        return msbFlag;
    }

    public void setMsbFlag(String msbFlag) {
        this.msbFlag = msbFlag;
    }

    public String getPopupDate() {
        return popupDate;
    }

    public void setPopupDate(String popupDate) {
        this.popupDate = popupDate;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

}
