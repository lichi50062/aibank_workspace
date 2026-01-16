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
package com.tfb.aibank.chl.component.userdatacache.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tfb.aibank.common.model.TxHeadRs;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB5557891Response.java
 * 
 * <p>Description:歸戶帳號電文(EB5557891)下行欄位 Body</p>
 * 包含以下格式欄位
 * <ul>
 *  <li>D001</li>
 *  <li>D002</li>
 *  <li>D003</li>
 *  <li>D004</li>
 *  <li>D005</li>
 *  <li>D007</li>
 *  <li>D008</li>
 *  <li>D009</li>
 * </ul>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/04, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "歸戶帳號電文(EB5557891)下行欄位 Body")
public class EB5557891Res extends TxHeadRs {

    private static final long serialVersionUID = 5623257127059548611L;

    /** 客戶身分證號 */
    @Schema(description = "客戶身分證號")
    private String idno;

    /** 電子通路分戶代碼 */
    @Schema(description = "電子通路分戶代碼")
    private String nameCode;

    /** 授信分行別 */
    @Schema(description = "授信分行別")
    private String brhName;

    /** 透支可用餘額 */
    @Schema(description = "透支可用餘額")
    private BigDecimal odavail1;

    /** 戶況 */
    @Schema(description = "戶況")
    private String actSts;

    /** 文件編號 */
    @Schema(description = "文件編號")
    private String docNo;

    /** 數位存款註記 */
    @Schema(description = "數位存款註記")
    private String digitalFlg;

    /** 是否為通提戶(Y/N) */
    @Schema(description = "是否為通提戶(Y/N)")
    private String crossFlg;

    /** 通提密碼變更錯誤次數 */
    @Schema(description = "通提密碼變更錯誤次數")
    private String pwdErrCnt;

    /** 是否為無摺戶(Y/N) */
    @Schema(description = "是否為無摺戶(Y/N)")
    private String passbookFlg;

    /** 產品標幟 */
    @Schema(description = "產品標幟")
    private String prodInd;

    /** 帳號 */
    @Schema(description = "帳號")
    private String acno;

    /** 原貸款金額 */
    @Schema(description = "原貸款金額")
    private BigDecimal oriLoanBal;

    /** 已繳期數 */
    @Schema(description = "已繳期數")
    private String loanTerm;

    /** 應繳日 */
    @Schema(description = "應繳日")
    private String intCycle;

    /** 每期期金 */
    @Schema(description = "每期期金")
    private String insAmt;

    /** 就貸：還款日 企業：交易日 */
    @Schema(description = "就貸：還款日 企業：交易日")
    private Date prnStrDate;

    /** 部份銷帳 */
    @Schema(description = "部份銷帳")
    private String partRecv;

    /** ACH 註記 */
    @Schema(description = "ACH 註記")
    private String achFlg;

    /** 貸款帳號特殊狀況註記 */
    @Schema(description = "貸款帳號特殊狀況註記")
    private String specSts;

    /** 交易明細查詢 */
    @Schema(description = "交易明細查詢")
    private String flg302611;

    /** 還款試算查詢 */
    @Schema(description = "還款試算查詢")
    private String flg302602;

    /** 定存帳號 */
    @Schema(description = "定存帳號")
    private String tdAct;

    /** 定存序號 */
    @Schema(description = "定存序號")
    private String slipNo;

    /** 存單號碼 */
    @Schema(description = "存單號碼")
    private String tdNo;

    /** 房貸透支流用金額總上限 */
    @Schema(description = "房貸透支流用金額總上限")
    private BigDecimal threshAmt;

    /** 還款方式 */
    @Schema(description = "還款方式")
    private String defaultString18;

    /** 訂約額度(額度式) */
    @Schema(description = "訂約額度(額度式)")
    private BigDecimal defaultString24;

    @Schema(description = "電文下行 Repeats")
    private List<EB5557891ResRep> repeats = new ArrayList<>();

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    public String getBrhName() {
        return brhName;
    }

