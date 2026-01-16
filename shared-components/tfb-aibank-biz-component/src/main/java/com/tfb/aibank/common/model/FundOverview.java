package com.tfb.aibank.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.annotations.FormatDate;
import com.tfb.aibank.common.type.FundTxType;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)FundOverview.java
 * 
 * <p>Description:基金總覽，整合資料使用</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/31, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "基金總覽專用物件")
public class FundOverview implements Serializable, Comparable<FundOverview> {

    private static final long serialVersionUID = 6412055110410757119L;

    public FundOverview() {
        // default constructor
    }

    public FundOverview(FundTxType fundTxType) {
        this.fundTxType = fundTxType;
        this.fundCategory = fundTxType.getCode();
    }

    /** 單筆(0001)、定期定額(0002)、定期不定額(0003)、定存轉基金(0004)、基金套餐(0005) 申購在途(1001)、轉換在途(1002)、贖回在途(1003) */
    private FundTxType fundTxType = FundTxType.UNKNOWN;
    /** 基金交易類型 */
    private String fundCategory;

    /** 下行HFMTID */
    @Schema(description = "下行HFMTID")
    private String hfmtid;
    /** 信託帳號 */
    @Schema(description = "信託帳號")
    private String acctId02;
    /** 憑證號碼 */
    @Schema(description = "憑證號碼")
    private String eviNum;
    /** 基金代號 */
    @Schema(description = "基金代號")
    private String fundNO;
    /** 基金名稱 */
    @Schema(description = "基金名稱")
    private String fundName;
    /** 基金幣別 */
    @Schema(description = "基金幣別")
    private String curFund;
    /** 交易幣別 */
    @Schema(description = "交易幣別")
    private String curCode;
    /** 投資金額 */
    @Schema(description = "投資金額")
    private BigDecimal curAmt;
    /** 投資金額折臺 */
    @Schema(description = "投資金額折臺")
    private BigDecimal curAmtNT;
    /** 參考市值 */
    @Schema(description = "參考市值")
    private BigDecimal curBal;
    /** 參考市值折臺 */
    @Schema(description = "參考市值折臺")
    private BigDecimal curBalNT;
    /** 投資損益 */
    @Schema(description = "投資損益")
    private BigDecimal profitAndLoss;
    /** 累積現金配息 */
    @Schema(description = "累積現金配息")
    private BigDecimal increase;
    /** 報酬率正負 */
    @Schema(description = "報酬率正負")
    private String signDigit;
    /** 報酬率 */
    @Schema(description = "報酬率")
    private BigDecimal returns;
    /** 含調整後累積現金配息報酬率正負 */
    @Schema(description = "含調整後累積現金配息報酬率正負")
    private String rewRateDigit;
    /** 含調整後累積現金配息報酬率 */
    @Schema(description = "含調整後累積現金配息報酬率")
    private BigDecimal accAllocateRewRate;
    /** 單位數 */
    @Schema(description = "單位數")
    private BigDecimal curUntNum;
    /** 參考匯率 */
    @Schema(description = "參考匯率")
    private BigDecimal referenceExchangeRate;
    /** 參考淨值日期 */
    @Schema(description = "參考淨值日期")
    @FormatDate(pattern = "yyyy/MM/dd")
    private Date netValueDate;
    /** 參考淨值 */
    @Schema(description = "參考淨值")
    private BigDecimal netValue;
    /** 停損正負號 */
    @Schema(description = "停損正負號")
    private String stoplossSign;
    /** 停損點 */
    @Schema(description = "停損點")
    private BigDecimal stoploss;
    /** 滿足正負號 */
    @Schema(description = "滿足正負號")
    private String satisfiedSign;
    /** 滿足點 */
    @Schema(description = "滿足點")
    private BigDecimal satisfied;
    /** 投資起日 */
    @Schema(description = "投資起日")
    @FormatDate(pattern = "yyyy/MM/dd")
    private Date strdate;
    /** 基金類型 */
    @Schema(description = "基金類型")
    private String fundType;
    /** 未核備(approveFlag == Y) */
    @Schema(description = "未核備")
    private String approveFlag;
    /** 專案代號 */
    @Schema(description = "專案代號")
    private String projectCode;
    /** 團體代號 */
    @Schema(description = "團體代號")
    private String groupCode;
    /** 收益入帳帳號 */
    @Schema(description = "收益入帳帳號")
    private String payAcctId;
    /** 調整後累積現金配息 */
    @Schema(description = "調整後累積現金配息")
    private BigDecimal accAllocateRew;
    /** 調整後累積現金配息折臺 */
    @Schema(description = "調整後累積現金配息折臺")
    private BigDecimal accAllocateRewNT;
    /** 遞延手續費率 */
    @Schema(description = "遞延手續費率")
    private BigDecimal deferreFee;
    /** 動態鎖利註記 */
    @Schema(description = "動態鎖利註記")
    private String dynamic;
    /** 組合報酬參考日 */
    @Schema(description = "組合報酬參考日")
    @FormatDate(pattern = "yyyy/MM/dd")
    private Date comboReturnDate;
    /** 組合報酬正負 */
    @Schema(description = "組合報酬正負")
    private String comboReturnSign;
    /** 基金組合報酬率 */
    @Schema(description = "基金組合報酬率")
    private BigDecimal comboReturn;
    /** 約定申購子基金日1 */
    @Schema(description = "約定申購子基金日1")
    private String satelliteBuyDate1;
    /** 約定申購子基金日2 */
    @Schema(description = "約定申購子基金日2")
    private String satelliteBuyDate2;
    /** 約定申購子基金日3 */
    @Schema(description = "約定申購子基金日3")
    private String satelliteBuyDate3;
    /** 約定申購子基金日4 */
    @Schema(description = "約定申購子基金日4")
    private String satelliteBuyDate4;
    /** 約定申購子基金日5 */
    @Schema(description = "約定申購子基金日5")
    private String satelliteBuyDate5;
    /** 約定申購子基金日6 */
    @Schema(description = "約定申購子基金日6")
    private String satelliteBuyDate6;
    /** 約定停利報酬率1 */
    @Schema(description = "約定停利報酬率1")
    private BigDecimal benefitReturnRate1;
    /** 約定停利報酬率2 */
    @Schema(description = "約定停利報酬率2")
    private BigDecimal benefitReturnRate2;
    /** 約定停利報酬率3 */
    @Schema(description = "約定停利報酬率3")
    private BigDecimal benefitReturnRate3;
    /** 轉換金額 */
    @Schema(description = "轉換金額")
    private BigDecimal transferAmt;
    /** 狀態 */
    @Schema(description = "狀態")
    private String status;
    /** 生效日期 */
    @Schema(description = "生效日期")
    @FormatDate(pattern = "yyyy/MM/dd")
    private Date date;
    /** 手續費幣別 */
    @Schema(description = "手續費幣別")
    private String feeCur;
    /** 手續費金額 */
    @Schema(description = "手續費金額")
    private BigDecimal feeAmt;
    /** 本次扣帳幣別 */
    @Schema(description = "本次扣帳幣別")
    private String trscCur;
    /** 本次扣帳金額 */
    @Schema(description = "本次扣帳金額")
    private BigDecimal trscAmt;
    /** 本次扣款金額折臺 */
    @Schema(description = "本次扣款金額折臺")
    private BigDecimal trscAmtNT;
    /** 兌換臺幣匯率 */
    @Schema(description = "兌換臺幣匯率")
    private BigDecimal exchangeRateNT;
    /** 信託型態 */
    @Schema(description = "信託型態")
    private String subCode;
    /** 交易類別 */
    @Schema(description = "交易類別")
    private String txType;
    /** 轉入基金代號 */
    @Schema(description = "轉入基金代號")
    private String inFundNO;
    /** 轉入基金名稱 */
    @Schema(description = "轉入基金名稱")
    private String inFundName;
    /** 轉出基金幣別 */
    @Schema(description = "轉出基金幣別")
    private String fundCur;
    /** 轉入基金幣別 */
    @Schema(description = "轉入基金幣別")
    private String inFundCur;
    /** 轉入交易幣別 */
    @Schema(description = "轉入交易幣別")
    private String inCurCode;
    /** 參考市值 */
    @Schema(description = "參考市值")
    private BigDecimal refAmt;
    /** 參考市值折臺 */
    @Schema(description = "參考市值折臺")
    private BigDecimal refAmtNT;
    /** 轉換手續費幣別 */
    @Schema(description = "轉換手續費幣別")
    private String transferFeeCur;
    /** 轉換手續費 */
    @Schema(description = "轉換手續費")
    private BigDecimal transferFee;
    /** 單位數 */
    @Schema(description = "單位數")
    private BigDecimal untNum;
    /** 參考匯率 */
    @Schema(description = "參考匯率")
    private BigDecimal referenceRate;
    /** 轉出參考淨值 */
    @Schema(description = "轉出參考淨值")
    private BigDecimal refNetValue;
    /** 轉入參考淨值日期 */
    @Schema(description = "轉入參考淨值日期")
    @FormatDate(pattern = "yyyy/MM/dd")
    private Date inNetValueDate;
    /** 轉入參考淨值 */
    @Schema(description = "轉入參考淨值")
    private BigDecimal inRefNetValue;
    /** 贖回手續費幣別 */
    @Schema(description = "贖回手續費幣別")
    private String redeemFeeCur;
    /** 贖回手續費 */
    @Schema(description = "贖回手續費")
    private BigDecimal redeemFee;
    /** 再申購基金代號 */
    @Schema(description = "再申購基金代號")
    private String newFundNO;
    /** 再申購基金名稱 */
    @Schema(description = "再申購基金名稱")
    private String newFundName;
    /** 再申購交易幣別 */
    @Schema(description = "再申購交易幣別")
    private String newCurCode;
    /** 再申購手續費率 */
    @Schema(description = "再申購手續費率")
    private BigDecimal newFee;
    /** 交易生效日 */
    @Schema(description = "交易生效日")
    @FormatDate(pattern = "yyyy/MM/dd")
    private Date trscDate;
    /** 營業時間註記 */
    @Schema(description = "營業時間註記")
    private String markedDate;
    /** 約定停損正負號 */
    @Schema(description = "約定停損正負號")
    private String autoStoplossSign;
    /** 約定停損點 */
    @Schema(description = "約定停損點")
    private BigDecimal autoStoploss;
    /** 約定滿足正負號 */
    @Schema(description = "約定滿足正負號")
    private String autoSatisfiedSign;
    /** 約定滿足點 */
    @Schema(description = "約定滿足點")
    private BigDecimal autoSatisfied;
    /** 約定報酬註記 */
    @Schema(description = "約定報酬註記")
    private String autoRewardFlag;
    /** 扣款日期一 */
    @Schema(description = "扣款日期一")
    private String transferDate01;
    /** 扣款日期二 */
    @Schema(description = "扣款日期二")
    private String transferDate02;
    /** 扣款日期三 */
    @Schema(description = "扣款日期三")
    private String transferDate03;
    /** 扣款日期四 */
    @Schema(description = "扣款日期四")
    private String transferDate04;
    /** 扣款日期五 */
    @Schema(description = "扣款日期五")
    private String transferDate05;
    /** 扣款日期六 */
    @Schema(description = "扣款日期六")
    private String transferDate06;
    /** 扣款次數 */
    @Schema(description = "扣款次數")
    private Integer transferCount;
    /** 扣款成功次數 */
    @Schema(description = "扣款成功次數")
    private String payCount;
    /** 扣款帳號 */
    @Schema(description = "扣款帳號")
    private String payAccountNo;
    /** 換匯申購 */
    @Schema(description = "換匯申購")
    private String frgnPurchaseFlag;
    /** 申請扣款是否為同一人 */
    @Schema(description = "申請扣款是否為同一人")
    private String same;
    /** 與時聚金折扣金額 */
    @Schema(description = "與時聚金折扣金額")
    private BigDecimal activeDisAmt;
    /** 扣款金額(高) */
    @Schema(description = "扣款金額(高)")
    private BigDecimal transferAmtH;
    /** 扣款金額(中) */
    @Schema(description = "扣款金額(中)")
    private BigDecimal transferAmtM;
    /** 扣款金額(低) */
    @Schema(description = "扣款金額(低)")
    private BigDecimal transferAmtL;
    /** 專案帳號 */
    @Schema(description = "專案帳號")
    private String acctId;
    /** 定存專案編號 */
    @Schema(description = "定存專案編號")
    private String timeDepositPrjCd;
    /** 總投資期數 */
    @Schema(description = "總投資期數")
    private Integer totalCnt;
    /** 已投資期數 */
    @Schema(description = "已投資期數")
    private Integer payCnt;
    /** 終止碼 */
    @Schema(description = "終止碼")
    private String endFlg;
    /** 基金套餐編號 */
    @Schema(description = "基金套餐編號")
    private String fundPackageNo;
    /** 基金套餐 */
    @Schema(description = "基金套餐")
    private String fundPackage;

