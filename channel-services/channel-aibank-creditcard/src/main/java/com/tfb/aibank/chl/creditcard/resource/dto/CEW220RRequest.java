package com.tfb.aibank.chl.creditcard.resource.dto;

import java.io.Serializable;

// @formatter:off
/**
 * @(#)CEW220RRequest.java
 * 
 * <p>Description:指定消費分期試算交易電文(CEW220R) 上行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/22, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class CEW220RRequest implements Serializable {

    private static final long serialVersionUID = 8812810868426412630L;

    /** 分期種類 */
    private String txtype;

    /** 消費金額 */
    private String txamt;

    /** 分期期數 */
    private String txstge;

    /** 消費日期 */
    private String ncbpchd;

    /** 歸戶id */
    private String txacid;

    /** 卡號 */
    private String custCdno;

    /** 扣帳順序 */
    private String ncsbsq;

    /** 入帳日 */
    private String nccday;

    /** 產生組別 */
    private String ncgrop;

    /** 交易序號 */
    private String ncseqn;

    /**
     * @return the txtype
     */
    public String getTxtype() {
        return txtype;
    }

    /**
     * @param txtype
     *            the txtype to set
     */
    public void setTxtype(String txtype) {
        this.txtype = txtype;
    }

    /**
     * @return the txamt
     */
    public String getTxamt() {
        return txamt;
    }

    /**
     * @param txamt
     *            the txamt to set
     */
    public void setTxamt(String txamt) {
        this.txamt = txamt;
    }

    /**
     * @return the txstge
     */
    public String getTxstge() {
        return txstge;
    }

    /**
     * @param txstge
     *            the txstge to set
     */
    public void setTxstge(String txstge) {
        this.txstge = txstge;
    }

    /**
     * @return the ncbpchd
     */
    public String getNcbpchd() {
        return ncbpchd;
    }

    /**
     * @param ncbpchd
     *            the ncbpchd to set
     */
    public void setNcbpchd(String ncbpchd) {
        this.ncbpchd = ncbpchd;
    }

    /**
     * @return the txacid
     */
    public String getTxacid() {
        return txacid;
    }

    /**
     * @param txacid
     *            the txacid to set
     */
    public void setTxacid(String txacid) {
        this.txacid = txacid;
    }

    /**
     * @return the custCdno
     */
    public String getCustCdno() {
        return custCdno;
    }

    /**
     * @param custCdno
     *            the custCdno to set
     */
    public void setCustCdno(String custCdno) {
        this.custCdno = custCdno;
    }

    /**
     * @return the ncsbsq
     */
    public String getNcsbsq() {
        return ncsbsq;
    }

    /**
     * @param ncsbsq
     *            the ncsbsq to set
     */
    public void setNcsbsq(String ncsbsq) {
        this.ncsbsq = ncsbsq;
    }

    /**
     * @return the nccday
     */
    public String getNccday() {
        return nccday;
    }

    /**
     * @param nccday
     *            the nccday to set
     */
    public void setNccday(String nccday) {
        this.nccday = nccday;
    }

    /**
     * @return the ncgrop
     */
    public String getNcgrop() {
        return ncgrop;
    }

    /**
     * @param ncgrop
     *            the ncgrop to set
     */
    public void setNcgrop(String ncgrop) {
        this.ncgrop = ncgrop;
    }

    /**
     * @return the ncseqn
     */
    public String getNcseqn() {
        return ncseqn;
    }

    /**
     * @param ncseqn
     *            the ncseqn to set
     */
    public void setNcseqn(String ncseqn) {
        this.ncseqn = ncseqn;
    }

}
