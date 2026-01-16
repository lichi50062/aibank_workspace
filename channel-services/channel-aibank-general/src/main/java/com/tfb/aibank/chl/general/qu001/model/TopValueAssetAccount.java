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
package com.tfb.aibank.chl.general.qu001.model;

import java.math.BigDecimal;

/**
 * @(#)FubonStockDataSyncStatusApiRequest.java
 *
 * <p>Description: 最高市值資產帳號/p>
 *
 * <p>Modify History:</p>
 * v1.0, 2025/07/30, Peter
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
public class TopValueAssetAccount {

    private String  accountType;
    private BigDecimal totalAmtTwd;

    public TopValueAssetAccount(String accountType, BigDecimal totalAmtTwd) {
        this.accountType = accountType;
        this.totalAmtTwd = totalAmtTwd;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getTotalAmtTwd() {
        return totalAmtTwd;
    }

    public void setTotalAmtTwd(BigDecimal totalAmtTwd) {
        this.totalAmtTwd = totalAmtTwd;
    }
}
