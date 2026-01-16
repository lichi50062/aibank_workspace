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

// @formatter:off
/**
 * @(#)AssetItemDetailData.java
 *
 * <p>Description:富邦證券標的明細 API 細部資料</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2025/07/30, Peter
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AssetItemTotalData {

    @SerializedName("assetcode")
    private String assetCode;

    @SerializedName("asset_amt_twd")
    private BigDecimal assetAmtTwd;

    @SerializedName("benefit_amt_twd")
    private BigDecimal benefitAmtTwd;

    @SerializedName("div_benefit_amt_twd")
    private BigDecimal divBenefitAmtTwd;

    @SerializedName("benefit_ratio_twd")
    private BigDecimal benefitRatioTwd;

    @SerializedName("div_benefit_ratio_twd")
    private BigDecimal divBenefitRatioTwd;

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public BigDecimal getDivBenefitRatioTwd() {
        return divBenefitRatioTwd;
    }

    public void setDivBenefitRatioTwd(BigDecimal divBenefitRatioTwd) {
        this.divBenefitRatioTwd = divBenefitRatioTwd;
    }

    public BigDecimal getBenefitRatioTwd() {
        return benefitRatioTwd;
    }

    public void setBenefitRatioTwd(BigDecimal benefitRatioTwd) {
        this.benefitRatioTwd = benefitRatioTwd;
    }

    public BigDecimal getDivBenefitAmtTwd() {
        return divBenefitAmtTwd;
    }

    public void setDivBenefitAmtTwd(BigDecimal divBenefitAmtTwd) {
        this.divBenefitAmtTwd = divBenefitAmtTwd;
    }

    public BigDecimal getBenefitAmtTwd() {
        return benefitAmtTwd;
    }

    public void setBenefitAmtTwd(BigDecimal benefitAmtTwd) {
        this.benefitAmtTwd = benefitAmtTwd;
    }

    public BigDecimal getAssetAmtTwd() {
        return assetAmtTwd;
    }

    public void setAssetAmtTwd(BigDecimal assetAmtTwd) {
        this.assetAmtTwd = assetAmtTwd;
    }
}
