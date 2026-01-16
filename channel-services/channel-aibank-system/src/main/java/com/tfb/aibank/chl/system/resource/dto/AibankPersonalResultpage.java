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
package com.tfb.aibank.chl.system.resource.dto;

import java.util.Date;

// @formatter:off
/**
 * @(#)AibankPersonalResultpage.java
 * 
 * <p>Description:AI Bank 個人化頁面紀錄表</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/26, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AibankPersonalResultpage {

    /**
     * 交易存取記錄追蹤編號
     */
    private String traceId;

    /**
     * TRACKING_ID
     */
    private String trackingId;

    /**
     * 版位代號
     */
    private String actionPointId;

    /**
     * 公司鍵值
     */
    private Integer companyKey;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 情境編號
     */
    private String offerId;

    /**
     * 頁面代號
     */
    private String pageId;

    /**
     * 畫面資料
     */
    private String resData;

    /**
     * 版型代號
     */
    private String templateId;

    /**
     * 使用者鍵值
     */
    private Integer userKey;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getActionPointId() {
        return actionPointId;
    }

    public void setActionPointId(String actionPointId) {
        this.actionPointId = actionPointId;
    }

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getResData() {
        return resData;
    }

    public void setResData(String resData) {
        this.resData = resData;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

}
