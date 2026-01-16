package com.tfb.aibank.chl.creditcard.qu001.model;

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
public class NCCQQU001Installment extends NCCQQU001Bill {
    /** 交易日期 X(7) */
    private String dateString;
    /** 入帳日期 */
    private String payDayString;

    /**
     * @return the dateString
     */
    public String getDateString() {
        return dateString;
    }

    /**
     * @param dateString
     *            the dateString to set
     */
    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    /**
     * @return the payDayString
     */
    public String getPayDayString() {
        return payDayString;
    }

    /**
     * @param payDayString
     *            the payDayString to set
     */
    public void setPayDayString(String payDayString) {
        this.payDayString = payDayString;
    }

}
