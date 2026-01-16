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
package com.tfb.aibank.chl.component.security.otp.bean.txseed;

import java.util.Date;

// @formatter:off
/**
 * @(#)OtpTxSeed.java
 * 
 * <p>Description:OTP共用交易因子</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/24, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public abstract class OtpTxSeed {

    /** OTP置換子 */
    public static final String OTP_REPLACER = "{0}";

    /** 交易代碼置換子 */
    public static final String TX_CODE_REPLACER = "{1}";

    /** 交易名稱置換子 */
    public static final String TX_NAME_REPLACER = "{2}";

    /** 是否已經由交易電文發送OTP */
    private boolean txnSendFlag = false;
    /** 交易電文發送OTP交易代碼 */
    private String txCode;
    /** 交易電文發送OTP有效時間 */
    private Date expireTime;

    /**
     * 取得此次交易客製化推播訊息內容
     * 
     * @return
     */
    public abstract String getSendMessage();

    /**
     * 組合該次交易相關參數
     * 
     * @return
     */
    public abstract String getTxFactors();

    /**
     * @return the txnSendFlag
     */
    public boolean isTxnSendFlag() {
        return txnSendFlag;
    }

    /**
     * @param txnSendFlag
     *            the txnSendFlag to set
     */
    public void setTxnSendFlag(boolean txnSendFlag) {
        this.txnSendFlag = txnSendFlag;
    }

    /**
     * @return the txCode
     */
    public String getTxCode() {
        return txCode;
    }

    /**
     * @param txCode
     *            the txCode to set
     */
    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }

    /**
     * @return the expireTime
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * @param expireTime
     *            the expireTime to set
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

}
