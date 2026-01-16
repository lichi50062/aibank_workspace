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
package com.tfb.aibank.chl.component.fxinterestrate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)FxInterestRate.java
 * 
 * <p>Description:外幣存款利率檔</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FxInterestRate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 資料鍵值
     */
    @Schema(description = "資料鍵值")
    private Long rateKey;

    /**
     * 0：最新利率 1：歷史利率
     */
    @Schema(description = "利率別")
    private Integer rateType;

    /**
     * 交易時間
     */
    @Schema(description = "交易時間")
    private Date txTime;

    /**
     * 幣別名稱
     */
    @Schema(description = "幣別名稱")
    private String currencyEname;

    /**
     * 類別編號
     */
    @Schema(description = "類別編號")
    private String typeNo;

    /**
     * 利率
     */
    @Schema(description = "利率")
    private BigDecimal interestRate;

    /**
     * 順序
     */
    @Schema(description = "順序")
    private Integer currencySort;

    /**
     * 建立時間
     */
    @Schema(description = "建立時間")
    private Date createTime;

    /**
     * 資料鍵值
     */
    public void setRateKey(Long rateKey) {
        this.rateKey = rateKey;
    }

    /**
     * 資料鍵值
     */
    public Long getRateKey() {
        return rateKey;
    }

    /**
     * 利率別
     */
    public void setRateType(Integer rateType) {
        this.rateType = rateType;
    }

    /**
     * 利率別
     */
    public Integer getRateType() {
        return rateType;
    }

    /**
     * 交易時間
     */
    public void setTxTime(Date txTime) {
        this.txTime = txTime;
    }

    /**
     * 交易時間
     */
    public Date getTxTime() {
        return txTime;
    }

    public String getCurrencyEname() {
        return currencyEname;
    }

    public void setCurrencyEname(String currencyEname) {
        this.currencyEname = currencyEname;
    }

    /**
     * 類別編號
     */
    public void setTypeNo(String typeNo) {
        this.typeNo = typeNo;
    }

    /**
     * 類別編號
     */
    public String getTypeNo() {
        return typeNo;
    }

    /**
     * 利率
     */
    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * 利率
     */
    public BigDecimal getInterestRate() {
        return interestRate;
    }

    /**
     * 順序
     */
    public void setCurrencySort(Integer currencySort) {
        this.currencySort = currencySort;
    }

    /**
     * 順序
     */
    public Integer getCurrencySort() {
        return currencySort;
    }

    /**
     * 建立時間
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 建立時間
     */
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        //@formatter:off
        return "FxInterestRate{" +
                "rateKey=" + rateKey + '\'' +
                "rateType=" + rateType + '\'' +
                "txTime=" + txTime + '\'' +
                "currencyEname=" + currencyEname + '\'' +
                "typeNo=" + typeNo + '\'' +
                "interestRate=" + interestRate + '\'' +
                "currencySort=" + currencySort + '\'' +
                "createTime=" + createTime + '\'' +
                '}';
        //@formatter:on
    }

}
