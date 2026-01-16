package com.tfb.aibank.chl.creditcard.resource.dto;

import java.io.Serializable;

// @formatter:off
/**
 * @(#)CEW221RRequest.java
 * 
 * <p>Description:單筆分期申請電文(CEW221R) 上行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/22, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class CEW221RRequest implements Serializable {

    private static final long serialVersionUID = 8812810868426412630L;

    /** 信用卡號 */
    private String custcdno;

    /** 檢查碼 */
    private String custcvv2;

    /** 保留一 */
    private String filler1;

    /** 保留二 */
    private String filler2;

    /** 歸戶ID */
    private String accid;

    /** 扣帳順序 */
    private String ncsbsq;

    /** NCC清算日 */
    private String nccday;

    /** 產生組別 */
    private String ncgrop;

    /** 交易序號 */
    private String ncseqn;

    /** 消費金額 */
    private String txamt;

    /** 分期種類 */
    private String txtype;

    /** 分期期數 */
    private String txstge;

    /** 通路別 */
    private String txchnl;

    /**
     * @return the custcdno
     */
    public String getCustcdno() {
        return custcdno;
    }

    /**
     * @param custcdno
     *            the custcdno to set
     */
    public void setCustcdno(String custcdno) {
        this.custcdno = custcdno;
    }

    /**
     * @return the custcvv2
     */
    public String getCustcvv2() {
        return custcvv2;
    }

    /**
     * @param custcvv2
     *            the custcvv2 to set
     */
    public void setCustcvv2(String custcvv2) {
        this.custcvv2 = custcvv2;
    }

    /**
     * @return the filler1
     */
    public String getFiller1() {
        return filler1;
    }

    /**
     * @param filler1
     *            the filler1 to set
     */
    public void setFiller1(String filler1) {
        this.filler1 = filler1;
    }

    /**
     * @return the filler2
     */
    public String getFiller2() {
        return filler2;
    }

    /**
     * @param filler2
     *            the filler2 to set
     */
    public void setFiller2(String filler2) {
        this.filler2 = filler2;
    }

    /**
     * @return the accid
     */
    public String getAccid() {
        return accid;
    }

    /**
     * @param accid
     *            the accid to set
     */
    public void setAccid(String accid) {
        this.accid = accid;
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
     * @return the txchnl
     */
    public String getTxchnl() {
        return txchnl;
    }

    /**
     * @param txchnl
     *            the txchnl to set
     */
    public void setTxchnl(String txchnl) {
        this.txchnl = txchnl;
    }

}
