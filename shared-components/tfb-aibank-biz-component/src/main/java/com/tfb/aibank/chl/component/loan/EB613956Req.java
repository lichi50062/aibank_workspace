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
 * @(#)EB613956Req.java
 * 
 * <p>Description:網路還本繳息</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/05, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB613956Req extends TxHeadRq {

    private static final long serialVersionUID = 1L;

    /** 取得繳款資訊 */
    public static final String GET = "GET";
    /** 建立繳款資訊 */
    public static final String CREATE = "CREATE";
    /** 執行繳款 */
    public static final String PAY = "PAY";

    /** 付款行行庫別. */
    @Schema(description = "付款行行庫別")
    private String payBank;

    /** 轉出帳號. */
    @Schema(description = "轉出帳號")
    private String acnoOut;

    /** 轉出戶身份証號. */
    @Schema(description = "轉出戶身份証號")
    private String outIdno;

    /** 誤別碼 . */
    @Schema(description = "誤別碼")
    private String idnoErrFlg;

    /** 帳務日期 . */
    @Schema(description = "帳務日期")
    private Date actDate;

    /** 功能 . */
    @Schema(description = "功能")
    private String func;

    /** 授信帳號 */
    @Schema(description = "授信帳號")
    private String acnoLn;

    /** 文件編號 . */
    @Schema(description = "文件編號")
    private String docNo;

    /** 文件編號序號 . */
    @Schema(description = "文件編號序號")
    private String docSeq;

    /** 部份還本金額 . */
    @Schema(description = "部份還本金額")
    private String prnAmt;

    /** 期數 . */
    @Schema(description = "期數")
    private String payPrd;

    /** 台幣實收金額 . */
    @Schema(description = "台幣實收金額")
    private BigDecimal rcvAmtNt;

    /** 選項 . */
    @Schema(description = "選項")
    private String option;

    /** 端末交易序號 . */
    @Schema(description = "端末交易序號")
    private String curCod;

    /** 端末交易序號 . */
    @Schema(description = "端末交易序號")
    private String txSer;

    /** 使用者代號 . */
    @Schema(description = "使用者代號")
    private String userId;

    /** 使用者密碼 . */
    @Schema(description = "使用者密碼")
    private String passWord;

    /** YEAR_TERM . */
    @Schema(description = "YEAR_TERM")
    private String yearTerm;

    /** 網銀前台日期 . */
    @Schema(description = "網銀前台日期")
    private Date payDate;

    /** 結清註記 . */
    @Schema(description = "結清註記")
    private String item01;

    /** 提前還款本金違約金額 . */
    @Schema(description = "提前還款本金違約金額")
    private BigDecimal arPntAmt;

    /** 利息金額 . */
    @Schema(description = "利息金額")
    private BigDecimal arIntAmt;

    /** 還款方式 . */
    @Schema(description = "還款方式")
    private String prnPayTyp;

    public String getPayBank() {
        return payBank;
    }

    public void setPayBank(String payBank) {
        this.payBank = payBank;
    }

    public String getAcnoOut() {
        return acnoOut;
    }

    public void setAcnoOut(String acnoOut) {
        this.acnoOut = acnoOut;
    }

    public String getOutIdno() {
        return outIdno;
    }

    public void setOutIdno(String outIdno) {
        this.outIdno = outIdno;
    }

    public String getIdnoErrFlg() {
        return idnoErrFlg;
    }

    public void setIdnoErrFlg(String idnoErrFlg) {
        this.idnoErrFlg = idnoErrFlg;
    }

    public Date getActDate() {
        return actDate;
    }

    public void setActDate(Date actDate) {
        this.actDate = actDate;
    }

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

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getDocSeq() {
        return docSeq;
    }

    public void setDocSeq(String docSeq) {
        this.docSeq = docSeq;
    }

    public String getPrnAmt() {
        return prnAmt;
    }

    public void setPrnAmt(String prnAmt) {
        this.prnAmt = prnAmt;
    }

    public String getPayPrd() {
        return payPrd;
    }

    public void setPayPrd(String payPrd) {
        this.payPrd = payPrd;
    }

    public BigDecimal getRcvAmtNt() {
        return rcvAmtNt;
    }

    public void setRcvAmtNt(BigDecimal rcvAmtNt) {
        this.rcvAmtNt = rcvAmtNt;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getCurCod() {
        return curCod;
    }

    public void setCurCod(String curCod) {
        this.curCod = curCod;
    }

    public String getTxSer() {
        return txSer;
    }

    public void setTxSer(String txSer) {
        this.txSer = txSer;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getYearTerm() {
        return yearTerm;
    }

    public void setYearTerm(String yearTerm) {
        this.yearTerm = yearTerm;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getItem01() {
        return item01;
    }

    public void setItem01(String item01) {
        this.item01 = item01;
    }

    public BigDecimal getArPntAmt() {
        return arPntAmt;
    }

    public void setArPntAmt(BigDecimal arPntAmt) {
        this.arPntAmt = arPntAmt;
    }

    public BigDecimal getArIntAmt() {
        return arIntAmt;
    }

    public void setArIntAmt(BigDecimal arIntAmt) {
        this.arIntAmt = arIntAmt;
    }

    public String getPrnPayTyp() {
        return prnPayTyp;
    }

    public void setPrnPayTyp(String prnPayTyp) {
        this.prnPayTyp = prnPayTyp;
    }
}
