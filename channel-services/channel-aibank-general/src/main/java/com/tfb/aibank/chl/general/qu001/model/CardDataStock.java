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
import java.util.List;

import com.tfb.aibank.chl.component.homepagecard.HomepageCard;
import com.tfb.aibank.chl.general.resource.dto.AssetAccount;

// @formatter:off
/**
 * @(#)CardDataStocks.java
 *
 * <p>Description: 功能牌卡-證券資料</p>
 *
 * <p>Modify History:</p>
 * v1, 2024/02/27, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class CardDataStock extends CardDataParent {

    private String autoSummary;

    private String stockInfoStatus;

    private Integer cardState;

    private BigDecimal totalAmount;

    private BigDecimal inCountryTotalAmount;

    private BigDecimal outCountryTotalAmount;

    private Boolean qSearchFlag;

    private List<AssetAccount> assetAccountList;

    private List<TopValueAssetAccount> topValueAssetAccountList;

    public CardDataStock() {
        super();
    }

    public CardDataStock(HomepageCard homepageCard) {
        super(homepageCard);
    }

    public String getAutoSummary() {
        return autoSummary;
    }

    public void setAutoSummary(String autoSummary) {
        this.autoSummary = autoSummary;
    }

    public String getStockInfoStatus() {
        return stockInfoStatus;
    }

    public void setStockInfoStatus(String stockInfoStatus) {
        this.stockInfoStatus = stockInfoStatus;
    }

    public Integer getCardState() {
        return cardState;
    }

    public void setCardState(Integer cardState) {
        this.cardState = cardState;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getInCountryTotalAmount() {
        return inCountryTotalAmount;
    }

    public void setInCountryTotalAmount(BigDecimal inCountryTotalAmount) {
        this.inCountryTotalAmount = inCountryTotalAmount;
    }

    public BigDecimal getOutCountryTotalAmount() {
        return outCountryTotalAmount;
    }

    public void setOutCountryTotalAmount(BigDecimal outCountryTotalAmount) {
        this.outCountryTotalAmount = outCountryTotalAmount;
    }

    public Boolean getqSearchFlag() {
        return qSearchFlag;
    }

    public void setqSearchFlag(Boolean qSearchFlag) {
        this.qSearchFlag = qSearchFlag;
    }

    public List<AssetAccount> getAssetAccountList() {
        return assetAccountList;
    }

    public void setAssetAccountList(List<AssetAccount> assetAccountList) {
        this.assetAccountList = assetAccountList;
    }

    public List<TopValueAssetAccount> getTopValueAssetAccountList() {
        return topValueAssetAccountList;
    }

    public void setTopValueAssetAccountList(List<TopValueAssetAccount> topValueAssetAccountList) {
        this.topValueAssetAccountList = topValueAssetAccountList;
    }
}
