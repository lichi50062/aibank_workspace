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

import java.util.List;

// @formatter:off
/**
 * @(#)ConfirmBindDeviceRequest.java
 * 
 * <p>Description:確認建立MOTP設備綁定 - Request</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/10, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ConfirmBindDeviceRequest {

    /** 身分證字號 */
    private String custId;

    /** 使用者代號 */
    private String userId;

    /** 公司類型 */
    private int companyKind;

    /** 誤別碼 */
    private String uidDup;

    /** 裝置ID */
    private String deviceIxd;

    /** SDK addProfile response deviceID */
    private String deviceId;

    /** SDK addProfile response ClientID */
    private String clientId;

    /** SDK addProfile response SN */
    private String sn;

    /** SDK addProfile response pushID */
    private String pushId;

    /** 裝置代碼 */
    private String model;

    /** 裝置作業系統 */
    private String devicePlatform;

    /** 裝置作業系統版本 */
    private String devicePlatformVersion;

    /** ClientIP */
    private String clientIp;

    /** SessionID */
    private String sessionId;

    /** MOTP MID認證資料鍵值 */
    private List<Integer> motpMidDataKeyList;

    /** MOTP綁定時約定條款版本 */
    private String motpTermsVer;

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
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId
     *            the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return the clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId
     *            the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the sn
     */
    public String getSn() {
        return sn;
    }

    /**
     * @param sn
     *            the sn to set
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * @return the pushId
     */
    public String getPushId() {
        return pushId;
    }

    /**
     * @param pushId
     *            the pushId to set
     */
    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model
     *            the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the devicePlatform
     */
    public String getDevicePlatform() {
        return devicePlatform;
    }

    /**
     * @param devicePlatform
     *            the devicePlatform to set
     */
    public void setDevicePlatform(String devicePlatform) {
        this.devicePlatform = devicePlatform;
    }

    /**
     * @return the devicePlatformVersion
     */
    public String getDevicePlatformVersion() {
        return devicePlatformVersion;
    }

    /**
     * @param devicePlatformVersion
     *            the devicePlatformVersion to set
     */
    public void setDevicePlatformVersion(String devicePlatformVersion) {
        this.devicePlatformVersion = devicePlatformVersion;
    }

    /**
     * @return the clientIp
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * @param clientIp
     *            the clientIp to set
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId
     *            the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @return the motpMidDataKeyList
     */
    public List<Integer> getMotpMidDataKeyList() {
        return motpMidDataKeyList;
    }

    /**
     * @param motpMidDataKeyList
     *            the motpMidDataKeyList to set
     */
    public void setMotpMidDataKeyList(List<Integer> motpMidDataKeyList) {
        this.motpMidDataKeyList = motpMidDataKeyList;
    }

    /**
     * @return {@link #motpTermsVer}
     */
    public String getMotpTermsVer() {
        return motpTermsVer;
    }

    /**
     * @param motpTermsVer
     *            {@link #motpTermsVer}
     */
    public void setMotpTermsVer(String motpTermsVer) {
        this.motpTermsVer = motpTermsVer;
    }

}
