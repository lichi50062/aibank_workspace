package com.tfb.aibank.chl.creditcard.ot003.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCOT003020Rs.java
 * 
 * <p>Description:超商繳信用卡款 020 繳款條碼頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/04, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCOT003020Rs implements RsData {

    /** 代收期限條碼 */
    private String collectPeriod;

    /** 繳款帳號條碼 */
    private String billAcctCD;

    /** 繳款金額條碼 */
    private String billAmtCD;

    /** 代收期限 */
    private String period;

    /** 繳款帳號 */
    private String billAcct;

    /** 客戶繳款期限 */
    private Date paymentDeadline;

    /** 繳款金額 */
    private BigDecimal billAmt;

    /**
     * @return the collectPeriod
     */
    public String getCollectPeriod() {
        return collectPeriod;
    }

    /**
     * @param collectPeriod
     *            the collectPeriod to set
     */
    public void setCollectPeriod(String collectPeriod) {
        this.collectPeriod = collectPeriod;
    }

    /**
     * @return the billAcctCD
     */
    public String getBillAcctCD() {
        return billAcctCD;
    }

    /**
     * @param billAcctCD
     *            the billAcctCD to set
     */
    public void setBillAcctCD(String billAcctCD) {
        this.billAcctCD = billAcctCD;
    }

    /**
     * @return the billAmtCD
     */
    public String getBillAmtCD() {
        return billAmtCD;
    }

    /**
     * @param billAmtCD
     *            the billAmtCD to set
     */
    public void setBillAmtCD(String billAmtCD) {
        this.billAmtCD = billAmtCD;
    }

    /**
     * @return the period
     */
    public String getPeriod() {
        return period;
    }

    /**
     * @param period
     *            the period to set
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * @return the billAcct
     */
    public String getBillAcct() {
        return billAcct;
    }

    /**
     * @param billAcct
     *            the billAcct to set
     */
    public void setBillAcct(String billAcct) {
        this.billAcct = billAcct;
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
     * @return the billAmt
     */
    public BigDecimal getBillAmt() {
        return billAmt;
    }

    /**
     * @param billAmt
     *            the billAmt to set
     */
    public void setBillAmt(BigDecimal billAmt) {
        this.billAmt = billAmt;
    }
}
