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
package com.tfb.aibank.biz.component.datacenter.model;

// @formatter:off
/**
 * @(#)CaseInfo.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, alexlee	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CaseInfo {
    /**
     * 與客戶的行銷接觸點，每一個頁面的每一個版型都有獨立且唯一的識別碼(格式：交易功能ID_頁面ID_流水號) NCCQU001_010_001
     */
    private String actionPointId;

    /**
     * 若使用者有登入，回傳富邦全行各通路統一的唯一識別碼=使用者身份證字號(10碼)+誤別碼(1碼) (加密採AES256) A1234567891 無資料時將帶空字串
     */
    private String id;

    /** 使用者每次使用服務時的匿名識別碼 da0631f2-0380-11ee-be56-0242ac120002 */
    private String anonymId;

    /**
     * 匿名識別碼種類 01：mobileID 02：cookie
     */
    private String anonymIdType;

    /**
     * 裝置識別碼(重裝 APP 將為新的識別碼) 50ac0e69-338b-47bb-a472-1ec65f6eb58b 無資料時將帶空字串
     */
    private String deviceId;

    /**
     * @return the actionPointId
     */
    public String getActionPointId() {
        return actionPointId;
    }

    /**
     * @param actionPointId
     *            the actionPointId to set
     */
    public void setActionPointId(String actionPointId) {
        this.actionPointId = actionPointId;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the anonymId
     */
    public String getAnonymId() {
        return anonymId;
    }

    /**
     * @param anonymId
     *            the anonymId to set
     */
    public void setAnonymId(String anonymId) {
        this.anonymId = anonymId;
    }

    /**
     * @return the anonymIdType
     */
    public String getAnonymIdType() {
        return anonymIdType;
    }

    /**
     * @param anonymIdType
     *            the anonymIdType to set
     */
    public void setAnonymIdType(String anonymIdType) {
        this.anonymIdType = anonymIdType;
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

}
