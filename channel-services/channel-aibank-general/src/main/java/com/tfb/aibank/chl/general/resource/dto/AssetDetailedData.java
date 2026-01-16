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

import java.math.BigDecimal;

// @formatter:off
/**
 * @(#)AssetDetailedData.java
 * 
 * <p>Description:富邦證券整註記異動API 下行資料 datas[n].assets細部資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/29, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AssetDetailedData {

    /**
     * 資產項目代碼
     */
    private String assetcode;

    /**
     * 資產項目名稱
     */
    private String assetName;

    /**
     * 幣別
     */
    private String unit;

    /**
     * 資料日期
     */
    private String tdate;

    /**
     * 庫存市值(帳面價值)
     */
    private BigDecimal assetAmtTwd;

    public AssetDetailedData() {
    }

    public AssetDetailedData(String assetcode, String assetName, String unit, String tdate, BigDecimal assetAmtTwd) {
        this.assetcode = assetcode;
        this.assetName = assetName;
        this.unit = unit;
        this.tdate = tdate;
        this.assetAmtTwd = assetAmtTwd;
    }

    public String getAssetcode() {
        return assetcode;
    }

    public void setAssetcode(String assetcode) {
        this.assetcode = assetcode;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTdate() {
        return tdate;
    }

    public void setTdate(String tdate) {
        this.tdate = tdate;
    }

    public BigDecimal getAssetAmtTwd() {
        return assetAmtTwd;
    }

    public void setAssetAmtTwd(BigDecimal assetAmtTwd) {
        this.assetAmtTwd = assetAmtTwd;
    }
}
