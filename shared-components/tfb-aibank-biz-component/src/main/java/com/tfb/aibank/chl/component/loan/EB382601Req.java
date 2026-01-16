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
 * @(#)EB382601Req.java
 * 
 * <p>Description:還本繳息查詢 (就學貸款)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/05, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB382601Req extends TxHeadRq {

    private static final long serialVersionUID = 1L;

    /** 功能. */
    @Schema(description = "功能")
    private String func;

    /** 授信帳號. */
    @Schema(description = "授信帳號")
    private String acnoSl;

    /** 文件編號. */
    @Schema(description = "文件編號")
    private Date yrTerm;

    /** 預定交易日 . */
    @Schema(description = "預定交易日")
    private Date rcvDate;

    /** 計息止日 . */
    @Schema(description = "計息止日")
    private Date intDate;

    /** 查詢明細 . */
    @Schema(description = "查詢明細")
    private String detailFlg;

    /** 預定還本金額 . */
    @Schema(description = "預定還本金額")
    private BigDecimal prnAmt;

    /** 期數 . */
    @Schema(description = "期數")
    private BigDecimal prd;

    /** 列印類別 . */
    @Schema(description = "列印類別")
    private String printFlg;

    /** 還款方式 . */
    @Schema(description = "還款方式")
    private String prdPayTyp;

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getAcnoSl() {
        return acnoSl;
    }

    public void setAcnoSl(String acnoSl) {
        this.acnoSl = acnoSl;
    }

    public Date getYrTerm() {
        return yrTerm;
    }

    public void setYrTerm(Date yrTerm) {
        this.yrTerm = yrTerm;
    }

    public Date getRcvDate() {
        return rcvDate;
    }

    public void setRcvDate(Date rcvDate) {
        this.rcvDate = rcvDate;
    }

    public Date getIntDate() {
        return intDate;
    }

    public void setIntDate(Date intDate) {
        this.intDate = intDate;
    }

    public String getDetailFlg() {
        return detailFlg;
    }

    public void setDetailFlg(String detailFlg) {
        this.detailFlg = detailFlg;
    }

    public BigDecimal getPrnAmt() {
        return prnAmt;
    }

    public void setPrnAmt(BigDecimal prnAmt) {
        this.prnAmt = prnAmt;
    }

    public BigDecimal getPrd() {
        return prd;
    }

    public void setPrd(BigDecimal prd) {
        this.prd = prd;
    }

    public String getPrintFlg() {
        return printFlg;
    }

    public void setPrintFlg(String printFlg) {
        this.printFlg = printFlg;
    }

    public String getPrdPayTyp() {
        return prdPayTyp;
    }

    public void setPrdPayTyp(String prdPayTyp) {
        this.prdPayTyp = prdPayTyp;
    }
}
