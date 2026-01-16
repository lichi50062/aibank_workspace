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
import java.util.List;

import com.tfb.aibank.common.model.TxHeadRs;

/**
 * 信用卡帳單分期查詢下行電文 Body
 * 
 * @author Edward Tien
 */
public class CEW315RResponse extends TxHeadRs {

    private static final long serialVersionUID = -2382156043722893283L;

    /** 傳送序號 */
    private String spRefId;

    /** 持卡人ID */
    private String cardHolderId;

    /** 持卡人ID */
    private List<CEW315RRepeat> repeats;

    /** 警示訊息 */
    private String wording;

    /** 本期帳單年月 */
    private String billYyyymm;

    /** 帳單結帳日 */
    private Date billCycleDay;

    /** 繳款截止日 */
    private Date paymentDeadline;

    /** 本期應繳餘額 */
    private BigDecimal paymentBalance;

    /** 最低應繳金額 */
    private BigDecimal minimumPay;

    /** 帳單分期利率 */
    private BigDecimal interestRate;

    /** 本期新增 */
    private BigDecimal purchaseAmount;

    /** 申請狀態 */
    private String status;

    /** 申請日期 */
    private Date applyDay;

    /** 分期期數 */
    private Integer period;

    /** 可分期期數種類 */
    private Integer periodOccur;

    /** 可分期期數1 */
    private String period1;

    /** 可分期期數2 */
    private String period2;

    /** 可分期期數3 */
    private String period3;

    /** 可分期期數4 */
    private String period4;

    /** 可分期期數5 */
    private String period5;

    /** 可分期期數6 */
    private String period6;

    /** 可分期期數7 */
    private String period7;

    /** 可分期期數8 */
    private String period8;

    /** 可分期期數9 */
    private String period9;

    /** 可分期期數10 */
    private String period10;

    /** 可分期期數11 */
    private String period11;

    /** 可分期期數12 */
    private String period12;

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public String getBillYyyymm() {
        return billYyyymm;
    }

    public void setBillYyyymm(String billYyyymm) {
        this.billYyyymm = billYyyymm;
    }

    public Date getBillCycleDay() {
        return billCycleDay;
    }

    public void setBillCycleDay(Date billCycleDay) {
        this.billCycleDay = billCycleDay;
    }

    public Date getPaymentDeadline() {
        return paymentDeadline;
    }

    public void setPaymentDeadline(Date paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }

    public BigDecimal getPaymentBalance() {
        return paymentBalance;
    }

    public void setPaymentBalance(BigDecimal paymentBalance) {
        this.paymentBalance = paymentBalance;
    }

    public BigDecimal getMinimumPay() {
        return minimumPay;
    }

    public void setMinimumPay(BigDecimal minimumPay) {
        this.minimumPay = minimumPay;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(BigDecimal purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Integer getPeriodOccur() {
        return periodOccur;
    }

    public void setPeriodOccur(Integer periodOccur) {
        this.periodOccur = periodOccur;
    }

    public String getPeriod1() {
        return period1;
    }

    public void setPeriod1(String period1) {
        this.period1 = period1;
    }

    public String getPeriod2() {
        return period2;
    }

    public void setPeriod2(String period2) {
        this.period2 = period2;
    }

    public String getPeriod3() {
        return period3;
    }

    public void setPeriod3(String period3) {
        this.period3 = period3;
    }

    public String getPeriod4() {
        return period4;
    }

    public void setPeriod4(String period4) {
        this.period4 = period4;
    }

    public String getPeriod5() {
        return period5;
    }

    public void setPeriod5(String period5) {
        this.period5 = period5;
    }

    public String getPeriod6() {
        return period6;
    }

    public void setPeriod6(String period6) {
        this.period6 = period6;
    }

    public String getPeriod7() {
        return period7;
    }

    public void setPeriod7(String period7) {
        this.period7 = period7;
    }

    public String getPeriod8() {
        return period8;
    }

    public void setPeriod8(String period8) {
        this.period8 = period8;
    }

    public String getPeriod9() {
        return period9;
    }

    public void setPeriod9(String period9) {
        this.period9 = period9;
    }

    public String getPeriod10() {
        return period10;
    }

    public void setPeriod10(String period10) {
        this.period10 = period10;
    }

    public String getPeriod11() {
        return period11;
    }

    public void setPeriod11(String period11) {
        this.period11 = period11;
    }

    public String getPeriod12() {
        return period12;
    }

    public void setPeriod12(String period12) {
        this.period12 = period12;
    }

    public String getSpRefId() {
        return spRefId;
    }

    public void setSpRefId(String spRefId) {
        this.spRefId = spRefId;
    }

    public String getCardHolderId() {
        return cardHolderId;
    }

    public void setCardHolderId(String cardHolderId) {
        this.cardHolderId = cardHolderId;
    }

    public List<CEW315RRepeat> getRepeats() {
        return repeats;
    }

    public void setRepeats(List<CEW315RRepeat> repeats) {
        this.repeats = repeats;
    }

}
