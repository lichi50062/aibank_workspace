package com.tfb.aibank.chl.general.qu001.model;

import com.ibm.tw.ibmb.base.model.RsData;
import org.springframework.stereotype.Component;

import java.util.List;
// @formatter:off
/**
 * @(#)NGNQU001050Rs.java
 *
 * <p>Description: 首頁-匯率資料區塊Rs</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>匯率資料區塊Rs</li>
 * </ol>
 */
//@formatter:on
@Component
public class NGNQU001050Rs implements RsData {

    private List<ExchangeRateVo> exchangeRates;

    public List<ExchangeRateVo> getExchangeRates() {
        return exchangeRates;
    }

    public void setExchangeRates(List<ExchangeRateVo> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }
}
