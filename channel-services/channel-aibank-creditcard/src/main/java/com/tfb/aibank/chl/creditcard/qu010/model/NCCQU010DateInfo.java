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

import com.ibm.tw.ibmb.model.LabelValueBean;

// @formatter:off
/**
* @(#)NCCQU010DateInfo.java
* 
* <p>Description: 分析日期資訊 </p>
* 
* <p>Modify History:</p>
* v1.0, 2024/06/28 Warren Lin
* <ol>
*  <li>初版</li>
* </ol>
*/
// @formatter:on
public class NCCQU010DateInfo extends LabelValueBean {

    /** 交易年 */
    private int txYear;
    
    /** 交易月份 */
    private int txMonth;

    /**
     * @return the txYear
     */
    public int getTxYear() {
        return txYear;
    }

    /**
     * Set the txYear
     *
     * @param txYear
     */
    public void setTxYear(int txYear) {
        this.txYear = txYear;
    }

    
    /**
     * @return the txMonth
     */
    public int getTxMonth() {
        return txMonth;
    }

    /**
     * Set the txMonth
     *
     * @param txMonth
     */
    public void setTxMonth(int txMonth) {
        this.txMonth = txMonth;
    }
}
