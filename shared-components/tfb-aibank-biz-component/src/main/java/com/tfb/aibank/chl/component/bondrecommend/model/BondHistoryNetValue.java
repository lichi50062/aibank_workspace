/*
 * ===========================================================================
 * 
 * WIS Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.bondrecommend.model;

import java.math.BigDecimal;
import java.util.Date;

// @formatter:off
/**
 * @(#)BondRecommendHistoryNetValue.java
 * 
 * <p>Description:專屬推薦歷史淨值</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/26, ghw
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BondHistoryNetValue {

    /** 債券代號 */
    private String bondNo;

    /** 儀價日期 */
    private Date txDate;

    /** 買進收盤價 */
    private BigDecimal buyPrice;

    /** 建立時間 */
    private Date createTime;

    /** 賣出收盤價 */
    private BigDecimal sellPrice;

    /** ODS批次日期 */
    private Date snapDate;

    public String getBondNo() {
        return bondNo;
    }

    public void setBondNo(String bondNo) {
        this.bondNo = bondNo;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Date getSnapDate() {
        return snapDate;
    }

    public void setSnapDate(Date snapDate) {
        this.snapDate = snapDate;
    }

}
