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
package com.tfb.aibank.chl.component.accountselect.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @formatter:off
/**
 * @(#)FxAccountSelector.java
 * 
 * <p>Description:『最近轉帳/常用/約定』帳號選擇元件(外幣)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/23, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FxAccountSelector implements Serializable {

    private static final long serialVersionUID = -7342986813563863027L;

    /** 轉出帳號 */
    private List<FxPayerRecord> payers;

    /** 轉帳紀錄 */
    private List<FxTransRecord> trans;

    /** 轉出對應常用轉入 */
    private Map<String, List<FxPayeeRecord>> favoritePayeeMap = new HashMap<>();

    /** 轉出對應約定轉入 */
    private Map<String, List<FxPayeeRecord>> agreedPayeeMap = new HashMap<>();

    /** 期貨帳號 */
    private List<FuturesAcctRecord> futuresAccts;

    /**
     * @return the payers
     */
    public List<FxPayerRecord> getPayers() {
        return payers;
    }

    /**
     * @param payers
     *            the payers to set
     */
    public void setPayers(List<FxPayerRecord> payers) {
        this.payers = payers;
    }

    /**
     * @return the trans
     */
    public List<FxTransRecord> getTrans() {
        return trans;
    }

    /**
     * @param trans
     *            the trans to set
     */
    public void setTrans(List<FxTransRecord> trans) {
        this.trans = trans;
    }

    /**
     * @return the favoritePayeeMap
     */
    public Map<String, List<FxPayeeRecord>> getFavoritePayeeMap() {
        return favoritePayeeMap;
    }

    /**
     * @param favoritePayeeMap
     *            the favoritePayeeMap to set
     */
    public void setFavoritePayeeMap(Map<String, List<FxPayeeRecord>> favoritePayeeMap) {
        this.favoritePayeeMap = favoritePayeeMap;
    }

    /**
     * @return the agreedPayeeMap
     */
    public Map<String, List<FxPayeeRecord>> getAgreedPayeeMap() {
        return agreedPayeeMap;
    }

    /**
     * @param agreedPayeeMap
     *            the agreedPayeeMap to set
     */
    public void setAgreedPayeeMap(Map<String, List<FxPayeeRecord>> agreedPayeeMap) {
        this.agreedPayeeMap = agreedPayeeMap;
    }

    /**
     * @return the futuresAccts
     */
    public List<FuturesAcctRecord> getFuturesAccts() {
        return futuresAccts;
    }

    /**
     * @param futuresAccts
     *            the futuresAccts to set
     */
    public void setFuturesAccts(List<FuturesAcctRecord> futuresAccts) {
        this.futuresAccts = futuresAccts;
    }

}
