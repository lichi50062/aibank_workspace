package com.tfb.aibank.chl.creditcard.resource.dto;

// @formatter:off
/**
 * @(#)FidoPushNotifyRequest.java
 * 
 * <p>Description:</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/5/16, billchang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class Fido2PushNotifyRequest {

    private String custId;
    private String uidDup;
    private String userId;
    private Integer companyKind;

    private String deviceId;

    private String pushCode;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getUidDup() {
        return uidDup;
    }

    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCompanyKind() {
        return companyKind;
    }

    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPushCode() {
        return pushCode;
    }

    public void setPushCode(String pushCode) {
        this.pushCode = pushCode;
    }
}
