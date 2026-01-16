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

// @formatter:off

import java.io.Serializable;
import java.util.List;
/**
 * @(#)PolicyCaseItem.java
 * 
 * <p>Description:投保方案</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/06, Jackie Chien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class PolicyCaseItem implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 投保方案 */
    private String insureCase;

    /** 優惠總保費 */
    private String totalPremium;

    /** 原始總保費 */
    private String oriTotalPremium;

    /** 投保商品 */
    private List<ProductsItem> products;

    public String getInsureCase() {
        return insureCase;
    }

    public void setInsureCase(String insureCase) {
        this.insureCase = insureCase;
    }

    public String getTotalPremium() {
        return totalPremium;
    }

    public void setTotalPremium(String totalPremium) {
        this.totalPremium = totalPremium;
    }

    public String getOriTotalPremium() {
        return oriTotalPremium;
    }

    public void setOriTotalPremium(String oriTotalPremium) {
        this.oriTotalPremium = oriTotalPremium;
    }

    public List<ProductsItem> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsItem> products) {
        this.products = products;
    }
}
