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
package com.tfb.aibank.chl.component.bankselect.model;

import java.util.List;

// @formatter:off
/**
 * @(#)BankSelector.java
 * 
 * <p>Description:銀行代碼選擇元件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/08, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BankSelector {

    /** 最近區塊 */
    private List<BankRecord> recentlyBanks;

    /** 熱門區塊 */
    private List<BankRecord> favoriteBanks;

    /** 全部區塊 */
    private List<BankRecord> allBanks;

    /**
     * @return the recentlyBanks
     */
    public List<BankRecord> getRecentlyBanks() {
        return recentlyBanks;
    }

    /**
     * @param recentlyBanks
     *            the recentlyBanks to set
     */
    public void setRecentlyBanks(List<BankRecord> recentlyBanks) {
        this.recentlyBanks = recentlyBanks;
    }

    /**
     * @return the favoriteBanks
     */
    public List<BankRecord> getFavoriteBanks() {
        return favoriteBanks;
    }

    /**
     * @param favoriteBanks
     *            the favoriteBanks to set
     */
    public void setFavoriteBanks(List<BankRecord> favoriteBanks) {
        this.favoriteBanks = favoriteBanks;
    }

    /**
     * @return the allBanks
     */
    public List<BankRecord> getAllBanks() {
        return allBanks;
    }

    /**
     * @param allBanks
     *            the allBanks to set
     */
    public void setAllBanks(List<BankRecord> allBanks) {
        this.allBanks = allBanks;
    }

}
