package com.tfb.aibank.chl.general.resource.dto;

import java.util.Date;

// @formatter:off
/**
 * @(#)FxTransDiscountId.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * <ol>v1.0, 2023/6/9, Marty
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FxTransDiscountId {

    /**
     * 公司鍵值
     */
    private Integer companyKey;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 優惠代號
     */
    private String discountCode;

    /**
     * 取得公司鍵值
     *
     * @return Integer 公司鍵值
     */
    public Integer getCompanyKey() {
        return this.companyKey;
    }

    /**
     * 設定公司鍵值
     *
     * @param companyKey
     *            要設定的公司鍵值
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

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
     *            要設定的建立時間
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得優惠代號
     *
     * @return String 優惠代號
     */
    public String getDiscountCode() {
        return this.discountCode;
    }

    /**
     * 設定優惠代號
     *
     * @param discountCode
     *            要設定的優惠代號
     */
    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }
}
