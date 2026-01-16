package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerCardApply {

    /**
     * 免附財可調額度(單位:元)
     */
    private BigDecimal adjustAmt;

    /**
     * AML風險等級（L/M/H）
     */
    private String amlRiskGrade;

    /**
     * [EN]年收入
     */
    private String annualIncome;

    /**
     * 存貸戶分群（Y：預篩名單、N：非預篩名）
     */
    private String bankCustFlag;

    /**
     * 信用卡分群（Y：白名單、N：婉拒名單、A：AML人工審差名單）
     */
    private String cardHolderFlag;

    /**
     * 新增日期
     */
    private Date createDate;

    /**
     * 信用評等
     */
    private String creditCardBs;

    /**
     * 本行目前額度(單位:元)(PER)(FAN)
     */
    private BigDecimal currentAmt;

    /**
     * [EN]ID
     */
    private String custId;

    /**
     * 收入代碼
     */
    private String incomeFlag;

    /**
     * PLDC婉拒名單（Y：婉拒名單、N：非婉拒名單）
     */
    private String pldcRejectFlag;

    /**
     * 資料日
     */
    private Date snapDate;

    /**
     * 專案目標額度(單位:元)
     */
    private BigDecimal targetAmt;

    /**
     * 目標額度所需年收(單位:元)
     */
    private BigDecimal targetIncome;

    public BigDecimal getAdjustAmt() {
        return adjustAmt;
    }

    public void setAdjustAmt(BigDecimal adjustAmt) {
        this.adjustAmt = adjustAmt;
    }

    public String getAmlRiskGrade() {
        return amlRiskGrade;
    }

    public void setAmlRiskGrade(String amlRiskGrade) {
        this.amlRiskGrade = amlRiskGrade;
    }

    public String getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(String annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getBankCustFlag() {
        return bankCustFlag;
    }

    public void setBankCustFlag(String bankCustFlag) {
        this.bankCustFlag = bankCustFlag;
    }

    public String getCardHolderFlag() {
        return cardHolderFlag;
    }

    public void setCardHolderFlag(String cardHolderFlag) {
        this.cardHolderFlag = cardHolderFlag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreditCardBs() {
        return creditCardBs;
    }

    public void setCreditCardBs(String creditCardBs) {
        this.creditCardBs = creditCardBs;
    }

    public BigDecimal getCurrentAmt() {
        return currentAmt;
    }

    public void setCurrentAmt(BigDecimal currentAmt) {
        this.currentAmt = currentAmt;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getIncomeFlag() {
        return incomeFlag;
    }

    public void setIncomeFlag(String incomeFlag) {
        this.incomeFlag = incomeFlag;
    }

    public String getPldcRejectFlag() {
        return pldcRejectFlag;
    }

    public void setPldcRejectFlag(String pldcRejectFlag) {
        this.pldcRejectFlag = pldcRejectFlag;
    }

    public Date getSnapDate() {
        return snapDate;
    }

    public void setSnapDate(Date snapDate) {
        this.snapDate = snapDate;
    }

    public BigDecimal getTargetAmt() {
        return targetAmt;
    }

    public void setTargetAmt(BigDecimal targetAmt) {
        this.targetAmt = targetAmt;
    }

    public BigDecimal getTargetIncome() {
        return targetIncome;
    }

    public void setTargetIncome(BigDecimal targetIncome) {
        this.targetIncome = targetIncome;
    }

}
