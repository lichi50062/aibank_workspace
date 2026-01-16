package com.tfb.aibank.chl.creditcard.qu001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NCCQU001010Rq.java
* 
* <p>Description:信用卡總覽 功能首頁 RQ</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCQU001010Rq implements RqData {
    /** 識別資訊(唯一值) */
    private String cardKey;
    /** 查詢類別 */
    private String actionType;
    /** 導頁參數 */
    private String GOTO;
    /** 來源交易 */
    private String txnSource;

    /**
     * @return the actionType
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * @param actionType
     *            the actionType to set
     */
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    /**
     * @return the gOTO
     */
    public String getGOTO() {
        return GOTO;
    }

    /**
     * @param gOTO
     *            the gOTO to set
     */
    public void setGOTO(String gOTO) {
        GOTO = gOTO;
    }

    public String getTxnSource() {
        return txnSource;
    }

    public void setTxnSource(String txnSource) {
        this.txnSource = txnSource;
    }

    /**
     * @return the cardKey
     */
    public String getCardKey() {
        return cardKey;
    }

    /**
     * @param cardKey the cardKey to set
     */
    public void setCardKey(String cardKey) {
        this.cardKey = cardKey;
    }

}
