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

//@formatter:off
/**
* @(#)CEW222RRepeat.java
* 
* <p>Description:單筆分期查詢(CEW222R) 下行 Repeat</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/07/21, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW222RRepeat {

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
    private BigDecimal txstge;

    /** 手續費率 */
    private BigDecimal txrate;

    /** 未入帳金額 */
    private BigDecimal txuamt;

    /** 已入帳期數 */
    private BigDecimal txbtic;

    /** 虛擬卡號 */
    private String hceno;

    /** 支付方式 */
    private String hcttyp;

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
    public BigDecimal getTxstge() {
        return txstge;
    }

    /**
     * @param txstge
     *            the txstge to set
     */
    public void setTxstge(BigDecimal txstge) {
        this.txstge = txstge;
    }

    /**
     * @return the txrate
     */
    public BigDecimal getTxrate() {
        return txrate;
    }

    /**
     * @param txrate
     *            the txrate to set
     */
    public void setTxrate(BigDecimal txrate) {
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
    public BigDecimal getTxbtic() {
        return txbtic;
    }

    /**
     * @param txbtic
     *            the txbtic to set
     */
    public void setTxbtic(BigDecimal txbtic) {
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
     * @return the hcttyp
     */
    public String getHcttyp() {
        return hcttyp;
    }

    /**
     * @param hcttyp
     *            the hcttyp to set
     */
    public void setHcttyp(String hcttyp) {
        this.hcttyp = hcttyp;
    }

}
