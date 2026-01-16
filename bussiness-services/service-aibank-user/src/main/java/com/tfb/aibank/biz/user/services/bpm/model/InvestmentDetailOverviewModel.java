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
package com.tfb.aibank.biz.user.services.bpm.model;

import java.math.BigDecimal;

import com.tfb.aibank.common.type.InvestProductType;

//@formatter:off
/**
* @(#)InvestmentDetailOverviewModel.java
* 
* <p>Description: 投資分析明細總覽</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/06/27, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class InvestmentDetailOverviewModel {

    /**
     * 投資幣別
     */
    private String currencyCode;

    /**
     * 庫存市值
     */
    private BigDecimal amount;

    /**
     * 庫存商品名稱
     */
    private String name;

    /**
     * 庫存商品種類
     */
    private String itemType;

    public InvestmentDetailOverviewModel() {
    }

    public InvestmentDetailOverviewModel(String currencyCode, BigDecimal amount, String name, String itemType) {
        this.currencyCode = currencyCode;
        this.amount = amount;
        this.name = name;
        this.itemType = itemType;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
