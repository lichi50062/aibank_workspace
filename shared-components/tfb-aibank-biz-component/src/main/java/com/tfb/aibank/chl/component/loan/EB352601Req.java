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
 * @(#)EB352601Req.java
 * 
 * <p>Description:還本繳息查詢 (國宅房貸)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/05, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB352601Req extends TxHeadRq {

    private static final long serialVersionUID = 1L;

    /** 功能. */
    @Schema(description = "功能")
    private String func;

    /** 國宅帳號. */
    @Schema(description = "國宅帳號")
    private String acnoPh;

    /** 預定交易日. */
    @Schema(description = "預定交易日")
    private Date txDate;

    /** 預定還本金額. */
    @Schema(description = "預定還本金額")
    private Date lstIntDate;

    /** 查詢明細 . */
    @Schema(description = "查詢明細")
    private String option;

    /** 查詢明細 . */
    @Schema(description = "預定還本金額")
    private BigDecimal prnAmt;

    /** 查詢明細 . */
    @Schema(description = "期數")
    private String prd;

    /** 查詢明細 . */
    @Schema(description = "列印類別")
    private String printFlg;

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getAcnoPh() {
        return acnoPh;
    }

    public void setAcnoPh(String acnoPh) {
        this.acnoPh = acnoPh;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public Date getLstIntDate() {
        return lstIntDate;
    }

    public void setLstIntDate(Date lstIntDate) {
        this.lstIntDate = lstIntDate;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
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

    public String getPrintFlg() {
        return printFlg;
    }

    public void setPrintFlg(String printFlg) {
        this.printFlg = printFlg;
    }
}
