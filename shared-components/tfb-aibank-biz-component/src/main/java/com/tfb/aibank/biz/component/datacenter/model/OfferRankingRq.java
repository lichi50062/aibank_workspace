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

//@formatter:off
/**
* @(#)OfferRankingRq.java
* 
* <p>Description:數據中心基本欄位 - Rq</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/08, Alex PY Li 
* <ol>
*  <li>初版</li>
* </ol>
* 
{
  "actionPointId": "NCCQU001_010_001",
  "id": "AES256加密後字串",
  "anonymId": "da0631f2-0380-11ee-be56-0242ac120002",
  "anonymIdType": "01",
  "deviceId": "",
  "offerNum": 3,
  "flexibleParams":{

  },
  "apiCallId": "9280d506-0369-11ee-be56-0242ac120002",
  "timestamp": 1685515741900,
}

*/
//@formatter:on
public class OfferRankingRq implements DataCenterBaseRq {

    /*
     * (non-Javadoc)
     *
     * @see com.tfb.aibank.biz.component.datacenter.model.DataCenterBaseRq#getPath()
     */
    @Override
    public String getPath() {
        return "/offerRanking/" + this.actionPointId;
    }

    /**
     * 與客戶的行銷接觸點，每一個頁面的每一個版型都有獨立且唯一的識別碼(格式：交易功能ID_頁面ID_流水號) NCCQU001_010_001
     */
    private String actionPointId;

    private String templateId;

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

    /** 前端期待API回傳的Offer數量，現行最大上限值設定為10則。 3 */
    private Integer offerNum;

    /**
     * 因應未來可能需求保留之彈性欄位，存放可能影響情境排序的環境 Key Value，例如：previousPageId 此為預留欄位 無資料時將帶空物件
     */
    private FlexibleParams flexibleParams;

    /** 每一次API CALL的ID 9280d506-0369-11ee-be56-0242ac120002 */
    private String apiCallId;

    /** 請求時間，採unix time(epoch)時間(千分之一秒) 1685515741900 */
    private Long timestamp;

    /**
     * @return the actionPointId
     */
    public String getActionPointId() {
        return actionPointId;
    }

    /**
     * @param actionPointId
     *         the actionPointId to set
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
     *         the id to set
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
     *         the anonymId to set
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
     *         the anonymIdType to set
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
     *         the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return the offerNum
     */
    public Integer getOfferNum() {
        return offerNum;
    }

    /**
     * @param offerNum
     *         the offerNum to set
     */
    public void setOfferNum(Integer offerNum) {
        this.offerNum = offerNum;
    }

    /**
     * @return the apiCallId
     */
    public String getApiCallId() {
        return apiCallId;
    }

    /**
     * @param apiCallId
     *         the apiCallId to set
     */
    public void setApiCallId(String apiCallId) {
        this.apiCallId = apiCallId;
    }

    /**
     * @return the timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp
     *         the timestamp to set
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public FlexibleParams getFlexibleParams() {
        return flexibleParams;
    }

    public void setFlexibleParams(FlexibleParams flexibleParams) {
        this.flexibleParams = flexibleParams;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}
