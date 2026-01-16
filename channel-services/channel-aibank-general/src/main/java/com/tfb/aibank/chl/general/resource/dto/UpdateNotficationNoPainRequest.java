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
package com.tfb.aibank.chl.general.resource.dto;

import java.util.Date;
import java.util.List;

// @formatter:off
/**
 * @(#)UpdateNotficationNoPainRequest.java
 * 
 * <p>Description:更新移轉推播通知設定</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/19, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class UpdateNotficationNoPainRequest {

    /** 身分證字號 */
    private String custId;

    /** 使用者代號 */
    private String userId;

    /** 誤別碼 */
    private String uidDup;

    /** 公司類型 */
    private Integer companyKind;

    /**
     * 是否授權訊息通知，1:已授權；0或空白:未授權; -1 本次不設定
     */
    private int notificationAgreeFlag;

    /**
     * 是否同意開啟Line 1:已授權；0或空白; -1 本次不設定
     */
    private int lineAgreeFlag;

    /**
     * 行動電話
     */
    private String mobileNo;

    /** 生日 */
    private Date birthday;

    /** 用戶代碼. */
    private String nameCode;

    /** 設定項目 */
    private List<String> notificationTypes;

    /**
     * 版本號
     */
    private String version;

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the custId
     */
    public String getCustId() {
        return custId;
    }

    /**
     * @param custId
     *            the custId to set
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the uidDup
     */
    public String getUidDup() {
        return uidDup;
    }

    /**
     * @param uidDup
     *            the uidDup to set
     */
    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }

    /**
     * @return the companyKind
     */
    public Integer getCompanyKind() {
        return companyKind;
    }

    /**
     * @param companyKind
     *            the companyKind to set
     */
    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

    /**
     * @return the notificationAgreeFlag
     */
    public int getNotificationAgreeFlag() {
        return notificationAgreeFlag;
    }

    /**
     * @param notificationAgreeFlag
     *            the notificationAgreeFlag to set
     */
    public void setNotificationAgreeFlag(int notificationAgreeFlag) {
        this.notificationAgreeFlag = notificationAgreeFlag;
    }

    /**
     * @return the lineAgreeFlag
     */
    public int getLineAgreeFlag() {
        return lineAgreeFlag;
    }

    /**
     * @param lineAgreeFlag
     *            the lineAgreeFlag to set
     */
    public void setLineAgreeFlag(int lineAgreeFlag) {
        this.lineAgreeFlag = lineAgreeFlag;
    }

    /**
     * @return the mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * @param mobileNo
     *            the mobileNo to set
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     *            the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the nameCode
     */
    public String getNameCode() {
        return nameCode;
    }

    /**
     * @param nameCode
     *            the nameCode to set
     */
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    /**
     * @return the notificationTypes
     */
    public List<String> getNotificationTypes() {
        return notificationTypes;
    }

    /**
     * @param notificationTypes
     *            the notificationTypes to set
     */
    public void setNotificationTypes(List<String> notificationTypes) {
        this.notificationTypes = notificationTypes;
    }

}
