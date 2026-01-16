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

import java.math.BigDecimal;

// @formatter:off
/**
 * @(#)AiOtherInsurDataModel.java
 *
 * <p>Description:台北富邦銀行內的的非富壽=日盛保險資料(資產)</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/03/22, Lillian.Tsai
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AiOtherInsurDataModel {
    /** 排列序號 1.保險公司代碼2.保險始期(舊到新)3.保單號碼4.主附約 */
    private int seqNo;

    /** 系統資料日期 */
    private String snapDate;

    /** 資料年月 */
    private String snapYyyyMm;

    /** 保險公司 */
    private String comNmae;

    /** 保險種類 */
    private String lsForm;

    /** 險種名稱 */
    private String policyFullName;

    /** 保單號碼 */
    private String policyNbr;

    /** 要保人ID */
    private String custId;

    /** 要保人 */
    private String applName;

    /** 被保險人ID */
    private String insId;

    /** 被保險人 */
    private String insName;

    /** 參考日期(價值/解約金計算日期) */
    private String calValueDate;

    /** 保險始期(保單生效日) */
    private String policyActiveDate;

    /** 保單狀態 */
    private String contractText;

    /** 契約狀態代碼 僅01、02、04、05、07、16 */
    private String contractStatus;

    /** 幣別 */
    private String crcyType;

    /** 繳別 'A':年繳; 'M':月繳; 'Q':季繳; 'D':躉繳;'S':半年繳;'F':彈性繳 */
    private String payType;

    /** 累計保費(累計實繳保費) */
    private BigDecimal acumPaidPolicyFee;

    /** 預估現金價值(帳戶價值) */
    private BigDecimal accValue;

    /** 險種代碼 */
    private String insType;

    /** 主附約(Y=主約；Null=附約) */
    private String mastSlaType;

    /** 保險公司代碼/編號 */
    private String comId;

    /** 進件代碼 */
    private String unitNbr;

    /** 對帳單分行別 */
    private String lsBrh;

    /** 密戶否((Y=密戶; N=非密戶) */
    private String secretYn;

    /** 匯率 */
    private BigDecimal twdRate;

    /** 折台累計保費 */
    private BigDecimal twdPolicyFee;

    /** 折台預估現金價值 */
    private BigDecimal twdAccValue;

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public String getSnapDate() {
        return snapDate;
    }

    public void setSnapDate(String snapDate) {
        this.snapDate = snapDate;
    }

    public String getSnapYyyyMm() {
        return snapYyyyMm;
    }

    public void setSnapYyyyMm(String snapYyyyMm) {
        this.snapYyyyMm = snapYyyyMm;
    }

    public String getComNmae() {
        return comNmae;
    }

    public void setComNmae(String comNmae) {
        this.comNmae = comNmae;
    }

    public String getLsForm() {
        return lsForm;
    }

    public void setLsForm(String lsForm) {
        this.lsForm = lsForm;
    }

    public String getPolicyFullName() {
        return policyFullName;
    }

    public void setPolicyFullName(String policyFullName) {
        this.policyFullName = policyFullName;
    }

    public String getPolicyNbr() {
        return policyNbr;
    }

    public void setPolicyNbr(String policyNbr) {
        this.policyNbr = policyNbr;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getApplName() {
        return applName;
    }

    public void setApplName(String applName) {
        this.applName = applName;
    }

    public String getInsId() {
        return insId;
    }

    public void setInsId(String insId) {
        this.insId = insId;
    }

    public String getInsName() {
        return insName;
    }

    public void setInsName(String insName) {
        this.insName = insName;
    }

    public String getCalValueDate() {
        return calValueDate;
    }

    public void setCalValueDate(String calValueDate) {
        this.calValueDate = calValueDate;
    }

    public String getPolicyActiveDate() {
        return policyActiveDate;
    }

    public void setPolicyActiveDate(String policyActiveDate) {
        this.policyActiveDate = policyActiveDate;
    }

    public String getContractText() {
        return contractText;
    }

    public void setContractText(String contractText) {
        this.contractText = contractText;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getCrcyType() {
        return crcyType;
    }

    public void setCrcyType(String crcyType) {
        this.crcyType = crcyType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public BigDecimal getAcumPaidPolicyFee() {
        return acumPaidPolicyFee;
    }

    public void setAcumPaidPolicyFee(BigDecimal acumPaidPolicyFee) {
        this.acumPaidPolicyFee = acumPaidPolicyFee;
    }

    public BigDecimal getAccValue() {
        return accValue;
    }

    public void setAccValue(BigDecimal accValue) {
        this.accValue = accValue;
    }

    public String getInsType() {
        return insType;
    }

    public void setInsType(String insType) {
        this.insType = insType;
    }

    public String getMastSlaType() {
        return mastSlaType;
    }

    public void setMastSlaType(String mastSlaType) {
        this.mastSlaType = mastSlaType;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getUnitNbr() {
        return unitNbr;
    }

    public void setUnitNbr(String unitNbr) {
        this.unitNbr = unitNbr;
    }

    public String getLsBrh() {
        return lsBrh;
    }

    public void setLsBrh(String lsBrh) {
        this.lsBrh = lsBrh;
    }

    public String getSecretYn() {
        return secretYn;
    }

    public void setSecretYn(String secretYn) {
        this.secretYn = secretYn;
    }

    public BigDecimal getTwdRate() {
        return twdRate;
    }

    public void setTwdRate(BigDecimal twdRate) {
        this.twdRate = twdRate;
    }

    public BigDecimal getTwdPolicyFee() {
        return twdPolicyFee;
    }

    public void setTwdPolicyFee(BigDecimal twdPolicyFee) {
        this.twdPolicyFee = twdPolicyFee;
    }

    public BigDecimal getTwdAccValue() {
        return twdAccValue;
    }

    public void setTwdAccValue(BigDecimal twdAccValue) {
        this.twdAccValue = twdAccValue;
    }
}
