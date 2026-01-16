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
package com.tfb.aibank.chl.component.mfddiscountinfo;

import java.util.Date;
import java.util.Map;

// @formatter:off
/**
 * @(#)MfdDiscountInfoModel.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/16,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class MfdDiscountInfo {

    /**
     * 建立時間
     */
    private Date createTime;
    /**
     * 專案優惠代碼
     */
    private String discountCode;
    /**
     * 專案優惠名稱
     */
    private String discountDesc;
    /**
     * 資料鍵值
     */
    private int discountKey;
    /**
     * 適用交易型態 A：單筆 B：定期(不)定額 C：定期定額(FUND久久) BC：定期(不)定額+定期定額(FUND久久)
     */
    private String discountTxType;
    /**
     * 結束日期
     */
    private Date endDate;
    /**
     * 語系
     */
    private String locale;
    /**
     * 開始日期
     */
    private Date startDate;
    /**
     * 更新時間
     */
    private Date updateTime;

    private Map<String, MfdDiscountAmount> amountMapByCurCode;

    public MfdDiscountInfo() {
        super();
    }

    /**
     * @return {@link #createTime}
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            {@link #createTime}
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return {@link #discountCode}
     */
    public String getDiscountCode() {
        return discountCode;
    }

    /**
     * @param discountCode
     *            {@link #discountCode}
     */
    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    /**
     * @return {@link #discountDesc}
     */
    public String getDiscountDesc() {
        return discountDesc;
    }

    /**
     * @param discountDesc
     *            {@link #discountDesc}
     */
    public void setDiscountDesc(String discountDesc) {
        this.discountDesc = discountDesc;
    }

    /**
     * @return {@link #discountKey}
     */
    public int getDiscountKey() {
        return discountKey;
    }

    /**
     * @param discountKey
     *            {@link #discountKey}
     */
    public void setDiscountKey(int discountKey) {
        this.discountKey = discountKey;
    }

    /**
     * @return {@link #discountTxType}
     */
    public String getDiscountTxType() {
        return discountTxType;
    }

    /**
     * @param discountTxType
     *            {@link #discountTxType}
     */
    public void setDiscountTxType(String discountTxType) {
        this.discountTxType = discountTxType;
    }

    /**
     * @return {@link #endDate}
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            {@link #endDate}
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return {@link #locale}
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @param locale
     *            {@link #locale}
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * @return {@link #startDate}
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     *            {@link #startDate}
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return {@link #updateTime}
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     *            {@link #updateTime}
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Map<String, MfdDiscountAmount> getAmountMapByCurCode() {
        return amountMapByCurCode;
    }

    public void setAmountMapByCurCode(Map<String, MfdDiscountAmount> amountMapByCurCode) {
        this.amountMapByCurCode = amountMapByCurCode;
    }

}
