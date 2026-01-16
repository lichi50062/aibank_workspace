package com.tfb.aibank.chl.creditcard.ag012.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.common.model.TxnResult;

// @formatter:off
/**
 * @(#)NCCAG012060Rs.java
 * 
 * <p>Description:信用卡交易設定 060 失敗結果頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/11, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG012060Rs implements RsData {
    /** 交易結果 */
    private TxnResult txnResult;

    /** 卡片資訊 */
    private NCCAG012CardInfo cardInfo;

    /** 卡片鍵值 */
    private String cardKeyForTxn;

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
     * @return the cardInfo
     */
    public NCCAG012CardInfo getCardInfo() {
        return cardInfo;
    }

    /**
     * @param cardInfo
     *            the cardInfo to set
     */
    public void setCardInfo(NCCAG012CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    /**
     * @return the cardKeyForTxn
     */
    public String getCardKeyForTxn() {
        return cardKeyForTxn;
    }

    /**
     * @param cardKeyForTxn
     *            the cardKeyForTxn to set
     */
    public void setCardKeyForTxn(String cardKeyForTxn) {
        this.cardKeyForTxn = cardKeyForTxn;
    }

}
