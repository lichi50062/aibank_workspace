package com.tfb.aibank.biz.user.services.bpm.model;

import java.math.BigDecimal;
import java.util.Map;

import com.tfb.aibank.common.type.InvestItemType;

// @formatter:off
import java.math.BigDecimal;
import java.util.Map;

import com.tfb.aibank.common.type.InvestItemType; 

/**
 * @(#)InvestmentOverviewModel.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * <ol>v1.0, 2023/6/10, Marty
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class InvestmentOverviewModel {

    /** 市值總值 */
    private BigDecimal investTtlAmt;

    private Map<String, BigDecimal> productValMap;

    /** 投資總覽市值大於0之集合 */
    private Map<InvestItemType, BigDecimal> productTypeMap;

    public BigDecimal getInvestTtlAmt() {
        return investTtlAmt;
    }

    public void setInvestTtlAmt(BigDecimal investTtlAmt) {
        this.investTtlAmt = investTtlAmt;
    }

    public Map<String, BigDecimal> getProductValMap() {
        return productValMap;
    }

    public void setProductValMap(Map<String, BigDecimal> productValMap) {
        this.productValMap = productValMap;
    }

    /**
     * @return the productTypeMap
     */
    public Map<InvestItemType, BigDecimal> getProductTypeMap() {
        return productTypeMap;
    }

    /**
     * @param productTypeMap
     *            the productTypeMap to set
     */
    public void setProductTypeMap(Map<InvestItemType, BigDecimal> productTypeMap) {
        this.productTypeMap = productTypeMap;
    }

}
