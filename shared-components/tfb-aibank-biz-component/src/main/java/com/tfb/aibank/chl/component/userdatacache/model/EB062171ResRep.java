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
package com.tfb.aibank.chl.component.userdatacache.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB062171ResRep.java
 * 
 * <p>Description:EB062171 撥貸紀錄 下行資訊 Repeat</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/31,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB062171ResRep {

    /** 撥貸金額 */
    private BigDecimal amtNumber;
    /** 撥貸筆數 */
    private String cntNumber;
    /** 撥貸註記(Y/N) */
    private String flagNumber;
    /** 撥貸件數天數查詢 (007、030、060、090、指定) */
    private String cycleNumber;

    public BigDecimal getAmtNumber() {
        return amtNumber;
    }

    /**
     * @param amtNumber
     */
    public void setAmtNumber(BigDecimal amtNumber) {
        this.amtNumber = amtNumber;
    }

    public String getCntNumber() {
        return cntNumber;
    }

    /**
     * @param cntNumber
     */
    public void setCntNumber(String cntNumber) {
        this.cntNumber = cntNumber;
    }

    public String getFlagNumber() {
        return flagNumber;
    }

    /**
     * @param flagNumber
     */
    public void setFlagNumber(String flagNumber) {
        this.flagNumber = flagNumber;
    }

    public String getCycleNumber() {
        return cycleNumber;
    }

    /**
     * @param cycleNumber
     */
    public void setCycleNumber(String cycleNumber) {
        this.cycleNumber = cycleNumber;
    }
}
