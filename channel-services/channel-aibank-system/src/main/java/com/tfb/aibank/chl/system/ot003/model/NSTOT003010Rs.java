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
package com.tfb.aibank.chl.system.ot003.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NSTOT003010Rs.java
* 
* <p>Description:安控驗證共用 - 交易初始檢查 - RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/27, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NSTOT003010Rs implements RsData {

    /** 交易安控機制類別 */
    private int txSecurityType;

    /** OTP使用類型(使用OTP時才有資料) */
    private int securityOtpType;

    /**
     * @return the txSecurityType
     */
    public int getTxSecurityType() {
        return txSecurityType;
    }

    /**
     * @param txSecurityType
     *            the txSecurityType to set
     */
    public void setTxSecurityType(int txSecurityType) {
        this.txSecurityType = txSecurityType;
    }

    /**
     * @return the securityOtpType
     */
    public int getSecurityOtpType() {
        return securityOtpType;
    }

    /**
     * @param securityOtpType
     *            the securityOtpType to set
     */
    public void setSecurityOtpType(int securityOtpType) {
        this.securityOtpType = securityOtpType;
    }

}
