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

import com.tfb.aibank.common.model.TxHeadRs;

/**
 * 單筆分期申請下行電文 Body
 * 
 * @author Evan Wang
 */
public class CEW221RResponse extends TxHeadRs implements Serializable {

    private static final long serialVersionUID = 6632305472539165184L;

    /** 頭期款 */
    private String orfamt;

    /** 每期期款 */
    private String oreamt;

    /** 手續費 */
    private String feeamt;

    /** 手續費率 */
    private String feerate;

    /**
     * @return the orfamt
     */
    public String getOrfamt() {
        return orfamt;
    }

    /**
     * @param orfamt
     *            the orfamt to set
     */
    public void setOrfamt(String orfamt) {
        this.orfamt = orfamt;
    }

    /**
     * @return the oreamt
     */
    public String getOreamt() {
        return oreamt;
    }

    /**
     * @param oreamt
     *            the oreamt to set
     */
    public void setOreamt(String oreamt) {
        this.oreamt = oreamt;
    }

    /**
     * @return the feeamt
     */
    public String getFeeamt() {
        return feeamt;
    }

    /**
     * @param feeamt
     *            the feeamt to set
     */
    public void setFeeamt(String feeamt) {
        this.feeamt = feeamt;
    }

    /**
     * @return the feerate
     */
    public String getFeerate() {
        return feerate;
    }

    /**
     * @param feerate
     *            the feerate to set
     */
    public void setFeerate(String feerate) {
        this.feerate = feerate;
    }

}
