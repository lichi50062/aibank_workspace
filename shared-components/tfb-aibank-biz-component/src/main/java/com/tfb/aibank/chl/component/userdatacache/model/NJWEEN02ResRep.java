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


// @formatter:off
/**
 * @(#)NJWEEN01Res.java
 * 
 * <p>Description:NJWEEN02ResRep</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/09, Charlie Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class NJWEEN02ResRep {
    /** 海外債劵金額 */
    private BigDecimal bondAmt1;
    /** 境外結構式商品金額 */
    private BigDecimal bondAmt2;

    /**
     * @return the bondAmt1
     */
    public BigDecimal getBondAmt1() {
        return bondAmt1;
    }

    /**
     * @param bondAmt1
     *            the bondAmt1 to set
     */
    public void setBondAmt1(BigDecimal bondAmt1) {
        this.bondAmt1 = bondAmt1;
    }

    /**
     * @return the bondAmt2
     */
    public BigDecimal getBondAmt2() {
        return bondAmt2;
    }

    /**
     * @param bondAmt2
     *            the bondAmt2 to set
     */
    public void setBondAmt2(BigDecimal bondAmt2) {
        this.bondAmt2 = bondAmt2;
    }

}
