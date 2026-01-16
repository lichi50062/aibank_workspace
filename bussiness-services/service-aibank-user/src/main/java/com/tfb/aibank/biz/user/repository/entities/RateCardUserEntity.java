package com.tfb.aibank.biz.user.repository.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * 客戶自訂匯率
 */
@Entity
@Table(name = "RATE_CARD_USER")
public class RateCardUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主檔鍵值
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RATE_CARD_USER_SEQ")
    @SequenceGenerator(name = "RATE_CARD_USER_SEQ", sequenceName = "RATE_CARD_USER_SEQ", allocationSize = 1)
    @Column(name = "RATE_CARD_KEY", nullable = false)
    private Integer rateCardKey;

    /**
     * 公司鍵值
     */
    @Column(name = "COMPANY_KEY", nullable = false)
    private Integer companyKey;

    /**
     * 使用者鍵值
     */
    @Column(name = "USER_KEY", nullable = false)
    private Integer userKey;

    /**
     * 幣別名稱1
     */
    @Column(name = "CURRENCY_ENAME_1", nullable = false)
    private String currencyEname1;

    /**
     * 幣別名稱2
     */
    @Column(name = "CURRENCY_ENAME_2")
    private String currencyEname2;

    /**
     * 幣別順序
     */
    @Column(name = "CURRENCY_SORT")
    private Integer currencySort;

    /**
     * 建立時間
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 更新時間
     */
    @Column(name = "UPDATE_TIME")
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
        return "RateCardUserEntity{" + "rateCardKey=" + rateCardKey + '\'' + "companyKey=" + companyKey + '\'' + "userKey=" + userKey + '\'' + "currencyEname1=" + currencyEname1 + '\'' + "currencyEname2=" + currencyEname2 + '\'' + "currencySort=" + currencySort + '\'' + "createTime=" + createTime + '\'' + "updateTime=" + updateTime + '\'' + '}';
    }
}
