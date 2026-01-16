package com.tfb.aibank.chl.creditcard.ag003.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.common.model.TxnResult;

// @formatter:off
/**
 * @(#)NCCAG003020Rs.java
 * 
 * <p>Description:信用卡email設定 020 結果頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG003020Rs implements RsData {

    /** 交易結果 */
    private TxnResult txnResult;

    /** 新email */
    private String newEmail;

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

}
