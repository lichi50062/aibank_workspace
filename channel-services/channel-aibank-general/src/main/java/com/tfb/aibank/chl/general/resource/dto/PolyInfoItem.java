/*
 * ===========================================================================
 *
 * IBM Confidential
 *
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.general.resource.dto;

// @formatter:off
import java.io.Serializable;
import java.util.List;
/**
 * @(#)PolyInfoItem.java
 * 
 * <p>Description:檢核結果</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/06, Jackie Chien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class PolyInfoItem implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 訊息代碼 */
    private String statusCode;

    /** 網銀-新契約編號 */
    private String oiOrder;

    /** 保單號碼 */
    private String polyNo;

    /** 報價單號 */
    private String quoNo;

    /** 任意險保單號碼 */
    private String voltPolyNo;

    /** 險種類型 */
    private String prodType;

    /** 保單狀態 */
    private String status;

    /** 投保日期 */
    private String applyDate;

    /** 投保期間(起) */
    private String policyEffectiveDate;

    /** 投保期間(迄) */
    private String policyExpiryDate;

    /** 投保天數/強制險投保期間 */
    private int policyPeriod;

    /** 目的地 */
    private String destination;

    /** 任意險開始日期 */
    private String voluntaryEffectiveDate;

    /** 任意險截止日期 */
    private String voluntaryExpiryDate;

    /** 任意險投保期間 */
    private int voltPolicyPeriod;

    /** 牌照號碼 */
    private String tagId;

    /** 要保人身分證字號 */
    private String ownerId;

    /** 要保人姓名 */
    private String ownerName;

    /** 被保險人身分證字號 */
    private String insuredId;

    /** 被保人姓名 */
    private String insuredName;

    /** 受益人與被保人關係 */
    private String benRelationship;

    /** 受益人姓名 */
    private String benName;

    /** 繳款方式 */
    private String payment;

    /** 使用紅利點數折抵 */
    private String useCardPoint;

    /** 投保方案 */
    private List<PolicyCaseItem> policyCase;

    /** 是否有強制險快到期 */
    private Boolean isInsuranceExpire;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getOiOrder() {
        return oiOrder;
    }

    public void setOiOrder(String oiOrder) {
        this.oiOrder = oiOrder;
    }

    public String getPolyNo() {
        return polyNo;
    }

    public void setPolyNo(String polyNo) {
        this.polyNo = polyNo;
    }

    public String getQuoNo() {
        return quoNo;
    }

    public void setQuoNo(String quoNo) {
        this.quoNo = quoNo;
    }

    public String getVoltPolyNo() {
        return voltPolyNo;
    }

    public void setVoltPolyNo(String voltPolyNo) {
        this.voltPolyNo = voltPolyNo;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getPolicyEffectiveDate() {
        return policyEffectiveDate;
    }

    public void setPolicyEffectiveDate(String policyEffectiveDate) {
        this.policyEffectiveDate = policyEffectiveDate;
    }

    public String getPolicyExpiryDate() {
        return policyExpiryDate;
    }

    public void setPolicyExpiryDate(String policyExpiryDate) {
        this.policyExpiryDate = policyExpiryDate;
    }

    public int getPolicyPeriod() {
        return policyPeriod;
    }

    public void setPolicyPeriod(int policyPeriod) {
        this.policyPeriod = policyPeriod;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getVoluntaryEffectiveDate() {
        return voluntaryEffectiveDate;
    }

    public void setVoluntaryEffectiveDate(String voluntaryEffectiveDate) {
        this.voluntaryEffectiveDate = voluntaryEffectiveDate;
    }

    public String getVoluntaryExpiryDate() {
        return voluntaryExpiryDate;
    }

    public void setVoluntaryExpiryDate(String voluntaryExpiryDate) {
        this.voluntaryExpiryDate = voluntaryExpiryDate;
    }

    public int getVoltPolicyPeriod() {
        return voltPolicyPeriod;
    }

    public void setVoltPolicyPeriod(int voltPolicyPeriod) {
        this.voltPolicyPeriod = voltPolicyPeriod;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getInsuredId() {
        return insuredId;
    }

    public void setInsuredId(String insuredId) {
        this.insuredId = insuredId;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getBenRelationship() {
        return benRelationship;
    }

    public void setBenRelationship(String benRelationship) {
        this.benRelationship = benRelationship;
    }

    public String getBenName() {
        return benName;
    }

    public void setBenName(String benName) {
        this.benName = benName;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getUseCardPoint() {
        return useCardPoint;
    }

    public void setUseCardPoint(String useCardPoint) {
        this.useCardPoint = useCardPoint;
    }

    public List<PolicyCaseItem> getPolicyCase() {
        return policyCase;
    }

    public void setPolicyCase(List<PolicyCaseItem> policyCase) {
        this.policyCase = policyCase;
    }

    public Boolean getInsuranceExpire() {
        return isInsuranceExpire;
    }

    public void setInsuranceExpire(Boolean insuranceExpire) {
        isInsuranceExpire = insuranceExpire;
    }
}
