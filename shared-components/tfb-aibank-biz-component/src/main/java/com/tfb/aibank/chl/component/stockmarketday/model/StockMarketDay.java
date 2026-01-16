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
package com.tfb.aibank.chl.component.stockmarketday.model;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)StockMarketDay.java
* 
* <p>Description:海外ETF/海外股票市場交易日</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/18, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Schema(description = "海外ETF/海外股票市場交易日 下行資料")
public class StockMarketDay {

    /**
     * 交易市場代碼
     */
    @Schema(description = "交易市場代碼")
    private String stockMarketCode;

    /**
     * 交易市場
     */
    @Schema(description = "交易市場")
    private String stockMarket;

    /**
     * 市場交易日期
     */
    @Schema(description = "市場交易日期")
    private Date stockMarketDay;

    /**
     * 買賣註記 0：不可買賣 1：可買賣
     */
    @Schema(description = "買賣註記 0：不可買賣 1：可買賣")
    private String stockMarketFlag;

    /**
     * 更新日期
     */
    @Schema(description = "更新日期")
    private Date updateTime;

    /**
     * 市場交易日期2
     */
    @Schema(description = "市場交易日期2")
    private Date stockMarketDay2;

    /**
     * 市場交易日期3
     */
    @Schema(description = "市場交易日期3")
    private Date stockMarketDay3;

    /**
     * @return the stockMarketCode
     */
    public String getStockMarketCode() {
        return stockMarketCode;
    }

    /**
     * @param stockMarketCode
     *            the stockMarketCode to set
     */
    public void setStockMarketCode(String stockMarketCode) {
        this.stockMarketCode = stockMarketCode;
    }

    /**
     * @return the stockMarket
     */
    public String getStockMarket() {
        return stockMarket;
    }

    /**
     * @param stockMarket
     *            the stockMarket to set
     */
    public void setStockMarket(String stockMarket) {
        this.stockMarket = stockMarket;
    }

    /**
     * @return the stockMarketDay
     */
    public Date getStockMarketDay() {
        return stockMarketDay;
    }

    /**
     * @param stockMarketDay
     *            the stockMarketDay to set
     */
    public void setStockMarketDay(Date stockMarketDay) {
        this.stockMarketDay = stockMarketDay;
    }

    /**
     * @return the stockMarketFlag
     */
    public String getStockMarketFlag() {
        return stockMarketFlag;
    }

    /**
     * @param stockMarketFlag
     *            the stockMarketFlag to set
     */
    public void setStockMarketFlag(String stockMarketFlag) {
        this.stockMarketFlag = stockMarketFlag;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     *            the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the stockMarketDay2
     */
    public Date getStockMarketDay2() {
        return stockMarketDay2;
    }

    /**
     * @param stockMarketDay2
     *            the stockMarketDay2 to set
     */
    public void setStockMarketDay2(Date stockMarketDay2) {
        this.stockMarketDay2 = stockMarketDay2;
    }

    /**
     * @return the stockMarketDay3
     */
    public Date getStockMarketDay3() {
        return stockMarketDay3;
    }

    /**
     * @param stockMarketDay3
     *            the stockMarketDay3 to set
     */
    public void setStockMarketDay3(Date stockMarketDay3) {
        this.stockMarketDay3 = stockMarketDay3;
    }

}
