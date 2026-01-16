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
package com.tfb.aibank.chl.general.resource.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Locale;

import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)StructuredBO.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/23, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class StructuredBO implements Serializable {

    /**
     * <code>serialVersionUID</code> 的註解
     */
    private static final long serialVersionUID = 1L;

    private String category;
    private Locale locale;

    /** 產品名稱 */
    private String PRDNAM;
    /** 產品代碼 */
    private String PRDID;
    /** 交易序號、存單號碼 */
    private String IVTDNO;
    /** 投資本金 */
    private BigDecimal IVAMT2;
    /** 幣別 */
    private String CCY;
    /** 商品牌告利率 */
    private String DEPRAT;
    /** 起息日 */
    private String DEPSTR;
    /** 到期日 */
    private String DEPEND;
    /** 報價日 */
    private String PRDDTE;
    /** 參考報價 */
    private String SDAMT2;
    /** 累積現金配息 */
    private BigDecimal PLAMT;
    /** 收件行 */
    private String IVBRH;
    /** 定存帳號 */
    private String IVTDAC;

    /** 交易序號 */
    private String TranNo;
    /** 商品幣別 */
    private String CurCode;
    /** 相對幣別 */
    private String TrscCur;
    /** 交易日 */
    private String TranDate;
    /** 商品(預定)到期日 */
    private String EndDate;
    /** 投資金額 */
    private BigDecimal InvestAmt;
    /** 履約價 */
    private BigDecimal TradeAmt;
    /** 折合台幣參考金額 */
    private BigDecimal RefAmt;

    /** 訊息碼 */
    private String msgCode;
    /** 交易編號PK */
    private String TRANID;
    /** 交易日 */
    private String TRADEDATE;
    /** 參考即期價格 */
    private BigDecimal SPOTRATE;
    /** 比價日 */
    private String EXPIRYDATE;
    /** 到期金額 */
    private BigDecimal DELIVERYDATEAMOUNT;
    /** 市場評估日 */
    private String MARKETEVALATIONDATE;
    /** 商品幣別 */
    private String CURRENCY;
    /** 到期日 */
    private String DELIVERYDATE;
    /** 履約價 */
    private BigDecimal STRIKE;
    /** 比價日即期價 */
    private BigDecimal EXPIRYDATESPOTRATE;
    /** 利息金額 */
    private BigDecimal INTERESTAMOUNT;
    /** 與本金之百分比 */
    private String COMPARETOAMOUNT;
    /** 相對幣別 */
    private String MAPPINGCURRENCY;
    /** 交易金額 */
    private BigDecimal DCDAMOUNT;
    /** 產品收益率 */
    private BigDecimal YIELD;
    /** 幣轉狀態 */
    private String CURRENCYCHANGE;
    /** 總費用 */
    private BigDecimal TOTALFEE;
    /** 交易狀態 */
    private String TRADESTATUS;
    /** FORMAT_COD */
    private String LSFORM;
    /** 參考台幣價格 */
    private BigDecimal DCDAMOUNTNTD;
    /** 保值型 */
    private String BREAKEVENID;

    public String getPRDNAM() {
        return PRDNAM;
    }

    public void setPRDNAM(String pRDNAM) {
        PRDNAM = pRDNAM;
    }

    public String getPRDID() {
        return PRDID;
    }

    public void setPRDID(String pRDID) {
        PRDID = pRDID;
    }

    public String getIVTDNO() {
        return IVTDNO;
    }

    public void setIVTDNO(String iVTDNO) {
        IVTDNO = iVTDNO;
    }

    public BigDecimal getIVAMT2() {
        return IVAMT2;
    }

    public void setIVAMT2(BigDecimal iVAMT2) {
        IVAMT2 = iVAMT2;
    }

    public String getCCY() {
        return CCY;
    }

    public void setCCY(String cCY) {
        CCY = cCY;
    }

    public String getDEPRAT() {
        return DEPRAT;
    }

    public void setDEPRAT(String dEPRAT) {
        DEPRAT = dEPRAT;
    }

    public String getDEPSTR() {
        return DEPSTR;
    }

    public void setDEPSTR(String dEPSTR) {
        DEPSTR = dEPSTR;
    }

    public String getDEPEND() {
        return DEPEND;
    }

    public void setDEPEND(String dEPEND) {
        DEPEND = dEPEND;
    }

    public String getPRDDTE() {
        return PRDDTE;
    }

    public void setPRDDTE(String pRDDTE) {
        PRDDTE = pRDDTE;
    }

    public String getSDAMT2() {
        return SDAMT2;
    }

    public void setSDAMT2(String sDAMT2) {
        SDAMT2 = sDAMT2;
    }

    public BigDecimal getPLAMT() {
        return PLAMT;
    }

    public void setPLAMT(BigDecimal pLAMT) {
        PLAMT = pLAMT;
    }

    public String getIVBRH() {
        return IVBRH;
    }

    public void setIVBRH(String iVBRH) {
        IVBRH = iVBRH;
    }

    public String getIVTDAC() {
        return IVTDAC;
    }

    public void setIVTDAC(String iVTDAC) {
        IVTDAC = iVTDAC;
    }

    public String getTranNo() {
        return TranNo;
    }

    public void setTranNo(String tranNo) {
        TranNo = tranNo;
    }

    public String getCurCode() {
        return CurCode;
    }

    public void setCurCode(String curCode) {
        CurCode = curCode;
    }

    public String getTrscCur() {
        return TrscCur;
    }

    public void setTrscCur(String trscCur) {
        TrscCur = trscCur;
    }

    public String getTranDate() {
        return TranDate;
    }

    public void setTranDate(String tranDate) {
        TranDate = tranDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public BigDecimal getInvestAmt() {
        return InvestAmt;
    }

    public void setInvestAmt(BigDecimal investAmt) {
        InvestAmt = investAmt;
    }

    public BigDecimal getTradeAmt() {
        return TradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        TradeAmt = tradeAmt;
    }

    public BigDecimal getRefAmt() {
        return RefAmt;
    }

    public void setRefAmt(BigDecimal refAmt) {
        RefAmt = refAmt;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getTRANID() {
        return TRANID;
    }

    public void setTRANID(String tRANID) {
        TRANID = tRANID;
    }

    public String getTRADEDATE() {
        return TRADEDATE;
    }

    public void setTRADEDATE(String tRADEDATE) {
        TRADEDATE = tRADEDATE;
    }

    public BigDecimal getSPOTRATE() {
        return SPOTRATE;
    }

    public void setSPOTRATE(BigDecimal sPOTRATE) {
        SPOTRATE = sPOTRATE;
    }

    public String getEXPIRYDATE() {
        return EXPIRYDATE;
    }

    public void setEXPIRYDATE(String eXPIRYDATE) {
        EXPIRYDATE = eXPIRYDATE;
    }

    public BigDecimal getDELIVERYDATEAMOUNT() {
        return DELIVERYDATEAMOUNT;
    }

    public void setDELIVERYDATEAMOUNT(BigDecimal dELIVERYDATEAMOUNT) {
        DELIVERYDATEAMOUNT = dELIVERYDATEAMOUNT;
    }

    public String getMARKETEVALATIONDATE() {
        return MARKETEVALATIONDATE;
    }

    public void setMARKETEVALATIONDATE(String mARKETEVALATIONDATE) {
        MARKETEVALATIONDATE = mARKETEVALATIONDATE;
    }

    public String getCURRENCY() {
        return CURRENCY;
    }

    public void setCURRENCY(String cURRENCY) {
        CURRENCY = cURRENCY;
    }

    public String getDELIVERYDATE() {
        return DELIVERYDATE;
    }

    public void setDELIVERYDATE(String dELIVERYDATE) {
        DELIVERYDATE = dELIVERYDATE;
    }

    public BigDecimal getSTRIKE() {
        return STRIKE;
    }

    public void setSTRIKE(BigDecimal sTRIKE) {
        STRIKE = sTRIKE;
    }

    public BigDecimal getEXPIRYDATESPOTRATE() {
        return EXPIRYDATESPOTRATE;
    }

    public void setEXPIRYDATESPOTRATE(BigDecimal eXPIRYDATESPOTRATE) {
        EXPIRYDATESPOTRATE = eXPIRYDATESPOTRATE;
    }

    public BigDecimal getINTERESTAMOUNT() {
        return INTERESTAMOUNT;
    }

    public void setINTERESTAMOUNT(BigDecimal iNTERESTAMOUNT) {
        INTERESTAMOUNT = iNTERESTAMOUNT;
    }

    public String getCOMPARETOAMOUNT() {
        return COMPARETOAMOUNT;
    }

    public void setCOMPARETOAMOUNT(String cOMPARETOAMOUNT) {
        COMPARETOAMOUNT = cOMPARETOAMOUNT;
    }

    public String getMAPPINGCURRENCY() {
        return MAPPINGCURRENCY;
    }

    public void setMAPPINGCURRENCY(String mAPPINGCURRENCY) {
        MAPPINGCURRENCY = mAPPINGCURRENCY;
    }

    public BigDecimal getDCDAMOUNT() {
        return DCDAMOUNT;
    }

    public void setDCDAMOUNT(BigDecimal dCDAMOUNT) {
        DCDAMOUNT = dCDAMOUNT;
    }

    public BigDecimal getYIELD() {
        return YIELD;
    }

    public void setYIELD(BigDecimal yIELD) {
        YIELD = yIELD;
    }

    public String getCURRENCYCHANGE() {
        return CURRENCYCHANGE;
    }

    public void setCURRENCYCHANGE(String cURRENCYCHANGE) {
        CURRENCYCHANGE = cURRENCYCHANGE;
    }

    public BigDecimal getTOTALFEE() {
        return TOTALFEE;
    }

    public void setTOTALFEE(BigDecimal tOTALFEE) {
        TOTALFEE = tOTALFEE;
    }

    public String getTRADESTATUS() {
        return TRADESTATUS;
    }

    public void setTRADESTATUS(String tRADESTATUS) {
        TRADESTATUS = tRADESTATUS;
    }

    public String getLSFORM() {
        return LSFORM;
    }

    public void setLSFORM(String lSFORM) {
        LSFORM = lSFORM;
    }

    public BigDecimal getDCDAMOUNTNTD() {
        return DCDAMOUNTNTD;
    }

    public void setDCDAMOUNTNTD(BigDecimal dCDAMOUNTNTD) {
        DCDAMOUNTNTD = dCDAMOUNTNTD;
    }

    public String getBREAKEVENID() {
        return BREAKEVENID;
    }

    public void setBREAKEVENID(String bREAKEVENID) {
        BREAKEVENID = bREAKEVENID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public BigDecimal getPieAmt() {
        if (StringUtils.equals(category, "SPWEBINQ")) {
            return this.RefAmt;
        }
        else if (StringUtils.equals(category, "SPWEBQ2")) {
            return this.RefAmt;
        }
        else if (StringUtils.equals(category, "BKDCD001")) {
            return this.DCDAMOUNTNTD;
        }
        else {
            return BigDecimal.ZERO;
        }
    }

    public String getPieLab() {
        if (StringUtils.equals(category, "SPWEBINQ")) {
            return this.PRDNAM;
        }
        else {
            return "";
        }
    }

}
