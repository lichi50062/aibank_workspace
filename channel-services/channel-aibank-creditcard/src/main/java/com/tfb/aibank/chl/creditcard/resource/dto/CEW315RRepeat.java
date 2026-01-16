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
package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 信用卡帳單分期查詢下行電文 Repeat
 * 
 * @author Edward Tien
 */
public class CEW315RRepeat {

    /** 帳單年月 */
    private String billYyyymm;

    /** 申請日期 */
    private Date applyDay;

    /** 分期期數 */
    private Integer period;

    /** 分期生效金額 */
    private BigDecimal approveAmount;

    /** 分期利率 */
    private BigDecimal interestRate;

    /** 分期未入帳金額 */
    private BigDecimal unbilledAmount;

    /** 首次入帳日 */
    private Date firstBilledDay;

    /** 最後一次入帳日 */
    private Date lastBilledDay;

    /** 已分期期 */
    private Integer billedPreiod;

    /** filler */
    private String filler;

    public String getBillYyyymm() {
        return billYyyymm;
    }

    public void setBillYyyymm(String billYyyymm) {
        this.billYyyymm = billYyyymm;
    }

    public Date getApplyDay() {
        return applyDay;
    }

    public void setApplyDay(Date applyDay) {
        this.applyDay = applyDay;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public BigDecimal getApproveAmount() {
        return approveAmount;
    }

    public void setApproveAmount(BigDecimal approveAmount) {
        this.approveAmount = approveAmount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getUnbilledAmount() {
        return unbilledAmount;
    }

    public void setUnbilledAmount(BigDecimal unbilledAmount) {
        this.unbilledAmount = unbilledAmount;
    }

    public Date getFirstBilledDay() {
        return firstBilledDay;
    }

    public void setFirstBilledDay(Date firstBilledDay) {
        this.firstBilledDay = firstBilledDay;
    }

    public Date getLastBilledDay() {
        return lastBilledDay;
    }

    public void setLastBilledDay(Date lastBilledDay) {
        this.lastBilledDay = lastBilledDay;
    }

    public Integer getBilledPreiod() {
        return billedPreiod;
    }

    public void setBilledPreiod(Integer billedPreiod) {
        this.billedPreiod = billedPreiod;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

}