    // ============================== 以下為擴充欄位 ===================================

    /** 交易幣別名稱 */
    @Schema(description = "交易幣別名稱")
    private String curName;

    /** 本次扣帳幣別名稱 */
    @Schema(description = "本次扣帳幣別名稱")
    private String trscCurName;

    /** 手續費幣別名稱 */
    @Schema(description = "手續費幣別名稱")
    private String feeCurName;

    /** 贖回手續費幣別名稱 */
    @Schema(description = "贖回手續費幣別名稱")
    private String redeemFeeCurName;

    /** 轉出基金幣別名稱 */
    @Schema(description = "轉出基金幣別名稱")
    private String fundCurName;

    /** 再申購交易幣別名稱 */
    @Schema(description = "再申購交易幣別名稱")
    private String newCurName;

    /** 轉換手續費幣別名稱 */
    @Schema(description = "轉換手續費幣別名稱")
    private String transferFeeCurName;

    /** 轉入交易幣別名稱 */
    @Schema(description = "轉入交易幣別名稱")
    private String inCurName;

    /** 轉入基金幣別名稱 */
    @Schema(description = "轉入基金幣別名稱")
    private String inFundCurName;

    /** 基金幣別名稱 */
    @Schema(description = "基金幣別名稱")
    private String curFundName;

