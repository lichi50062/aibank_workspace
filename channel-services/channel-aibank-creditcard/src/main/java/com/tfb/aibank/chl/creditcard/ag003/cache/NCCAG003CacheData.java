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
package com.tfb.aibank.chl.creditcard.ag003.cache;

import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthKeepData;
import com.tfb.aibank.common.model.TxnResult;

// @formatter:off
/**
 * @(#)NCCAG003CacheData.java
 * 
 * <p>Description:信用卡email設定</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/14, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCAG003CacheData {
    /** 交易結果 */
    private TxnResult txnResult;

    /** 初始email */
    private String originEmail;

    /** 是否有初始email */
    private Boolean isHasOriginEmail;

    /** 新email */
    private String newEmail;

    /** 驗證流程暫存資料 */
    private OtpAuthKeepData otpAuthKeepData;

    /**
     * @return the txnResult
     */
    public TxnResult getTxnResult() {
        return txnResult;
    }

    /**
     * @param txnResult
     *            the txnResult to set
     */
    public void setTxnResult(TxnResult txnResult) {
        this.txnResult = txnResult;
    }

    /**
     * @return the originEmail
     */
    public String getOriginEmail() {
        return originEmail;
    }

    /**
     * @param originEmail
     *            the originEmail to set
     */
    public void setOriginEmail(String originEmail) {
        this.originEmail = originEmail;
    }

    /**
     * @return the isHasOriginEmail
     */
    public Boolean getIsHasOriginEmail() {
        return isHasOriginEmail;
    }

    /**
     * @param isHasOriginEmail
     *            the isHasOriginEmail to set
     */
    public void setIsHasOriginEmail(Boolean isHasOriginEmail) {
        this.isHasOriginEmail = isHasOriginEmail;
    }

    /**
     * @return the newEmail
     */
    public String getNewEmail() {
        return newEmail;
    }

    /**
     * @param newEmail
     *            the newEmail to set
     */
    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    /**
     * @return the otpAuthKeepData
     */
    public OtpAuthKeepData getOtpAuthKeepData() {
        return otpAuthKeepData;
    }

    /**
     * @param otpAuthKeepData
     *            the otpAuthKeepData to set
     */
    public void setOtpAuthKeepData(OtpAuthKeepData otpAuthKeepData) {
        this.otpAuthKeepData = otpAuthKeepData;
    }

}
