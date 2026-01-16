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

import java.util.List;

// @formatter:off
/**
 * @(#)AssetData.java
 * 
 * <p>Description:富邦證券整註記異動API 下行資料 datas細部資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/27, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AssetData {

    /**
     * 資產類別
     */
    private String category;

    /**
     * 資產細節資料
     */
    private List<AssetDetailedData> assets;

    public AssetData() {
    }

    public AssetData(String category, List<AssetDetailedData> assets) {
        this.category = category;
        this.assets = assets;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<AssetDetailedData> getAssets() {
        return assets;
    }

    public void setAssets(List<AssetDetailedData> assets) {
        this.assets = assets;
    }

    @Override
    public String toString() {
        return "AssetData [category=" + category + ", assets=" + assets + "]";
    }
}
