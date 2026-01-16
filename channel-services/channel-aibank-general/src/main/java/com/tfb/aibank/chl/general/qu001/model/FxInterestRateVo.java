package com.tfb.aibank.chl.general.qu001.model;

import com.tfb.aibank.chl.component.fxinterestrate.FxInterestRate;

// @formatter:off
/**
 * @(#)FxInterestRateVo.java
 *
 * <p>Description: 存款利率資料VO</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>[存款利率資料VO]</li>
 * </ol>
 */
//@formatter:on
public class FxInterestRateVo extends FxInterestRate {

    private String currencyDispName;

    private String txTimeStr;

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

    @Override
    public String toString() {
        //@formatter:off
        return "FxInterestRateVo{" +
                "currencyCname=" + currencyDispName + '\'' +
                "rateKey=" + getRateKey() + '\'' +
                "rateType=" + getRateType() + '\'' +
                "txTime=" + getTxTime() + '\'' +
                "txTimeStr=" + txTimeStr + '\'' +
                "currencyEname=" + getCurrencyEname() + '\'' +
                "typeNo=" + getTypeNo() + '\'' +
                "interestRate=" + getInterestRate() + '\'' +
                "currencySort=" + getCurrencySort() + '\'' +
                "createTime=" + getCreateTime() + '\'' +
                '}';
        //@formatter:on
    }
}
