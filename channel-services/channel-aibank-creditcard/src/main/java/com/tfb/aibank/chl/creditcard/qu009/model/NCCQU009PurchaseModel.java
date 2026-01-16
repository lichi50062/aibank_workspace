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
package com.tfb.aibank.chl.creditcard.qu009.model;

import java.util.ArrayList;
import java.util.List;

// @formatter:off
/**
 * @(#)NCCQU009PurchaseDetail.java
 * 
 * <p>Description:單月消費明細</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/19, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU009PurchaseModel {
    public static final String TXN_RESULT_HAS_DATA = "成功有資料";

    public static final String TXN_RESULT_NO_DATA = "無資料";

    public static final String TXN_RESULT_NO_DATA_NO_PAY = "無資料且有未扣款成功的消費";

    public static final String TXN_RESULT_ERROR = "查詢失敗";

    /** 當月所有明細相關資訊 */
    private List<NCCQU009PurchaseByMonth> purchaseByMonth = new ArrayList<>();

    /** 交易結果回傳flag "成功有資料" "無資料" "無資料且有未扣款成功的消費" "查詢失敗" */
    private String txnResultFlag;

    /**
     * @return the purchaseByMonth
     */
    public List<NCCQU009PurchaseByMonth> getPurchaseByMonth() {
        return purchaseByMonth;
    }

    /**
     * @param purchaseByMonth
     *            the purchaseByMonth to set
     */
    public void setPurchaseByMonth(List<NCCQU009PurchaseByMonth> purchaseByMonth) {
        this.purchaseByMonth = purchaseByMonth;
    }

    /**
     * @return the txnResultFlag
     */
    public String getTxnResultFlag() {
        return txnResultFlag;
    }

    /**
     * @param txnResultFlag
     *            the txnResultFlag to set
     */
    public void setTxnResultFlag(String txnResultFlag) {
        this.txnResultFlag = txnResultFlag;
    }

}
