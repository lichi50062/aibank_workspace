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

/**
 * 取得分期未入帳金額下行電文 repeat
 * 
 * @author Evan
 */
public class CEW326RRepeat {

    /** 入帳年月 */
    private String billYyyymm;

    /** 消費分期本金 */
    private BigDecimal unbillAmount;

    /** 消費分期利息 */
    private BigDecimal unbillInt;

    /** 帳單/長循分期本金 */
    private BigDecimal billAmount;

    /** 帳單/長循分期利息 */
    private BigDecimal billInt;

    /** FILLER */
    private String filler;

    /**
     * @return the billYyyymm
     */
    public String getBillYyyymm() {
        return billYyyymm;
    }

    /**
     * @param billYyyymm
     *            the billYyyymm to set
     */
    public void setBillYyyymm(String billYyyymm) {
        this.billYyyymm = billYyyymm;
    }

    /**
     * @return the unbillAmount
     */
    public BigDecimal getUnbillAmount() {
        return unbillAmount;
    }

    /**
     * @param unbillAmount
     *            the unbillAmount to set
     */
    public void setUnbillAmount(BigDecimal unbillAmount) {
        this.unbillAmount = unbillAmount;
    }

    /**
     * @return the unbillInt
     */
    public BigDecimal getUnbillInt() {
        return unbillInt;
    }

    /**
     * @param unbillInt
     *            the unbillInt to set
     */
    public void setUnbillInt(BigDecimal unbillInt) {
        this.unbillInt = unbillInt;
    }

    /**
     * @return the billAmount
     */
    public BigDecimal getBillAmount() {
        return billAmount;
    }

    /**
     * @param billAmount
     *            the billAmount to set
     */
    public void setBillAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    /**
     * @return the billInt
     */
    public BigDecimal getBillInt() {
        return billInt;
    }

    /**
     * @param billInt
     *            the billInt to set
     */
    public void setBillInt(BigDecimal billInt) {
        this.billInt = billInt;
    }

    /**
     * @return the filler
     */
    public String getFiller() {
        return filler;
    }

    /**
     * @param filler
     *            the filler to set
     */
    public void setFiller(String filler) {
        this.filler = filler;
    }

}
