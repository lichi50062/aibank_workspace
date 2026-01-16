package com.tfb.aibank.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.tfb.aibank.common.type.BondTxType;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)BondOverview.java
 * 
 * <p>Description:債券總覽，整合資訊使用</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/31, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BondOverview implements Comparable<BondOverview>, Serializable {

    private static final long serialVersionUID = 1L;

    public BondOverview() {
        // default constructor
    }

    public BondOverview(BondTxType bondTxType) {
        this.bondTxType = bondTxType;
    }

    /** 庫存(0001)、申購在途(1001)、贖回在途(1002) */
    private BondTxType bondTxType = BondTxType.UNKNOWN;

    /** 信託帳號 */
    @Schema(description = "信託帳號")
    private String subAcctId;
    /** 憑證號碼 */
    @Schema(description = "憑證號碼")
    private String eviNum;
    /** 債券ISIN */
    @Schema(description = "債券ISIN")
    private String bondISIN;
    /** 債券代號 */
    @Schema(description = "債券代號")
    private String bondNO;
    /** 債券名稱 */
    @Schema(description = "債券名稱")
    private String bondName;
    /** 商品幣別 */
    @Schema(description = "商品幣別")
    private String curCode;
    /** 交易幣別 */
    @Schema(description = "交易幣別")
    private String trustCur;
    /** 配息幣別 */
    @Schema(description = "配息幣別")
    private String intCur;
    /** 庫存面額 */
    @Schema(description = "庫存面額")
    private BigDecimal invVal;
    /** 單位面額 */
    @Schema(description = "單位面額")
    private BigDecimal untVal;
    /** 單位數 */
    @Schema(description = "單位數")
    private BigDecimal untNum;
    /** 信託金額 */
    @Schema(description = "信託金額")
    private BigDecimal trustAmt;
    /** 信託金額折臺 */
    @Schema(description = "信託金額折臺")
    private BigDecimal trustAmtNT;
    /** 參考市值 */
    @Schema(description = "參考市值")
    private BigDecimal refAmt;
    /** 參考市值折臺 */
    @Schema(description = "參考市值折臺")
    private BigDecimal refAmtNT;
    /** 累積現金配息 */
    @Schema(description = "累積現金配息")
    private BigDecimal interest;
    /** 調整後累積現金配息 */
    @Schema(description = "調整後累積現金配息")
    private BigDecimal adjustInterest;
    /** 含累積現金配息報酬率正負號 */
    @Schema(description = "含累積現金配息報酬率正負號")
    private String sign;
    /** 含累積現金配息報酬率 */
    @Schema(description = "含累積現金配息報酬率")
    private BigDecimal intRate;
    /** 期初參考匯率 */
    @Schema(description = "期初參考匯率")
    private BigDecimal exchangeRate;
    /** 參考匯率 */
    @Schema(description = "參考匯率")
    private BigDecimal refExRate;
    /** 參考報價日期 */
    @Schema(description = "參考報價日期")
    private Date refDt;
    /** 參考報價 */
    @Schema(description = "參考報價")
    private BigDecimal refValue;
    /** 投資始日 */
    @Schema(description = "投資始日")
    private Date strdate;
    /** 預定到期日 */
    @Schema(description = "預定到期日")
    private Date enddate;
    /** 結算日 */
    @Schema(description = "結算日")
    private Date closingDate;
    /** 收益帳號 */
    @Schema(description = "收益帳號")
    private String rcvAcct;
    /** 信託種類 */
    @Schema(description = "信託種類")
    private String trustType;
    /** 可否贖回 */
    @Schema(description = "可否贖回")
    private String callBack;
    /** 可否部分贖回 */
    @Schema(description = "可否部分贖回")
    private String parCallBack;
    /** 可否電子贖回 */
    @Schema(description = "可否電子贖回")
    private String isRedemption;
    /** 可否電子變更 */
    @Schema(description = "可否電子變更")
    private String isChange;
    /** 贖回委託日 */
    @Schema(description = "贖回委託日")
    private Date backDt;
    /** 贖回委託面額 */
    @Schema(description = "贖回委託面額")
    private BigDecimal backAmt;
    /** 贖回在途面額 */
    @Schema(description = "贖回在途面額")
    private BigDecimal onWayAmt;
    /** 已付前手息 */
    @Schema(description = "已付前手息")
    private BigDecimal frontfee1;
    /** 應收前手息 */
    @Schema(description = "應收前手息")
    private BigDecimal frontfee2;
    /** 委託日期 */
    @Schema(description = "委託日期")
    private Date trustDt;
    /** 委託面額 */
    @Schema(description = "委託面額")
    private BigDecimal trustVal;
    /** 限價方式 */
    @Schema(description = "限價方式")
    private String reedType;
    /** 委賣單價 */
    @Schema(description = "委賣單價")
    private BigDecimal reedPrice;
    /** 交易生效日 */
    @Schema(description = "交易生效日")
    private Date trscDate;
    /** 營業時間註記 */
    @Schema(description = "營業時間註記")
    private String markedDate;
    /** 客戶風險適格等級 */
    @Schema(description = "客戶風險適格等級")
    private String custLevel;
    /** 暫停交易 */
    @Schema(description = "暫停交易")
    private String pauseFlg;
    /** 訊息編號 */
    @Schema(description = "訊息編號")
    private String respCode;
    /** 圈存狀態，SN (1:圈存 2:解圈扣款)、海外債(固定空白) */
    @Schema(description = "圈存狀態，SN (1:圈存 2:解圈扣款)、海外債(固定空白)")
    private String loanSts;

    // ======================== 以下為擴充欄位 ===========================

    /** 商品幣別名稱 */
    @Schema(description = "商品幣別名稱")
    private String curName;

    /** 交易幣別名稱 */
    @Schema(description = "交易幣別名稱")
    private String trustCurName;

    // private BigDecimal totalPieRefAmt = BigDecimal.ZERO;
    //
    // private BigDecimal totalPieInvestAmt = BigDecimal.ZERO;

    // public void addMoney(BondOverview bo) {
    // this.totalPieRefAmt = this.addMoney(totalPieRefAmt, bo.getPieRefAmt());
    // this.totalPieInvestAmt = this.addMoney(totalPieInvestAmt, bo.getTrustAmtNT());
    // }

    // /** 庫存(0001)、申購在途(1001)、贖回在途(1002) */
    // public BigDecimal getPieRefAmt() {
    // if (bondTxType.isInventory()) {
    // return this.refAmtNT;
    // }
    // else if (bondTxType.isSubscriptionInTransit()) {
    // return this.trustAmtNT;
    // }
    // else if (bondTxType.isRedemptionInTransit()) {
    // return this.refAmtNT;
    // }
    // else {
    // return BigDecimal.ZERO;
    // }
    // }

    @Override
    public int compareTo(BondOverview o) {
        if (o == null) {
            return -1;
        }
        else if (this == o) {
            return 0;
        }

        int res = this.intRate.compareTo(o.getIntRate());

        return -res;
    }

    public BondTxType getBondTxType() {
        return bondTxType;
    }

    public void setBondTxType(BondTxType bondTxType) {
        this.bondTxType = bondTxType;
    }

    public String getSubAcctId() {
        return subAcctId;
    }

    public void setSubAcctId(String subAcctId) {
        this.subAcctId = subAcctId;
    }

    public String getEviNum() {
        return eviNum;
    }

    public void setEviNum(String eviNum) {
        this.eviNum = eviNum;
    }

    public String getBondISIN() {
        return bondISIN;
    }

    public void setBondISIN(String bondISIN) {
        this.bondISIN = bondISIN;
    }

    public String getBondNO() {
        return bondNO;
    }

    public void setBondNO(String bondNO) {
        this.bondNO = bondNO;
    }

    public String getBondName() {
        return bondName;
    }

    public void setBondName(String bondName) {
        this.bondName = bondName;
    }

    public String getCurCode() {
        return curCode;
    }

    public void setCurCode(String curCode) {
        this.curCode = curCode;
    }

    public String getTrustCur() {
        return trustCur;
    }

    public void setTrustCur(String trustCur) {
        this.trustCur = trustCur;
    }

    public String getIntCur() {
        return intCur;
    }

    public void setIntCur(String intCur) {
        this.intCur = intCur;
    }

    public BigDecimal getInvVal() {
        return invVal;
    }

    public void setInvVal(BigDecimal invVal) {
        this.invVal = invVal;
    }

    public BigDecimal getUntVal() {
        return untVal;
    }

    public void setUntVal(BigDecimal untVal) {
        this.untVal = untVal;
    }

    public BigDecimal getUntNum() {
        return untNum;
    }

    public void setUntNum(BigDecimal untNum) {
        this.untNum = untNum;
    }

    public BigDecimal getTrustAmt() {
        return trustAmt;
    }

    public void setTrustAmt(BigDecimal trustAmt) {
        this.trustAmt = trustAmt;
    }

    public BigDecimal getTrustAmtNT() {
        return trustAmtNT;
    }

    public void setTrustAmtNT(BigDecimal trustAmtNT) {
        this.trustAmtNT = trustAmtNT;
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

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getAdjustInterest() {
        return adjustInterest;
    }

    public void setAdjustInterest(BigDecimal adjustInterest) {
        this.adjustInterest = adjustInterest;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public BigDecimal getIntRate() {
        return intRate;
    }

    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getRefExRate() {
        return refExRate;
    }

    public void setRefExRate(BigDecimal refExRate) {
        this.refExRate = refExRate;
    }

    public Date getRefDt() {
        return refDt;
    }

    public void setRefDt(Date refDt) {
        this.refDt = refDt;
    }

    public BigDecimal getRefValue() {
        return refValue;
    }

    public void setRefValue(BigDecimal refValue) {
        this.refValue = refValue;
    }

    public Date getStrdate() {
        return strdate;
    }

    public void setStrdate(Date strdate) {
        this.strdate = strdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public String getRcvAcct() {
        return rcvAcct;
    }

    public void setRcvAcct(String rcvAcct) {
        this.rcvAcct = rcvAcct;
    }

    public String getTrustType() {
        return trustType;
    }

    public void setTrustType(String trustType) {
        this.trustType = trustType;
    }

    public String getCallBack() {
        return callBack;
    }

    public void setCallBack(String callBack) {
        this.callBack = callBack;
    }

    public String getParCallBack() {
        return parCallBack;
    }

    public void setParCallBack(String parCallBack) {
        this.parCallBack = parCallBack;
    }

    public String getIsRedemption() {
        return isRedemption;
    }

    public void setIsRedemption(String isRedemption) {
        this.isRedemption = isRedemption;
    }

    public String getIsChange() {
        return isChange;
    }

    public void setIsChange(String isChange) {
        this.isChange = isChange;
    }

    public Date getBackDt() {
        return backDt;
    }

    public void setBackDt(Date backDt) {
        this.backDt = backDt;
    }

    public BigDecimal getBackAmt() {
        return backAmt;
    }

    public void setBackAmt(BigDecimal backAmt) {
        this.backAmt = backAmt;
    }

    public BigDecimal getOnWayAmt() {
        return onWayAmt;
    }

    public void setOnWayAmt(BigDecimal onWayAmt) {
        this.onWayAmt = onWayAmt;
    }

    public BigDecimal getFrontfee1() {
        return frontfee1;
    }

    public void setFrontfee1(BigDecimal frontfee1) {
        this.frontfee1 = frontfee1;
    }

    public BigDecimal getFrontfee2() {
        return frontfee2;
    }

    public void setFrontfee2(BigDecimal frontfee2) {
        this.frontfee2 = frontfee2;
    }

    public Date getTrustDt() {
        return trustDt;
    }

    public void setTrustDt(Date trustDt) {
        this.trustDt = trustDt;
    }

    public BigDecimal getTrustVal() {
        return trustVal;
    }

    public void setTrustVal(BigDecimal trustVal) {
        this.trustVal = trustVal;
    }

    public String getReedType() {
        return reedType;
    }

    public void setReedType(String reedType) {
        this.reedType = reedType;
    }

    public BigDecimal getReedPrice() {
        return reedPrice;
    }

    public void setReedPrice(BigDecimal reedPrice) {
        this.reedPrice = reedPrice;
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

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getPauseFlg() {
        return pauseFlg;
    }

    public void setPauseFlg(String pauseFlg) {
        this.pauseFlg = pauseFlg;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    /**
     * @return the curName
     */
    public String getCurName() {
        return curName;
    }

    /**
     * @param curName
     *            the curName to set
     */
    public void setCurName(String curName) {
        this.curName = curName;
    }

    /**
     * @return the trustCurName
     */
    public String getTrustCurName() {
        return trustCurName;
    }

    /**
     * @param trustCurName
     *            the trustCurName to set
     */
    public void setTrustCurName(String trustCurName) {
        this.trustCurName = trustCurName;
    }

    public String getLoanSts() {
        return loanSts;
    }

    public void setLoanSts(String loanSts) {
        this.loanSts = loanSts;
    }
}
