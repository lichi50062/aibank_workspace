package com.tfb.aibank.chl.component.bondrecommend.model;

import java.math.BigDecimal;

// @formatter:off
/**
 * @(#)BondRecommendLineGraph.java
 * 
 * <p>Description:專屬推薦折線圖</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/26, ghw
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BondRecommendLineGraph {

    /** 債券代號 */
    private String bondNo;

    /** 儀價日期 */
    private long txDate;

    /** 買進收盤價 */
    private BigDecimal buyPrice;

    /** 賣出收盤價 */
    private BigDecimal sellPrice;

    /**
     * @return the bondNo
     */
    public String getBondNo() {
        return bondNo;
    }

    /**
     * @param bondNo
     *            the bondNo to set
     */
    public void setBondNo(String bondNo) {
        this.bondNo = bondNo;
    }

    /**
     * @return the txDate
     */
    public long getTxDate() {
        return txDate;
    }

    /**
     * @param txDate
     *            the txDate to set
     */
    public void setTxDate(long txDate) {
        this.txDate = txDate;
    }

    /**
     * @return the buyPrice
     */
    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    /**
     * @param buyPrice
     *            the buyPrice to set
     */
    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    /**
     * @return the sellPrice
     */
    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    /**
     * @param sellPrice
     *            the sellPrice to set
     */
    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

}
