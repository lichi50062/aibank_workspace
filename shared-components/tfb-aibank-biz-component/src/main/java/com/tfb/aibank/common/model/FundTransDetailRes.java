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
package com.tfb.aibank.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

// @formatter:off
/**
 * @(#)FundTransDetailRes.java
 * 
 * <p>Description:NFEE082/AFEE082 基金交易明細查詢-明細 下行資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/29, Charlie Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FundTransDetailRes implements Serializable {
    /** 生效日期 */
    private Date trscDate;
    /** 憑證號碼 */
    private String eviNum;
    /** 基金代號 */
    private String fundNO;
    /** 交易幣別 */
    private String curcode;
    /** 申購金額 */
    private BigDecimal trustAmt;
    /** 扣款金額 */
    private BigDecimal payAmt;
    /** 手續費 */
    private BigDecimal fee;
    /** 單位數 */
    private BigDecimal untNum;
    /** 淨值 */
    private BigDecimal netValue;
    /** 匯率 */
    private BigDecimal exRate;
    /** 信託帳號 */
    private String trustAcct;
    /** 扣款帳號 */
    private String payAcct;
    /** 專案編號 */
    private String timeDepositPrjCd;
    /** 套餐編號 */
    private String fundPackageNo;
    /** 基金幣別 */
    private String fundCurcode;
    /** 申購金額(原幣) */
    private BigDecimal curAmt;
    /** 套餐代號 */
    private String fundPackage;
    /** 本次扣款金額(臺幣) */
    private BigDecimal trscAmtNT;
    /** 台幣兌換匯率 */
    private BigDecimal tWDExRate;
    /** 換匯申購 */
    private String chgPurchase;
    /** 約定扣款日期1 */
    private Date payDate1;
    /** 約定扣款日期2 */
    private Date payDate2;
    /** 約定扣款日期3 */
    private Date payDate3;
    /** 約定扣款日期4 */
    private Date payDate4;
    /** 約定扣款日期5 */
    private Date payDate5;
    /** 約定扣款日期6 */
    private Date payDate6;
    /** 約定申購金額1 */
    private BigDecimal payAmt01;
    /** 約定申購金額2 */
    private BigDecimal payAmt02;
    /** 約定申購金額3 */
    private BigDecimal payAmt03;
    /** 約定申購金額4 */
    private BigDecimal payAmt04;
    /** 約定申購金額5 */
    private BigDecimal payAmt05;
    /** 約定申購金額6 */
    private BigDecimal payAmt06;
    /** 手續費1 */
    private BigDecimal fee1;
    /** 手續費2 */
    private BigDecimal fee2;
    /** 手續費3 */
    private BigDecimal fee3;
    /** 手續費4 */
    private BigDecimal fee4;
    /** 手續費5 */
    private BigDecimal fee5;
    /** 手續費6 */
    private BigDecimal fee6;
    /** 轉出基金代號 */
    private String outFundNO;
    /** 轉出基金名稱 */
    private String outFundName;
    /** 轉入基金代號 */
    private String inFundNO;
    /** 轉入基金名稱 */
    private String inFundName;
    /** 轉出交易幣別 */
    private String outCurCode;
    /** 轉入交易幣別 */
    private String inCurcode;
    /** 轉出信託金額 */
    private BigDecimal outTrustAmt;
    /** 轉入信託金額 */
    private BigDecimal inTrustAmt;
    /** 轉換手續費 */
    private BigDecimal transferFee;
    /** 短線手續費 */
    private BigDecimal shortFee;
    /** 轉出單位數 */
    private BigDecimal outUntNum;
    /** 轉入單位數 */
    private BigDecimal inUntNum;
    /** 轉出淨值 */
    private BigDecimal outNetValue;
    /** 轉入淨值 */
    private BigDecimal inNetValue;
    /** 分配日 */
    private Date allocateDate;
    /** 轉出基金幣別 */
    private String outFundCurCode;
    /** 轉入基金幣別 */
    private String inFundCurcode;
    /** 贖回金額 */
    private BigDecimal redeemAmt;
    /** 入帳金額 */
    private BigDecimal rcvAmt;
    /** 贖回手續費 */
    private BigDecimal redeemFee;
    /** 信託管理費 */
    private BigDecimal magtFee;
    /** 入帳帳號 */
    private String rcvAcct;
    /** 報酬率正負 */
    private String signDigit;
    /** 報酬率 */
    private BigDecimal returnValue;
    /** 再申購憑證號碼 */
    private String reEviNum;
    /** 再申購基金代號 */
    private String reFundNO;
    /** 再申購基金名稱 */
    private String reFundName;
    /** 再申購交易幣別 */
    private String reCurcode;
    /** 再申購申購金額 */
    private BigDecimal reTrustAmt;
    /** 再申購扣款金額 */
    private BigDecimal rePayAmt;
    /** 再申購手續費 */
    private BigDecimal reFee;
    /** 再申購單位數 */
    private BigDecimal reUntNum;
    /** 再申購淨值 */
    private BigDecimal reNetValue;
    /** 再申購匯率 */
    private BigDecimal reExRate;
    /** 再申購信託帳號 */
    private String reTrustAcct;
    /** 再申購扣款帳號 */
    private String rePayAcct;
    /** 再申購金額(原幣) */
    private BigDecimal reCurAmt;
    /** 再申購基金幣別 */
    private String reFundCurcode;
    /** 提前買回費 */
    private BigDecimal advFee;
    /** 報酬率正負(含息) */
    private String interestSignDigit;
    /** 報酬率(含息) */
    private BigDecimal interestReturn;
    /** 遞延手續費 */
    private BigDecimal deferreFee;
    /** 報酬率正負(含轉換前息) */
    private String befCashReturnSign;
    /** 報酬率(含轉換前息) */
    private BigDecimal befCashReturn;
    /** 每單位分配金額 */
    private BigDecimal amt;
    /** 給付淨額 */
    private BigDecimal rcvNetAmt;

    public Date getTrscDate() {
        return trscDate;
    }

    public void setTrscDate(Date trscDate) {
        this.trscDate = trscDate;
    }

    public String getEviNum() {
        return eviNum;
    }

    public void setEviNum(String eviNum) {
        this.eviNum = eviNum;
    }

    public String getFundNO() {
        return fundNO;
    }

    public void setFundNO(String fundNO) {
        this.fundNO = fundNO;
    }

    public String getCurcode() {
        return curcode;
    }

    public void setCurcode(String curcode) {
        this.curcode = curcode;
    }

    public BigDecimal getTrustAmt() {
        return trustAmt;
    }

    public void setTrustAmt(BigDecimal trustAmt) {
        this.trustAmt = trustAmt;
    }

    public BigDecimal getPayAmt() {
        return payAmt;
    }

    public void setPayAmt(BigDecimal payAmt) {
        this.payAmt = payAmt;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getUntNum() {
        return untNum;
    }

    public void setUntNum(BigDecimal untNum) {
        this.untNum = untNum;
    }

    public BigDecimal getNetValue() {
        return netValue;
    }

    public void setNetValue(BigDecimal netValue) {
        this.netValue = netValue;
    }

    public BigDecimal getExRate() {
        return exRate;
    }

    public void setExRate(BigDecimal exRate) {
        this.exRate = exRate;
    }

    public String getTrustAcct() {
        return trustAcct;
    }

    public void setTrustAcct(String trustAcct) {
        this.trustAcct = trustAcct;
    }

    public String getPayAcct() {
        return payAcct;
    }

    public void setPayAcct(String payAcct) {
        this.payAcct = payAcct;
    }

    public String getTimeDepositPrjCd() {
        return timeDepositPrjCd;
    }

    public void setTimeDepositPrjCd(String timeDepositPrjCd) {
        this.timeDepositPrjCd = timeDepositPrjCd;
    }

    public String getFundPackageNo() {
        return fundPackageNo;
    }

    public void setFundPackageNo(String fundPackageNo) {
        this.fundPackageNo = fundPackageNo;
    }

    public String getFundCurcode() {
        return fundCurcode;
    }

    public void setFundCurcode(String fundCurcode) {
        this.fundCurcode = fundCurcode;
    }

    public BigDecimal getCurAmt() {
        return curAmt;
    }

    public void setCurAmt(BigDecimal curAmt) {
        this.curAmt = curAmt;
    }

    public String getFundPackage() {
        return fundPackage;
    }

    public void setFundPackage(String fundPackage) {
        this.fundPackage = fundPackage;
    }

    public BigDecimal getTrscAmtNT() {
        return trscAmtNT;
    }

    public void setTrscAmtNT(BigDecimal trscAmtNT) {
        this.trscAmtNT = trscAmtNT;
    }

    public BigDecimal getTWDExRate() {
        return tWDExRate;
    }

    public void setTWDExRate(BigDecimal tWDExRate) {
        this.tWDExRate = tWDExRate;
    }

    public String getChgPurchase() {
        return chgPurchase;
    }

    public void setChgPurchase(String chgPurchase) {
        this.chgPurchase = chgPurchase;
    }

    public Date getPayDate1() {
        return payDate1;
    }

    public void setPayDate1(Date payDate1) {
        this.payDate1 = payDate1;
    }

    public Date getPayDate2() {
        return payDate2;
    }

    public void setPayDate2(Date payDate2) {
        this.payDate2 = payDate2;
    }

    public Date getPayDate3() {
        return payDate3;
    }

    public void setPayDate3(Date payDate3) {
        this.payDate3 = payDate3;
    }

    public Date getPayDate4() {
        return payDate4;
    }

    public void setPayDate4(Date payDate4) {
        this.payDate4 = payDate4;
    }

    public Date getPayDate5() {
        return payDate5;
    }

    public void setPayDate5(Date payDate5) {
        this.payDate5 = payDate5;
    }

    public Date getPayDate6() {
        return payDate6;
    }

    public void setPayDate6(Date payDate6) {
        this.payDate6 = payDate6;
    }

    public BigDecimal getPayAmt01() {
        return payAmt01;
    }

    public void setPayAmt01(BigDecimal payAmt01) {
        this.payAmt01 = payAmt01;
    }

    public BigDecimal getPayAmt02() {
        return payAmt02;
    }

    public void setPayAmt02(BigDecimal payAmt02) {
        this.payAmt02 = payAmt02;
    }

    public BigDecimal getPayAmt03() {
        return payAmt03;
    }

    public void setPayAmt03(BigDecimal payAmt03) {
        this.payAmt03 = payAmt03;
    }

    public BigDecimal getPayAmt04() {
        return payAmt04;
    }

    public void setPayAmt04(BigDecimal payAmt04) {
        this.payAmt04 = payAmt04;
    }

    public BigDecimal getPayAmt05() {
        return payAmt05;
    }

    public void setPayAmt05(BigDecimal payAmt05) {
        this.payAmt05 = payAmt05;
    }

    public BigDecimal getPayAmt06() {
        return payAmt06;
    }

    public void setPayAmt06(BigDecimal payAmt06) {
        this.payAmt06 = payAmt06;
    }

    public BigDecimal getFee1() {
        return fee1;
    }

    public void setFee1(BigDecimal fee1) {
        this.fee1 = fee1;
    }

    public BigDecimal getFee2() {
        return fee2;
    }

    public void setFee2(BigDecimal fee2) {
        this.fee2 = fee2;
    }

    public BigDecimal getFee3() {
        return fee3;
    }

    public void setFee3(BigDecimal fee3) {
        this.fee3 = fee3;
    }

    public BigDecimal getFee4() {
        return fee4;
    }

    public void setFee4(BigDecimal fee4) {
        this.fee4 = fee4;
    }

    public BigDecimal getFee5() {
        return fee5;
    }

    public void setFee5(BigDecimal fee5) {
        this.fee5 = fee5;
    }

    public BigDecimal getFee6() {
        return fee6;
    }

    public void setFee6(BigDecimal fee6) {
        this.fee6 = fee6;
    }

    public String getOutFundNO() {
        return outFundNO;
    }

    public void setOutFundNO(String outFundNO) {
        this.outFundNO = outFundNO;
    }

    public String getOutFundName() {
        return outFundName;
    }

    public void setOutFundName(String outFundName) {
        this.outFundName = outFundName;
    }

    public String getInFundNO() {
        return inFundNO;
    }

    public void setInFundNO(String inFundNO) {
        this.inFundNO = inFundNO;
    }

    public String getInFundName() {
        return inFundName;
    }

    public void setInFundName(String inFundName) {
        this.inFundName = inFundName;
    }

    public String getOutCurCode() {
        return outCurCode;
    }

    public void setOutCurCode(String outCurCode) {
        this.outCurCode = outCurCode;
    }

    public String getInCurcode() {
        return inCurcode;
    }

    public void setInCurcode(String inCurcode) {
        this.inCurcode = inCurcode;
    }

    public BigDecimal getOutTrustAmt() {
        return outTrustAmt;
    }

    public void setOutTrustAmt(BigDecimal outTrustAmt) {
        this.outTrustAmt = outTrustAmt;
    }

    public BigDecimal getInTrustAmt() {
        return inTrustAmt;
    }

    public void setInTrustAmt(BigDecimal inTrustAmt) {
        this.inTrustAmt = inTrustAmt;
    }

    public BigDecimal getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(BigDecimal transferFee) {
        this.transferFee = transferFee;
    }

    public BigDecimal getShortFee() {
        return shortFee;
    }

    public void setShortFee(BigDecimal shortFee) {
        this.shortFee = shortFee;
    }

    public BigDecimal getOutUntNum() {
        return outUntNum;
    }

    public void setOutUntNum(BigDecimal outUntNum) {
        this.outUntNum = outUntNum;
    }

    public BigDecimal getInUntNum() {
        return inUntNum;
    }

    public void setInUntNum(BigDecimal inUntNum) {
        this.inUntNum = inUntNum;
    }

    public BigDecimal getOutNetValue() {
        return outNetValue;
    }

    public void setOutNetValue(BigDecimal outNetValue) {
        this.outNetValue = outNetValue;
    }

    public BigDecimal getInNetValue() {
        return inNetValue;
    }

    public void setInNetValue(BigDecimal inNetValue) {
        this.inNetValue = inNetValue;
    }

    public Date getAllocateDate() {
        return allocateDate;
    }

    public void setAllocateDate(Date allocateDate) {
        this.allocateDate = allocateDate;
    }

    public String getOutFundCurCode() {
        return outFundCurCode;
    }

    public void setOutFundCurCode(String outFundCurCode) {
        this.outFundCurCode = outFundCurCode;
    }

    public String getInFundCurcode() {
        return inFundCurcode;
    }

    public void setInFundCurcode(String inFundCurcode) {
        this.inFundCurcode = inFundCurcode;
    }

    public BigDecimal getRedeemAmt() {
        return redeemAmt;
    }

    public void setRedeemAmt(BigDecimal redeemAmt) {
        this.redeemAmt = redeemAmt;
    }

    public BigDecimal getRcvAmt() {
        return rcvAmt;
    }

    public void setRcvAmt(BigDecimal rcvAmt) {
        this.rcvAmt = rcvAmt;
    }

    public BigDecimal getRedeemFee() {
        return redeemFee;
    }

    public void setRedeemFee(BigDecimal redeemFee) {
        this.redeemFee = redeemFee;
    }

    public BigDecimal getMagtFee() {
        return magtFee;
    }

    public void setMagtFee(BigDecimal magtFee) {
        this.magtFee = magtFee;
    }

    public String getRcvAcct() {
        return rcvAcct;
    }

    public void setRcvAcct(String rcvAcct) {
        this.rcvAcct = rcvAcct;
    }

    public String getSignDigit() {
        return signDigit;
    }

    public void setSignDigit(String signDigit) {
        this.signDigit = signDigit;
    }

    public BigDecimal getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(BigDecimal returnValue) {
        this.returnValue = returnValue;
    }

    public String getReEviNum() {
        return reEviNum;
    }

    public void setReEviNum(String reEviNum) {
        this.reEviNum = reEviNum;
    }

    public String getReFundNO() {
        return reFundNO;
    }

    public void setReFundNO(String reFundNO) {
        this.reFundNO = reFundNO;
    }

    public String getReFundName() {
        return reFundName;
    }

    public void setReFundName(String reFundName) {
        this.reFundName = reFundName;
    }

    public String getReCurcode() {
        return reCurcode;
    }

    public void setReCurcode(String reCurcode) {
        this.reCurcode = reCurcode;
    }

    public BigDecimal getReTrustAmt() {
        return reTrustAmt;
    }

    public void setReTrustAmt(BigDecimal reTrustAmt) {
        this.reTrustAmt = reTrustAmt;
    }

    public BigDecimal getRePayAmt() {
        return rePayAmt;
    }

    public void setRePayAmt(BigDecimal rePayAmt) {
        this.rePayAmt = rePayAmt;
    }

    public BigDecimal getReFee() {
        return reFee;
    }

    public void setReFee(BigDecimal reFee) {
        this.reFee = reFee;
    }

    public BigDecimal getReUntNum() {
        return reUntNum;
    }

    public void setReUntNum(BigDecimal reUntNum) {
        this.reUntNum = reUntNum;
    }

    public BigDecimal getReNetValue() {
        return reNetValue;
    }

    public void setReNetValue(BigDecimal reNetValue) {
        this.reNetValue = reNetValue;
    }

    public BigDecimal getReExRate() {
        return reExRate;
    }

    public void setReExRate(BigDecimal reExRate) {
        this.reExRate = reExRate;
    }

    public String getReTrustAcct() {
        return reTrustAcct;
    }

    public void setReTrustAcct(String reTrustAcct) {
        this.reTrustAcct = reTrustAcct;
    }

    public String getRePayAcct() {
        return rePayAcct;
    }

    public void setRePayAcct(String rePayAcct) {
        this.rePayAcct = rePayAcct;
    }

    public BigDecimal getReCurAmt() {
        return reCurAmt;
    }

    public void setReCurAmt(BigDecimal reCurAmt) {
        this.reCurAmt = reCurAmt;
    }

    public String getReFundCurcode() {
        return reFundCurcode;
    }

    public void setReFundCurcode(String reFundCurcode) {
        this.reFundCurcode = reFundCurcode;
    }

    public BigDecimal getAdvFee() {
        return advFee;
    }

    public void setAdvFee(BigDecimal advFee) {
        this.advFee = advFee;
    }

    public String getInterestSignDigit() {
        return interestSignDigit;
    }

    public void setInterestSignDigit(String interestSignDigit) {
        this.interestSignDigit = interestSignDigit;
    }

    public BigDecimal getInterestReturn() {
        return interestReturn;
    }

    public void setInterestReturn(BigDecimal interestReturn) {
        this.interestReturn = interestReturn;
    }

    public BigDecimal getDeferreFee() {
        return deferreFee;
    }

    public void setDeferreFee(BigDecimal deferreFee) {
        this.deferreFee = deferreFee;
    }

    public String getBefCashReturnSign() {
        return befCashReturnSign;
    }

    public void setBefCashReturnSign(String befCashReturnSign) {
        this.befCashReturnSign = befCashReturnSign;
    }

    public BigDecimal getBefCashReturn() {
        return befCashReturn;
    }

    public void setBefCashReturn(BigDecimal befCashReturn) {
        this.befCashReturn = befCashReturn;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public BigDecimal getRcvNetAmt() {
        return rcvNetAmt;
    }

    public void setRcvNetAmt(BigDecimal rcvNetAmt) {
        this.rcvNetAmt = rcvNetAmt;
    }

}
