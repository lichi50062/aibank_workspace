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

import java.math.BigDecimal;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.common.type.HybridProdType;

// @formatter:off
/**
 * @(#)CombinedProdOverview.java
 * 
 * <p>Description:[組合式商品]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/03, Leiley 
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class HybridProdOverview {

    public HybridProdOverview() {
        // default constructor
        super();
    }

    public HybridProdOverview(HybridProdType prodType) {
        this.prodType = prodType;
    }

    /** SI(SPWEBINQ)、DCI(SPWEBQ2、BKDCD001) */
    private HybridProdType prodType = HybridProdType.UNKNOWN;

    /** 類型 1:SI, 2:DCI SPWEBQ2 , 3:DCI BKDCD001 */
    private String category;

    /** 商品名稱 */
    private String prdNam;

    /** 商品幣別 */
    private String ccy;

    /** 商品幣別 */
    private String ccyCode;

    /** 投資金額 */
    private BigDecimal ivamt2;

    /** 投資金額 */
    private BigDecimal investAmt;
    /** 投資金額 */
    private BigDecimal dcdAmount;
    /** 投資金額 */
    private BigDecimal dcdAmountNtd;

    /** 參考市值(折台). */
    private BigDecimal refAmt;

    /** 參考市值(折台). */
    private BigDecimal refAmtNtd;

    /** 參考市值. */
    private BigDecimal refAmtOri;

    /** SI現金配息 */
    private BigDecimal plamt;

    /** DCI配息金額 */
    private BigDecimal interestAmount;

    public HybridProdOverview(StructuredOverview bo) {
        if (bo.getProdType().isSPWEBINQ()) {
            this.prodType = HybridProdType.SPWEBINQ;
            this.category = "1";
            this.prdNam = StringUtils.defaultString(bo.getPrdNam());
            this.ccyCode = bo.getCcy();
            this.ivamt2 = bo.getIvamt2();
            this.refAmt = bo.getRefAmt();
            this.refAmtOri = bo.getRefAmtOri();

            // ({SI參考投資市值}+{SI現金配息}-{SI投資金額})
            // (3) {SI參考投資市值}資料來源：SPWEBINQ_Rs.(CCY+)RefAmt(整數位)。
            // (4) {SI現金配息}資料來源：SPWEBINQ_Rs.(CCY+)PLAMT。
            // (5) {SI投資金額}資料來源：SPWEBINQ_Rs.(CCY+)IVAMT2。
            this.plamt = bo.getPlamt();
        }
        else if (bo.getProdType().isSPWEBQ2()) {
            this.prodType = HybridProdType.SPWEBQ2;
            // ({SI參考投資市值}+{SI現金配息}-{SI投資金額})
            // (3) {SI參考投資市值}資料來源：SPWEBQ2_Rs.RefAmt (整數位)。
            // (4) {SI現金配息}資料來源：固定顯示0 (無配息)
            // (5) {SI投資金額}資料來源：SPWEBQ2_Rs. (CurCode+)InvestAmt
            this.category = "2";
            this.ccyCode = bo.getCurCode();
            this.investAmt = bo.getInvestAmt();
            this.refAmt = bo.getRefAmt();
            this.refAmtOri = bo.getRefAmtOri();
            this.plamt = bo.getPlamt();
        }
        else if (bo.getProdType().isBKDCD001()) {
            this.prodType = HybridProdType.BKDCD001;
            // (2) {DCI含息報酬}計算方式：{DCI參考投資市值}+{DCI配息金額}-{DCI投資金額}。
            // (3) {DCI含息報酬率}計算方式：({DCI參考投資市值}+{DCI配息金額}-{DCI投資金額})/{DCI投資金額}*100%
            // (3) {DCI參考投資市值}資料來源：BKDCD001_Rs.DCDCURAMOUNT(小數二位)/DCDCURAMOUNTNTD(整數位)。
            // (4) {DCI投資金額}資料來源：BKDCD001_Rs. (CURRENCY+)DCDAMOUNT/DCDAMOUNTNTD。
            // (5) {DCI配息金額}資料來源：BKDCD001_Rs. (CURRENCY+) INTERESTAMOUNT。
            this.category = "3";
            this.ccyCode = bo.getCurrency();
            this.dcdAmount = bo.getDcdAmount();
            this.dcdAmountNtd = bo.getDcdAmountNtd();
            this.refAmt = bo.getDcdCurAmount();
            this.refAmtNtd = bo.getDcdCurAmountNtd();
            this.interestAmount = bo.getInterestAmount();
        }
    }

    /**
     * @return the prodType
     */
    public HybridProdType getProdType() {
        return prodType;
    }

    /**
     * @param prodType
     *            the prodType to set
     */
    public void setProdType(HybridProdType prodType) {
        this.prodType = prodType;
    }

    /**
     * @return {@link #category}
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category
     *            {@link #category}
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return {@link #prdNam}
     */
    public String getPrdNam() {
        return prdNam;
    }

    /**
     * @param prdNam
     *            {@link #prdNam}
     */
    public void setPrdNam(String prdNam) {
        this.prdNam = prdNam;
    }

    /**
     * @return {@link #ccy}
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * @param ccy
     *            {@link #ccy}
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * @return {@link #ccyCode}
     */
    public String getCcyCode() {
        return ccyCode;
    }

    /**
     * @param ccyCode
     *            {@link #ccyCode}
     */
    public void setCcyCode(String ccyCode) {
        this.ccyCode = ccyCode;
    }

    /**
     * @return the ivamt2
     */
    public BigDecimal getIvamt2() {
        return ivamt2;
    }

    /**
     * @param ivamt2
     *            the ivamt2 to set
     */
    public void setIvamt2(BigDecimal ivamt2) {
        this.ivamt2 = ivamt2;
    }

    /**
     * @return the investAmt
     */
    public BigDecimal getInvestAmt() {
        return investAmt;
    }

    /**
     * @param investAmt
     *            the investAmt to set
     */
    public void setInvestAmt(BigDecimal investAmt) {
        this.investAmt = investAmt;
    }

    /**
     * @return the dcdAmount
     */
    public BigDecimal getDcdAmount() {
        return dcdAmount;
    }

    /**
     * @param dcdAmount
     *            the dcdAmount to set
     */
    public void setDcdAmount(BigDecimal dcdAmount) {
        this.dcdAmount = dcdAmount;
    }

    /**
     * @return the dcdAmountNtd
     */
    public BigDecimal getDcdAmountNtd() {
        return dcdAmountNtd;
    }

    /**
     * @param dcdAmountNtd
     *            the dcdAmountNtd to set
     */
    public void setDcdAmountNtd(BigDecimal dcdAmountNtd) {
        this.dcdAmountNtd = dcdAmountNtd;
    }

    /**
     * @return the refAmt
     */
    public BigDecimal getRefAmt() {
        return refAmt;
    }

    /**
     * @param refAmt
     *            the refAmt to set
     */
    public void setRefAmt(BigDecimal refAmt) {
        this.refAmt = refAmt;
    }

    /**
     * @return the refAmtNtd
     */
    public BigDecimal getRefAmtNtd() {
        return refAmtNtd;
    }

    /**
     * @param refAmtNtd
     *            the refAmtNtd to set
     */
    public void setRefAmtNtd(BigDecimal refAmtNtd) {
        this.refAmtNtd = refAmtNtd;
    }

    /**
     * @return the refAmtOri
     */
    public BigDecimal getRefAmtOri() {
        return refAmtOri;
    }

    /**
     * @param refAmtOri
     *            the refAmtOri to set
     */
    public void setRefAmtOri(BigDecimal refAmtOri) {
        this.refAmtOri = refAmtOri;
    }

    /**
     * @return the plamt
     */
    public BigDecimal getPlamt() {
        return plamt;
    }

    /**
     * @param plamt
     *            the plamt to set
     */
    public void setPlamt(BigDecimal plamt) {
        this.plamt = plamt;
    }

    /**
     * @return the interestAmount
     */
    public BigDecimal getInterestAmount() {
        return interestAmount;
    }

    /**
     * @param interestAmount
     *            the interestAmount to set
     */
    public void setInterestAmount(BigDecimal interestAmount) {
        this.interestAmount = interestAmount;
    }
}
