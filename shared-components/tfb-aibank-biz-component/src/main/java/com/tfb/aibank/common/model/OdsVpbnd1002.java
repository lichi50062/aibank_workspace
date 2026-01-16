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
package com.tfb.aibank.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

// @formatter:off
/**
 * @(#)OdsVpbnd1002.java
 * 
 * <p>Description:外國債券(自營)庫存檔 Model</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/09/04, Leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class OdsVpbnd1002 implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 基準日
     */
    private Date mCrtBnd12;

    /**
     * 交易單號
     */
    private BigDecimal mNb;
    /**
     * ISIN_CODE
     */
    private String mSeCode;

    /**
     * 交易對手(PER)(加密ID)
     */
    private String mTpCntrp;

    /**
     * 產品別
     */
    private String mTrnGrp;

    /**
     * PAYOUT
     */
    private String payout;

    /**
     * 已付前手息
     */
    private BigDecimal accruedAmtPay;

    /**
     * 台幣已付前手息
     */
    private BigDecimal accruedAmtPayTwd;

    /**
     * 應收前手息(預估)
     */
    private BigDecimal accruedAmtRcv;

    /**
     * 台幣應收前手息
     */
    private BigDecimal accruedAmtRcvTwd;

    /**
     * 累計現金配息
     */
    private BigDecimal accumulatedInterest;

    /**
     * 台幣累計現金配息
     */
    private BigDecimal accumulatedInterestTwd;

    /**
     * 擔保品設質註記
     */
    private String collSettintFlag;

    /**
     * 投資成本
     */
    private BigDecimal investCostAmt;

    /**
     * 投資成本台幣
     */
    private BigDecimal investCostAmtTwd;
    /**
     * 是否RS
     */
    private String isRs;

    /**
     * 債券名稱
     */
    private String mInstrument;

    /**
     * 發行人
     */
    private String mIssuerNm;

    /**
     * 交易對手(PER)(ID)_MASK
     */
    private String mTpCntrpMask;

    /**
     * 交易幣別
     */
    private String mTpNomcur;

    /**
     * 面額
     */
    private BigDecimal mTpNominal;

    /**
     * 成交價
     */
    private BigDecimal mTpPriced;

    /**
     * 參考市值
     */
    private BigDecimal marketValueAmt;

    /**
     * 參考市值台幣
     */
    private BigDecimal marketValueAmtTwd;

    /**
     * 損益預估(不含息)
     */
    private BigDecimal nonintPnlAmt;

    /**
     * 台幣損益預估(不含息)
     */
    private BigDecimal nonintPnlAmtTwd;

    /**
     * 損益預估(含息)
     */
    private BigDecimal pnlAmt;

    /**
     * 台幣損益預估(含息)
     */
    private BigDecimal pnlAmtTwd;

    /**
     * 參考贖回報價
     */
    private BigDecimal qutoation;

    /**
     * REPO承作金額
     */
    private BigDecimal repoStartNominal;

    /**
     * 含息報酬率
     */
    private BigDecimal roiWithInt;

    /**
     * 資料日期
     */
    private Date snapDate;

    /**
     * 交易日
     */
    private Date txnDate;

    /**
     * @return the mCrtBnd12
     */
    public Date getMCrtBnd12() {
        return mCrtBnd12;
    }

    /**
     * @param mCrtBnd12
     *            the mCrtBnd12 to set
     */
    public void setMCrtBnd12(Date mCrtBnd12) {
        this.mCrtBnd12 = mCrtBnd12;
    }

    /**
     * @return the mNb
     */
    public BigDecimal getMNb() {
        return mNb;
    }

    /**
     * @param mNb
     *            the mNb to set
     */
    public void setMNb(BigDecimal mNb) {
        this.mNb = mNb;
    }

    /**
     * @return the mSeCode
     */
    public String getMSeCode() {
        return mSeCode;
    }

    /**
     * @param mSeCode
     *            the mSeCode to set
     */
    public void setMSeCode(String mSeCode) {
        this.mSeCode = mSeCode;
    }

    /**
     * @return the mTpCntrp
     */
    public String getMTpCntrp() {
        return mTpCntrp;
    }

    /**
     * @param mTpCntrp
     *            the mTpCntrp to set
     */
    public void setMTpCntrp(String mTpCntrp) {
        this.mTpCntrp = mTpCntrp;
    }

    /**
     * @return the mTrnGrp
     */
    public String getMTrnGrp() {
        return mTrnGrp;
    }

    /**
     * @param mTrnGrp
     *            the mTrnGrp to set
     */
    public void setMTrnGrp(String mTrnGrp) {
        this.mTrnGrp = mTrnGrp;
    }

    /**
     * @return the payout
     */
    public String getPayout() {
        return payout;
    }

    /**
     * @param payout
     *            the payout to set
     */
    public void setPayout(String payout) {
        this.payout = payout;
    }

    /**
     * @return the accruedAmtPay
     */
    public BigDecimal getAccruedAmtPay() {
        return accruedAmtPay;
    }

    /**
     * @param accruedAmtPay
     *            the accruedAmtPay to set
     */
    public void setAccruedAmtPay(BigDecimal accruedAmtPay) {
        this.accruedAmtPay = accruedAmtPay;
    }

    /**
     * @return the accruedAmtPayTwd
     */
    public BigDecimal getAccruedAmtPayTwd() {
        return accruedAmtPayTwd;
    }

    /**
     * @param accruedAmtPayTwd
     *            the accruedAmtPayTwd to set
     */
    public void setAccruedAmtPayTwd(BigDecimal accruedAmtPayTwd) {
        this.accruedAmtPayTwd = accruedAmtPayTwd;
    }

    /**
     * @return the accruedAmtRcv
     */
    public BigDecimal getAccruedAmtRcv() {
        return accruedAmtRcv;
    }

    /**
     * @param accruedAmtRcv
     *            the accruedAmtRcv to set
     */
    public void setAccruedAmtRcv(BigDecimal accruedAmtRcv) {
        this.accruedAmtRcv = accruedAmtRcv;
    }

    /**
     * @return the accruedAmtRcvTwd
     */
    public BigDecimal getAccruedAmtRcvTwd() {
        return accruedAmtRcvTwd;
    }

    /**
     * @param accruedAmtRcvTwd
     *            the accruedAmtRcvTwd to set
     */
    public void setAccruedAmtRcvTwd(BigDecimal accruedAmtRcvTwd) {
        this.accruedAmtRcvTwd = accruedAmtRcvTwd;
    }

    /**
     * @return the accumulatedInterest
     */
    public BigDecimal getAccumulatedInterest() {
        return accumulatedInterest;
    }

    /**
     * @param accumulatedInterest
     *            the accumulatedInterest to set
     */
    public void setAccumulatedInterest(BigDecimal accumulatedInterest) {
        this.accumulatedInterest = accumulatedInterest;
    }

    /**
     * @return the accumulatedInterestTwd
     */
    public BigDecimal getAccumulatedInterestTwd() {
        return accumulatedInterestTwd;
    }

    /**
     * @param accumulatedInterestTwd
     *            the accumulatedInterestTwd to set
     */
    public void setAccumulatedInterestTwd(BigDecimal accumulatedInterestTwd) {
        this.accumulatedInterestTwd = accumulatedInterestTwd;
    }

    /**
     * @return the collSettintFlag
     */
    public String getCollSettintFlag() {
        return collSettintFlag;
    }

    /**
     * @param collSettintFlag
     *            the collSettintFlag to set
     */
    public void setCollSettintFlag(String collSettintFlag) {
        this.collSettintFlag = collSettintFlag;
    }

    /**
     * @return the investCostAmt
     */
    public BigDecimal getInvestCostAmt() {
        return investCostAmt;
    }

    /**
     * @param investCostAmt
     *            the investCostAmt to set
     */
    public void setInvestCostAmt(BigDecimal investCostAmt) {
        this.investCostAmt = investCostAmt;
    }

    /**
     * @return the investCostAmtTwd
     */
    public BigDecimal getInvestCostAmtTwd() {
        return investCostAmtTwd;
    }

    /**
     * @param investCostAmtTwd
     *            the investCostAmtTwd to set
     */
    public void setInvestCostAmtTwd(BigDecimal investCostAmtTwd) {
        this.investCostAmtTwd = investCostAmtTwd;
    }

    /**
     * @return the isRs
     */
    public String getIsRs() {
        return isRs;
    }

    /**
     * @param isRs
     *            the isRs to set
     */
    public void setIsRs(String isRs) {
        this.isRs = isRs;
    }

    /**
     * @return the mInstrument
     */
    public String getMInstrument() {
        return mInstrument;
    }

    /**
     * @param mInstrument
     *            the mInstrument to set
     */
    public void setMInstrument(String mInstrument) {
        this.mInstrument = mInstrument;
    }

    /**
     * @return the mIssuerNm
     */
    public String getMIssuerNm() {
        return mIssuerNm;
    }

    /**
     * @param mIssuerNm
     *            the mIssuerNm to set
     */
    public void setMIssuerNm(String mIssuerNm) {
        this.mIssuerNm = mIssuerNm;
    }

    /**
     * @return the mTpCntrpMask
     */
    public String getMTpCntrpMask() {
        return mTpCntrpMask;
    }

    /**
     * @param mTpCntrpMask
     *            the mTpCntrpMask to set
     */
    public void setMTpCntrpMask(String mTpCntrpMask) {
        this.mTpCntrpMask = mTpCntrpMask;
    }

    /**
     * @return the mTpNomcur
     */
    public String getMTpNomcur() {
        return mTpNomcur;
    }

    /**
     * @param mTpNomcur
     *            the mTpNomcur to set
     */
    public void setMTpNomcur(String mTpNomcur) {
        this.mTpNomcur = mTpNomcur;
    }

    /**
     * @return the mTpNominal
     */
    public BigDecimal getMTpNominal() {
        return mTpNominal;
    }

    /**
     * @param mTpNominal
     *            the mTpNominal to set
     */
    public void setMTpNominal(BigDecimal mTpNominal) {
        this.mTpNominal = mTpNominal;
    }

    /**
     * @return the mTpPriced
     */
    public BigDecimal getMTpPriced() {
        return mTpPriced;
    }

    /**
     * @param mTpPriced
     *            the mTpPriced to set
     */
    public void setMTpPriced(BigDecimal mTpPriced) {
        this.mTpPriced = mTpPriced;
    }

    /**
     * @return the marketValueAmt
     */
    public BigDecimal getMarketValueAmt() {
        return marketValueAmt;
    }

    /**
     * @param marketValueAmt
     *            the marketValueAmt to set
     */
    public void setMarketValueAmt(BigDecimal marketValueAmt) {
        this.marketValueAmt = marketValueAmt;
    }

    /**
     * @return the marketValueAmtTwd
     */
    public BigDecimal getMarketValueAmtTwd() {
        return marketValueAmtTwd;
    }

    /**
     * @param marketValueAmtTwd
     *            the marketValueAmtTwd to set
     */
    public void setMarketValueAmtTwd(BigDecimal marketValueAmtTwd) {
        this.marketValueAmtTwd = marketValueAmtTwd;
    }

    /**
     * @return the nonintPnlAmt
     */
    public BigDecimal getNonintPnlAmt() {
        return nonintPnlAmt;
    }

    /**
     * @param nonintPnlAmt
     *            the nonintPnlAmt to set
     */
    public void setNonintPnlAmt(BigDecimal nonintPnlAmt) {
        this.nonintPnlAmt = nonintPnlAmt;
    }

    /**
     * @return the nonintPnlAmtTwd
     */
    public BigDecimal getNonintPnlAmtTwd() {
        return nonintPnlAmtTwd;
    }

    /**
     * @param nonintPnlAmtTwd
     *            the nonintPnlAmtTwd to set
     */
    public void setNonintPnlAmtTwd(BigDecimal nonintPnlAmtTwd) {
        this.nonintPnlAmtTwd = nonintPnlAmtTwd;
    }

    /**
     * @return the pnlAmt
     */
    public BigDecimal getPnlAmt() {
        return pnlAmt;
    }

    /**
     * @param pnlAmt
     *            the pnlAmt to set
     */
    public void setPnlAmt(BigDecimal pnlAmt) {
        this.pnlAmt = pnlAmt;
    }

    /**
     * @return the pnlAmtTwd
     */
    public BigDecimal getPnlAmtTwd() {
        return pnlAmtTwd;
    }

    /**
     * @param pnlAmtTwd
     *            the pnlAmtTwd to set
     */
    public void setPnlAmtTwd(BigDecimal pnlAmtTwd) {
        this.pnlAmtTwd = pnlAmtTwd;
    }

    /**
     * @return the qutoation
     */
    public BigDecimal getQutoation() {
        return qutoation;
    }

    /**
     * @param qutoation
     *            the qutoation to set
     */
    public void setQutoation(BigDecimal qutoation) {
        this.qutoation = qutoation;
    }

    /**
     * @return the repoStartNominal
     */
    public BigDecimal getRepoStartNominal() {
        return repoStartNominal;
    }

    /**
     * @param repoStartNominal
     *            the repoStartNominal to set
     */
    public void setRepoStartNominal(BigDecimal repoStartNominal) {
        this.repoStartNominal = repoStartNominal;
    }

    /**
     * @return the roiWithInt
     */
    public BigDecimal getRoiWithInt() {
        return roiWithInt;
    }

    /**
     * @param roiWithInt
     *            the roiWithInt to set
     */
    public void setRoiWithInt(BigDecimal roiWithInt) {
        this.roiWithInt = roiWithInt;
    }

    /**
     * @return the snapDate
     */
    public Date getSnapDate() {
        return snapDate;
    }

    /**
     * @param snapDate
     *            the snapDate to set
     */
    public void setSnapDate(Date snapDate) {
        this.snapDate = snapDate;
    }

    /**
     * @return the txnDate
     */
    public Date getTxnDate() {
        return txnDate;
    }

    /**
     * @param txnDate
     *            the txnDate to set
     */
    public void setTxnDate(Date txnDate) {
        this.txnDate = txnDate;
    }

}
