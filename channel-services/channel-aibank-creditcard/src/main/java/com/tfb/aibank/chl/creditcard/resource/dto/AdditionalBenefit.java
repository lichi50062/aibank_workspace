package com.tfb.aibank.chl.creditcard.resource.dto;

import com.tfb.aibank.common.type.AdditionalBenefitType;

import java.io.Serializable;
import java.math.BigDecimal;

public class AdditionalBenefit implements Serializable {

    // VNTYPE  <!-- 查詢項目 -->
    private String queryType;
    // VNFLAG   <!-- 適用權益 -->
    private String applicable;
    // VNDAYB <!-- 優惠期間_起 -->
    private String benefitStartDate;
    // VNDAYE <!-- 優惠期間_迄 -->
    private String benefitEndDate;
    // VNTCNT <!-- 可使用次數 -->
    private BigDecimal totalUsableCount;
    // VNSCNT <!-- 剩餘次數 -->
    private BigDecimal remainingUsageCount;
    // VNTCN1 <!-- 私銀可使用次數 -->
    private BigDecimal pbTotalUsableCount;
    // VNSCN1 <!-- 私銀剩餘次數 -->
    private BigDecimal pbRemainingUsageCount;
    // VNPDYS  <!-- 機場停車_每次最高停車天數 -->
    private BigDecimal maxParkingDays;
    // VNMENO <!-- 貼心提醒 -->
    private String reminderMessage;
    // VNAMTA <!-- 尚欠金額 -->
    private BigDecimal amountToQualify;
    // VNFLA1 <!-- 當期使用資格 -->
    private String eligible;

    private String privateBanking;

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getApplicable() {
        return applicable;
    }

    public void setApplicable(String applicable) {
        this.applicable = applicable;
    }

    public String getBenefitStartDate() {
        return benefitStartDate;
    }

    public void setBenefitStartDate(String benefitStartDate) {
        this.benefitStartDate = benefitStartDate;
    }

    public String getBenefitEndDate() {
        return benefitEndDate;
    }

    public void setBenefitEndDate(String benefitEndDate) {
        this.benefitEndDate = benefitEndDate;
    }

    public BigDecimal getTotalUsableCount() {
        return totalUsableCount;
    }

    public void setTotalUsableCount(BigDecimal totalUsableCount) {
        this.totalUsableCount = totalUsableCount;
    }

    public BigDecimal getRemainingUsageCount() {
        return remainingUsageCount;
    }

    public void setRemainingUsageCount(BigDecimal remainingUsageCount) {
        this.remainingUsageCount = remainingUsageCount;
    }

    public BigDecimal getMaxParkingDays() {
        return maxParkingDays;
    }

    public void setMaxParkingDays(BigDecimal maxParkingDays) {
        this.maxParkingDays = maxParkingDays;
    }

    public String getReminderMessage() {
        return reminderMessage;
    }

    public void setReminderMessage(String reminderMessage) {
        this.reminderMessage = reminderMessage;
    }

    public BigDecimal getAmountToQualify() {
        return amountToQualify;
    }

    public void setAmountToQualify(BigDecimal amountToQualify) {
        this.amountToQualify = amountToQualify;
    }

    public String getEligible() {
        return eligible;
    }

    public void setEligible(String eligible) {
        this.eligible = eligible;
    }

    public BigDecimal getPbTotalUsableCount() {
        return pbTotalUsableCount;
    }

    public void setPbTotalUsableCount(BigDecimal pbTotalUsableCount) {
        this.pbTotalUsableCount = pbTotalUsableCount;
    }

    public BigDecimal getPbRemainingUsageCount() {
        return pbRemainingUsageCount;
    }

    public void setPbRemainingUsageCount(BigDecimal pbRemainingUsageCount) {
        this.pbRemainingUsageCount = pbRemainingUsageCount;
    }

    public String getPrivateBanking() {
        return privateBanking;
    }

    public void setPrivateBanking(String privateBanking) {
        this.privateBanking = privateBanking;
    }

    public AdditionalBenefitType benefitType() {
        return AdditionalBenefitType.fromCode(this.getQueryType());
    }

    public int queryTypeRank() {
        return AdditionalBenefitType.queryTypeRank(this.getQueryType());
    }


}
