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

import java.math.BigDecimal;
import java.util.Date;

import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)FundReserve.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/02, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FundReserve {

    // 信託型態 對應編碼
    public static final String TRUST_KIND_SINGLE = "TRUST_KIND_SINGLE"; // 單筆
    public static final String TRUST_KIND_FIXED_99 = "TRUST_KIND_FIXED_99"; // 定期定額(Fund久久)
    public static final String TRUST_KIND_NOTFIXED_99 = "TRUST_KIND_NOTFIXED_99"; // 定期不定額(Fund久久)
    public static final String TRUST_KIND_FIXED_EASY = "TRUST_KIND_FIXED_EASY"; // 定期定額(Fund心投)
    public static final String TRUST_KIND_NOTFIXED_EASY = "TRUST_KIND_NOTFIXED_EASY"; // 定期不定額(Fund心投)
    public static final String TRUST_KIND_FIXED = "TRUST_KIND_FIXED"; // 定期定額
    public static final String TRUST_KIND_NOTFIXED = "TRUST_KIND_NOTFIXED"; // 定期不定額
    public static final String TRUST_KIND_FUND_PACKAGE = "TRUST_KIND_FUND_PACKAGE"; // 基金套餐
    public static final String TRUST_KIND_DEPOSIT_TO_FUND = "TRUST_KIND_DEPOSIT_TO_FUND"; // 定存轉基金
    public static final String TRUST_KIND_NOSUBCODE = "TRUST_KIND_NOSUBCODE"; // SUBCODE無值

    private boolean printMode; // 是否為列印模式

    // Vo 欄位 for 電文資料 轉入此 Vo Start---------------------------------------------------

    /**
     * 0001、0024(單筆申購)、0002(定期定額/定期不定額申購)、0003、0025(轉換)、 0004、0026(贖回)、0005(變更扣款日期)、0006(變更扣款金額)、 0007(暫停扣款)、0008(恢復扣款)、0009(變更投資標的)、 0010(變更扣款帳號)、0013(基金套餐定期定額申購)、 0014(基金套餐變更扣款日期)、0015(基金套餐變更扣款金額)、 0016(基金套餐暫停扣款)、0017(基金套餐恢復扣款)、
     * 0018(基金套餐變更投資套餐)、0019(基金套餐變更扣款帳號)、 0021(變更受益入帳帳號)、0022、0027(變更約定條件)、 0023(基金套餐變更約定條件)、0090(再申購資訊)
     */
    private String hfmtid;

    /** 生效日期 */
    private Date trscDate;

    /** 憑証編號 */
    private String eviNum;

    /** 設定日期 */
    private Date markedDate;

    /** 基金名稱 */
    private String fundName;

    /** 基金代號 */
    private String fundNo;

    /** 申購金額 */
    private BigDecimal trustAmt;

    /** 幣別 */
    private String curCode;

    /** 手續費 */
    private BigDecimal fee;

    /**
     * 信託種類
     */
    private String trustType;

    /** 扣款帳號 */
    private String payAcctId;

    /**
     * 處理結果 U：未處理 S：已成功
     */
    private String abendCode;

    /** 預約序號 */
    private String prereqSrl;

    /** 手續費率 */
    private BigDecimal feeRate;
    /** 手續費率 */
    private BigDecimal feeRateM;
    /** 手續費率 */
    private BigDecimal feeRateH;

    /** 優惠專案代碼 */
    private String beneCode;

    /**
     * 停損正負號 未設定時的欄位內容=X
     */
    private String stopSign;

    /** 停損點 */
    private BigDecimal stopLoss;

    /**
     * 滿足正負號 未設定時的欄位內容=X
     */
    private String satisSign;

    /** 滿足點 */
    private BigDecimal satisFied;

    /** 每月扣款日一 */
    private String perMonthPayDate01;

    /** 每月扣款日二 */
    private String perMonthPayDate02;

    /** 每月扣款日三 */
    private String perMonthPayDate03;

    /** 每月扣款日四 */
    private String perMonthPayDate04;

    /** 每月扣款日五 */
    private String perMonthPayDate05;

    /** 每月扣款日六 */
    private String perMonthPayDate06;

    /**
     * 申購金額中 定期定額時為0 <>0.00&<>空值=定期不定額
     */
    private BigDecimal trustAmtM;

    /**
     * 申購金額高 定期定額時為0
     */
    private BigDecimal trustAmtH;

    /**
     * 手續費中 定期定額時為0
     */
    private BigDecimal feeM;

    /**
     * 手續費高 定期定額時為0
     */
    private BigDecimal feeH;

    /**
     * 交易類別 空白:一般 Y: FUND久久
     */
    private String txType;

    /** 換匯申購 (Y/ ) */
    private String chgPurchase;

    /** 轉入基金名稱 */
    private String inFundName;

    /** 轉入基金代號 */
    private String inFundNo;

    /** 轉換單位數 */
    private BigDecimal transferUntNum;

    /** 剩餘單位數 */
    private BigDecimal leftUntNum;

    /** 原有單位數 */
    private BigDecimal originalUntNum;

    /**
     * 信託型態 1:單筆 2:定期定額 3:定期不定額 4:定存轉基金 5:基金套餐
     */
    private String subCode;

    /** 轉出交易幣別 */
    private String outCurCode;

    /** 轉入交易幣別 */
    private String inCurCode;

    /**
     * 是否為短線交易
     */
    private String shortTermFlg;

    /** 逾短線交易日期 */
    private Date shortTermDate;

    /** 原始申購日 */
    private Date applyDate;

    /** 短線單位數 */
    private BigDecimal shortTermUnit;

    /** 參考淨值日 */
    private Date referNetDt;

    /** 參考淨值 */
    private BigDecimal refNetValue;

    /** 短線費率% */
    private BigDecimal shortTermRate;

    /** 預估短線費 */
    private BigDecimal estShortTermFee;

    /** 預估短線費幣別 */
    private String estShortTermFeeCur;

    /**
     * 短線訊息提示 空白=原訊息 / Y=新訊息
     */
    private String msgAlert;

    /** 贖回單位數 */
    private BigDecimal recvUntNum;

    /** 贖回款入帳帳號 */
    private String inAcctId;

    /** 贖回手續費 */
    private BigDecimal backFee;

    /** 贖回手續費幣別 */
    private String backFeeCur;

    /** 贖回續扣 */
    private String callBack;

    /** 預估信託管理費 */
    private BigDecimal estimateMagtFee;

    /**
     * 預約狀態 1:贖回預約中 2:申購預約中 不顯示臨櫃再申購交易
     */
    private String revStatus;

    /** 再申購 */
    private String rePurchase;

    /** 再申購基金代號 */
    private String reFundNo;

    /** 再申購手續費率 */
    private BigDecimal reFeeRate;

    /** 憑証編號1 */
    private String eviNum1;

    /** 憑証編號2 */
    private String eviNum2;

    /** 憑証編號3 */
    private String eviNum3;

    /** 憑証編號4 */
    private String eviNum4;

    /** 憑証編號5 */
    private String eviNum5;

    /** 基金代號1 */
    private String fundNo1;

    /** 基金代號2 */
    private String fundNo2;

    /** 基金代號3 */
    private String fundNo3;

    /** 基金代號4 */
    private String fundNo4;

    /** 基金代號5 */
    private String fundNo5;

    /** 變更後基金名稱/基金套餐名稱 */
    private String exchangeFundName;

    /** 變更後基金代號/基金套餐代號 */
    private String exchangeFundNo;

    /** 變更後基金代號1 */
    private String exchangeFundNo1;

    /** 變更後基金代號2 */
    private String exchangeFundNo2;

    /** 變更後基金代號3 */
    private String exchangeFundNo3;

    /** 變更後基金代號4 */
    private String exchangeFundNo4;

    /** 變更後基金代號5 */
    private String exchangeFundNo5;

    /**
     * 變更後扣款金額
     */
    private BigDecimal exchangeAmt;

    /**
     * 變更後扣款金額中
     */
    private BigDecimal exchangeAmtM;

    /**
     * 變更後扣款金額高
     */
    private BigDecimal exchangeAmtH;

    /** 扣款日一 */
    private String payDate01;

    /** 扣款日二 */
    private String payDate02;

    /** 扣款日三 */
    private String payDate03;

    /** 扣款日四 */
    private String payDate04;

    /** 扣款日五 */
    private String payDate05;

    /** 扣款日六 */
    private String payDate06;

    /** 變更後扣款日一 */
    private String exchangePayDate01;

    /** 變更後扣款日二 */
    private String exchangePayDate02;

    /** 變更後扣款日三 */
    private String exchangePayDate03;

    /** 變更後扣款日四 */
    private String exchangePayDate04;

    /** 變更後扣款日五 */
    private String exchangePayDate05;

    /** 變更後扣款日六 */
    private String exchangePayDate06;

    /** 變更後扣款日七 */
    private String exchangePayDate07;

    /** 變更後扣款日八 */
    private String exchangePayDate08;

    /** 變更後扣款日九 */
    private String exchangePayDate09;

    /** 變更後扣款日十 */
    private String exchangePayDate10;

    /** 變更後扣款日十一 */
    private String exchangePayDate11;

    /** 變更後扣款日十二 */
    private String exchangePayDate12;

    /** 變更後扣款帳號 */
    private String exchangePayAcctId;

    /** 受益入帳帳號 */
    private String rcvAcctId;

    /** 變更後受益入帳帳號 */
    private String exchangeRcvAcctId;

    /**
     * 狀態 0:正常 1:暫停 2:非主標的
     */
    private String status;

    /**
     * 變更後狀態 0:正常 1:暫停 2:非主標的
     */
    private String exchangeStatus;

    /** 變更後換匯申購 (Y/ ) */
    private String exchangeChgPurchase;

    /** 扣款金額 */
    private BigDecimal amt;

    /** 扣款金額中 */
    private BigDecimal amtM;

    /** 扣款金額高 */
    private BigDecimal amtH;

    /** 再申購基金名稱 */
    private String reFundName;

    /** 變更後_交易幣別代碼 */
    private String afterCurCode;

    /** 變更後_手續費 */
    private BigDecimal afterExchangeFee;

    /** 變更後_手續費_中 */
    private BigDecimal afterExchangeFeeM;

    /** 變更後_手續費_高 */
    private BigDecimal afterExchangeFeeH;

    /** 專案編號 */
    private String timeDepositPrjCd;

    /** 基金套餐變更扣款日期 */
    private String fundPackageNo;

    /** 變更扣款金額 */
    private BigDecimal beforeExchangeAmt;

    /** 變更扣款金額 */
    private BigDecimal beforeExchangeAmtM;

    /** 變更扣款金額 */
    private BigDecimal beforeExchangeAmtH;

    /** 變更扣款金額 */
    private BigDecimal afterExchangeAmt;

    /** 變更扣款金額 */
    private BigDecimal afterExchangeAmtM;

    /** 變更扣款金額 */
    private BigDecimal afterExchangeAmtH;

    /** 變更投資標的 交易幣別代碼 */
    private String beforeExchangeCurCode;

    /** 變更投資標的 變更後_交易幣別代碼 */
    private String afterExchangeCurCode;

    /** 變更扣款帳號 */
    private String afterPayAcctId;

    /** 變更約定條件註記 */
    private String chngeFlag;

    /** 序號 */
    private Integer index;

    /** 目標到期型基金 */
    private BigDecimal advFeeRate;

    /** 遞延手續費 */
    private BigDecimal deferreFee;

    // Vo 欄位 End---------------------------------------------------

    /**
     * @return {@link #printMode}
     */
    public boolean isPrintMode() {
        return printMode;
    }

    /**
     * @param printMode
     *            {@link #printMode}
     */
    public void setPrintMode(boolean printMode) {
        this.printMode = printMode;
    }

    /**
     * @return {@link #hfmtid}
     */
    public String getHfmtid() {
        return hfmtid;
    }

    /**
     * @param hfmtid
     *            {@link #hfmtid}
     */
    public void setHfmtid(String hfmtid) {
        this.hfmtid = hfmtid;
    }

    /**
     * @return {@link #trscDate}
     */
    public Date getTrscDate() {
        return trscDate;
    }

    /**
     * @param trscDate
     *            {@link #trscDate}
     */
    public void setTrscDate(Date trscDate) {
        this.trscDate = trscDate;
    }

    /**
     * @return {@link #eviNum}
     */
    public String getEviNum() {
        return eviNum;
    }

    /**
     * @param eviNum
     *            {@link #eviNum}
     */
    public void setEviNum(String eviNum) {
        this.eviNum = eviNum;
    }

    /**
     * @return {@link #markedDate}
     */
    public Date getMarkedDate() {
        return markedDate;
    }

    /**
     * @param markedDate
     *            {@link #markedDate}
     */
    public void setMarkedDate(Date markedDate) {
        this.markedDate = markedDate;
    }

    /**
     * @return {@link #fundName}
     */
    public String getFundName() {
        return fundName;
    }

    /**
     * @param fundName
     *            {@link #fundName}
     */
    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    /**
     * @return {@link #fundNo}
     */
    public String getFundNo() {
        return fundNo;
    }

    /**
     * @param fundNo
     *            {@link #fundNo}
     */
    public void setFundNo(String fundNo) {
        this.fundNo = fundNo;
    }

    /**
     * @return {@link #trustAmt}
     */
    public BigDecimal getTrustAmt() {
        return trustAmt;
    }

    /**
     * @param trustAmt
     *            {@link #trustAmt}
     */
    public void setTrustAmt(BigDecimal trustAmt) {
        this.trustAmt = trustAmt;
    }

    /**
     * @return {@link #curCode}
     */
    public String getCurCode() {
        return curCode;
    }

    /**
     * @param curCode
     *            {@link #curCode}
     */
    public void setCurCode(String curCode) {
        this.curCode = curCode;
    }

    /**
     * @return {@link #fee}
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * @param fee
     *            {@link #fee}
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * @return {@link #trustType}
     */
    public String getTrustType() {
        return trustType;
    }

    /**
     * @param trustType
     *            {@link #trustType}
     */
    public void setTrustType(String trustType) {
        this.trustType = trustType;
    }

    /**
     * @return {@link #payAcctId}
     */
    public String getPayAcctId() {
        return payAcctId;
    }

    /**
     * @param payAcctId
     *            {@link #payAcctId}
     */
    public void setPayAcctId(String payAcctId) {
        this.payAcctId = payAcctId;
    }

    /**
     * @return {@link #abendCode}
     */
    public String getAbendCode() {
        return abendCode;
    }

    /**
     * @param abendCode
     *            {@link #abendCode}
     */
    public void setAbendCode(String abendCode) {
        this.abendCode = abendCode;
    }

    /**
     * @return {@link #prereqSrl}
     */
    public String getPrereqSrl() {
        return prereqSrl;
    }

    /**
     * @param prereqSrl
     *            {@link #prereqSrl}
     */
    public void setPrereqSrl(String prereqSrl) {
        this.prereqSrl = prereqSrl;
    }

    /**
     * @return {@link #feeRate}
     */
    public BigDecimal getFeeRate() {
        return feeRate;
    }

    /**
     * @param feeRate
     *            {@link #feeRate}
     */
    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    /**
     * @return {@link #feeRateM}
     */
    public BigDecimal getFeeRateM() {
        return feeRateM;
    }

    /**
     * @param feeRateM
     *            {@link #feeRateM}
     */
    public void setFeeRateM(BigDecimal feeRateM) {
        this.feeRateM = feeRateM;
    }

    /**
     * @return {@link #feeRateH}
     */
    public BigDecimal getFeeRateH() {
        return feeRateH;
    }

    /**
     * @param feeRateH
     *            {@link #feeRateH}
     */
    public void setFeeRateH(BigDecimal feeRateH) {
        this.feeRateH = feeRateH;
    }

    /**
     * @return {@link #beneCode}
     */
    public String getBeneCode() {
        return beneCode;
    }

    /**
     * @param beneCode
     *            {@link #beneCode}
     */
    public void setBeneCode(String beneCode) {
        this.beneCode = beneCode;
    }

    /**
     * @return {@link #stopSign}
     */
    public String getStopSign() {
        return stopSign;
    }

    /**
     * @param stopSign
     *            {@link #stopSign}
     */
    public void setStopSign(String stopSign) {
        this.stopSign = stopSign;
    }

    /**
     * @return {@link #stopLoss}
     */
    public BigDecimal getStopLoss() {
        return stopLoss;
    }

    /**
     * @param stopLoss
     *            {@link #stopLoss}
     */
    public void setStopLoss(BigDecimal stopLoss) {
        this.stopLoss = stopLoss;
    }

    /**
     * @return {@link #satisSign}
     */
    public String getSatisSign() {
        return satisSign;
    }

    /**
     * @param satisSign
     *            {@link #satisSign}
     */
    public void setSatisSign(String satisSign) {
        this.satisSign = satisSign;
    }

    /**
     * @return {@link #satisFied}
     */
    public BigDecimal getSatisFied() {
        return satisFied;
    }

    /**
     * @param satisFied
     *            {@link #satisFied}
     */
    public void setSatisFied(BigDecimal satisFied) {
        this.satisFied = satisFied;
    }

    /**
     * @return {@link #perMonthPayDate01}
     */
    public String getPerMonthPayDate01() {
        return perMonthPayDate01;
    }

    /**
     * @param perMonthPayDate01
     *            {@link #perMonthPayDate01}
     */
    public void setPerMonthPayDate01(String perMonthPayDate01) {
        this.perMonthPayDate01 = perMonthPayDate01;
    }

    /**
     * @return {@link #perMonthPayDate02}
     */
    public String getPerMonthPayDate02() {
        return perMonthPayDate02;
    }

    /**
     * @param perMonthPayDate02
     *            {@link #perMonthPayDate02}
     */
    public void setPerMonthPayDate02(String perMonthPayDate02) {
        this.perMonthPayDate02 = perMonthPayDate02;
    }

    /**
     * @return {@link #perMonthPayDate03}
     */
    public String getPerMonthPayDate03() {
        return perMonthPayDate03;
    }

    /**
     * @param perMonthPayDate03
     *            {@link #perMonthPayDate03}
     */
    public void setPerMonthPayDate03(String perMonthPayDate03) {
        this.perMonthPayDate03 = perMonthPayDate03;
    }

    /**
     * @return {@link #perMonthPayDate04}
     */
    public String getPerMonthPayDate04() {
        return perMonthPayDate04;
    }

    /**
     * @param perMonthPayDate04
     *            {@link #perMonthPayDate04}
     */
    public void setPerMonthPayDate04(String perMonthPayDate04) {
        this.perMonthPayDate04 = perMonthPayDate04;
    }

    /**
     * @return {@link #perMonthPayDate05}
     */
    public String getPerMonthPayDate05() {
        return perMonthPayDate05;
    }

    /**
     * @param perMonthPayDate05
     *            {@link #perMonthPayDate05}
     */
    public void setPerMonthPayDate05(String perMonthPayDate05) {
        this.perMonthPayDate05 = perMonthPayDate05;
    }

    /**
     * @return {@link #perMonthPayDate06}
     */
    public String getPerMonthPayDate06() {
        return perMonthPayDate06;
    }

    /**
     * @param perMonthPayDate06
     *            {@link #perMonthPayDate06}
     */
    public void setPerMonthPayDate06(String perMonthPayDate06) {
        this.perMonthPayDate06 = perMonthPayDate06;
    }

    /**
     * @return {@link #trustAmtM}
     */
    public BigDecimal getTrustAmtM() {
        return trustAmtM;
    }

    /**
     * @param trustAmtM
     *            {@link #trustAmtM}
     */
    public void setTrustAmtM(BigDecimal trustAmtM) {
        this.trustAmtM = trustAmtM;
    }

    /**
     * @return {@link #trustAmtH}
     */
    public BigDecimal getTrustAmtH() {
        return trustAmtH;
    }

    /**
     * @param trustAmtH
     *            {@link #trustAmtH}
     */
    public void setTrustAmtH(BigDecimal trustAmtH) {
        this.trustAmtH = trustAmtH;
    }

    /**
     * @return {@link #feeM}
     */
    public BigDecimal getFeeM() {
        return feeM;
    }

    /**
     * @param feeM
     *            {@link #feeM}
     */
    public void setFeeM(BigDecimal feeM) {
        this.feeM = feeM;
    }

    /**
     * @return {@link #feeH}
     */
    public BigDecimal getFeeH() {
        return feeH;
    }

    /**
     * @param feeH
     *            {@link #feeH}
     */
    public void setFeeH(BigDecimal feeH) {
        this.feeH = feeH;
    }

    /**
     * @return {@link #txType}
     */
    public String getTxType() {
        return txType;
    }

    /**
     * @param txType
     *            {@link #txType}
     */
    public void setTxType(String txType) {
        this.txType = txType;
    }

    /**
     * @return {@link #chgPurchase}
     */
    public String getChgPurchase() {
        return chgPurchase;
    }

    /**
     * @param chgPurchase
     *            {@link #chgPurchase}
     */
    public void setChgPurchase(String chgPurchase) {
        this.chgPurchase = chgPurchase;
    }

    /**
     * @return {@link #inFundName}
     */
    public String getInFundName() {
        return inFundName;
    }

    /**
     * @param inFundName
     *            {@link #inFundName}
     */
    public void setInFundName(String inFundName) {
        this.inFundName = inFundName;
    }

    /**
     * @return {@link #inFundNo}
     */
    public String getInFundNo() {
        return inFundNo;
    }

    /**
     * @param inFundNo
     *            {@link #inFundNo}
     */
    public void setInFundNo(String inFundNo) {
        this.inFundNo = inFundNo;
    }

    /**
     * @return {@link #transferUntNum}
     */
    public BigDecimal getTransferUntNum() {
        return transferUntNum;
    }

    /**
     * @param transferUntNum
     *            {@link #transferUntNum}
     */
    public void setTransferUntNum(BigDecimal transferUntNum) {
        this.transferUntNum = transferUntNum;
    }

    /**
     * @return {@link #leftUntNum}
     */
    public BigDecimal getLeftUntNum() {
        return leftUntNum;
    }

    /**
     * @param leftUntNum
     *            {@link #leftUntNum}
     */
    public void setLeftUntNum(BigDecimal leftUntNum) {
        this.leftUntNum = leftUntNum;
    }

    /**
     * @return {@link #originalUntNum}
     */
    public BigDecimal getOriginalUntNum() {
        return originalUntNum;
    }

    /**
     * @param originalUntNum
     *            {@link #originalUntNum}
     */
    public void setOriginalUntNum(BigDecimal originalUntNum) {
        this.originalUntNum = originalUntNum;
    }

    /**
     * @return {@link #subCode}
     */
    public String getSubCode() {
        return subCode;
    }

    /**
     * @param subCode
     *            {@link #subCode}
     */
    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    /**
     * @return {@link #outCurCode}
     */
    public String getOutCurCode() {
        return outCurCode;
    }

    /**
     * @param outCurCode
     *            {@link #outCurCode}
     */
    public void setOutCurCode(String outCurCode) {
        this.outCurCode = outCurCode;
    }

    /**
     * @return {@link #inCurCode}
     */
    public String getInCurCode() {
        return inCurCode;
    }

    /**
     * @param inCurCode
     *            {@link #inCurCode}
     */
    public void setInCurCode(String inCurCode) {
        this.inCurCode = inCurCode;
    }

    /**
     * @return {@link #shortTermFlg}
     */
    public String getShortTermFlg() {
        return shortTermFlg;
    }

    /**
     * @param shortTermFlg
     *            {@link #shortTermFlg}
     */
    public void setShortTermFlg(String shortTermFlg) {
        this.shortTermFlg = shortTermFlg;
    }

    /**
     * @return {@link #shortTermDate}
     */
    public Date getShortTermDate() {
        return shortTermDate;
    }

    /**
     * @param shortTermDate
     *            {@link #shortTermDate}
     */
    public void setShortTermDate(Date shortTermDate) {
        this.shortTermDate = shortTermDate;
    }

    /**
     * @return {@link #applyDate}
     */
    public Date getApplyDate() {
        return applyDate;
    }

    /**
     * @param applyDate
     *            {@link #applyDate}
     */
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    /**
     * @return {@link #shortTermUnit}
     */
    public BigDecimal getShortTermUnit() {
        return shortTermUnit;
    }

    /**
     * @param shortTermUnit
     *            {@link #shortTermUnit}
     */
    public void setShortTermUnit(BigDecimal shortTermUnit) {
        this.shortTermUnit = shortTermUnit;
    }

    /**
     * @return {@link #referNetDt}
     */
    public Date getReferNetDt() {
        return referNetDt;
    }

    /**
     * @param referNetDt
     *            {@link #referNetDt}
     */
    public void setReferNetDt(Date referNetDt) {
        this.referNetDt = referNetDt;
    }

    /**
     * @return {@link #refNetValue}
     */
    public BigDecimal getRefNetValue() {
        return refNetValue;
    }

    /**
     * @param refNetValue
     *            {@link #refNetValue}
     */
    public void setRefNetValue(BigDecimal refNetValue) {
        this.refNetValue = refNetValue;
    }

    /**
     * @return {@link #shortTermRate}
     */
    public BigDecimal getShortTermRate() {
        return shortTermRate;
    }

    /**
     * @param shortTermRate
     *            {@link #shortTermRate}
     */
    public void setShortTermRate(BigDecimal shortTermRate) {
        this.shortTermRate = shortTermRate;
    }

    /**
     * @return {@link #estShortTermFee}
     */
    public BigDecimal getEstShortTermFee() {
        return estShortTermFee;
    }

    /**
     * @param estShortTermFee
     *            {@link #estShortTermFee}
     */
    public void setEstShortTermFee(BigDecimal estShortTermFee) {
        this.estShortTermFee = estShortTermFee;
    }

    /**
     * @return {@link #estShortTermFeeCur}
     */
    public String getEstShortTermFeeCur() {
        return estShortTermFeeCur;
    }

    /**
     * @param estShortTermFeeCur
     *            {@link #estShortTermFeeCur}
     */
    public void setEstShortTermFeeCur(String estShortTermFeeCur) {
        this.estShortTermFeeCur = estShortTermFeeCur;
    }

    /**
     * @return {@link #msgAlert}
     */
    public String getMsgAlert() {
        return msgAlert;
    }

    /**
     * @param msgAlert
     *            {@link #msgAlert}
     */
    public void setMsgAlert(String msgAlert) {
        this.msgAlert = msgAlert;
    }

    /**
     * @return {@link #recvUntNum}
     */
    public BigDecimal getRecvUntNum() {
        return recvUntNum;
    }

    /**
     * @param recvUntNum
     *            {@link #recvUntNum}
     */
    public void setRecvUntNum(BigDecimal recvUntNum) {
        this.recvUntNum = recvUntNum;
    }

    /**
     * @return {@link #inAcctId}
     */
    public String getInAcctId() {
        return inAcctId;
    }

    /**
     * @param inAcctId
     *            {@link #inAcctId}
     */
    public void setInAcctId(String inAcctId) {
        this.inAcctId = inAcctId;
    }

    /**
     * @return {@link #backFee}
     */
    public BigDecimal getBackFee() {
        return backFee;
    }

    /**
     * @param backFee
     *            {@link #backFee}
     */
    public void setBackFee(BigDecimal backFee) {
        this.backFee = backFee;
    }

    /**
     * @return {@link #backFeeCur}
     */
    public String getBackFeeCur() {
        return backFeeCur;
    }

    /**
     * @param backFeeCur
     *            {@link #backFeeCur}
     */
    public void setBackFeeCur(String backFeeCur) {
        this.backFeeCur = backFeeCur;
    }

    /**
     * @return {@link #callBack}
     */
    public String getCallBack() {
        return callBack;
    }

    /**
     * @param callBack
     *            {@link #callBack}
     */
    public void setCallBack(String callBack) {
        this.callBack = callBack;
    }

    /**
     * @return {@link #estimateMagtFee}
     */
    public BigDecimal getEstimateMagtFee() {
        return estimateMagtFee;
    }

    /**
     * @param estimateMagtFee
     *            {@link #estimateMagtFee}
     */
    public void setEstimateMagtFee(BigDecimal estimateMagtFee) {
        this.estimateMagtFee = estimateMagtFee;
    }

    /**
     * @return {@link #revStatus}
     */
    public String getRevStatus() {
        return revStatus;
    }

    /**
     * @param revStatus
     *            {@link #revStatus}
     */
    public void setRevStatus(String revStatus) {
        this.revStatus = revStatus;
    }

    /**
     * @return {@link #rePurchase}
     */
    public String getRePurchase() {
        return rePurchase;
    }

    /**
     * @param rePurchase
     *            {@link #rePurchase}
     */
    public void setRePurchase(String rePurchase) {
        this.rePurchase = rePurchase;
    }

    /**
     * @return {@link #reFundNo}
     */
    public String getReFundNo() {
        return reFundNo;
    }

    /**
     * @param reFundNo
     *            {@link #reFundNo}
     */
    public void setReFundNo(String reFundNo) {
        this.reFundNo = reFundNo;
    }

    /**
     * @return {@link #reFeeRate}
     */
    public BigDecimal getReFeeRate() {
        return reFeeRate;
    }

    /**
     * @param reFeeRate
     *            {@link #reFeeRate}
     */
    public void setReFeeRate(BigDecimal reFeeRate) {
        this.reFeeRate = reFeeRate;
    }

    /**
     * @return {@link #eviNum1}
     */
    public String getEviNum1() {
        return eviNum1;
    }

    /**
     * @param eviNum1
     *            {@link #eviNum1}
     */
    public void setEviNum1(String eviNum1) {
        this.eviNum1 = eviNum1;
    }

    /**
     * @return {@link #eviNum2}
     */
    public String getEviNum2() {
        return eviNum2;
    }

    /**
     * @param eviNum2
     *            {@link #eviNum2}
     */
    public void setEviNum2(String eviNum2) {
        this.eviNum2 = eviNum2;
    }

    /**
     * @return {@link #eviNum3}
     */
    public String getEviNum3() {
        return eviNum3;
    }

    /**
     * @param eviNum3
     *            {@link #eviNum3}
     */
    public void setEviNum3(String eviNum3) {
        this.eviNum3 = eviNum3;
    }

    /**
     * @return {@link #eviNum4}
     */
    public String getEviNum4() {
        return eviNum4;
    }

    /**
     * @param eviNum4
     *            {@link #eviNum4}
     */
    public void setEviNum4(String eviNum4) {
        this.eviNum4 = eviNum4;
    }

    /**
     * @return {@link #eviNum5}
     */
    public String getEviNum5() {
        return eviNum5;
    }

    /**
     * @param eviNum5
     *            {@link #eviNum5}
     */
    public void setEviNum5(String eviNum5) {
        this.eviNum5 = eviNum5;
    }

    /**
     * @return {@link #fundNo1}
     */
    public String getFundNo1() {
        return fundNo1;
    }

    /**
     * @param fundNo1
     *            {@link #fundNo1}
     */
    public void setFundNo1(String fundNo1) {
        this.fundNo1 = fundNo1;
    }

    /**
     * @return {@link #fundNo2}
     */
    public String getFundNo2() {
        return fundNo2;
    }

    /**
     * @param fundNo2
     *            {@link #fundNo2}
     */
    public void setFundNo2(String fundNo2) {
        this.fundNo2 = fundNo2;
    }

    /**
     * @return {@link #fundNo3}
     */
    public String getFundNo3() {
        return fundNo3;
    }

    /**
     * @param fundNo3
     *            {@link #fundNo3}
     */
    public void setFundNo3(String fundNo3) {
        this.fundNo3 = fundNo3;
    }

    /**
     * @return {@link #fundNo4}
     */
    public String getFundNo4() {
        return fundNo4;
    }

    /**
     * @param fundNo4
     *            {@link #fundNo4}
     */
    public void setFundNo4(String fundNo4) {
        this.fundNo4 = fundNo4;
    }

    /**
     * @return {@link #fundNo5}
     */
    public String getFundNo5() {
        return fundNo5;
    }

    /**
     * @param fundNo5
     *            {@link #fundNo5}
     */
    public void setFundNo5(String fundNo5) {
        this.fundNo5 = fundNo5;
    }

    /**
     * @return {@link #exchangeFundName}
     */
    public String getExchangeFundName() {
        return exchangeFundName;
    }

    /**
     * @param exchangeFundName
     *            {@link #exchangeFundName}
     */
    public void setExchangeFundName(String exchangeFundName) {
        this.exchangeFundName = exchangeFundName;
    }

    /**
     * @return {@link #exchangeFundNo}
     */
    public String getExchangeFundNo() {
        return exchangeFundNo;
    }

    /**
     * @param exchangeFundNo
     *            {@link #exchangeFundNo}
     */
    public void setExchangeFundNo(String exchangeFundNo) {
        this.exchangeFundNo = exchangeFundNo;
    }

    /**
     * @return {@link #exchangeFundNo1}
     */
    public String getExchangeFundNo1() {
        return exchangeFundNo1;
    }

    /**
     * @param exchangeFundNo1
     *            {@link #exchangeFundNo1}
     */
    public void setExchangeFundNo1(String exchangeFundNo1) {
        this.exchangeFundNo1 = exchangeFundNo1;
    }

    /**
     * @return {@link #exchangeFundNo2}
     */
    public String getExchangeFundNo2() {
        return exchangeFundNo2;
    }

    /**
     * @param exchangeFundNo2
     *            {@link #exchangeFundNo2}
     */
    public void setExchangeFundNo2(String exchangeFundNo2) {
        this.exchangeFundNo2 = exchangeFundNo2;
    }

    /**
     * @return {@link #exchangeFundNo3}
     */
    public String getExchangeFundNo3() {
        return exchangeFundNo3;
    }

    /**
     * @param exchangeFundNo3
     *            {@link #exchangeFundNo3}
     */
    public void setExchangeFundNo3(String exchangeFundNo3) {
        this.exchangeFundNo3 = exchangeFundNo3;
    }

    /**
     * @return {@link #exchangeFundNo4}
     */
    public String getExchangeFundNo4() {
        return exchangeFundNo4;
    }

    /**
     * @param exchangeFundNo4
     *            {@link #exchangeFundNo4}
     */
    public void setExchangeFundNo4(String exchangeFundNo4) {
        this.exchangeFundNo4 = exchangeFundNo4;
    }

    /**
     * @return {@link #exchangeFundNo5}
     */
    public String getExchangeFundNo5() {
        return exchangeFundNo5;
    }

    /**
     * @param exchangeFundNo5
     *            {@link #exchangeFundNo5}
     */
    public void setExchangeFundNo5(String exchangeFundNo5) {
        this.exchangeFundNo5 = exchangeFundNo5;
    }

    /**
     * @return {@link #exchangeAmt}
     */
    public BigDecimal getExchangeAmt() {
        return exchangeAmt;
    }

    /**
     * @param exchangeAmt
     *            {@link #exchangeAmt}
     */
    public void setExchangeAmt(BigDecimal exchangeAmt) {
        this.exchangeAmt = exchangeAmt;
    }

    /**
     * @return {@link #exchangeAmtM}
     */
    public BigDecimal getExchangeAmtM() {
        return exchangeAmtM;
    }

    /**
     * @param exchangeAmtM
     *            {@link #exchangeAmtM}
     */
    public void setExchangeAmtM(BigDecimal exchangeAmtM) {
        this.exchangeAmtM = exchangeAmtM;
    }

    /**
     * @return {@link #exchangeAmtH}
     */
    public BigDecimal getExchangeAmtH() {
        return exchangeAmtH;
    }

    /**
     * @param exchangeAmtH
     *            {@link #exchangeAmtH}
     */
    public void setExchangeAmtH(BigDecimal exchangeAmtH) {
        this.exchangeAmtH = exchangeAmtH;
    }

    /**
     * @return {@link #payDate01}
     */
    public String getPayDate01() {
        return payDate01;
    }

    /**
     * @param payDate01
     *            {@link #payDate01}
     */
    public void setPayDate01(String payDate01) {
        this.payDate01 = payDate01;
    }

    /**
     * @return {@link #payDate02}
     */
    public String getPayDate02() {
        return payDate02;
    }

    /**
     * @param payDate02
     *            {@link #payDate02}
     */
    public void setPayDate02(String payDate02) {
        this.payDate02 = payDate02;
    }

    /**
     * @return {@link #payDate03}
     */
    public String getPayDate03() {
        return payDate03;
    }

    /**
     * @param payDate03
     *            {@link #payDate03}
     */
    public void setPayDate03(String payDate03) {
        this.payDate03 = payDate03;
    }

    /**
     * @return {@link #payDate04}
     */
    public String getPayDate04() {
        return payDate04;
    }

    /**
     * @param payDate04
     *            {@link #payDate04}
     */
    public void setPayDate04(String payDate04) {
        this.payDate04 = payDate04;
    }

    /**
     * @return {@link #payDate05}
     */
    public String getPayDate05() {
        return payDate05;
    }

    /**
     * @param payDate05
     *            {@link #payDate05}
     */
    public void setPayDate05(String payDate05) {
        this.payDate05 = payDate05;
    }

    /**
     * @return {@link #payDate06}
     */
    public String getPayDate06() {
        return payDate06;
    }

    /**
     * @param payDate06
     *            {@link #payDate06}
     */
    public void setPayDate06(String payDate06) {
        this.payDate06 = payDate06;
    }

    /**
     * @return {@link #exchangePayDate01}
     */
    public String getExchangePayDate01() {
        return exchangePayDate01;
    }

    /**
     * @param exchangePayDate01
     *            {@link #exchangePayDate01}
     */
    public void setExchangePayDate01(String exchangePayDate01) {
        this.exchangePayDate01 = exchangePayDate01;
    }

    /**
     * @return {@link #exchangePayDate02}
     */
    public String getExchangePayDate02() {
        return exchangePayDate02;
    }

    /**
     * @param exchangePayDate02
     *            {@link #exchangePayDate02}
     */
    public void setExchangePayDate02(String exchangePayDate02) {
        this.exchangePayDate02 = exchangePayDate02;
    }

    /**
     * @return {@link #exchangePayDate03}
     */
    public String getExchangePayDate03() {
        return exchangePayDate03;
    }

    /**
     * @param exchangePayDate03
     *            {@link #exchangePayDate03}
     */
    public void setExchangePayDate03(String exchangePayDate03) {
        this.exchangePayDate03 = exchangePayDate03;
    }

    /**
     * @return {@link #exchangePayDate04}
     */
    public String getExchangePayDate04() {
        return exchangePayDate04;
    }

    /**
     * @param exchangePayDate04
     *            {@link #exchangePayDate04}
     */
    public void setExchangePayDate04(String exchangePayDate04) {
        this.exchangePayDate04 = exchangePayDate04;
    }

    /**
     * @return {@link #exchangePayDate05}
     */
    public String getExchangePayDate05() {
        return exchangePayDate05;
    }

    /**
     * @param exchangePayDate05
     *            {@link #exchangePayDate05}
     */
    public void setExchangePayDate05(String exchangePayDate05) {
        this.exchangePayDate05 = exchangePayDate05;
    }

    /**
     * @return {@link #exchangePayDate06}
     */
    public String getExchangePayDate06() {
        return exchangePayDate06;
    }

    /**
     * @param exchangePayDate06
     *            {@link #exchangePayDate06}
     */
    public void setExchangePayDate06(String exchangePayDate06) {
        this.exchangePayDate06 = exchangePayDate06;
    }

    /**
     * @return {@link #exchangePayDate07}
     */
    public String getExchangePayDate07() {
        return exchangePayDate07;
    }

    /**
     * @param exchangePayDate07
     *            {@link #exchangePayDate07}
     */
    public void setExchangePayDate07(String exchangePayDate07) {
        this.exchangePayDate07 = exchangePayDate07;
    }

    /**
     * @return {@link #exchangePayDate08}
     */
    public String getExchangePayDate08() {
        return exchangePayDate08;
    }

    /**
     * @param exchangePayDate08
     *            {@link #exchangePayDate08}
     */
    public void setExchangePayDate08(String exchangePayDate08) {
        this.exchangePayDate08 = exchangePayDate08;
    }

    /**
     * @return {@link #exchangePayDate09}
     */
    public String getExchangePayDate09() {
        return exchangePayDate09;
    }

    /**
     * @param exchangePayDate09
     *            {@link #exchangePayDate09}
     */
    public void setExchangePayDate09(String exchangePayDate09) {
        this.exchangePayDate09 = exchangePayDate09;
    }

    /**
     * @return {@link #exchangePayDate10}
     */
    public String getExchangePayDate10() {
        return exchangePayDate10;
    }

    /**
     * @param exchangePayDate10
     *            {@link #exchangePayDate10}
     */
    public void setExchangePayDate10(String exchangePayDate10) {
        this.exchangePayDate10 = exchangePayDate10;
    }

    /**
     * @return {@link #exchangePayDate11}
     */
    public String getExchangePayDate11() {
        return exchangePayDate11;
    }

    /**
     * @param exchangePayDate11
     *            {@link #exchangePayDate11}
     */
    public void setExchangePayDate11(String exchangePayDate11) {
        this.exchangePayDate11 = exchangePayDate11;
    }

    /**
     * @return {@link #exchangePayDate12}
     */
    public String getExchangePayDate12() {
        return exchangePayDate12;
    }

    /**
     * @param exchangePayDate12
     *            {@link #exchangePayDate12}
     */
    public void setExchangePayDate12(String exchangePayDate12) {
        this.exchangePayDate12 = exchangePayDate12;
    }

    /**
     * @return {@link #exchangePayAcctId}
     */
    public String getExchangePayAcctId() {
        return exchangePayAcctId;
    }

    /**
     * @param exchangePayAcctId
     *            {@link #exchangePayAcctId}
     */
    public void setExchangePayAcctId(String exchangePayAcctId) {
        this.exchangePayAcctId = exchangePayAcctId;
    }

    /**
     * @return {@link #rcvAcctId}
     */
    public String getRcvAcctId() {
        return rcvAcctId;
    }

    /**
     * @param rcvAcctId
     *            {@link #rcvAcctId}
     */
    public void setRcvAcctId(String rcvAcctId) {
        this.rcvAcctId = rcvAcctId;
    }

    /**
     * @return {@link #exchangeRcvAcctId}
     */
    public String getExchangeRcvAcctId() {
        return exchangeRcvAcctId;
    }

    /**
     * @param exchangeRcvAcctId
     *            {@link #exchangeRcvAcctId}
     */
    public void setExchangeRcvAcctId(String exchangeRcvAcctId) {
        this.exchangeRcvAcctId = exchangeRcvAcctId;
    }

    /**
     * @return {@link #status}
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            {@link #status}
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return {@link #exchangeStatus}
     */
    public String getExchangeStatus() {
        return exchangeStatus;
    }

    /**
     * @param exchangeStatus
     *            {@link #exchangeStatus}
     */
    public void setExchangeStatus(String exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }

    /**
     * @return {@link #exchangeChgPurchase}
     */
    public String getExchangeChgPurchase() {
        return exchangeChgPurchase;
    }

    /**
     * @param exchangeChgPurchase
     *            {@link #exchangeChgPurchase}
     */
    public void setExchangeChgPurchase(String exchangeChgPurchase) {
        this.exchangeChgPurchase = exchangeChgPurchase;
    }

    /**
     * @return {@link #amt}
     */
    public BigDecimal getAmt() {
        return amt;
    }

    /**
     * @param amt
     *            {@link #amt}
     */
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    /**
     * @return {@link #amtM}
     */
    public BigDecimal getAmtM() {
        return amtM;
    }

    /**
     * @param amtM
     *            {@link #amtM}
     */
    public void setAmtM(BigDecimal amtM) {
        this.amtM = amtM;
    }

    /**
     * @return {@link #amtH}
     */
    public BigDecimal getAmtH() {
        return amtH;
    }

    /**
     * @param amtH
     *            {@link #amtH}
     */
    public void setAmtH(BigDecimal amtH) {
        this.amtH = amtH;
    }

    /**
     * @return {@link #reFundName}
     */
    public String getReFundName() {
        return reFundName;
    }

    /**
     * @param reFundName
     *            {@link #reFundName}
     */
    public void setReFundName(String reFundName) {
        this.reFundName = reFundName;
    }

    /**
     * @return {@link #afterCurCode}
     */
    public String getAfterCurCode() {
        return afterCurCode;
    }

    /**
     * @param afterCurCode
     *            {@link #afterCurCode}
     */
    public void setAfterCurCode(String afterCurCode) {
        this.afterCurCode = afterCurCode;
    }

    /**
     * @return {@link #afterExchangeFee}
     */
    public BigDecimal getAfterExchangeFee() {
        return afterExchangeFee;
    }

    /**
     * @param afterExchangeFee
     *            {@link #afterExchangeFee}
     */
    public void setAfterExchangeFee(BigDecimal afterExchangeFee) {
        this.afterExchangeFee = afterExchangeFee;
    }

    /**
     * @return {@link #afterExchangeFeeM}
     */
    public BigDecimal getAfterExchangeFeeM() {
        return afterExchangeFeeM;
    }

    /**
     * @param afterExchangeFeeM
     *            {@link #afterExchangeFeeM}
     */
    public void setAfterExchangeFeeM(BigDecimal afterExchangeFeeM) {
        this.afterExchangeFeeM = afterExchangeFeeM;
    }

    /**
     * @return {@link #afterExchangeFeeH}
     */
    public BigDecimal getAfterExchangeFeeH() {
        return afterExchangeFeeH;
    }

    /**
     * @param afterExchangeFeeH
     *            {@link #afterExchangeFeeH}
     */
    public void setAfterExchangeFeeH(BigDecimal afterExchangeFeeH) {
        this.afterExchangeFeeH = afterExchangeFeeH;
    }

    /**
     * @return {@link #timeDepositPrjCd}
     */
    public String getTimeDepositPrjCd() {
        return timeDepositPrjCd;
    }

    /**
     * @param timeDepositPrjCd
     *            {@link #timeDepositPrjCd}
     */
    public void setTimeDepositPrjCd(String timeDepositPrjCd) {
        this.timeDepositPrjCd = timeDepositPrjCd;
    }

    /**
     * @return {@link #fundPackageNo}
     */
    public String getFundPackageNo() {
        return fundPackageNo;
    }

    /**
     * @param fundPackageNo
     *            {@link #fundPackageNo}
     */
    public void setFundPackageNo(String fundPackageNo) {
        this.fundPackageNo = fundPackageNo;
    }

    /**
     * @return {@link #beforeExchangeAmt}
     */
    public BigDecimal getBeforeExchangeAmt() {
        return beforeExchangeAmt;
    }

    /**
     * @param beforeExchangeAmt
     *            {@link #beforeExchangeAmt}
     */
    public void setBeforeExchangeAmt(BigDecimal beforeExchangeAmt) {
        this.beforeExchangeAmt = beforeExchangeAmt;
    }

    /**
     * @return {@link #beforeExchangeAmtM}
     */
    public BigDecimal getBeforeExchangeAmtM() {
        return beforeExchangeAmtM;
    }

    /**
     * @param beforeExchangeAmtM
     *            {@link #beforeExchangeAmtM}
     */
    public void setBeforeExchangeAmtM(BigDecimal beforeExchangeAmtM) {
        this.beforeExchangeAmtM = beforeExchangeAmtM;
    }

    /**
     * @return {@link #beforeExchangeAmtH}
     */
    public BigDecimal getBeforeExchangeAmtH() {
        return beforeExchangeAmtH;
    }

    /**
     * @param beforeExchangeAmtH
     *            {@link #beforeExchangeAmtH}
     */
    public void setBeforeExchangeAmtH(BigDecimal beforeExchangeAmtH) {
        this.beforeExchangeAmtH = beforeExchangeAmtH;
    }

    /**
     * @return {@link #afterExchangeAmt}
     */
    public BigDecimal getAfterExchangeAmt() {
        return afterExchangeAmt;
    }

    /**
     * @param afterExchangeAmt
     *            {@link #afterExchangeAmt}
     */
    public void setAfterExchangeAmt(BigDecimal afterExchangeAmt) {
        this.afterExchangeAmt = afterExchangeAmt;
    }

    /**
     * @return {@link #afterExchangeAmtM}
     */
    public BigDecimal getAfterExchangeAmtM() {
        return afterExchangeAmtM;
    }

    /**
     * @param afterExchangeAmtM
     *            {@link #afterExchangeAmtM}
     */
    public void setAfterExchangeAmtM(BigDecimal afterExchangeAmtM) {
        this.afterExchangeAmtM = afterExchangeAmtM;
    }

    /**
     * @return {@link #afterExchangeAmtH}
     */
    public BigDecimal getAfterExchangeAmtH() {
        return afterExchangeAmtH;
    }

    /**
     * @param afterExchangeAmtH
     *            {@link #afterExchangeAmtH}
     */
    public void setAfterExchangeAmtH(BigDecimal afterExchangeAmtH) {
        this.afterExchangeAmtH = afterExchangeAmtH;
    }

    /**
     * @return {@link #beforeExchangeCurCode}
     */
    public String getBeforeExchangeCurCode() {
        return beforeExchangeCurCode;
    }

    /**
     * @param beforeExchangeCurCode
     *            {@link #beforeExchangeCurCode}
     */
    public void setBeforeExchangeCurCode(String beforeExchangeCurCode) {
        this.beforeExchangeCurCode = beforeExchangeCurCode;
    }

    /**
     * @return {@link #afterExchangeCurCode}
     */
    public String getAfterExchangeCurCode() {
        return afterExchangeCurCode;
    }

    /**
     * @param afterExchangeCurCode
     *            {@link #afterExchangeCurCode}
     */
    public void setAfterExchangeCurCode(String afterExchangeCurCode) {
        this.afterExchangeCurCode = afterExchangeCurCode;
    }

    /**
     * @return {@link #afterPayAcctId}
     */
    public String getAfterPayAcctId() {
        return afterPayAcctId;
    }

    /**
     * @param afterPayAcctId
     *            {@link #afterPayAcctId}
     */
    public void setAfterPayAcctId(String afterPayAcctId) {
        this.afterPayAcctId = afterPayAcctId;
    }

    /**
     * @return {@link #chngeFlag}
     */
    public String getChngeFlag() {
        return chngeFlag;
    }

    /**
     * @param chngeFlag
     *            {@link #chngeFlag}
     */
    public void setChngeFlag(String chngeFlag) {
        this.chngeFlag = chngeFlag;
    }

    /**
     * @return {@link #index}
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index
     *            {@link #index}
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * @return {@link #advFeeRate}
     */
    public BigDecimal getAdvFeeRate() {
        return advFeeRate;
    }

    /**
     * @param advFeeRate
     *            {@link #advFeeRate}
     */
    public void setAdvFeeRate(BigDecimal advFeeRate) {
        this.advFeeRate = advFeeRate;
    }

    /**
     * @return {@link #deferreFee}
     */
    public BigDecimal getDeferreFee() {
        return deferreFee;
    }

    /**
     * @param deferreFee
     *            {@link #deferreFee}
     */
    public void setDeferreFee(BigDecimal deferreFee) {
        this.deferreFee = deferreFee;
    }

    public String getTrustKind() {
        if (hfmtid.matches("0001|0024|0025|0026|0027")) {
            // (1)若TxHead.HFMTID=0001，則顯示“單筆”
            return TRUST_KIND_SINGLE;

        }
        else if (StringUtils.equals("0002", hfmtid)) {
            // (2)若TxHead.HFMTID=0002，且TxBody.TxType=Y，
            // (a)若定期定額/定期不定額申購.TrustAmt_M=0或空值，則顯示“定期定額(Fund久久)”
            // (b)不符合上述(a)，則顯示“定期不定額(Fund久久)”

            if (StringUtils.equals("Y", txType)) {
                // txType =Y --> Fund久久
                if (null == trustAmtM || trustAmtM.compareTo(BigDecimal.ZERO) == 0) {
                    return TRUST_KIND_FIXED_99;
                }
                else {
                    return TRUST_KIND_NOTFIXED_99;
                }
            }
            if (StringUtils.equals("A", txType)) {
                // txType =A --> Fund心投
                if (null == trustAmtM || trustAmtM.compareTo(BigDecimal.ZERO) == 0) {
                    return TRUST_KIND_FIXED_EASY;
                }
                else {
                    return TRUST_KIND_NOTFIXED_EASY;
                }
            }
            else {
                // TxBody.TxType<>Y
                // (3)若TxHead.HFMTID=0002，且TxBody.TxType<>Y，
                // (a)若定期定額/定期不定額申購.TrustAmt_M=0或空值，則顯示“定期定額”
                // (b)不符合上述(a)，則顯示“定期不定額”

                if (null == trustAmtM || trustAmtM.compareTo(BigDecimal.ZERO) == 0) {
                    return TRUST_KIND_FIXED;
                }
                else {
                    return TRUST_KIND_NOTFIXED;
                }
            }

        }
        else if (hfmtid.matches("0013|0014|0015|0016|0017|0018|0019|0023")) {
            // (4)若TxHead.HFMTID=0013、0014、0015、0016、0017、0018、0019、0023，則顯示“基金套餐”
            return TRUST_KIND_FUND_PACKAGE;
        }
        else {

            // (5)若不符合上述，
            // (a)若TxBody.SubCode=1，則顯示“單筆”
            // (b)若TxBody.SubCode=2，且TxBody.TxType=Y，則顯示“定期定額(Fund久久)”
            // (c)若TxBody.SubCode=2，且TxBody.TxType<>Y，則顯示“定期定額”
            // (d)若TxBody.SubCode=3，且TxBody.TxType=Y，則顯示“定期不定額(Fund久久)”
            // (e)若TxBody.SubCode=3，且TxBody.TxType<>Y，則顯示“定期不定額”
            // (f)若TxBody.SubCode=4，則顯示“定存轉基金”
            // (g)若TxBody.SubCode=5，則顯示“基金套餐”
            if (StringUtils.equals("1", subCode)) {
                return TRUST_KIND_SINGLE;
            }
            else if (StringUtils.equals("2", subCode) && StringUtils.equals("Y", txType)) {
                return TRUST_KIND_FIXED_99;
            }
            else if (StringUtils.equals("2", subCode) && StringUtils.equals("A", txType)) {
                return TRUST_KIND_FIXED_EASY;
            }
            else if (StringUtils.equals("2", subCode) && !StringUtils.equals("Y", txType) && !StringUtils.equals("A", txType)) {
                return TRUST_KIND_FIXED;
            }
            else if (StringUtils.equals("3", subCode) && StringUtils.equals("Y", txType)) {
                return TRUST_KIND_NOTFIXED_99;
            }
            else if (StringUtils.equals("3", subCode) && StringUtils.equals("A", txType)) {
                return TRUST_KIND_NOTFIXED_EASY;
            }
            else if (StringUtils.equals("3", subCode) && !StringUtils.equals("Y", txType) && !StringUtils.equals("A", txType)) {
                return TRUST_KIND_NOTFIXED;
            }
            else if (StringUtils.equals("4", subCode)) {
                return TRUST_KIND_DEPOSIT_TO_FUND;
            }
            else if (StringUtils.equals("5", subCode)) {
                return TRUST_KIND_FUND_PACKAGE;
            }
            else {
                return TRUST_KIND_NOSUBCODE;
            }
        }
    }
}
