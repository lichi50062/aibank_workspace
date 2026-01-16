/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.system.resource.dto;

//@formatter:off
/**
* @(#)UserCardViewLogRequest.java
* 
* <p>Description: 用戶觀看廣告紀錄 request</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/05/14, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class UserCardViewLogRequest {

    /**
     * 用戶ID
     */
    private String custId;

    /**
     * 誤別碼
     */
    private String uidDup;

    /**
     * 使用者代號
     */
    private String userId;

    /**
     * 公司別
     */
    private Integer companyKind;

    /**
     * deviceUuid
     */
    private String deviceUuid;

    /**
     * 廣告牌卡鍵值
     */
    private Integer cardKey;

    /**
     * 牌卡應用交易
     */
    private String cardUsedTaskId;

    /**
     * 廣告牌卡版本
     */
    private Integer cardVersion;

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

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public Integer getCardKey() {
        return cardKey;
    }

    public void setCardKey(Integer cardKey) {
        this.cardKey = cardKey;
    }

    public String getCardUsedTaskId() {
        return cardUsedTaskId;
    }

    public void setCardUsedTaskId(String cardUsedTaskId) {
        this.cardUsedTaskId = cardUsedTaskId;
    }

    public Integer getCardVersion() {
        return cardVersion;
    }

    public void setCardVersion(Integer cardVersion) {
        this.cardVersion = cardVersion;
    }
}
