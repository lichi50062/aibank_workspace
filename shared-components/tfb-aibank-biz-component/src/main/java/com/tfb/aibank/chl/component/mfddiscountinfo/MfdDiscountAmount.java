/*
 * ===========================================================================
 * IBM Confidential
 * AIS Source Materials
 *
 *
 * (C) Copyright IBM Corp. 2013.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.mfddiscountinfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

// @formatter:off
/**
 * @(#)MfdDiscountInfoEntity.java
 *
 * <p>基金優惠資訊</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/02/16,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class MfdDiscountAmount implements Serializable {

    /**
     * <code>serialVersionUID</code> 的註解
     */
    private static final long serialVersionUID = 2100202013441295387L;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 幣別代碼
     */
    private String currencyCode;

    /**
     * 專案優惠-資料鍵值
     */
    private Integer discountKey;

    /**
     * 金額上限
     */
    private BigDecimal maxAmount;

    /**
     * 金額下限
     */
    private BigDecimal minAmount;

    /**
     * 更新時間
     */
    private Date updateTime;

    /**
     * 取得建立時間
     *
     * @return Date 建立時間
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 設定建立時間
     *
     * @param createTime
     *         要設定的建立時間
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得幣別代碼
     *
     * @return String 幣別代碼
     */
    public String getCurrencyCode() {
        return this.currencyCode;
    }

    /**
     * 設定幣別代碼
     *
     * @param currencyCode
     *         要設定的幣別代碼
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * 取得專案優惠-資料鍵值
     *
     * @return Integer 專案優惠-資料鍵值
     */
    public Integer getDiscountKey() {
        return this.discountKey;
    }

    /**
     * 設定專案優惠-資料鍵值
     *
     * @param discountKey
     *         要設定的專案優惠-資料鍵值
     */
    public void setDiscountKey(Integer discountKey) {
        this.discountKey = discountKey;
    }

    /**
     * 取得金額上限
     *
     * @return BigDecimal 金額上限
     */
    public BigDecimal getMaxAmount() {
        return this.maxAmount;
    }

    /**
     * 設定金額上限
     *
     * @param maxAmount
     *         要設定的金額上限
     */
    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    /**
     * 取得金額下限
     *
     * @return BigDecimal 金額下限
     */
    public BigDecimal getMinAmount() {
        return this.minAmount;
    }

    /**
     * 設定金額下限
     *
     * @param minAmount
     *         要設定的金額下限
     */
    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    /**
     * 取得更新時間
     *
     * @return Date 更新時間
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 設定更新時間
     *
     * @param updateTime
     *         要設定的更新時間
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
