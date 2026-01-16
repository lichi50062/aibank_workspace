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

import com.google.gson.annotations.SerializedName;

// @formatter:off
/**
 * @(#)AssetItemDetailData.java
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
public class AssetItemDetailData {

    /**
     * 股票簡稱（例：鴻海）
     */
    @SerializedName("prod_abbr_name")
    private String prodAbbrName;

    /**
     * 股票代號（例：2317）
     */
    @SerializedName("prod_code")
    private String prodCode;

    /**
     * 庫存金額（TWD）
     */
    @SerializedName("bal_amt_twd")
    private BigDecimal balAmtTwd;

    /**
     * 損益金額（TWD）
     */
    @SerializedName("benefit_amt_twd")
    private BigDecimal benefitAmtTwd;

    /**
     * 損益率（%）
     */
    @SerializedName("benefit_ratio_twd")
    private BigDecimal benefitRatioTwd;


    public AssetItemDetailData() {
    }

    public AssetItemDetailData(String prodAbbrName, String prodCode,
                               BigDecimal balAmtTwd, BigDecimal benefitAmtTwd,
                               BigDecimal benefitRatioTwd) {
        this.prodAbbrName = prodAbbrName;
        this.prodCode = prodCode;
        this.balAmtTwd = balAmtTwd;
        this.benefitAmtTwd = benefitAmtTwd;
        this.benefitRatioTwd = benefitRatioTwd;
    }


    public String getProdAbbrName() {
        return prodAbbrName;
    }

    public void setProdAbbrName(String prodAbbrName) {
        this.prodAbbrName = prodAbbrName;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public BigDecimal getBalAmtTwd() {
        return balAmtTwd;
    }

    public void setBalAmtTwd(BigDecimal balAmtTwd) {
        this.balAmtTwd = balAmtTwd;
    }

    public BigDecimal getBenefitAmtTwd() {
        return benefitAmtTwd;
    }

    public void setBenefitAmtTwd(BigDecimal benefitAmtTwd) {
        this.benefitAmtTwd = benefitAmtTwd;
    }

    public BigDecimal getBenefitRatioTwd() {
        return benefitRatioTwd;
    }

    public void setBenefitRatioTwd(BigDecimal benefitRatioTwd) {
        this.benefitRatioTwd = benefitRatioTwd;
    }


    @Override
    public String toString() {
        return "AssetItemDetailData{" +
                "prodAbbrName='" + prodAbbrName + '\'' +
                ", prodCode='" + prodCode + '\'' +
                ", balAmtTwd=" + balAmtTwd +
                ", benefitAmtTwd=" + benefitAmtTwd +
                ", benefitRatioTwd=" + benefitRatioTwd +
                '}';
    }

}
