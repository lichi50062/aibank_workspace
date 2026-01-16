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
package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 分期查詢交易下行電文 repeat
 * 
 * @author Evan
 */
public class CEW343RRepeat {
    /** 分期類別 */
    private String ogtype;

    /** 消費日期 */
    private Date pchday;

    /** 入帳日 */
    private Date nccday;

    /** 消費說明 */
    private String mercnm;

    /** 卡號後四碼 */
    private String crdno4;

    /** 台幣金額 */
    private BigDecimal desamt;

    /** 申請日期 */
    private Date txiday;

    /** 分期期數 */
    private String txstge;

    /** 手續費率 */
    private String txrate;

    /** 未入帳金額 */
    private BigDecimal txuamt;

    /** 已入帳期數 */
    private String txbtic;

    /** HCE卡號 */
    private String hceno;

    /**
     * 民國年YYYMM 只有帳單分期才會有此項資訊
     */
    private Date txYymm;

    /**
     * @return the ogtype
     */
    public String getOgtype() {
        return ogtype;
    }

    /**
     * @param ogtype
     *            the ogtype to set
     */
    public void setOgtype(String ogtype) {
        this.ogtype = ogtype;
    }

    /**
     * @return the pchday
     */
    public Date getPchday() {
        return pchday;
    }

    /**
     * @param pchday
     *            the pchday to set
     */
    public void setPchday(Date pchday) {
        this.pchday = pchday;
    }

    /**
     * @return the nccday
     */
    public Date getNccday() {
        return nccday;
    }

    /**
     * @param nccday
     *            the nccday to set
     */
    public void setNccday(Date nccday) {
        this.nccday = nccday;
    }

    /**
     * @return the mercnm
     */
    public String getMercnm() {
        return mercnm;
    }

    /**
     * @param mercnm
     *            the mercnm to set
     */
    public void setMercnm(String mercnm) {
        this.mercnm = mercnm;
    }

    /**
     * @return the crdno4
     */
    public String getCrdno4() {
        return crdno4;
    }

    /**
     * @param crdno4
     *            the crdno4 to set
     */
    public void setCrdno4(String crdno4) {
        this.crdno4 = crdno4;
    }

    /**
     * @return the desamt
     */
    public BigDecimal getDesamt() {
        return desamt;
    }

    /**
     * @param desamt
     *            the desamt to set
     */
    public void setDesamt(BigDecimal desamt) {
        this.desamt = desamt;
    }

    /**
     * @return the txiday
     */
    public Date getTxiday() {
        return txiday;
    }

    /**
     * @param txiday
     *            the txiday to set
     */
    public void setTxiday(Date txiday) {
        this.txiday = txiday;
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
     * @return the txrate
     */
    public String getTxrate() {
        return txrate;
    }

    /**
     * @param txrate
     *            the txrate to set
     */
    public void setTxrate(String txrate) {
        this.txrate = txrate;
    }

    /**
     * @return the txuamt
     */
    public BigDecimal getTxuamt() {
        return txuamt;
    }

    /**
     * @param txuamt
     *            the txuamt to set
     */
    public void setTxuamt(BigDecimal txuamt) {
        this.txuamt = txuamt;
    }

    /**
     * @return the txbtic
     */
    public String getTxbtic() {
        return txbtic;
    }

    /**
     * @param txbtic
     *            the txbtic to set
     */
    public void setTxbtic(String txbtic) {
        this.txbtic = txbtic;
    }

    /**
     * @return the hceno
     */
    public String getHceno() {
        return hceno;
    }

    /**
     * @param hceno
     *            the hceno to set
     */
    public void setHceno(String hceno) {
        this.hceno = hceno;
    }

    /**
     * @return the txYymm
     */
    public Date getTxYymm() {
        return txYymm;
    }

    /**
     * @param txYymm
     *            the txYymm to set
     */
    public void setTxYymm(Date txYymm) {
        this.txYymm = txYymm;
    }

}
