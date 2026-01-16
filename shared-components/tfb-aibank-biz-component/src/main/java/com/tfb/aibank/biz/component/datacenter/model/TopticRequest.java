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
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import io.swagger.v3.oas.annotations.media.Schema; /**
* @(#)OfferActionRequest.java
* 
* <p>Description: - Requesst</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/04/30, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Schema(description = "B2E 個人化主題 toptics")
public class TopticRequest implements Serializable {
    @Schema(description = "版位代號")
    @SerializedName("ACTION_POINT_ID")
    private String actionPointId;
    @Schema(description = "情境編號")
    @SerializedName("OFFER_ID")
    private String offerId;
    @Schema(description = "情境類別")
    @SerializedName("OFFER_TYPE")
    private String offerType;
    @Schema(description = "情境說明")
    @SerializedName("OFFER_DESC")
    private String offerDesc;
    @Schema(description = "情境分數")
    @SerializedName("OFFER_SCORE")
    private Integer offerScore;
    @Schema(description = "觸發有效天數")
    @SerializedName("EXPIRE_AFTER")
    private Integer expireAfter;
    @Schema(description = "版型代號")
    @SerializedName("TEMPLATE_ID")
    private String templateId;
    @Schema(description = "情境文案")
    @SerializedName("OFFER_INFOS")
    private List<HashMap<String, Object>> offerInfos;
    @Schema(description = "一般情境/Backfill")
    @SerializedName("IS_BACKFILL")
    private String isBackfill;
    @Schema(description = "產品大類")
    @SerializedName("PRODUCT_TYPE_1")
    private String productType1;
    @Schema(description = "產品中類")
    @SerializedName("PRODUCT_TYPE_2")
    private String productType2;
    @Schema(description = "產品子類")
    @SerializedName("PRODUCT_TYPE_3")
    private String productType3;
    @Schema(description = "行動目標")
    @SerializedName("CALL_TO_ACTION")
    private String callToAction;
    @Schema(description = "特殊節日")
    @SerializedName("HOLIDAY")
    private String holiday;
    @Schema(description = "生效起始日期")
    @SerializedName("START_DATE")
    private String startDate;
    @Schema(description = "生效結束日期")
    @SerializedName("END_DATE")
    private String endDate;
    @Schema(description = "是否為必須發送")
    @SerializedName("MUST_SEND")
    private String mustSend;

    public String getActionPointId() {
        return actionPointId;
    }

    public void setActionPointId(String actionPointId) {
        this.actionPointId = actionPointId;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public String getOfferDesc() {
        return offerDesc;
    }

    public void setOfferDesc(String offerDesc) {
        this.offerDesc = offerDesc;
    }

    public Integer getOfferScore() {
        return offerScore;
    }

    public void setOfferScore(Integer offerScore) {
        this.offerScore = offerScore;
    }

    /**
     * @return {@link #expireAfter}
     */
    public Integer getExpireAfter() {
        return expireAfter;
    }

    /**
     * @param expireAfter
     *            {@link #expireAfter}
     */
    public void setExpireAfter(Integer expireAfter) {
        this.expireAfter = expireAfter;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public List<HashMap<String, Object>> getOfferInfos() {
        return offerInfos;
    }

    public void setOfferInfos(List<HashMap<String, Object>> offerInfos) {
        this.offerInfos = offerInfos;
    }

    public String getIsBackfill() {
        return isBackfill;
    }

    public void setIsBackfill(String isBackfill) {
        this.isBackfill = isBackfill;
    }

    public String getProductType1() {
        return productType1;
    }

    public void setProductType1(String productType1) {
        this.productType1 = productType1;
    }

    public String getProductType2() {
        return productType2;
    }

    public void setProductType2(String productType2) {
        this.productType2 = productType2;
    }

    public String getProductType3() {
        return productType3;
    }

    public void setProductType3(String productType3) {
        this.productType3 = productType3;
    }

    public String getCallToAction() {
        return callToAction;
    }

    public void setCallToAction(String callToAction) {
        this.callToAction = callToAction;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getMustSend() {
        return mustSend;
    }

    public void setMustSend(String mustSend) {
        this.mustSend = mustSend;
    }
}
