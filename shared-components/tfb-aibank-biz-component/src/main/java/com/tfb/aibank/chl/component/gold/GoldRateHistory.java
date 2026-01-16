package com.tfb.aibank.chl.component.gold;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)GoldRateHistory.java
 * 
 * <p>Description:黃金存摺歷史牌價</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/29, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "黃金存摺歷史牌價")
public class GoldRateHistory implements Serializable {

    private static final long serialVersionUID = -8719129324703670982L;

    /**
     * 資料鍵值
     */
    private Integer rateKey;

    /**
     * 牌價日期
     */
    private Date effectDate;

    /**
     * 商品代號
     */
    private String productCode;

    /**
     * 商品名稱
     */
    private String productName;

    /**
     * 幣別代碼
     */
    private String currencyCode;

    /**
     * 銀行買入價
     */
    private BigDecimal buy;

    /**
     * 銀行賣出價
     */
    private BigDecimal sell;

    /**
     * 轉換價差
     */
    private BigDecimal transfer;

    /**
     * 買價符號
     */
    private String buyCSign;

    /**
     * 買價數值
     */
    private BigDecimal buyC;

    /**
     * 買價漲跌幅
     */
    private BigDecimal buyCRate;

    /**
     * 建立時間
     */
    private Date createTime;

    public Integer getRateKey() {
        return rateKey;
    }

    public void setRateKey(Integer rateKey) {
        this.rateKey = rateKey;
    }

    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getBuy() {
        return buy;
    }

    public void setBuy(BigDecimal buy) {
        this.buy = buy;
    }

    public BigDecimal getSell() {
        return sell;
    }

    public void setSell(BigDecimal sell) {
        this.sell = sell;
    }

    public BigDecimal getTransfer() {
        return transfer;
    }

    public void setTransfer(BigDecimal transfer) {
        this.transfer = transfer;
    }

    public String getBuyCSign() {
        return buyCSign;
    }

    public void setBuyCSign(String buyCSign) {
        this.buyCSign = buyCSign;
    }

    public BigDecimal getBuyC() {
        return buyC;
    }

    public void setBuyC(BigDecimal buyC) {
        this.buyC = buyC;
    }

    public BigDecimal getBuyCRate() {
        return buyCRate;
    }

    public void setBuyCRate(BigDecimal buyCRate) {
        this.buyCRate = buyCRate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
