package com.tfb.aibank.chl.creditcard.qu001.model;

import java.util.Date;

//@formatter:off
/**
* @(#)NCCQQU001BillingDetail.java
* 
* <p>Description:信用卡總覽 帳單明細之分期資料</p> 
* 
* <p>Modify History:</p>
* v1.0, 2025/01/07, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCCQQU001InstallmentGroup {
    /** 消費本金 消費日期 */
    private Date date;

    /** 消費本金 */
    private NCCQQU001Installment capital;
    /** 消費利息 */
    private NCCQQU001Installment interest;
    /** 利息退款 */
    private NCCQQU001Installment refund;

    /**
     * @return the capital
     */
    public NCCQQU001Installment getCapital() {
        return capital;
    }

    /**
     * @param capital
     *            the capital to set
     */
    public void setCapital(NCCQQU001Installment capital) {
        this.capital = capital;
    }

    /**
     * @return the interest
     */
    public NCCQQU001Installment getInterest() {
        return interest;
    }

    /**
     * @param interest
     *            the interest to set
     */
    public void setInterest(NCCQQU001Installment interest) {
        this.interest = interest;
    }

    /**
     * @return the refund
     */
    public NCCQQU001Installment getRefund() {
        return refund;
    }

    /**
     * @param refund
     *            the refund to set
     */
    public void setRefund(NCCQQU001Installment refund) {
        this.refund = refund;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

}
