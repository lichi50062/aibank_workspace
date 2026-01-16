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
 * @(#)EB372601Req.java
 * 
 * <p>Description:還本繳息查詢 (分期型房貸、分期型信貸、留學貸款)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/05, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB372602Req extends TxHeadRq {

    private static final long serialVersionUID = 1L;

    /** 功能. */
    @Schema(description = "功能")
    private String func;

    /** 授信帳號. */
    @Schema(description = "授信帳號")
    private String acnoLn;

    /** 文件編號. */
    @Schema(description = "文件編號")
    private Date docNo;

    /** 文件編號序號. */
    @Schema(description = "文件編號序號")
    private String docSeq;

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

    /** 匯率 . */
    @Schema(description = "匯率")
    private BigDecimal fxExRate;

    /** 列印類別 . */
    @Schema(description = "列印類別")
    private String printType;

    /** 利息列印原幣 . */
    @Schema(description = "利息列印原幣")
    private String intCurCod;

    /** 列印期數 . */
    @Schema(description = "列印期數")
    private String payPrd;

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getAcnoLn() {
        return acnoLn;
    }

    public void setAcnoLn(String acnoLn) {
        this.acnoLn = acnoLn;
    }

    public Date getDocNo() {
        return docNo;
    }

    public void setDocNo(Date docNo) {
        this.docNo = docNo;
    }

    public String getDocSeq() {
        return docSeq;
    }

    public void setDocSeq(String docSeq) {
        this.docSeq = docSeq;
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

    public BigDecimal getFxExRate() {
        return fxExRate;
    }

    public void setFxExRate(BigDecimal fxExRate) {
        this.fxExRate = fxExRate;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public String getIntCurCod() {
        return intCurCod;
    }

    public void setIntCurCod(String intCurCod) {
        this.intCurCod = intCurCod;
    }

    public String getPayPrd() {
        return payPrd;
    }

    public void setPayPrd(String payPrd) {
        this.payPrd = payPrd;
    }
}
