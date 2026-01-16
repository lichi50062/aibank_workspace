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
package com.tfb.aibank.biz.user.services.identitytype.model;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)FinancialIndustryModel.java
 * 
 * <p>Description:金融同業身分業 Model</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/16, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "金融同業身分業")
public class FinancialIndustryModel {
    /** 是否金融同業身分 */
    @Schema(description = "是否金融同業身分")
    private Boolean isFinancialIndustry;

    /** 金融同業身分代碼 */
    @Schema(description = "金融同業身分代碼")
    private String financialIndustryCode;

    /**
     * @return the isFinancialIndustry
     */
    public Boolean getIsFinancialIndustry() {
        return isFinancialIndustry;
    }

    /**
     * @param isFinancialIndustry
     *            the isFinancialIndustry to set
     */
    public void setIsFinancialIndustry(Boolean isFinancialIndustry) {
        this.isFinancialIndustry = isFinancialIndustry;
    }

    /**
     * @return the financialIndustryCode
     */
    public String getFinancialIndustryCode() {
        return financialIndustryCode;
    }

    /**
     * @param financialIndustryCode
     *            the financialIndustryCode to set
     */
    public void setFinancialIndustryCode(String financialIndustryCode) {
        this.financialIndustryCode = financialIndustryCode;
    }

}
