/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.common.model;

import java.util.List;

//@formatter:off
/**
* @(#)QueryFastPurchaseResult.java
* 
* <p>Description: NMFTX004 投資快速申購 query result</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/04/01, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class QueryFastPurchaseResult<T> {

    /**
     * 尚未申購
     */
    private List<T> notPurchasedYet;

    /**
     * 申購完成
     */
    private List<T> purchased;

    /**
     * 申購失敗
     */
    private List<T> purchasedFailed;

    public List<T> getNotPurchasedYet() {
        return notPurchasedYet;
    }

    public void setNotPurchasedYet(List<T> notPurchasedYet) {
        this.notPurchasedYet = notPurchasedYet;
    }

    public List<T> getPurchased() {
        return purchased;
    }

    public void setPurchased(List<T> purchased) {
        this.purchased = purchased;
    }

    public List<T> getPurchasedFailed() {
        return purchasedFailed;
    }

    public void setPurchasedFailed(List<T> purchasedFailed) {
        this.purchasedFailed = purchasedFailed;
    }
}
