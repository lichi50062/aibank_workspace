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

// @formatter:off
/**
 * @(#)TopValueProduct.java
 * 
 * <p>Description:投資牌卡報酬率物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/27, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TopValueProduct {

    private String productName;

    private BigDecimal total;

    private BigDecimal roe;

    private String investProductType;

    public TopValueProduct() {
    }

    public TopValueProduct(String productName, BigDecimal total, BigDecimal roe, String investProductType) {
        this.productName = productName;
        this.total = total;
        this.roe = roe;
        this.investProductType = investProductType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getRoe() {
        return roe;
    }

    public void setRoe(BigDecimal roe) {
        this.roe = roe;
    }

    public String getInvestProductType() {
        return investProductType;
    }

    public void setInvestProductType(String investProductType) {
        this.investProductType = investProductType;
    }
}
