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
 * @(#)MissionDetailModel.java
 * 
 * <p>Description:任務牆活動明細檔</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/30, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class MissionDetailMappingModel {

    /**
     * COMPANY_KEY
     */
    private Integer companyKey;

    /**
     * 完成任務日期
     */
    private Date completeTime;

    /**
     * 任務代碼
     */
    private String missionCode;

    /**
     * 任務所屬關卡
     */
    private String missionLevel;

    /**
     * 任務名稱
     */
    private String missionName;

    /**
     * USER_KEY
     */
    private Integer userKey;

    /**
     * 任務標籤
     */
    private String missionTag;

    /**
     * 任務說明
     */
    private String missionDesc;

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
     * @return the completeTime
     */
    public Date getCompleteTime() {
        return completeTime;
    }

    /**
     * @param completeTime
     *            the completeTime to set
     */
    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    /**
     * @return the missionCode
     */
    public String getMissionCode() {
        return missionCode;
    }

    /**
     * @param missionCode
     *            the missionCode to set
     */
    public void setMissionCode(String missionCode) {
        this.missionCode = missionCode;
    }

    /**
     * @return the missionLevel
     */
    public String getMissionLevel() {
        return missionLevel;
    }

    /**
     * @param missionLevel
     *            the missionLevel to set
     */
    public void setMissionLevel(String missionLevel) {
        this.missionLevel = missionLevel;
    }

    /**
     * @return the missionName
     */
    public String getMissionName() {
        return missionName;
    }

    /**
     * @param missionName
     *            the missionName to set
     */
    public void setMissionName(String missionName) {
        this.missionName = missionName;
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
     * @return the missionTag
     */
    public String getMissionTag() {
        return missionTag;
    }

    /**
     * @param missionTag
     *            the missionTag to set
     */
    public void setMissionTag(String missionTag) {
        this.missionTag = missionTag;
    }

    /**
     * @return the missionDesc
     */
    public String getMissionDesc() {
        return missionDesc;
    }

    /**
     * @param missionDesc
     *            the missionDesc to set
     */
    public void setMissionDesc(String missionDesc) {
        this.missionDesc = missionDesc;
    }

}
