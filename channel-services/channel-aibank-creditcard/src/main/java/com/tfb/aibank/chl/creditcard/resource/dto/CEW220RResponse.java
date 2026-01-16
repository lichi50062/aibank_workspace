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

import java.io.Serializable;
import java.math.BigDecimal;

import com.tfb.aibank.common.model.TxHeadRs;

/**
 * 指定消費分期試算交易下行電文 Body
 * 
 * @author Evan Wang
 */
public class CEW220RResponse extends TxHeadRs implements Serializable {

    private static final long serialVersionUID = 6632305472539165184L;

    /** 頭期款 */
    private BigDecimal orfamt;

    /** 每期期款 */
    private BigDecimal oreamt;

    /** 手續費 */
    private BigDecimal feeamt;

    /** 手續費率 */
    private BigDecimal feerate;

    /**
     * @return the orfamt
     */
    public BigDecimal getOrfamt() {
        return orfamt;
    }

    /**
     * @param orfamt
     *            the orfamt to set
     */
    public void setOrfamt(BigDecimal orfamt) {
        this.orfamt = orfamt;
    }

    /**
     * @return the oreamt
     */
    public BigDecimal getOreamt() {
        return oreamt;
    }

    /**
     * @param oreamt
     *            the oreamt to set
     */
    public void setOreamt(BigDecimal oreamt) {
        this.oreamt = oreamt;
    }

    /**
     * @return the feeamt
     */
    public BigDecimal getFeeamt() {
        return feeamt;
    }

    /**
     * @param feeamt
     *            the feeamt to set
     */
    public void setFeeamt(BigDecimal feeamt) {
        this.feeamt = feeamt;
    }

    /**
     * @return the feerate
     */
    public BigDecimal getFeerate() {
        return feerate;
    }

    /**
     * @param feerate
     *            the feerate to set
     */
    public void setFeerate(BigDecimal feerate) {
        this.feerate = feerate;
    }

}
