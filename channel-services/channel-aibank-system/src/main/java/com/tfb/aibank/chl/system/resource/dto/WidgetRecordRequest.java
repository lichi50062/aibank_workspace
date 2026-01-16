package com.tfb.aibank.chl.system.resource.dto;

/**
 * Widget record
 * @author horance
 *
 */
public class WidgetRecordRequest {

    /**
     * 裝置ID
     */
    private String deviceCode;
    /**
     * company key
     */
    private Integer companyKey;
    /**
     * user key
     */
    private Integer userKey;
    /**
     * ios/android
     */
    private String platform;
    /**
     * 小工具類別
     */
    private String type;
    /**
     * 交易代號
     */
    private String taskId;

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

}
