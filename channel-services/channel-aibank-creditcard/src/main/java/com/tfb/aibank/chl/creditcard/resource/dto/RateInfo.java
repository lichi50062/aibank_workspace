package com.tfb.aibank.chl.creditcard.resource.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class RateInfo {

    private Date createTime;

    /**
     * 生效日期
     */
    private Date effectDate;


    /**
     * 生效日期 (顯示用)
     */
    private String effectDateDsp;

    /**
     * 利率
     */
    private BigDecimal rate;

    /**
     * 資料鍵值
     */
    private Integer rateKey;

    /**
     * 利率別
     */
    private String rateKind;

    /**
     * 利率種類
     */
    private String rateTypeNo;


    private Map<String, String> rateTypeNameMap;

    /** 依照語系和Rate_Type比對出的名稱 */
    private String rateTypeName;

    /** 依規格在NCCQU014中顯示的名稱 */
    private String rateTypeNameDsp;

    private Integer orderSeq;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得生效日期
     *
     * @return Date 生效日期
     */
    public Date getEffectDate() {
        return this.effectDate;
    }

    /**
     * 設定生效日期
     *
     * @param effectDate 要設定的生效日期
     */
    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public String getEffectDateDsp() {
        return effectDateDsp;
    }

    public void setEffectDateDsp(String effectDateDsp) {
        this.effectDateDsp = effectDateDsp;
    }

    /**
     * 取得利率
     *
     * @return BigDecimal 利率
     */
    public BigDecimal getRate() {
        return this.rate;
    }

    /**
     * 設定利率
     *
     * @param rate 要設定的利率
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 取得資料鍵值
     *
     * @return Integer 資料鍵值
     */
    public Integer getRateKey() {
        return this.rateKey;
    }

    /**
     * 設定資料鍵值
     *
     * @param rateKey 要設定的資料鍵值
     */
    public void setRateKey(Integer rateKey) {
        this.rateKey = rateKey;
    }

    /**
     * 取得利率別
     *
     * @return String 利率別
     */
    public String getRateKind() {
        return this.rateKind;
    }

    /**
     * 設定利率別
     *
     * @param rateKind 要設定的利率別
     */
    public void setRateKind(String rateKind) {
        this.rateKind = rateKind;
    }

    /**
     * 取得利率種類
     *
     * @return String 利率種類
     */
    public String getRateTypeNo() {
        return this.rateTypeNo;
    }

    /**
     * 設定利率種類
     *
     * @param rateTypeNo 要設定的利率種類
     */
    public void setRateTypeNo(String rateTypeNo) {
        this.rateTypeNo = rateTypeNo;
    }

    public Map<String, String> getRateTypeNameMap() {
        return rateTypeNameMap;
    }

    public void setRateTypeNameMap(Map<String, String> rateTypeNameMap) {
        this.rateTypeNameMap = rateTypeNameMap;
    }

    public String getRateTypeName() {
        return rateTypeName;
    }

    public void setRateTypeName(String rateTypeName) {
        this.rateTypeName = rateTypeName;
    }

    public String getRateTypeNameDsp() {
        return rateTypeNameDsp;
    }

    public void setRateTypeNameDsp(String rateTypeNameDsp) {
        this.rateTypeNameDsp = rateTypeNameDsp;
    }

    public Integer getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(Integer orderSeq) {
        this.orderSeq = orderSeq;
    }
}
