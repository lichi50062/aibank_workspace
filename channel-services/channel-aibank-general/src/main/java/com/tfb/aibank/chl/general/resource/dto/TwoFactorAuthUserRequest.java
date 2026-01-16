package com.tfb.aibank.chl.general.resource.dto;

/**
 * // @formatter:off
/**
 * @(#)TwoFactorAuthUserRequest.java
 * 
 * <p>Description:[設定雙重驗證]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/13, bensonlin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
public class TwoFactorAuthUserRequest {
    
    /**
     * 
     */
    private Integer companyKey;
    /**
     * 
     */
    private Integer userKey;
    
    /**
     * 驗證序號
     */
    private Long seq;
    /**
     * 推播Key
     */
    private Integer personNotificationRecordKey;
    /**
     * 新增:1, 查詢:2, 
     */
    private Integer action;
    
    /**
        WAIT：待驗證
        CANCEL：取消驗證
        SUCCESS：已驗證可登入
        FAIL：已驗證不可登入
        TIMEOUT
     */
    private String status;
    
    /**
     * DEVICE：裝置驗證
        OTP：OTP驗證
     */
    private String authType;
    
    /**登入IP*/
    private String ip;
    
    /**
     * device UUID
     */
    private String deviceId;
    /**
     * 地點
     */
    private String location;

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
    public Long getSeq() {
        return seq;
    }
    public void setSeq(Long seq) {
        this.seq = seq;
    }
    public Integer getAction() {
        return action;
    }
    public void setAction(Integer action) {
        this.action = action;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getAuthType() {
        return authType;
    }
    public void setAuthType(String authType) {
        this.authType = authType;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public Integer getPersonNotificationRecordKey() {
        return personNotificationRecordKey;
    }
    public void setPersonNotificationRecordKey(Integer personNotificationRecordKey) {
        this.personNotificationRecordKey = personNotificationRecordKey;
    }
    @Override
    public String toString() {
        return "TwoFactorAuthUserRequest [companyKey=" + companyKey + ", userKey=" + userKey + ", seq=" + seq + ", personNotificationRecordKey=" + personNotificationRecordKey + ", action=" + action + ", status=" + status + ", authType=" + authType + ", ip=" + ip + ", deviceId=" + deviceId + ", location=" + location + "]";
    }

    
}