    /** 定期定額其他註記 */
    @Schema(description = "定期定額其他註記")
    private String aplyType;

    /** 交易通路 */
    @Schema(description = "交易通路")
    private String chSource;

    /** 可取消註記 */
    @Schema(description = "可取消註記")
    private String voidFlag;

    /** 調整後累積現金配息(含轉換前息) */
    @Schema(description = "調整後累積現金配息(含轉換前息)")
    private BigDecimal befDivisCash;

    /** 調整後累積現金配息折臺(含轉換前息) */
    @Schema(description = "調整後累積現金配息折臺(含轉換前息)")
    private BigDecimal befDivisCashTWD;

    /** 含調整後累積現金配息報酬率正負 */
    @Schema(description = "含調整後累積現金配息報酬率正負")
    private String befCashReturnSign;

    /** 含調整後累積現金配息(含轉換前息)報酬率 */
    @Schema(description = "含調整後累積現金配息(含轉換前息)報酬率")
    private BigDecimal befCashReturn;

    /**
     * 是否為「庫存」
     * 
     * @return
     */
    public boolean isInventory() {
        return getFundTxType().isInventory();
    }

    /**
     * 是否為「在途」
     * 
     * @return
     */
    public boolean isTransit() {
        return getFundTxType().isTransit();
    }

