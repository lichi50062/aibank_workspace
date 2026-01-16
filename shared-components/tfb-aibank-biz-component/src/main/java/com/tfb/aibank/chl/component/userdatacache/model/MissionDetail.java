/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.userdatacache.model;

import java.io.Serializable;
import java.util.Date;

// @formatter:off
/**
 * @(#)MissionDetail.java
 * 
 * <p>Description:任務牆活動明細檔</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/09/09, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class MissionDetail implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 關連的主檔
     */
    private MissionMaster missionMaster;

    /**
     * 取得COMPANY_KEY
     * 
     * @return int COMPANY_KEY
     */
    public Integer getCompanyKey() {
        return this.companyKey;
    }

    /**
     * 設定COMPANY_KEY
     * 
     * @param companyKey
     *            要設定的COMPANY_KEY
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * 取得完成任務日期
     * 
     * @return Date 完成任務日期
     */
    public Date getCompleteTime() {
        return this.completeTime;
    }

    /**
     * 設定完成任務日期
     * 
     * @param completeTime
     *            要設定的完成任務日期
     */
    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    /**
     * 取得任務代碼
     * 
     * @return String 任務代碼
     */
    public String getMissionCode() {
        return this.missionCode;
    }

    /**
     * 設定任務代碼
     * 
     * @param missionCode
     *            要設定的任務代碼
     */
    public void setMissionCode(String missionCode) {
        this.missionCode = missionCode;
    }

    /**
     * 取得任務所屬關卡
     * 
     * @return String 任務所屬關卡
     */
    public String getMissionLevel() {
        return this.missionLevel;
    }

    /**
     * 設定任務所屬關卡
     * 
     * @param missionLevel
     *            要設定的任務所屬關卡
     */
    public void setMissionLevel(String missionLevel) {
        this.missionLevel = missionLevel;
    }

    /**
     * 取得任務名稱
     * 
     * @return String 任務名稱
     */
    public String getMissionName() {
        return this.missionName;
    }

    /**
     * 設定任務名稱
     * 
     * @param missionName
     *            要設定的任務名稱
     */
    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    /**
     * 取得USER_KEY
     * 
     * @return int USER_KEY
     */
    public Integer getUserKey() {
        return this.userKey;
    }

    /**
     * 設定USER_KEY
     * 
     * @param userKey
     *            要設定的USER_KEY
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public MissionMaster getMissionMaster() {
        return missionMaster;
    }

    public void setMissionMaster(MissionMaster missionMaster) {
        this.missionMaster = missionMaster;
    }

}
