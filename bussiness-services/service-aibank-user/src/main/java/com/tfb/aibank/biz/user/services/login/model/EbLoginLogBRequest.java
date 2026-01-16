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
package com.tfb.aibank.biz.user.services.login.model;

import com.ibm.tw.commons.exception.ActionException;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EbLoginLogBRequest.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/28, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EbLoginLogBRequest {

    /** 身份證字號 */
    @Schema(description = "身份證字號")
    private String uid;

    /** 使用者代號 */
    @Schema(description = "使用者代號")
    private String uuid;

    /** 客戶端IP */
    @Schema(description = "客戶端IP")
    private String clientIP;

    /** 錯誤狀態 */
    @Schema(description = " 錯誤狀態")
    private ActionException error;

    /**
     * User Agent
     */
    @Schema(description = "User Agent")
    private String userAgent;

    /** 螢幕寬 */
    @Schema(description = "螢幕寬")
    private int screenWidth;

    /** 螢幕高 */
    @Schema(description = "螢幕高")
    private int screenHeight;

    /**
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     *            the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     *            the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the userAgent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * @param userAgent
     *            the userAgent to set
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * @return the screenWidth
     */
    public int getScreenWidth() {
        return screenWidth;
    }

    /**
     * @param screenWidth
     *            the screenWidth to set
     */
    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    /**
     * @return the screenHeight
     */
    public int getScreenHeight() {
        return screenHeight;
    }

    /**
     * @param screenHeight
     *            the screenHeight to set
     */
    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    /**
     * @return the clientIP
     */
    public String getClientIP() {
        return clientIP;
    }

    /**
     * @param clientIP
     *            the clientIP to set
     */
    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    /**
     * @return the error
     */
    public ActionException getError() {
        return error;
    }

    /**
     * @param error
     *            the error to set
     */
    public void setError(ActionException error) {
        this.error = error;
    }

}
