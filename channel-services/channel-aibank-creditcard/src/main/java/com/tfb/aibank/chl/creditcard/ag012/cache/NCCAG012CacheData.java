package com.tfb.aibank.chl.creditcard.ag012.cache;

import java.util.List;

import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012CardCollect;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012CardInfo;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012LockStatusInfo;
import com.tfb.aibank.common.model.TxnResult;

//@formatter:off
/**
* @(#) NCCAG012CacheData.java
* 
* <p>Description:信用卡交易設定 cache</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCCAG012CacheData {
    /** 卡片資訊集 */
    private List<NCCAG012CardCollect> cardCollects;

    /** 卡片資訊 */
    private NCCAG012CardInfo cardInfoForTx;

    /** 設定頁鎖定狀態資訊 */
    private NCCAG012LockStatusInfo lockStatusInfo;

    /** 交易結果 */
    private TxnResult txnResult;

    /** 卡片鍵值 */
    private String cardKeyForTxn;

    /**
     * @return the cardCollects
     */
    public List<NCCAG012CardCollect> getCardCollects() {
        return cardCollects;
    }

    /**
     * @param cardCollects
     *            the cardCollects to set
     */
    public void setCardCollects(List<NCCAG012CardCollect> cardCollects) {
        this.cardCollects = cardCollects;
    }

    /**
     * @return the cardInfoForTx
     */
    public NCCAG012CardInfo getCardInfoForTx() {
        return cardInfoForTx;
    }

    /**
     * @param cardInfoForTx
     *            the cardInfoForTx to set
     */
    public void setCardInfoForTx(NCCAG012CardInfo cardInfoForTx) {
        this.cardInfoForTx = cardInfoForTx;
    }

    /**
     * @return the lockStatusInfo
     */
    public NCCAG012LockStatusInfo getLockStatusInfo() {
        return lockStatusInfo;
    }

    /**
     * @param lockStatusInfo
     *            the lockStatusInfo to set
     */
    public void setLockStatusInfo(NCCAG012LockStatusInfo lockStatusInfo) {
        this.lockStatusInfo = lockStatusInfo;
    }

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
