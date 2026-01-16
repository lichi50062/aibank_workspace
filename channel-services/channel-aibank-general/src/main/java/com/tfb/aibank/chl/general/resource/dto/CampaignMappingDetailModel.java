/*
 * =========================================================================== IBM Confidential AIS Source Materials
 *
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.general.resource.dto;

// @formatter:off
/**
 * @(#)CampaignMappingDetailModel.java
 * 
 * <p>Description:活動參數對照明細檔
 *  COPY FROM CHL_POINT_SERVICE
 * </p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/05, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CampaignMappingDetailModel {

    /**
     * 活動主檔ID
     */
    private String campaignId;

    /**
     * 任務所屬關卡(1、2、3)
     */
    private String missionLevel;

    /**
     * 圖檔路徑
     */
    private String photoUrl;

    /**
     * 利率專案代號
     */
    private String projectCode;

    /**
     * 利率
     */
    private String rate;

    /**
     * 取得活動主檔ID
     * 
     * @return String 活動主檔ID
     */
    public String getCampaignId() {
        return this.campaignId;
    }

    /**
     * 設定活動主檔ID
     * 
     * @param campaignId
     *            要設定的活動主檔ID
     */
    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    /**
     * 取得任務所屬關卡(1、2、3)
     * 
     * @return String 任務所屬關卡(1、2、3)
     */
    public String getMissionLevel() {
        return this.missionLevel;
    }

    /**
     * 設定任務所屬關卡(1、2、3)
     * 
     * @param missionLevel
     *            要設定的任務所屬關卡(1、2、3)
     */
    public void setMissionLevel(String missionLevel) {
        this.missionLevel = missionLevel;
    }

    /**
     * 取得圖檔路徑
     * 
     * @return String 圖檔路徑
     */
    public String getPhotoUrl() {
        return this.photoUrl;
    }

    /**
     * 設定圖檔路徑
     * 
     * @param photoUrl
     *            要設定的圖檔路徑
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * 取得利率專案代號
     * 
     * @return String 利率專案代號
     */
    public String getProjectCode() {
        return this.projectCode;
    }

    /**
     * 設定利率專案代號
     * 
     * @param projectCode
     *            要設定的利率專案代號
     */
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    /**
     * 取得利率
     * 
     * @return String 利率
     */
    public String getRate() {
        return this.rate;
    }

    /**
     * 設定利率
     * 
     * @param rate
     *            要設定的利率
     */
    public void setRate(String rate) {
        this.rate = rate;
    }
}
