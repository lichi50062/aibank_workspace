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
package com.tfb.aibank.chl.general.resource.dto;



import com.tfb.aibank.chl.general.type.AssetAccountType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// @formatter:off
/**
 * @(#)AssetAccount.java
 *
 * <p>Description:富邦證券資場帳戶 細部資料</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2025/07/30, Peter
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
public class AssetAccount {

    /**
     *  資產帳戶類別  SECURITIES / SUBBROKER / FUTURES
     */
    private String  accountType;
    /**
     *  資產帳戶總市值
     */
    private BigDecimal totalAmtTwd;
    /**
     *  證券資產約台市值總計 資料列表
     */
    private List<AssetItemTotalData> items;
    /**
     *  證券資產標的明細 資料列表 (證券帳戶才取該值)
     */
    private List<AssetItemDetailData> detailItems;


    public AssetAccount(AssetAccountType type) {
        this.accountType = type.name();
        this.totalAmtTwd = BigDecimal.ZERO;
        this.items       = new ArrayList<>();
    }
    public void add(AssetItemTotalData item) {
        items.add(item);
        totalAmtTwd = totalAmtTwd.add(item.getAssetAmtTwd());
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public List<AssetItemTotalData> getItems() {
        return items;
    }

    public void setItems(List<AssetItemTotalData> items) {
        this.items = items;
    }

    public BigDecimal getTotalAmtTwd() {
        return totalAmtTwd;
    }

    public void setTotalAmtTwd(BigDecimal totalAmtTwd) {
        this.totalAmtTwd = totalAmtTwd;
    }

    public List<AssetItemDetailData> getDetailItems() {
        return detailItems;
    }

    public void setDetailItems(List<AssetItemDetailData> detailItems) {
        this.detailItems = detailItems;
    }
}
