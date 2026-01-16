package com.tfb.aibank.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

//@formatter:off
/**
* @(#)GoldOverview.java
* 
* <p>Description:[我的黃金存摺]</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/05/03, leiley 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class GoldOverview implements Serializable {

    private static final long serialVersionUID = 4024531596767768898L;

    /** 黃金帳號 */
    private String acno;

    /** 幣別 */
    private String cur;

    /** 幣別中文 */
    private String curName;

    /** c3庫存單位 */
    private BigDecimal bal;

    /** c4參考牌價(最新且有效) */
    private BigDecimal brdPrice;

    /** c5牌價日期 */
    private Date brdDate;

    /** c6牌價時間 */
    private Date brdTime;

    /** c7參考現值 */
    private BigDecimal pValue;

    /** c8平均成本(每單位成本) */
    private BigDecimal avgCost;

    /** c9損益正負 */
    private String yieldS;

    /** c10參考損益金額 */
    private BigDecimal yieldAmt;

    /** c11總投資金額 */
    private BigDecimal invAmt;

    /** c12參考損益% */
    private BigDecimal yield;

    /** 約定扣款日期 / 開放扣款日 */
    private String mDate;

    /** 每月投資日期 */
    private String mDateFormat;

    /** c14定期定額每期扣款金額 */
    private BigDecimal mAmt;

    /** c15定期定額狀態代碼 */
    private String mStsCod;

    /** c16定期定額狀態中 */
    private String mSts;

    /** 定期定額手續費 */
    private BigDecimal mFeeAmt;

    /** c18法扣單 */
    private BigDecimal detaAmt;

    /** c19申請開戶日 */
    private Date opnDate;

    /** c20上次異動日(最後一次事件變更 */
    private Date mtnDate;

    /** 計價單位(公克、盎司 */
    private String unit;

    public String getAcno() {
        return acno;
    }

    public void setAcno(String acno) {
        this.acno = acno;
    }

    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur;
    }

    public BigDecimal getBal() {
        return bal;
    }

    public void setBal(BigDecimal bal) {
        this.bal = bal;
    }

    public BigDecimal getBrdPrice() {
        return brdPrice;
    }

    public void setBrdPrice(BigDecimal brdPrice) {
        this.brdPrice = brdPrice;
    }

    public Date getBrdDate() {
        return brdDate;
    }

    public void setBrdDate(Date brdDate) {
        this.brdDate = brdDate;
    }

    public Date getBrdTime() {
        return brdTime;
    }

    public void setBrdTime(Date brdTime) {
        this.brdTime = brdTime;
    }

    public BigDecimal getpValue() {
        return pValue;
    }

    public void setpValue(BigDecimal pValue) {
        this.pValue = pValue;
    }

    public BigDecimal getAvgCost() {
        return avgCost;
    }

    public void setAvgCost(BigDecimal avgCost) {
        this.avgCost = avgCost;
    }

    public String getYieldS() {
        return yieldS;
    }

    public void setYieldS(String yieldS) {
        this.yieldS = yieldS;
    }

    public BigDecimal getYieldAmt() {
        return yieldAmt;
    }

    public void setYieldAmt(BigDecimal yieldAmt) {
        this.yieldAmt = yieldAmt;
    }

    public BigDecimal getInvAmt() {
        return invAmt;
    }

    public void setInvAmt(BigDecimal invAmt) {
        this.invAmt = invAmt;
    }

    public BigDecimal getYield() {
        return yield;
    }

    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public BigDecimal getmAmt() {
        return mAmt;
    }

    public void setmAmt(BigDecimal mAmt) {
        this.mAmt = mAmt;
    }

    public String getmStsCod() {
        return mStsCod;
    }

    public void setmStsCod(String mStsCod) {
        this.mStsCod = mStsCod;
    }

    public String getmSts() {
        return mSts;
    }

    public void setmSts(String mSts) {
        this.mSts = mSts;
    }

    public BigDecimal getmFeeAmt() {
        return mFeeAmt;
    }

    public void setmFeeAmt(BigDecimal mFeeAmt) {
        this.mFeeAmt = mFeeAmt;
    }

    public BigDecimal getDetaAmt() {
        return detaAmt;
    }

    public void setDetaAmt(BigDecimal detaAmt) {
        this.detaAmt = detaAmt;
    }

    public Date getOpnDate() {
        return opnDate;
    }

    public void setOpnDate(Date opnDate) {
        this.opnDate = opnDate;
    }

    public Date getMtnDate() {
        return mtnDate;
    }

    public void setMtnDate(Date mtnDate) {
        this.mtnDate = mtnDate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public String getmDateFormat() {
        return mDateFormat;
    }

    public void setmDateFormat(String mDateFormat) {
        this.mDateFormat = mDateFormat;
    }
}
