package com.tfb.aibank.chl.component.exchangeratehistory;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 匯率檔
 * 
 * @author Edward Tien
 */
public class ExRateHistoryMax implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 幣別代碼1
     */
    private String currencyEname1;

    /**
     * 幣別代碼
     */
    private String currencyEname2;


    /**
     * 匯率類別，0：台幣對換匯率；1：外幣對換匯率
     */
    private String rateType;

    private BigDecimal buyMax;

    private BigDecimal sellMin;

    public String getCurrencyEname1() {
        return currencyEname1;
    }

    public void setCurrencyEname1(String currencyEname1) {
        this.currencyEname1 = currencyEname1;
    }

    public String getCurrencyEname2() {
        return currencyEname2;
    }

    public void setCurrencyEname2(String currencyEname2) {
        this.currencyEname2 = currencyEname2;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public BigDecimal getBuyMax() {
        return buyMax;
    }

    public void setBuyMax(BigDecimal buyMax) {
        this.buyMax = buyMax;
    }

    public BigDecimal getSellMin() {
        return sellMin;
    }

    public void setSellMin(BigDecimal sellMin) {
        this.sellMin = sellMin;
    }

    @Override
    public String toString() {
        return "{" +
                "currencyEname1='" + currencyEname1 + '\'' +
                ", currencyEname2='" + currencyEname2 + '\'' +
                ", rateType='" + rateType + '\'' +
                ", buyMax=" + buyMax +
                ", sellMin=" + sellMin +
                '}';
    }
}
