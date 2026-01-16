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
package com.tfb.aibank.chl.component.loan;

import java.math.BigDecimal;
import java.util.Date;

import com.tfb.aibank.common.model.TxHeadRq;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB362601Req.java
 * 
 * <p>Description:還本繳息查詢 (公教房貸)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/05, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB362601Req extends TxHeadRq {

    private static final long serialVersionUID = 1L;

    /** 功能. */
    @Schema(description = "功能")
    private String func;

    /** 公教帳號. */
    @Schema(description = "公教帳號")
    private String acnoOt;

    /** 預定交易日. */
    @Schema(description = "預定交易日")
    private Date txDate;

    /** 預定還本金額. */
    @Schema(description = "預定還本金額")
    private BigDecimal prnAmt;

    /** 期數 . */
    @Schema(description = "期數")
    private String prd;

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getAcnoOt() {
        return acnoOt;
    }

    public void setAcnoOt(String acnoOt) {
        this.acnoOt = acnoOt;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public BigDecimal getPrnAmt() {
        return prnAmt;
    }

    public void setPrnAmt(BigDecimal prnAmt) {
        this.prnAmt = prnAmt;
    }

    public String getPrd() {
        return prd;
    }

    public void setPrd(String prd) {
        this.prd = prd;
    }
}
