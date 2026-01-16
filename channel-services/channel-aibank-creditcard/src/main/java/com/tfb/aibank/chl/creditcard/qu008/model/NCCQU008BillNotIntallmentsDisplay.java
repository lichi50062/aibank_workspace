package com.tfb.aibank.chl.creditcard.qu008.model;

import java.math.BigDecimal;

// @formatter:off
/**
 * @(#)NCCQU008BillNotIntallmentsDisplay.java
 * 
 * <p>Description:未分期帳單</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/07/05, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class NCCQU008BillNotIntallmentsDisplay {
    /** e.g., 帳單年: 2025 */
    private String billYyyy;

    /** e.g., 帳單月: 07 */
    private String billMm;
    /** 狀態代碼 (1: 申請中, 2: 未申請) */
    private String status;

    /** 帳單金額 格式化 */
    private String txAmt;

    /** 帳單金額 */
    private BigDecimal txAmtOrigin;

    /**
     * @return the billYyyy
     */
    public String getBillYyyy() {
        return billYyyy;
    }

    /**
     * @param billYyyy
     *            the billYyyy to set
     */
    public void setBillYyyy(String billYyyy) {
        this.billYyyy = billYyyy;
    }

    /**
     * @return the billMm
     */
    public String getBillMm() {
        return billMm;
    }

    /**
     * @param billMm
     *            the billMm to set
     */
    public void setBillMm(String billMm) {
        this.billMm = billMm;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the txAmt
     */
    public String getTxAmt() {
        return txAmt;
    }

    /**
     * @param txAmt
     *            the txAmt to set
     */
    public void setTxAmt(String txAmt) {
        this.txAmt = txAmt;
    }

    /**
     * @return the txAmtOrigin
     */
    public BigDecimal getTxAmtOrigin() {
        return txAmtOrigin;
    }

    /**
     * @param txAmtOrigin
     *            the txAmtOrigin to set
     */
    public void setTxAmtOrigin(BigDecimal txAmtOrigin) {
        this.txAmtOrigin = txAmtOrigin;
    }

}
