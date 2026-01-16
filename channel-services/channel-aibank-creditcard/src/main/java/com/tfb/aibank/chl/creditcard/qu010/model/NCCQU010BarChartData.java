/*
 * ===========================================================================
 *
 * IBM Confidential
 *
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.creditcard.qu010.model;

import java.math.BigDecimal;

// @formatter:off
/**
* @(#)NCCQU010BarChartData.java
* 
* <p>Description: 柱狀圖資料 </p>
* 
* <p>Modify History:</p>
* v1.0, 2024/06/28 Warren Lin
* <ol>
*  <li>初版</li>
* </ol>
*/
// @formatter:on
public class NCCQU010BarChartData {

    /** 百分比 */
    private BigDecimal percentage;
    
    public NCCQU010BarChartData(BigDecimal percentage) {
        this.percentage = percentage;
    }

    /**
     * @return the percentage
     */
    public BigDecimal getPercentage() {
        return percentage;
    }

    /**
     * Set the percentage
     *
     * @param percentage
     */
    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }
}
