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
package com.tfb.aibank.biz.user.services.feediscount.model;

import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 自動化手續費優惠查詢 下行電文
 * 
 * @author Edward Tien
 */
@Schema(description = "自動化手續費優惠查詢 下行電文")
public class FEP512161Response implements Serializable {

    private static final long serialVersionUID = -8747571027142204855L;

    /** 提款減免次數 */
    @Schema(description = "提款減免次數")
    private Integer csCnt;

    /** 薪轉生效日 */
    @Schema(description = "薪轉生效日")
    private Date staDateSA;

    /** vbanking生效日 */
    @Schema(description = "vbanking生效日")
    private Date staDateVB;

    /** 提款剩餘次數 */
    @Schema(description = "提款剩餘次數")
    private Integer csCntR;

    /** 薪轉終止日 */
    @Schema(description = "薪轉終止日")
    private Date endDateSA;

    /** vbanking終止日 */
    @Schema(description = "vbanking終止日")
    private Date endDateVB;

    /** 自跨轉減免次數 */
    @Schema(description = "自跨轉減免次數")
    private Integer trCnt;

    /** 其他戶生效日 */
    @Schema(description = "其他戶生效日")
    private Date staDateOT;

    /** vip生效日 */
    @Schema(description = "vip生效日")
    private Date staDateVP;

    /** 自跨轉剩餘次數 */
    @Schema(description = "自跨轉剩餘次數")
    private Integer trCntR;

    /** 其他戶終止日 */
    @Schema(description = "其他戶終止日")
    private Date endDateOT;

    /** vip終止日 */
    @Schema(description = "vip終止日")
    private Date endDateVP;

    /** 使用他行設備轉帳減免 */
    @Schema(description = "使用他行設備轉帳減免")
    private Integer trCnt2;

    /** 電子帳單生效日 */
    @Schema(description = "電子帳單生效日")
    private Date staDateEB;

    /** 使用他行設備轉帳剩餘 */
    @Schema(description = "使用他行設備轉帳剩餘")
    private Integer trCntR2;

    /** 電子帳單終止日 */
    @Schema(description = "電子帳單終止日")
    private Date endDateEB;

    /** 優惠身份註記1 */
    @Schema(description = "優惠身份註記1")
    private String srcMemo1;

    /** 優惠身份註記2 */
    @Schema(description = "優惠身份註記2")
    private String srcMemo2;

    /** 優惠身份註記3 */
    @Schema(description = "優惠身份註記3")
    private String srcMemo3;

    /** 優惠身份註記4 */
    @Schema(description = "優惠身份註記4")
    private String srcMemo4;

    /** 優惠身份註記5 */
    @Schema(description = "優惠身份註記5")
    private String srcMemo5;

    /** 優惠身份註記6 */
    @Schema(description = "優惠身份註記6")
    private String srcMemo6;

    /** 其他戶優惠公司 */
    @Schema(description = "其他戶優惠公司")
    private String srcId4;

    /** 其它戶公司統編中文名稱 */
    @Schema(description = "其它戶公司統編中文名稱")
    private String flg4Name;

    public Integer getCsCnt() {
        return csCnt;
    }

    public void setCsCnt(Integer csCnt) {
        this.csCnt = csCnt;
    }

    public Date getStaDateSA() {
        return staDateSA;
    }

    public void setStaDateSA(Date staDateSA) {
        this.staDateSA = staDateSA;
    }

    public Date getStaDateVB() {
        return staDateVB;
    }

    public void setStaDateVB(Date staDateVB) {
        this.staDateVB = staDateVB;
    }

    public Integer getCsCntR() {
        return csCntR;
    }

    public void setCsCntR(Integer csCntR) {
        this.csCntR = csCntR;
    }

    public Date getEndDateSA() {
        return endDateSA;
    }

    public void setEndDateSA(Date endDateSA) {
        this.endDateSA = endDateSA;
    }

    public Date getEndDateVB() {
        return endDateVB;
    }

    public void setEndDateVB(Date endDateVB) {
        this.endDateVB = endDateVB;
    }

    public Integer getTrCnt() {
        return trCnt;
    }

    public void setTrCnt(Integer trCnt) {
        this.trCnt = trCnt;
    }

    public Date getStaDateOT() {
        return staDateOT;
    }

    public void setStaDateOT(Date staDateOT) {
        this.staDateOT = staDateOT;
    }

    public Date getStaDateVP() {
        return staDateVP;
    }

    public void setStaDateVP(Date staDateVP) {
        this.staDateVP = staDateVP;
    }

    public Integer getTrCntR() {
        return trCntR;
    }

    public void setTrCntR(Integer trCntR) {
        this.trCntR = trCntR;
    }

    public Date getEndDateOT() {
        return endDateOT;
    }

    public void setEndDateOT(Date endDateOT) {
        this.endDateOT = endDateOT;
    }

    public Date getEndDateVP() {
        return endDateVP;
    }

    public void setEndDateVP(Date endDateVP) {
        this.endDateVP = endDateVP;
    }

    public Integer getTrCnt2() {
        return trCnt2;
    }

    public void setTrCnt2(Integer trCnt2) {
        this.trCnt2 = trCnt2;
    }

    public Date getStaDateEB() {
        return staDateEB;
    }

    public void setStaDateEB(Date staDateEB) {
        this.staDateEB = staDateEB;
    }

    public Integer getTrCntR2() {
        return trCntR2;
    }

    public void setTrCntR2(Integer trCntR2) {
        this.trCntR2 = trCntR2;
    }

    public Date getEndDateEB() {
        return endDateEB;
    }

    public void setEndDateEB(Date endDateEB) {
        this.endDateEB = endDateEB;
    }

    public String getSrcMemo1() {
        return srcMemo1;
    }

    public void setSrcMemo1(String srcMemo1) {
        this.srcMemo1 = srcMemo1;
    }

    public String getSrcMemo2() {
        return srcMemo2;
    }

    public void setSrcMemo2(String srcMemo2) {
        this.srcMemo2 = srcMemo2;
    }

    public String getSrcMemo3() {
        return srcMemo3;
    }

    public void setSrcMemo3(String srcMemo3) {
        this.srcMemo3 = srcMemo3;
    }

    public String getSrcMemo4() {
        return srcMemo4;
    }

    public void setSrcMemo4(String srcMemo4) {
        this.srcMemo4 = srcMemo4;
    }

    public String getSrcMemo5() {
        return srcMemo5;
    }

    public void setSrcMemo5(String srcMemo5) {
        this.srcMemo5 = srcMemo5;
    }

    public String getSrcMemo6() {
        return srcMemo6;
    }

    public void setSrcMemo6(String srcMemo6) {
        this.srcMemo6 = srcMemo6;
    }

    public String getSrcId4() {
        return srcId4;
    }

    public void setSrcId4(String srcId4) {
        this.srcId4 = srcId4;
    }

    public String getFlg4Name() {
        return flg4Name;
    }

    public void setFlg4Name(String flg4Name) {
        this.flg4Name = flg4Name;
    }

}
