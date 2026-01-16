package com.tfb.aibank.biz.user.services.user.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class GesturesPwdRequest {

    /** 裝置代號 */
    @Schema(description = "裝置代號")
    private String deviceId;

    /** 身分證字號 */
    @Schema(description = "身分證字號")
    private String custId;

    /** 使用者代號 */
    @Schema(description = "使用者代號")
    private String userId;

    /** 指紋/顏值 登入的 challenge */
    @Schema(description = "指紋/顏值 登入的 challenge")
    private String challenge;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }
}
