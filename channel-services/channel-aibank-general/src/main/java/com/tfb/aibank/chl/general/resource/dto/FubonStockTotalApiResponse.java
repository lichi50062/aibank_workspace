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

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;

// @formatter:off
/**
 * @(#)FubonStockTotalApiResponse.java
 * 
 * <p>Description:富邦台市值總計API 下行資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/07/30, Peter
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FubonStockTotalApiResponse {


    @SerializedName("tdate")
    private String tdate;

    @SerializedName("isValid")
    private String isValid;

    @SerializedName("total_asset_amt_twd")
    private BigDecimal totalAssetAmtTwd;

    @SerializedName("assets")
    private List<AssetItemTotalData> assets;


    public String getTdate() {
        return tdate;
    }

    public void setTdate(String tdate) {
        this.tdate = tdate;
    }

    public List<AssetItemTotalData> getAssets() {
        return assets;
    }

    public void setAssets(List<AssetItemTotalData> assets) {
        this.assets = assets;
    }

    public BigDecimal getTotalAssetAmtTwd() {
        return totalAssetAmtTwd;
    }

    public void setTotalAssetAmtTwd(BigDecimal totalAssetAmtTwd) {
        this.totalAssetAmtTwd = totalAssetAmtTwd;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

}
