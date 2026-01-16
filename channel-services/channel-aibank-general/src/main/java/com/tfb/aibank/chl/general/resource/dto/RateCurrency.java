package com.tfb.aibank.chl.general.resource.dto;

import java.util.Date;

// @formatter:off
/**
 * @(#)RateCurrencyModel.java
 * 
 * <p>Description:匯利率幣別檔</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class RateCurrency {

    /**
     * 資料鍵值
     */
    private Long rateKey;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 幣別代碼1
     */
    private String currencyEname1;

    /**
     * 幣別代碼2
     */
    private String currencyEname2;

    /**
     * 順序
     */
    private Integer currencySort;

    /**
     * 匯利率類別，0：臺幣對外幣匯率(台對外、外對台)、1：外幣對外幣匯率、2：保管銀行牌告匯率(兌換臺幣)、3：外幣利率、4：保管銀行牌告匯率(兌換外幣)、5：臺幣對外幣今日遠期匯率、6：外幣定存優利專案
     */
    private String rateType;

    /**
     * 更新時間
     */
    private Date updateTime;

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
     * @return the currencyEname1
     */
    public String getCurrencyEname1() {
        return currencyEname1;
    }

    /**
     * @param currencyEname1
     *            the currencyEname1 to set
     */
    public void setCurrencyEname1(String currencyEname1) {
        this.currencyEname1 = currencyEname1;
    }

    /**
     * @return the currencyEname2
     */
    public String getCurrencyEname2() {
        return currencyEname2;
    }

    /**
     * @param currencyEname2
     *            the currencyEname2 to set
     */
    public void setCurrencyEname2(String currencyEname2) {
        this.currencyEname2 = currencyEname2;
    }

    /**
     * @return the currencySort
     */
    public Integer getCurrencySort() {
        return currencySort;
    }

    /**
     * @param currencySort
     *            the currencySort to set
     */
    public void setCurrencySort(Integer currencySort) {
        this.currencySort = currencySort;
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

}
