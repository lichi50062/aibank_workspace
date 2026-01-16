/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.general.qu001.model;

import java.math.BigDecimal;

//@formatter:off
/**
* @(#)FundCalculateModel.java
* 
* <p>Description: 基金計算報酬率用(投資牌卡)</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/07/05, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class FundCalculateModel {

    /**
     * 參考市值折臺
     */
    private BigDecimal refAmt;

    /**
     * 投資金額折臺
     */
    private BigDecimal invAmt;

    /**
     * 調整後累積現金配息折臺
     */
    private BigDecimal adjustAmt;

    public FundCalculateModel() {
    }

    public FundCalculateModel(BigDecimal refAmt, BigDecimal invAmt, BigDecimal adjustAmt) {
        this.refAmt = refAmt;
        this.invAmt = invAmt;
        this.adjustAmt = adjustAmt;
    }

    public BigDecimal getRefAmt() {
        return refAmt;
    }

    public void setRefAmt(BigDecimal refAmt) {
        this.refAmt = refAmt;
    }

    public BigDecimal getInvAmt() {
        return invAmt;
    }

    public void setInvAmt(BigDecimal invAmt) {
        this.invAmt = invAmt;
    }

    public BigDecimal getAdjustAmt() {
        return adjustAmt;
    }

    public void setAdjustAmt(BigDecimal adjustAmt) {
        this.adjustAmt = adjustAmt;
    }
}