    @Override
    public int compareTo(FundOverview o2) {
        // 依信託型態排序(單筆>定期定額>定期定額Fund久久>定期不定額>定期不定額Fund久久>定存轉基金>基金套餐)
        int compareValue = getSort().compareTo(o2.getSort());
        // 憑證號碼由小到大
        if (compareValue == 0) {
            return this.getEviNum().compareTo(o2.getEviNum());
        }
        return compareValue;
    }

    /**
     * 依信託型態判斷順序
     * 
     * @return
     */
    public String getSort() {
        String result = StringUtils.EMPTY;
        if (getFundTxType().isSinglePurchase()) {
            result = "10";
        }
        else if (getFundTxType().isRegularFixedAmount()) {
            if (StringUtils.isY(this.txType)) {
                result = "21";
            }
            else {
                result = "20";
            }
        }
        else if (getFundTxType().isRegularVariableAmount()) {
            if (StringUtils.isY(this.txType)) {
                result = "31";
            }
            else {
                result = "30";
            }
        }
        else if (getFundTxType().isTimeDepositToFund()) {
            result = "40";
        }
        else if (getFundTxType().isFundPackage()) {
            result = "50";
        }
        else if (getFundTxType().isTransit()) {
            if ("1".equals(this.subCode)) {
                result = "10";
            }
            else if ("2".equals(this.subCode)) {
                if ("Y".equals(this.txType)) {
                    result = "21";
                }
                else {
                    result = "20";
                }
            }
            else if ("3".equals(this.subCode)) {
                if (StringUtils.isY(this.txType)) {
                    result = "31";
                }
                else {
                    result = "30";
                }
            }
            else if ("4".equals(this.subCode)) {
                result = "40";
            }
            else if ("5".equals(this.subCode)) {
                result = "50";
            }
        }
        return result;
    }

    public FundTxType getFundTxType() {
        return fundTxType;
    }

    public void setFundTxType(FundTxType fundTxType) {
        this.fundTxType = fundTxType;
    }

    public String getAcctId02() {
        return acctId02;
    }

