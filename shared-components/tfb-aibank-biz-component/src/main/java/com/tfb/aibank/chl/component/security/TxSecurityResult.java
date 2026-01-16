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
package com.tfb.aibank.chl.component.security;

import com.tfb.aibank.chl.type.TxSecurityType;

// @formatter:off
/**
 * @(#)TxSecurityResult.java
 * 
 * <p>Description:交易安控驗證結果</p>
 * 
 * <pre>
 * 提供各交易取得安控驗證中關聯的Table鍵值
 * 若使用OTP:可取得OTP.OTP_KEY
 * 若使用MOTP:可取得MOTP_TRANS_DATA.MOTP_TRANS_KEY
 * </pre>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/06, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TxSecurityResult {

    /** 交易安控機制類別 */
    private TxSecurityType txSecurityType = TxSecurityType.UNKNOWN;

    /** OTP_KEY */
    private Integer otpKey;

    /** MOTP_TRANS_KEY */
    private Integer motpTransKey;

    /**
     * @return the txSecurityType
     */
    public TxSecurityType getTxSecurityType() {
        return txSecurityType;
    }

    /**
     * @param txSecurityType
     *            the txSecurityType to set
     */
    public void setTxSecurityType(TxSecurityType txSecurityType) {
        this.txSecurityType = txSecurityType;
    }

    /**
     * @return the otpKey
     */
    public Integer getOtpKey() {
        return otpKey;
    }

    /**
     * @param otpKey
     *            the otpKey to set
     */
    public void setOtpKey(Integer otpKey) {
        this.otpKey = otpKey;
    }

    /**
     * @return the motpTransKey
     */
    public Integer getMotpTransKey() {
        return motpTransKey;
    }

    /**
     * @param motpTransKey
     *            the motpTransKey to set
     */
    public void setMotpTransKey(Integer motpTransKey) {
        this.motpTransKey = motpTransKey;
    }

}
