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
package com.tfb.aibank.biz.user.services.nopain.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)OnboardingLogRequest.java
 * 
 * <p>Description:記錄無痛移轉資料庫檔 Request</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/07, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class OnboardingLogRequest {

    /** 身分證字號 */
    @Schema(description = "身分證字號")
    private String custId;

    /** 使用者代號 */
    @Schema(description = "使用者代號")
    private String userId;

    /** 公司類型 */
    @Schema(description = "公司類型")
    private int companyKind;

    /** 誤別碼 */
    @Schema(description = "誤別碼")
    private String uidDup;

    /** 裝置ID */
    @Schema(description = "裝置ID")
    private String deviceIxd;

    /** 無痛移轉用戶旅程 */
    @Schema(description = "無痛移轉用戶旅程")
    private int onboardingType;

    // 定訊息通知
    @Schema(description = "訊息通知")
    private int notification;

    // 免登速查
    @Schema(description = "免登速查")
    private int quickSearch;

    // 推播動態密碼MOTP
    @Schema(description = "推播動態密碼MOTP")
    private int motpSetting;

    // 無卡提款
    @Schema(description = "無卡提款")
    private int noCardwithDraw;

    // 手機號碼收款
    @Schema(description = "手機號碼收款")
    private int phoneTransfer;

    // 提高非約轉額度
    @Schema(description = " 提高非約轉額度")
    private int transferQuota;

    /** 失敗的設定項目 */
    @Schema(description = "失敗的設定項目")
    private List<String> failNotificationTypes;

    /** 成功的設定項目 */
    @Schema(description = "成功的設定項目")
    private List<String> succNotificationTypes;

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
     * @return the companyKind
     */
    public int getCompanyKind() {
        return companyKind;
    }

    /**
     * @param companyKind
     *            the companyKind to set
     */
    public void setCompanyKind(int companyKind) {
        this.companyKind = companyKind;
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
     * @return the deviceIxd
     */
    public String getDeviceIxd() {
        return deviceIxd;
    }

    /**
     * @param deviceIxd
     *            the deviceIxd to set
     */
    public void setDeviceIxd(String deviceIxd) {
        this.deviceIxd = deviceIxd;
    }

    /**
     * @return the notification
     */
    public int getNotification() {
        return notification;
    }

    /**
     * @param notification
     *            the notification to set
     */
    public void setNotification(int notification) {
        this.notification = notification;
    }

    /**
     * @return the quickSearch
     */
    public int getQuickSearch() {
        return quickSearch;
    }

    /**
     * @param quickSearch
     *            the quickSearch to set
     */
    public void setQuickSearch(int quickSearch) {
        this.quickSearch = quickSearch;
    }

    /**
     * @return the motpSetting
     */
    public int getMotpSetting() {
        return motpSetting;
    }

    /**
     * @param motpSetting
     *            the motpSetting to set
     */
    public void setMotpSetting(int motpSetting) {
        this.motpSetting = motpSetting;
    }

    /**
     * @return the noCardwithDraw
     */
    public int getNoCardwithDraw() {
        return noCardwithDraw;
    }

    /**
     * @param noCardwithDraw
     *            the noCardwithDraw to set
     */
    public void setNoCardwithDraw(int noCardwithDraw) {
        this.noCardwithDraw = noCardwithDraw;
    }

    /**
     * @return the phoneTransfer
     */
    public int getPhoneTransfer() {
        return phoneTransfer;
    }

    /**
     * @param phoneTransfer
     *            the phoneTransfer to set
     */
    public void setPhoneTransfer(int phoneTransfer) {
        this.phoneTransfer = phoneTransfer;
    }

    /**
     * @return the transferQuota
     */
    public int getTransferQuota() {
        return transferQuota;
    }

    /**
     * @param transferQuota
     *            the transferQuota to set
     */
    public void setTransferQuota(int transferQuota) {
        this.transferQuota = transferQuota;
    }

    /**
     * @return the failNotificationTypes
     */
    public List<String> getFailNotificationTypes() {
        return failNotificationTypes;
    }

    /**
     * @param failNotificationTypes
     *            the failNotificationTypes to set
     */
    public void setFailNotificationTypes(List<String> failNotificationTypes) {
        this.failNotificationTypes = failNotificationTypes;
    }

    /**
     * @return the succNotificationTypes
     */
    public List<String> getSuccNotificationTypes() {
        return succNotificationTypes;
    }

    /**
     * @param succNotificationTypes
     *            the succNotificationTypes to set
     */
    public void setSuccNotificationTypes(List<String> succNotificationTypes) {
        this.succNotificationTypes = succNotificationTypes;
    }

    /**
     * @return the onboardingType
     */
    public int getOnboardingType() {
        return onboardingType;
    }

    /**
     * @param onboardingType
     *            the onboardingType to set
     */
    public void setOnboardingType(int onboardingType) {
        this.onboardingType = onboardingType;
    }

}