    public void setBrhName(String brhName) {
        this.brhName = brhName;
    }

    public BigDecimal getOdavail1() {
        return odavail1;
    }

    public void setOdavail1(BigDecimal odavail1) {
        this.odavail1 = odavail1;
    }

    public String getActSts() {
        return actSts;
    }

    public void setActSts(String actSts) {
        this.actSts = actSts;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getDigitalFlg() {
        return digitalFlg;
    }

    public void setDigitalFlg(String digitalFlg) {
        this.digitalFlg = digitalFlg;
    }

    public String getCrossFlg() {
        return crossFlg;
    }

    public void setCrossFlg(String crossFlg) {
        this.crossFlg = crossFlg;
    }

    public String getPwdErrCnt() {
        return pwdErrCnt;
    }

    public void setPwdErrCnt(String pwdErrCnt) {
        this.pwdErrCnt = pwdErrCnt;
    }

    public String getPassbookFlg() {
        return passbookFlg;
    }

    public void setPassbookFlg(String passbookFlg) {
        this.passbookFlg = passbookFlg;
    }

    public String getProdInd() {
        return prodInd;
    }

    public void setProdInd(String prodInd) {
        this.prodInd = prodInd;
    }

    public String getAcno() {
        return acno;
    }

    public void setAcno(String acno) {
        this.acno = acno;
    }

    public BigDecimal getOriLoanBal() {
        return oriLoanBal;
    }

    public void setOriLoanBal(BigDecimal oriLoanBal) {
        this.oriLoanBal = oriLoanBal;
    }

    public String getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(String loanTerm) {
        this.loanTerm = loanTerm;
    }

    public String getIntCycle() {
        return intCycle;
    }

    public void setIntCycle(String intCycle) {
        this.intCycle = intCycle;
    }

    public String getInsAmt() {
        return insAmt;
    }

    public void setInsAmt(String insAmt) {
        this.insAmt = insAmt;
    }

    public Date getPrnStrDate() {
        return prnStrDate;
    }

    public void setPrnStrDate(Date prnStrDate) {
        this.prnStrDate = prnStrDate;
    }

    public String getPartRecv() {
        return partRecv;
    }

    public void setPartRecv(String partRecv) {
        this.partRecv = partRecv;
    }

    public String getAchFlg() {
        return achFlg;
    }

    public void setAchFlg(String achFlg) {
        this.achFlg = achFlg;
    }

    public String getSpecSts() {
        return specSts;
    }

    public void setSpecSts(String specSts) {
        this.specSts = specSts;
    }

    public String getFlg302611() {
        return flg302611;
    }

    public void setFlg302611(String flg302611) {
        this.flg302611 = flg302611;
    }

    public String getFlg302602() {
        return flg302602;
    }

    public void setFlg302602(String flg302602) {
        this.flg302602 = flg302602;
    }

    public String getTdAct() {
        return tdAct;
    }

    public void setTdAct(String tdAct) {
        this.tdAct = tdAct;
    }

    public String getSlipNo() {
        return slipNo;
    }

    public void setSlipNo(String slipNo) {
        this.slipNo = slipNo;
    }

    public String getTdNo() {
        return tdNo;
    }

    public void setTdNo(String tdNo) {
        this.tdNo = tdNo;
    }

    public BigDecimal getThreshAmt() {
        return threshAmt;
    }

    public void setThreshAmt(BigDecimal threshAmt) {
        this.threshAmt = threshAmt;
    }

    public String getDefaultString18() {
        return defaultString18;
    }

    public void setDefaultString18(String defaultString18) {
        this.defaultString18 = defaultString18;
    }

    public BigDecimal getDefaultString24() {
        return defaultString24;
    }

    public void setDefaultString24(BigDecimal defaultString24) {
        this.defaultString24 = defaultString24;
    }

    public List<EB5557891ResRep> getRepeats() {
        return repeats;
    }

    public void setRepeats(List<EB5557891ResRep> repeats) {
        this.repeats = repeats;
    }

}