    public void setAcctId02(String acctId02) {
        this.acctId02 = acctId02;
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

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getCurFund() {
        return curFund;
    }

    public void setCurFund(String curFund) {
        this.curFund = curFund;
    }

    public String getCurCode() {
        return curCode;
    }

    public void setCurCode(String curCode) {
        this.curCode = curCode;
    }

    public BigDecimal getCurAmt() {
        return curAmt;
    }

    public void setCurAmt(BigDecimal curAmt) {
        this.curAmt = curAmt;
    }

    public BigDecimal getCurAmtNT() {
        return curAmtNT;
    }

    public void setCurAmtNT(BigDecimal curAmtNT) {
        this.curAmtNT = curAmtNT;
    }

    public BigDecimal getCurBal() {
        return curBal;
    }

    public void setCurBal(BigDecimal curBal) {
        this.curBal = curBal;
    }

    public BigDecimal getCurBalNT() {
        return curBalNT;
    }

    public void setCurBalNT(BigDecimal curBalNT) {
        this.curBalNT = curBalNT;
    }

    public BigDecimal getProfitAndLoss() {
        return profitAndLoss;
    }

    public void setProfitAndLoss(BigDecimal profitAndLoss) {
        this.profitAndLoss = profitAndLoss;
    }

    public BigDecimal getIncrease() {
        return increase;
    }

    public void setIncrease(BigDecimal increase) {
        this.increase = increase;
    }

    public String getSignDigit() {
        return signDigit;
    }

    public void setSignDigit(String signDigit) {
        this.signDigit = signDigit;
    }

    public BigDecimal getReturns() {
        return returns;
    }

    public void setReturns(BigDecimal returns) {
        this.returns = returns;
    }

    public String getRewRateDigit() {
        return rewRateDigit;
    }

    public void setRewRateDigit(String rewRateDigit) {
        this.rewRateDigit = rewRateDigit;
    }

    public BigDecimal getAccAllocateRewRate() {
        return accAllocateRewRate;
    }

    public void setAccAllocateRewRate(BigDecimal accAllocateRewRate) {
        this.accAllocateRewRate = accAllocateRewRate;
    }

    public BigDecimal getCurUntNum() {
        return curUntNum;
    }

    public void setCurUntNum(BigDecimal curUntNum) {
        this.curUntNum = curUntNum;
    }

    public BigDecimal getReferenceExchangeRate() {
        return referenceExchangeRate;
    }

    public void setReferenceExchangeRate(BigDecimal referenceExchangeRate) {
        this.referenceExchangeRate = referenceExchangeRate;
    }

    public Date getNetValueDate() {
        return netValueDate;
    }

    public void setNetValueDate(Date netValueDate) {
        this.netValueDate = netValueDate;
    }

    public BigDecimal getNetValue() {
        return netValue;
    }

    public void setNetValue(BigDecimal netValue) {
        this.netValue = netValue;
    }

    public String getStoplossSign() {
        return stoplossSign;
    }

    public void setStoplossSign(String stoplossSign) {
        this.stoplossSign = stoplossSign;
    }

    public BigDecimal getStoploss() {
        return stoploss;
    }

    public void setStoploss(BigDecimal stoploss) {
        this.stoploss = stoploss;
    }

    public String getSatisfiedSign() {
        return satisfiedSign;
    }

    public void setSatisfiedSign(String satisfiedSign) {
        this.satisfiedSign = satisfiedSign;
    }

    public BigDecimal getSatisfied() {
        return satisfied;
    }

    public void setSatisfied(BigDecimal satisfied) {
        this.satisfied = satisfied;
    }

    public Date getStrdate() {
        return strdate;
    }

    public void setStrdate(Date strdate) {
        this.strdate = strdate;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public String getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(String approveFlag) {
        this.approveFlag = approveFlag;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getPayAcctId() {
        return payAcctId;
    }

    public void setPayAcctId(String payAcctId) {
        this.payAcctId = payAcctId;
    }

    public BigDecimal getAccAllocateRew() {
        return accAllocateRew;
    }

    public void setAccAllocateRew(BigDecimal accAllocateRew) {
        this.accAllocateRew = accAllocateRew;
    }

    public BigDecimal getAccAllocateRewNT() {
        return accAllocateRewNT;
    }

    public void setAccAllocateRewNT(BigDecimal accAllocateRewNT) {
        this.accAllocateRewNT = accAllocateRewNT;
    }

    public BigDecimal getDeferreFee() {
        return deferreFee;
    }

    public void setDeferreFee(BigDecimal deferreFee) {
        this.deferreFee = deferreFee;
    }

    public String getDynamic() {
        return dynamic;
    }

    public void setDynamic(String dynamic) {
        this.dynamic = dynamic;
    }

    public Date getComboReturnDate() {
        return comboReturnDate;
    }

    public void setComboReturnDate(Date comboReturnDate) {
        this.comboReturnDate = comboReturnDate;
    }

    public String getComboReturnSign() {
        return comboReturnSign;
    }

    public void setComboReturnSign(String comboReturnSign) {
        this.comboReturnSign = comboReturnSign;
    }

    public BigDecimal getComboReturn() {
        return comboReturn;
    }

    public void setComboReturn(BigDecimal comboReturn) {
        this.comboReturn = comboReturn;
    }

    public String getSatelliteBuyDate1() {
        return satelliteBuyDate1;
    }

    public void setSatelliteBuyDate1(String satelliteBuyDate1) {
        this.satelliteBuyDate1 = satelliteBuyDate1;
    }

    public String getSatelliteBuyDate2() {
        return satelliteBuyDate2;
    }

    public void setSatelliteBuyDate2(String satelliteBuyDate2) {
        this.satelliteBuyDate2 = satelliteBuyDate2;
    }

    public String getSatelliteBuyDate3() {
        return satelliteBuyDate3;
    }

    public void setSatelliteBuyDate3(String satelliteBuyDate3) {
        this.satelliteBuyDate3 = satelliteBuyDate3;
    }

    public String getSatelliteBuyDate4() {
        return satelliteBuyDate4;
    }

    public void setSatelliteBuyDate4(String satelliteBuyDate4) {
        this.satelliteBuyDate4 = satelliteBuyDate4;
    }

    public String getSatelliteBuyDate5() {
        return satelliteBuyDate5;
    }

    public void setSatelliteBuyDate5(String satelliteBuyDate5) {
        this.satelliteBuyDate5 = satelliteBuyDate5;
    }

    public String getSatelliteBuyDate6() {
        return satelliteBuyDate6;
    }

    public void setSatelliteBuyDate6(String satelliteBuyDate6) {
        this.satelliteBuyDate6 = satelliteBuyDate6;
    }

    public BigDecimal getBenefitReturnRate1() {
        return benefitReturnRate1;
    }

    public void setBenefitReturnRate1(BigDecimal benefitReturnRate1) {
        this.benefitReturnRate1 = benefitReturnRate1;
    }

    public BigDecimal getBenefitReturnRate2() {
        return benefitReturnRate2;
    }

    public void setBenefitReturnRate2(BigDecimal benefitReturnRate2) {
        this.benefitReturnRate2 = benefitReturnRate2;
    }

    public BigDecimal getBenefitReturnRate3() {
        return benefitReturnRate3;
    }

    public void setBenefitReturnRate3(BigDecimal benefitReturnRate3) {
        this.benefitReturnRate3 = benefitReturnRate3;
    }

    public BigDecimal getTransferAmt() {
        return transferAmt;
    }

    public void setTransferAmt(BigDecimal transferAmt) {
        this.transferAmt = transferAmt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFeeCur() {
        return feeCur;
    }

    public void setFeeCur(String feeCur) {
        this.feeCur = feeCur;
    }

    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    public String getTrscCur() {
        return trscCur;
    }

    public void setTrscCur(String trscCur) {
        this.trscCur = trscCur;
    }

    public BigDecimal getTrscAmt() {
        return trscAmt;
    }

    public void setTrscAmt(BigDecimal trscAmt) {
        this.trscAmt = trscAmt;
    }

    public BigDecimal getTrscAmtNT() {
        return trscAmtNT;
    }

    public void setTrscAmtNT(BigDecimal trscAmtNT) {
        this.trscAmtNT = trscAmtNT;
    }

    public BigDecimal getExchangeRateNT() {
        return exchangeRateNT;
    }

    public void setExchangeRateNT(BigDecimal exchangeRateNT) {
        this.exchangeRateNT = exchangeRateNT;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getTxType() {
        return txType;
    }

    public void setTxType(String txType) {
        this.txType = txType;
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

    public String getFundCur() {
        return fundCur;
    }

    public void setFundCur(String fundCur) {
        this.fundCur = fundCur;
    }

    public String getInFundCur() {
        return inFundCur;
    }

    public void setInFundCur(String inFundCur) {
        this.inFundCur = inFundCur;
    }

    public String getInCurCode() {
        return inCurCode;
    }

    public void setInCurCode(String inCurCode) {
        this.inCurCode = inCurCode;
    }

    public BigDecimal getRefAmt() {
        return refAmt;
    }

    public void setRefAmt(BigDecimal refAmt) {
        this.refAmt = refAmt;
    }

    public BigDecimal getRefAmtNT() {
        return refAmtNT;
    }

    public void setRefAmtNT(BigDecimal refAmtNT) {
        this.refAmtNT = refAmtNT;
    }

    public String getTransferFeeCur() {
        return transferFeeCur;
    }

    public void setTransferFeeCur(String transferFeeCur) {
        this.transferFeeCur = transferFeeCur;
    }

    public BigDecimal getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(BigDecimal transferFee) {
        this.transferFee = transferFee;
    }

    public BigDecimal getUntNum() {
        return untNum;
    }

    public void setUntNum(BigDecimal untNum) {
        this.untNum = untNum;
    }

    public BigDecimal getReferenceRate() {
        return referenceRate;
    }

    public void setReferenceRate(BigDecimal referenceRate) {
        this.referenceRate = referenceRate;
    }

    public BigDecimal getRefNetValue() {
        return refNetValue;
    }

    public void setRefNetValue(BigDecimal refNetValue) {
        this.refNetValue = refNetValue;
    }

    public Date getInNetValueDate() {
        return inNetValueDate;
    }

    public void setInNetValueDate(Date inNetValueDate) {
        this.inNetValueDate = inNetValueDate;
    }

    public BigDecimal getInRefNetValue() {
        return inRefNetValue;
    }

    public void setInRefNetValue(BigDecimal inRefNetValue) {
        this.inRefNetValue = inRefNetValue;
    }

    public String getRedeemFeeCur() {
        return redeemFeeCur;
    }

    public void setRedeemFeeCur(String redeemFeeCur) {
        this.redeemFeeCur = redeemFeeCur;
    }

    public BigDecimal getRedeemFee() {
        return redeemFee;
    }

    public void setRedeemFee(BigDecimal redeemFee) {
        this.redeemFee = redeemFee;
    }

    public String getNewFundNO() {
        return newFundNO;
    }

    public void setNewFundNO(String newFundNO) {
        this.newFundNO = newFundNO;
    }

    public String getNewFundName() {
        return newFundName;
    }

    public void setNewFundName(String newFundName) {
        this.newFundName = newFundName;
    }

    public String getNewCurCode() {
        return newCurCode;
    }

    public void setNewCurCode(String newCurCode) {
        this.newCurCode = newCurCode;
    }

    public BigDecimal getNewFee() {
        return newFee;
    }

    public void setNewFee(BigDecimal newFee) {
        this.newFee = newFee;
    }

    public Date getTrscDate() {
        return trscDate;
    }

    public void setTrscDate(Date trscDate) {
        this.trscDate = trscDate;
    }

    public String getMarkedDate() {
        return markedDate;
    }

    public void setMarkedDate(String markedDate) {
        this.markedDate = markedDate;
    }

    public String getAutoStoplossSign() {
        return autoStoplossSign;
    }

    public void setAutoStoplossSign(String autoStoplossSign) {
        this.autoStoplossSign = autoStoplossSign;
    }

    public BigDecimal getAutoStoploss() {
        return autoStoploss;
    }

    public void setAutoStoploss(BigDecimal autoStoploss) {
        this.autoStoploss = autoStoploss;
    }

    public String getAutoSatisfiedSign() {
        return autoSatisfiedSign;
    }

    public void setAutoSatisfiedSign(String autoSatisfiedSign) {
        this.autoSatisfiedSign = autoSatisfiedSign;
    }

    public BigDecimal getAutoSatisfied() {
        return autoSatisfied;
    }

    public void setAutoSatisfied(BigDecimal autoSatisfied) {
        this.autoSatisfied = autoSatisfied;
    }

    public String getAutoRewardFlag() {
        return autoRewardFlag;
    }

    public void setAutoRewardFlag(String autoRewardFlag) {
        this.autoRewardFlag = autoRewardFlag;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public String getTrscCurName() {
        return trscCurName;
    }

    public void setTrscCurName(String trscCurName) {
        this.trscCurName = trscCurName;
    }

    public String getFeeCurName() {
        return feeCurName;
    }

    public void setFeeCurName(String feeCurName) {
        this.feeCurName = feeCurName;
    }

    public String getRedeemFeeCurName() {
        return redeemFeeCurName;
    }

    public void setRedeemFeeCurName(String redeemFeeCurName) {
        this.redeemFeeCurName = redeemFeeCurName;
    }

    public String getFundCurName() {
        return fundCurName;
    }

    public void setFundCurName(String fundCurName) {
        this.fundCurName = fundCurName;
    }

    public String getNewCurName() {
        return newCurName;
    }

    public void setNewCurName(String newCurName) {
        this.newCurName = newCurName;
    }

    public String getTransferFeeCurName() {
        return transferFeeCurName;
    }

    public void setTransferFeeCurName(String transferFeeCurName) {
        this.transferFeeCurName = transferFeeCurName;
    }

    public String getInCurName() {
        return inCurName;
    }

    public void setInCurName(String inCurName) {
        this.inCurName = inCurName;
    }

    public String getInFundCurName() {
        return inFundCurName;
    }

    public void setInFundCurName(String inFundCurName) {
        this.inFundCurName = inFundCurName;
    }

    public String getCurFundName() {
        return curFundName;
    }

    public void setCurFundName(String curFundName) {
        this.curFundName = curFundName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getTransferDate01() {
        return transferDate01;
    }

    public void setTransferDate01(String transferDate01) {
        this.transferDate01 = transferDate01;
    }

    public String getTransferDate02() {
        return transferDate02;
    }

    public void setTransferDate02(String transferDate02) {
        this.transferDate02 = transferDate02;
    }

    public String getTransferDate03() {
        return transferDate03;
    }

    public void setTransferDate03(String transferDate03) {
        this.transferDate03 = transferDate03;
    }

    public String getTransferDate04() {
        return transferDate04;
    }

    public void setTransferDate04(String transferDate04) {
        this.transferDate04 = transferDate04;
    }

    public String getTransferDate05() {
        return transferDate05;
    }

    public void setTransferDate05(String transferDate05) {
        this.transferDate05 = transferDate05;
    }

    public String getTransferDate06() {
        return transferDate06;
    }

    public void setTransferDate06(String transferDate06) {
        this.transferDate06 = transferDate06;
    }

    public Integer getTransferCount() {
        return transferCount;
    }

    public void setTransferCount(Integer transferCount) {
        this.transferCount = transferCount;
    }

    public String getPayCount() {
        return payCount;
    }

    public void setPayCount(String payCount) {
        this.payCount = payCount;
    }

    public String getPayAccountNo() {
        return payAccountNo;
    }

    public void setPayAccountNo(String payAccountNo) {
        this.payAccountNo = payAccountNo;
    }

    public String getFrgnPurchaseFlag() {
        return frgnPurchaseFlag;
    }

    public void setFrgnPurchaseFlag(String frgnPurchaseFlag) {
        this.frgnPurchaseFlag = frgnPurchaseFlag;
    }

    public String getSame() {
        return same;
    }

    public void setSame(String same) {
        this.same = same;
    }

    public BigDecimal getActiveDisAmt() {
        return activeDisAmt;
    }

    public void setActiveDisAmt(BigDecimal activeDisAmt) {
        this.activeDisAmt = activeDisAmt;
    }

    public BigDecimal getTransferAmtH() {
        return transferAmtH;
    }

    public void setTransferAmtH(BigDecimal transferAmtH) {
        this.transferAmtH = transferAmtH;
    }

    public BigDecimal getTransferAmtM() {
        return transferAmtM;
    }

    public void setTransferAmtM(BigDecimal transferAmtM) {
        this.transferAmtM = transferAmtM;
    }

    public BigDecimal getTransferAmtL() {
        return transferAmtL;
    }

    public void setTransferAmtL(BigDecimal transferAmtL) {
        this.transferAmtL = transferAmtL;
    }

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    public String getTimeDepositPrjCd() {
        return timeDepositPrjCd;
    }

    public void setTimeDepositPrjCd(String timeDepositPrjCd) {
        this.timeDepositPrjCd = timeDepositPrjCd;
    }

    public Integer getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(Integer totalCnt) {
        this.totalCnt = totalCnt;
    }

    public Integer getPayCnt() {
        return payCnt;
    }

    public void setPayCnt(Integer payCnt) {
        this.payCnt = payCnt;
    }

    public String getEndFlg() {
        return endFlg;
    }

    public void setEndFlg(String endFlg) {
        this.endFlg = endFlg;
    }

    public String getFundPackageNo() {
        return fundPackageNo;
    }

    public void setFundPackageNo(String fundPackageNo) {
        this.fundPackageNo = fundPackageNo;
    }

    public String getFundPackage() {
        return fundPackage;
    }

    public void setFundPackage(String fundPackage) {
        this.fundPackage = fundPackage;
    }

    public String getFundCategory() {
        return fundCategory;
    }

    public void setFundCategory(String fundCategory) {
        this.fundCategory = fundCategory;
    }

    public String getHfmtid() {
        return hfmtid;
    }

    public void setHfmtid(String hfmtid) {
        this.hfmtid = hfmtid;
    }

    /**
     * @return {@link #aplyType}
     */
    public String getAplyType() {
        return aplyType;
    }

    /**
     * @param aplyType
     *            {@link #aplyType}
     */
    public void setAplyType(String aplyType) {
        this.aplyType = aplyType;
    }

    public String getChSource() {
        return chSource;
    }

    public void setChSource(String chSource) {
        this.chSource = chSource;
    }

    public String getVoidFlag() {
        return voidFlag;
    }

    public void setVoidFlag(String voidFlag) {
        this.voidFlag = voidFlag;
    }

    public BigDecimal getBefDivisCash() {
        return befDivisCash;
    }

    public void setBefDivisCash(BigDecimal befDivisCash) {
        this.befDivisCash = befDivisCash;
    }

    public BigDecimal getBefDivisCashTWD() {
        return befDivisCashTWD;
    }

    public void setBefDivisCashTWD(BigDecimal befDivisCashTWD) {
        this.befDivisCashTWD = befDivisCashTWD;
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

}
