/*
 * =========================================================================== IBM Confidential AIS Source Materials
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.stockcodeinfo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)StockInfoEntity.java
* 
* <p>Description:海外ETF/海外股票代碼資訊</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/02/02, Jojo Wei
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Schema(description = "海外ETF/海外股票代碼資訊")
public class StockCodeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 語系
     */
    @Schema(description = "語系")
    private String locale;

    /**
     * 代碼
     */
    @Schema(description = "代碼")
    private String stockCode;

    /**
     * 建立時間
     */
    @Schema(description = "建立時間")
    private Date createTime;

    /**
     * 說明
     */
    @Schema(description = "說明")
    private String stockCodeName;

    /**
     * 類型 A1：投資標的類型 A2：投資區域 A3：發行公司
     */
    @Schema(description = "類型 A1：投資標的類型 A2：投資區域 A3：發行公司")
    private String stockCodeType;

    /**
     * 更新時間
     */
    @Schema(description = "更新時間")
    private Date updateTime;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStockCodeName() {
        return stockCodeName;
    }

    public void setStockCodeName(String stockCodeName) {
        this.stockCodeName = stockCodeName;
    }

    public String getStockCodeType() {
        return stockCodeType;
    }

    public void setStockCodeType(String stockCodeType) {
        this.stockCodeType = stockCodeType;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
