package com.tfb.aibank.biz.user.services.ratecarduser.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 客戶自訂匯率幣別牌卡
 */
public class RateCardUserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主檔鍵值
     */
    private Integer rateCardKey;

    /**
     * 公司鍵值
     */
    private Integer companyKey;

    /**
     * 使用者鍵值
     */
    private Integer userKey;

    /**
     * 幣別名稱1
     */
    private String currencyEname1;

    /**
     * 幣別名稱2
     */
    private String currencyEname2;

    /**
     * 幣別順序
     */
    private Integer currencySort;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 更新時間
     */
    private Date updateTime;

    /**
     * 主檔鍵值
     */
    public void setRateCardKey(Integer rateCardKey) {
        this.rateCardKey = rateCardKey;
    }

    /**
     * 主檔鍵值
     */
    public Integer getRateCardKey() {
        return rateCardKey;
    }

    /**
     * 公司鍵值
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * 公司鍵值
     */
    public Integer getCompanyKey() {
        return companyKey;
    }

    /**
     * 使用者鍵值
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    /**
     * 使用者鍵值
     */
    public Integer getUserKey() {
        return userKey;
    }

    /**
     * 幣別名稱1
     */
    public void setCurrencyEname1(String currencyEname1) {
        this.currencyEname1 = currencyEname1;
    }

    /**
     * 幣別名稱1
     */
    public String getCurrencyEname1() {
        return currencyEname1;
    }

    /**
     * 幣別名稱2
     */
    public void setCurrencyEname2(String currencyEname2) {
        this.currencyEname2 = currencyEname2;
    }

    /**
     * 幣別名稱2
     */
    public String getCurrencyEname2() {
        return currencyEname2;
    }

    /**
     * 幣別順序
     */
    public void setCurrencySort(Integer currencySort) {
        this.currencySort = currencySort;
    }

    /**
     * 幣別順序
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

    /**
     * 更新時間
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 更新時間
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public String toString() {
        return "RateCardUserModel{" +
                "rateCardKey=" + rateCardKey + '\'' +
                "companyKey=" + companyKey + '\'' +
                "userKey=" + userKey + '\'' +
                "currencyEname1=" + currencyEname1 + '\'' +
                "currencyEname2=" + currencyEname2 + '\'' +
                "currencySort=" + currencySort + '\'' +
                "createTime=" + createTime + '\'' +
                "updateTime=" + updateTime + '\'' +
                '}';
    }
}
