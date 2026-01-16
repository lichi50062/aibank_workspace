package com.tfb.aibank.chl.creditcard.ag009.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.common.model.TxnResult;

// @formatter:off
/**
 * @(#)NCCAG009011Rs.java
 * 
 * <p>Description:[信用卡掛失 掛失 action Rs]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/22, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on

@Component
public class NCCAG009011Rs implements RsData {

    /** 交易結果 */
    private TxnResult txnResult;

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

}
