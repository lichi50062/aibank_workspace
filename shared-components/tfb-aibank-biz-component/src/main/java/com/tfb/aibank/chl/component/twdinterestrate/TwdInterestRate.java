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
package com.tfb.aibank.chl.component.twdinterestrate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)TwdInterestRate.java
 * 
 * <p>Description: 台幣存款利率檔</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TwdInterestRate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 利率鍵值
     */
    @Schema(description = "利率鍵值")
    private Long rateKey;

    /**
     * 利率類型
     */
    @Schema(description = "利率類型")
    private String rateType;

    /**
     * CATG類別
     */
    @Schema(description = "CATG類別")
    private String catgType;

    /**
     * 建立時間
     */
    @Schema(description = "建立時間")
    private Date createTime;

    /**
     * 機動利率
     */
    @Schema(description = "機動利率")
    private BigDecimal floatingRate;

    /**
     * 限定類別
     */
    @Schema(description = "限定類別")
    private String limitType;

    /**
     * 固定利率
     */
    @Schema(description = "固定利率")
    private BigDecimal staticRate;

    /**
     * SV 類別
     */
    @Schema(description = "SV 類別")
    private String svType;

    /**
     * TERM類別
     */
    @Schema(description = "TERM類別")
    private String termType;

    /**
     * 交易時間
     */
    @Schema(description = "交易時間")
    private Date txTime;

    /**
     * 利率種類
     */
    @Schema(description = "利率種類")
    private String intTyp;

    /**
     * @return the rateKey
     */
    public Long getRateKey() {
        return rateKey;
    }

    /**
     * @param rateKey
     *            the rateKey to set
     */
    public void setRateKey(Long rateKey) {
        this.rateKey = rateKey;
    }

    /**
     * @return the rateType
     */
    public String getRateType() {
        return rateType;
    }

    /**
     * @param rateType
     *            the rateType to set
     */
    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    /**
     * @return the catgType
     */
    public String getCatgType() {
        return catgType;
    }

    /**
     * @param catgType
     *            the catgType to set
     */
    public void setCatgType(String catgType) {
        this.catgType = catgType;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the floatingRate
     */
    public BigDecimal getFloatingRate() {
        return floatingRate;
    }

    /**
     * @param floatingRate
     *            the floatingRate to set
     */
    public void setFloatingRate(BigDecimal floatingRate) {
        this.floatingRate = floatingRate;
    }

    /**
     * @return the limitType
     */
    public String getLimitType() {
        return limitType;
    }

    /**
     * @param limitType
     *            the limitType to set
     */
    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }

    /**
     * @return the staticRate
     */
    public BigDecimal getStaticRate() {
        return staticRate;
    }

    /**
     * @param staticRate
     *            the staticRate to set
     */
    public void setStaticRate(BigDecimal staticRate) {
        this.staticRate = staticRate;
    }

    /**
     * @return the svType
     */
    public String getSvType() {
        return svType;
    }

    /**
     * @param svType
     *            the svType to set
     */
    public void setSvType(String svType) {
        this.svType = svType;
    }

    /**
     * @return the termType
     */
    public String getTermType() {
        return termType;
    }

    /**
     * @param termType
     *            the termType to set
     */
    public void setTermType(String termType) {
        this.termType = termType;
    }

    /**
     * @return the txTime
     */
    public Date getTxTime() {
        return txTime;
    }

    /**
     * @param txTime
     *            the txTime to set
     */
    public void setTxTime(Date txTime) {
        this.txTime = txTime;
    }

    /**
     * @return the intTyp
     */
    public String getIntTyp() {
        return intTyp;
    }

    /**
     * @param intTyp
     *            the intTyp to set
     */
    public void setIntTyp(String intTyp) {
        this.intTyp = intTyp;
    }

}
