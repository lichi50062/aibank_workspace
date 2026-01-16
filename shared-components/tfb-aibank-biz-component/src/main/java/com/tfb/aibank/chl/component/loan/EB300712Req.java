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

import com.tfb.aibank.common.model.TxHeadRq;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB300712Req.java
 * 
 * <p>Description:取得貸款墊付保險費資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/05, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB300712Req extends TxHeadRq {

    private static final long serialVersionUID = 1L;

    /** 功能. */
    @Schema(description = "功能")
    private String func;

    /** DTL_TYP. */
    @Schema(description = "DTL_TYP")
    private String dtlTyp;

    /** TYPE. */
    @Schema(description = "TYPE")
    private String type;

    /** 授信帳號 . */
    @Schema(description = "授信帳號")
    private String acnoLn;

    /** TRF_ACT . */
    @Schema(description = "TRF_ACT")
    private String trfAct;

    /** 文件編號 . */
    @Schema(description = "文件編號")
    private String docNo;

    /** 文件編號序號 . */
    @Schema(description = "文件編號序號")
    private String docSeq;

    /** STA_OFFSET_1 . */
    @Schema(description = "STA_OFFSET_1")
    private String staOffset1;

    /** CUR_COD . */
    @Schema(description = "CUR_COD")
    private String curCod;

    /** ALC_AMT . */
    @Schema(description = "ALC_AMT")
    private BigDecimal alcAmt;

    /** TX_SRL . */
    @Schema(description = "TX_SRL")
    private String txSrl;

    /** RSK_NAT . */
    @Schema(description = "RSK_NAT")
    private String rskNat;

    /** RMK_ITM . */
    @Schema(description = "RMK_ITM")
    private String rmkItm;

    /** RCV_TYP . */
    @Schema(description = "RCV_TYP")
    private String rcvTyp;

    /** ADDR_1 . */
    @Schema(description = "ADDR_1")
    private String addr1;

    /** CONFIRM_COD . */
    @Schema(description = "CONFIRM_COD")
    private String confirmCod;

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getDtlTyp() {
        return dtlTyp;
    }

    public void setDtlTyp(String dtlTyp) {
        this.dtlTyp = dtlTyp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAcnoLn() {
        return acnoLn;
    }

    public void setAcnoLn(String acnoLn) {
        this.acnoLn = acnoLn;
    }

    public String getTrfAct() {
        return trfAct;
    }

    public void setTrfAct(String trfAct) {
        this.trfAct = trfAct;
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

    public String getStaOffset1() {
        return staOffset1;
    }

    public void setStaOffset1(String staOffset1) {
        this.staOffset1 = staOffset1;
    }

    public String getCurCod() {
        return curCod;
    }

    public void setCurCod(String curCod) {
        this.curCod = curCod;
    }

    public BigDecimal getAlcAmt() {
        return alcAmt;
    }

    public void setAlcAmt(BigDecimal alcAmt) {
        this.alcAmt = alcAmt;
    }

    public String getTxSrl() {
        return txSrl;
    }

    public void setTxSrl(String txSrl) {
        this.txSrl = txSrl;
    }

    public String getRskNat() {
        return rskNat;
    }

    public void setRskNat(String rskNat) {
        this.rskNat = rskNat;
    }

    public String getRmkItm() {
        return rmkItm;
    }

    public void setRmkItm(String rmkItm) {
        this.rmkItm = rmkItm;
    }

    public String getRcvTyp() {
        return rcvTyp;
    }

    public void setRcvTyp(String rcvTyp) {
        this.rcvTyp = rcvTyp;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getConfirmCod() {
        return confirmCod;
    }

    public void setConfirmCod(String confirmCod) {
        this.confirmCod = confirmCod;
    }
}
