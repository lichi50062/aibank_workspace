package com.tfb.aibank.chl.general.qu001.model;

import com.tfb.aibank.chl.component.exchangerate.ExchangeRate;


// @formatter:off
/**
 * @(#)ExchangeRateVo.java
 *
 * <p>Description: 匯率資料VO</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>[匯率資料VO]</li>
 * </ol>
 */
//@formatter:on
public class ExchangeRateVo extends ExchangeRate {

    private String currencyDispName;

    private String txTimeStr;

    /** 匯率優惠訊息 */
    private String rateInfo;

    private Integer rateInfoClass;

    private Integer orderSeq;

    private boolean tfbEmployee;
    
    private String rateInfoKey;
    
    private String rateInfoParam;

    public String getCurrencyDispName() {
        return currencyDispName;
    }

    public void setCurrencyDispName(String currencyDispName) {
        this.currencyDispName = currencyDispName;
    }

    public String getTxTimeStr() {
        return txTimeStr;
    }

    public void setTxTimeStr(String txTimeStr) {
        this.txTimeStr = txTimeStr;
    }

    public String getRateInfo() {
        return rateInfo;
    }

    public void setRateInfo(String rateInfo) {
        this.rateInfo = rateInfo;
    }

    public Integer getRateInfoClass() {
        return rateInfoClass;
    }

    public void setRateInfoClass(Integer rateInfoClass) {
        this.rateInfoClass = rateInfoClass;
    }

    public Integer getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(Integer orderSeq) {
        this.orderSeq = orderSeq;
    }

    public boolean isTfbEmployee() {
        return tfbEmployee;
    }

    public void setTfbEmployee(boolean tfbEmployee) {
        this.tfbEmployee = tfbEmployee;
    }
    //@formatter:off
    @Override public String toString() {
        return "ExchangeRateVo{" +
                "currencyDispName='" + currencyDispName + '\'' +
                ", txTimeStr='" + txTimeStr + '\'' +
                ", rateInfo='" + rateInfo + '\'' +
                ", orderSeq=" + orderSeq +
                "}" + super.toString();
    }
    //@formatter:on

    public String getRateInfoKey() {
        return rateInfoKey;
    }

    public void setRateInfoKey(String rateInfoKey) {
        this.rateInfoKey = rateInfoKey;
    }

    public String getRateInfoParam() {
        return rateInfoParam;
    }

    public void setRateInfoParam(String rateInfoParam) {
        this.rateInfoParam = rateInfoParam;
    }

}
