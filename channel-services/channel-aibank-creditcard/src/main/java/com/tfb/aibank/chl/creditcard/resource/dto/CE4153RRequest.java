package com.tfb.aibank.chl.creditcard.resource.dto;

// @formatter:off
/**
 * @(#)CE4153RRequest.java
 * 
 * <p>Description:指定消費分期設定交易(CE4153R) 上行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/22, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class CE4153RRequest {

    /** 交易類別 */
    private String txtype;

    /** 歸戶 ID */
    private String pid;

    /** 分期金額 */
    private String txAmt;

    /** 卡號 */
    private String cdno;

    /** 消費日期 */
    private String pchday;

    /** 授權碼 */
    private String authCode;

    /** 分期期數 */
    private String period;

    /** 通路類別 */
    private String chel;

    /** 扣帳順序 */
    private String ncsbsq;

    /** 入帳日期 */
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
     * @return the pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * @param pid
     *            the pid to set
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * @return the txAmt
     */
    public String getTxAmt() {
        return txAmt;
    }

    /**
     * @param txAmt
     *            the txAmt to set
     */
    public void setTxAmt(String txAmt) {
        this.txAmt = txAmt;
    }

    /**
     * @return the cdno
     */
    public String getCdno() {
        return cdno;
    }

    /**
     * @param cdno
     *            the cdno to set
     */
    public void setCdno(String cdno) {
        this.cdno = cdno;
    }

    /**
     * @return the pchday
     */
    public String getPchday() {
        return pchday;
    }

    /**
     * @param pchday
     *            the pchday to set
     */
    public void setPchday(String pchday) {
        this.pchday = pchday;
    }

    /**
     * @return the authCode
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * @param authCode
     *            the authCode to set
     */
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    /**
     * @return the period
     */
    public String getPeriod() {
        return period;
    }

    /**
     * @param period
     *            the period to set
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * @return the chel
     */
    public String getChel() {
        return chel;
    }

    /**
     * @param chel
     *            the chel to set
     */
    public void setChel(String chel) {
        this.chel = chel;
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
