package com.tfb.aibank.chl.creditcard.ot003.model;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCOT003010Rs.java
 * 
 * <p>Description:超商繳信用卡款 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/04, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCOT003010Rs implements RsData {
    /** 直接去下一頁 */
    private boolean goNextPage;

    /** 提示訊息類別 A-E */
    private String msgType;

    /** 月份 */
    private String month;

    /** 結帳日 */
    private String checkoutDay;

    /** 繳款期限 */
    private String paymentDeadline;

    /** 已繳納的日期 */
    private String amountPaidDay;

    /** 應繳金額 */
    private BigDecimal acctIdSbal;

    /** 已繳金額 */
    private BigDecimal acctIdPayd;

    /** 剩餘應繳金額 */
    private BigDecimal acctIdDpayd;

    /** 最低應繳金額 */
    private BigDecimal acctIdMinp;

    /**
     * @return the msgType
     */
    public String getMsgType() {
        return msgType;
    }

    /**
     * @param msgType
     *            the msgType to set
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month
     *            the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @return the checkoutDay
     */
    public String getCheckoutDay() {
        return checkoutDay;
    }

    /**
     * @param checkoutDay
     *            the checkoutDay to set
     */
    public void setCheckoutDay(String checkoutDay) {
        this.checkoutDay = checkoutDay;
    }

    /**
     * @return the paymentDeadline
     */
    public String getPaymentDeadline() {
        return paymentDeadline;
    }

    /**
     * @param paymentDeadline
     *            the paymentDeadline to set
     */
    public void setPaymentDeadline(String paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }

    /**
     * @return the amountPaidDay
     */
    public String getAmountPaidDay() {
        return amountPaidDay;
    }

    /**
     * @param amountPaidDay
     *            the amountPaidDay to set
     */
    public void setAmountPaidDay(String amountPaidDay) {
        this.amountPaidDay = amountPaidDay;
    }

    /**
     * @return the acctIdSbal
     */
    public BigDecimal getAcctIdSbal() {
        return acctIdSbal;
    }

    /**
     * @param acctIdSbal
     *            the acctIdSbal to set
     */
    public void setAcctIdSbal(BigDecimal acctIdSbal) {
        this.acctIdSbal = acctIdSbal;
    }

    /**
     * @return the acctIdPayd
     */
    public BigDecimal getAcctIdPayd() {
        return acctIdPayd;
    }

    /**
     * @param acctIdPayd
     *            the acctIdPayd to set
     */
    public void setAcctIdPayd(BigDecimal acctIdPayd) {
        this.acctIdPayd = acctIdPayd;
    }

    /**
     * @return the acctIdDpayd
     */
    public BigDecimal getAcctIdDpayd() {
        return acctIdDpayd;
    }

    /**
     * @param acctIdDpayd
     *            the acctIdDpayd to set
     */
    public void setAcctIdDpayd(BigDecimal acctIdDpayd) {
        this.acctIdDpayd = acctIdDpayd;
    }

    /**
     * @return the acctIdMinp
     */
    public BigDecimal getAcctIdMinp() {
        return acctIdMinp;
    }

    /**
     * @param acctIdMinp
     *            the acctIdMinp to set
     */
    public void setAcctIdMinp(BigDecimal acctIdMinp) {
        this.acctIdMinp = acctIdMinp;
    }

    public boolean isGoNextPage() {
        return goNextPage;
    }

    public void setGoNextPage(boolean goNextPage) {
        this.goNextPage = goNextPage;
    }
}
