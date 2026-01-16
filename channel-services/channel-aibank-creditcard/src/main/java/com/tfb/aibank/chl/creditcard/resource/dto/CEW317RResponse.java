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
 * 帳單分期申請下行電文 Body
 * 
 * @author Evan
 */
public class CEW317RResponse {

    /** 持卡人ID */
    private String cardHolderId;

    /** 交易編號 */
    private String refNo;

    /** 本期帳單年月 */
    private Date billYyyymm;

    /** 帳單結帳日 */
    private Date billCycleDay;

    /** 繳款截止日 */
    private Date paymentDeadline;

    /** 分期利率 */
    private BigDecimal interestRate;

    /** 分期期數 */
    private Integer period;

    /** E-MAIL */
    private String email;

    /**
     * @return the cardHolderId
     */
    public String getCardHolderId() {
        return cardHolderId;
    }

    /**
     * @param cardHolderId
     *            the cardHolderId to set
     */
    public void setCardHolderId(String cardHolderId) {
        this.cardHolderId = cardHolderId;
    }

    /**
     * @return the refNo
     */
    public String getRefNo() {
        return refNo;
    }

    /**
     * @param refNo
     *            the refNo to set
     */
    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    /**
     * @return the billYyyymm
     */
    public Date getBillYyyymm() {
        return billYyyymm;
    }

    /**
     * @param billYyyymm
     *            the billYyyymm to set
     */
    public void setBillYyyymm(Date billYyyymm) {
        this.billYyyymm = billYyyymm;
    }

    /**
     * @return the billCycleDay
     */
    public Date getBillCycleDay() {
        return billCycleDay;
    }

    /**
     * @param billCycleDay
     *            the billCycleDay to set
     */
    public void setBillCycleDay(Date billCycleDay) {
        this.billCycleDay = billCycleDay;
    }

    /**
     * @return the paymentDeadline
     */
    public Date getPaymentDeadline() {
        return paymentDeadline;
    }

    /**
     * @param paymentDeadline
     *            the paymentDeadline to set
     */
    public void setPaymentDeadline(Date paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }

    /**
     * @return the interestRate
     */
    public BigDecimal getInterestRate() {
        return interestRate;
    }

    /**
     * @param interestRate
     *            the interestRate to set
     */
    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * @return the period
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * @param period
     *            the period to set
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
