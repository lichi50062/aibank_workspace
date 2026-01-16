/*
 * =========================================================================== IBM Confidential AIS Source Materials
 *
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.general.resource.dto;

import java.util.Date;

// @formatter:off
/**
 * @(#)MissionListModel.java
 * 
 * <p>Description:任務牆活動名單檔</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/30, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class MissionListModel {

    /**
     * 數存帳號
     */
    private String account;

    /**
     * 使用者ID
     */
    private String companyUid;

    /**
     * 身分類型，N：新戶，O：舊戶
     */
    private String idType;

    /**
     * 開戶日
     */
    private Date openDate;

    /**
     * 更新時間
     */
    private Date updateTime;

    /**
     * 取得數存帳號
     * 
     * @return String 數存帳號
     */
    public String getAccount() {
        return this.account;
    }

    /**
     * 設定數存帳號
     * 
     * @param account
     *            要設定的數存帳號
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 取得使用者ID
     * 
     * @return String 使用者ID
     */
    public String getCompanyUid() {
        return this.companyUid;
    }

    /**
     * 設定使用者ID
     * 
     * @param companyUid
     *            要設定的使用者ID
     */
    public void setCompanyUid(String companyUid) {
        this.companyUid = companyUid;
    }

    /**
     * 取得身分類型，N：新戶，O：舊戶
     * 
     * @return String 身分類型，N：新戶，O：舊戶
     */
    public String getIdType() {
        return this.idType;
    }

    /**
     * 設定身分類型，N：新戶，O：舊戶
     * 
     * @param idType
     *            要設定的身分類型，N：新戶，O：舊戶
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }

    /**
     * 取得開戶日
     * 
     * @return Date 開戶日
     */
    public Date getOpenDate() {
        return this.openDate;
    }

    /**
     * 設定開戶日
     * 
     * @param openDate
     *            要設定的開戶日
     */
    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    /**
     * 取得更新時間
     * 
     * @return Date 更新時間
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 設定更新時間
     * 
     * @param updateTime
     *            要設定的更新時間
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
