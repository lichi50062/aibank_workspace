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
package com.tfb.aibank.chl.system.ot004.model;

// @formatter:off
/**
 * @(#)NSTOT004Input.java
 * 
 * <p>Description:NSTOT004 專用輸入物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/26, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NSTOT004Input {

    /** 類型，resultPage:結果頁 personalization:個人化版位 */
    private String type;

    /** 由前端傳入的「交易存取記錄追蹤編號」 */
    private String traceId;

    /** 結果頁HTML */
    private String resData;

    /** 版型代號 */
    private String templateId;

    /** 情境編號 */
    private String offerId;

    /** 版位代號 */
    private String actionPointId;

    /** 交易追蹤Id */
    private String trackingId;

    /** 頁面代號，從哪一頁面進入 */
    private String pageId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getResData() {
        return resData;
    }

    public void setResData(String resData) {
        this.resData = resData;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getActionPointId() {
        return actionPointId;
    }

    public void setActionPointId(String actionPointId) {
        this.actionPointId = actionPointId;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

}
