package com.tfb.aibank.chl.general.resource.dto;

import java.util.Date;

// @formatter:off
/**
 * @(#)MissionMasterModel.java
 * 
 * <p>Description:任務牆活動主檔</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/30, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class MissionMasterModel {
    /**
     * 數存帳號
     */
    private String account;

    /**
     * COMPANY_KEY
     */
    private Integer companyKey;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 任務迄日
     */
    private Date endTime;

    /**
     * 破關時間
     */
    private Date finishTime;

    /**
     * 身分類型，N：新戶，O：舊戶
     */
    private String idType;

    /**
     * 是否已開始任務挑戰，0：尚未開始，1：已開始
     */
    private String isStart;

    /**
     * 關卡一任務完成時間
     */
    private Date level1CompleteTime;

    /**
     * 關卡二任務完成時間
     */
    private Date level2CompleteTime;

    /**
     * 關卡三任務完成時間
     */
    private Date level3CompleteTime;

    /**
     * 已完成的關卡等級，0、1、2、3
     */
    private String missionLevel;

    /**
     * 已適用的活存利率(%)
     */
    private String missionValue;

    /**
     * 挑戰任務開始時間
     */
    private Date startTime;

    /**
     * 更新時間
     */
    private Date updateTime;

    /**
     * USER_KEY
     */
    private Integer userKey;

    /**
     * 活動ID
     */
    private String campaignId;

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     *            the account to set
     */
    public void setAccount(String account) {
        this.account = account;
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
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the finishTime
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * @param finishTime
     *            the finishTime to set
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * @return the idType
     */
    public String getIdType() {
        return idType;
    }

    /**
     * @param idType
     *            the idType to set
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }

    /**
     * @return the isStart
     */
    public String getIsStart() {
        return isStart;
    }

    /**
     * @param isStart
     *            the isStart to set
     */
    public void setIsStart(String isStart) {
        this.isStart = isStart;
    }

    /**
     * @return the level1CompleteTime
     */
    public Date getLevel1CompleteTime() {
        return level1CompleteTime;
    }

    /**
     * @param level1CompleteTime
     *            the level1CompleteTime to set
     */
    public void setLevel1CompleteTime(Date level1CompleteTime) {
        this.level1CompleteTime = level1CompleteTime;
    }

    /**
     * @return the level2CompleteTime
     */
    public Date getLevel2CompleteTime() {
        return level2CompleteTime;
    }

    /**
     * @param level2CompleteTime
     *            the level2CompleteTime to set
     */
    public void setLevel2CompleteTime(Date level2CompleteTime) {
        this.level2CompleteTime = level2CompleteTime;
    }

    /**
     * @return the level3CompleteTime
     */
    public Date getLevel3CompleteTime() {
        return level3CompleteTime;
    }

    /**
     * @param level3CompleteTime
     *            the level3CompleteTime to set
     */
    public void setLevel3CompleteTime(Date level3CompleteTime) {
        this.level3CompleteTime = level3CompleteTime;
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
     * @return the missionValue
     */
    public String getMissionValue() {
        return missionValue;
    }

    /**
     * @param missionValue
     *            the missionValue to set
     */
    public void setMissionValue(String missionValue) {
        this.missionValue = missionValue;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     *            the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
     * @return the campaignId
     */
    public String getCampaignId() {
        return campaignId;
    }

    /**
     * @param campaignId
     *            the campaignId to set
     */
    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

}
