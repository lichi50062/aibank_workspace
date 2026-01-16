package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;
import java.util.Date;

//@formatter:off
/**
* @(#)CEW205RRepeat.java
* 
* <p>Description:CEW205R 結帳日前消費明細查詢 API下行txRepeat.</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW205RRepeat {

    /** 卡號 X(16) */
    private String ncbCard;
    /** 消費日 X(7) */
    private Date ncbPchd;
    /** 入帳日 X(7) */
    private Date ncbNccd;
    /** 消費說明 X(40) */
    private String ncbTxt1;
    /** 台幣消費金額 X(9) */
    private BigDecimal ncbTwd;
    /** 正負符號 X(1) */
    private String ncbSign;
    /** 幣別 X(3) */
    private String srcCur;
    /** 原始地金額 X(12) */
    private BigDecimal srcAmt;
    /** 扣帳順序 X(2) */
    private String ncSbsq;
    /** NCC清算日 X(7) */
    private Date ncCday;
    /** 產生組別 X(11) */
    private String ncGrop;
    /** 交易序號 X(7) */
    private String ncSeqn;
    /** 是否可分期 X(1) */
    private String oreFlag;
    /** 分期種類 X(4) */
    private String txType;
    /** 行動支付卡號 X(16) */
    private String mpayCd;
    /** 錢包類別 X(1) */
    private String mptTyp;
    /** 卡片暱稱 X(14) */
    private String vnnkNm;

    /**
     * @return the ncbCard
     */
    public String getNcbCard() {
        return ncbCard;
    }

    /**
     * @param ncbCard
     *            the ncbCard to set
     */
    public void setNcbCard(String ncbCard) {
        this.ncbCard = ncbCard;
    }

    /**
     * @return the ncbPchd
     */
    public Date getNcbPchd() {
        return ncbPchd;
    }

    /**
     * @param ncbPchd
     *            the ncbPchd to set
     */
    public void setNcbPchd(Date ncbPchd) {
        this.ncbPchd = ncbPchd;
    }

    /**
     * @return the ncbNccd
     */
    public Date getNcbNccd() {
        return ncbNccd;
    }

    /**
     * @param ncbNccd
     *            the ncbNccd to set
     */
    public void setNcbNccd(Date ncbNccd) {
        this.ncbNccd = ncbNccd;
    }

    /**
     * @return the ncbTxt1
     */
    public String getNcbTxt1() {
        return ncbTxt1;
    }

    /**
     * @param ncbTxt1
     *            the ncbTxt1 to set
     */
    public void setNcbTxt1(String ncbTxt1) {
        this.ncbTxt1 = ncbTxt1;
    }

    /**
     * @return the ncbTwd
     */
    public BigDecimal getNcbTwd() {
        return ncbTwd;
    }

    /**
     * @param ncbTwd
     *            the ncbTwd to set
     */
    public void setNcbTwd(BigDecimal ncbTwd) {
        this.ncbTwd = ncbTwd;
    }

    /**
     * @return the ncbSign
     */
    public String getNcbSign() {
        return ncbSign;
    }

    /**
     * @param ncbSign
     *            the ncbSign to set
     */
    public void setNcbSign(String ncbSign) {
        this.ncbSign = ncbSign;
    }

    /**
     * @return the srcCur
     */
    public String getSrcCur() {
        return srcCur;
    }

    /**
     * @param srcCur
     *            the srcCur to set
     */
    public void setSrcCur(String srcCur) {
        this.srcCur = srcCur;
    }

    /**
     * @return the srcAmt
     */
    public BigDecimal getSrcAmt() {
        return srcAmt;
    }

    /**
     * @param srcAmt
     *            the srcAmt to set
     */
    public void setSrcAmt(BigDecimal srcAmt) {
        this.srcAmt = srcAmt;
    }

    /**
     * @return the ncSbsq
     */
    public String getNcSbsq() {
        return ncSbsq;
    }

    /**
     * @param ncSbsq
     *            the ncSbsq to set
     */
    public void setNcSbsq(String ncSbsq) {
        this.ncSbsq = ncSbsq;
    }

    /**
     * @return the ncCday
     */
    public Date getNcCday() {
        return ncCday;
    }

    /**
     * @param ncCday
     *            the ncCday to set
     */
    public void setNcCday(Date ncCday) {
        this.ncCday = ncCday;
    }

    /**
     * @return the ncGrop
     */
    public String getNcGrop() {
        return ncGrop;
    }

    /**
     * @param ncGrop
     *            the ncGrop to set
     */
    public void setNcGrop(String ncGrop) {
        this.ncGrop = ncGrop;
    }

    /**
     * @return the ncSeqn
     */
    public String getNcSeqn() {
        return ncSeqn;
    }

    /**
     * @param ncSeqn
     *            the ncSeqn to set
     */
    public void setNcSeqn(String ncSeqn) {
        this.ncSeqn = ncSeqn;
    }

    /**
     * @return the oreFlag
     */
    public String getOreFlag() {
        return oreFlag;
    }

    /**
     * @param oreFlag
     *            the oreFlag to set
     */
    public void setOreFlag(String oreFlag) {
        this.oreFlag = oreFlag;
    }

    /**
     * @return the txType
     */
    public String getTxType() {
        return txType;
    }

    /**
     * @param txType
     *            the txType to set
     */
    public void setTxType(String txType) {
        this.txType = txType;
    }

    /**
     * @return the mpayCd
     */
    public String getMpayCd() {
        return mpayCd;
    }

    /**
     * @param mpayCd
     *            the mpayCd to set
     */
    public void setMpayCd(String mpayCd) {
        this.mpayCd = mpayCd;
    }

    /**
     * @return the mptTyp
     */
    public String getMptTyp() {
        return mptTyp;
    }

    /**
     * @param mptTyp
     *            the mptTyp to set
     */
    public void setMptTyp(String mptTyp) {
        this.mptTyp = mptTyp;
    }

    /**
     * @return the vnnkNm
     */
    public String getVnnkNm() {
        return vnnkNm;
    }

    /**
     * @param vnnkNm
     *            the vnnkNm to set
     */
    public void setVnnkNm(String vnnkNm) {
        this.vnnkNm = vnnkNm;
    }

}
