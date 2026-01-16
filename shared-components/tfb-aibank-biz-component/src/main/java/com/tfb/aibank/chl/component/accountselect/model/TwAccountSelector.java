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
 * @(#)AccountSelector.java
 * 
 * <p>Description:『最近轉帳/常用/約定』帳號選擇元件(臺幣)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/08, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@SuppressWarnings("serial")
public class TwAccountSelector implements Serializable {

    /** 轉出帳號 */
    private List<TwPayerRecord> payers;

    /** 本人帳號 */
    private List<TwPayerRecord> selfs;

    /** 轉帳紀錄 */
    private List<TwTransRecord> trans;

    /** 轉出對應常用轉入 */
    private Map<String, List<TwPayeeRecord>> favoritePayeeMap = new HashMap<>();

    /** 轉出對應約定轉入 */
    private Map<String, List<TwPayeeRecord>> agreedPayeeMap = new HashMap<>();

    /** 期貨帳號 */
    private List<FuturesAcctRecord> futuresAccts;

    /**
     * @return the payers
     */
    public List<TwPayerRecord> getPayers() {
        return payers;
    }

    /**
     * @param payers
     *            the payers to set
     */
    public void setPayers(List<TwPayerRecord> payers) {
        this.payers = payers;
    }

    /**
     * @return {@link #selfs}
     */
    public List<TwPayerRecord> getSelfs() {
        return selfs;
    }

    /**
     * @param selfs
     *            {@link #selfs}
     */
    public void setSelfs(List<TwPayerRecord> selfs) {
        this.selfs = selfs;
    }

    /**
     * @return the trans
     */
    public List<TwTransRecord> getTrans() {
        return trans;
    }

    /**
     * @param trans
     *            the trans to set
     */
    public void setTrans(List<TwTransRecord> trans) {
        this.trans = trans;
    }

    /**
     * @return the favoritePayeeMap
     */
    public Map<String, List<TwPayeeRecord>> getFavoritePayeeMap() {
        return favoritePayeeMap;
    }

    /**
     * @param favoritePayeeMap
     *            the favoritePayeeMap to set
     */
    public void setFavoritePayeeMap(Map<String, List<TwPayeeRecord>> favoritePayeeMap) {
        this.favoritePayeeMap = favoritePayeeMap;
    }

    /**
     * @return the agreedPayeeMap
     */
    public Map<String, List<TwPayeeRecord>> getAgreedPayeeMap() {
        return agreedPayeeMap;
    }

    /**
     * @param agreedPayeeMap
     *            the agreedPayeeMap to set
     */
    public void setAgreedPayeeMap(Map<String, List<TwPayeeRecord>> agreedPayeeMap) {
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
