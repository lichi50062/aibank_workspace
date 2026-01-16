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
package com.tfb.aibank.chl.component.exchangerate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// @formatter:off
/**
 * @(#)ExchangeRateCacheManager.java
 * 
 * <p>Description:匯率檔 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateResource rateResource;

    /**
     * 依 RATE_TYPE 取得資料
     * 
     * @param rateType
     *            0：台幣對外幣匯率(台對外、外對台) 1：外幣對外幣匯率 2：保管銀行牌告匯率 3：清算匯率
     * @return
     */
    public List<ExchangeRate> getExchangeRatesByRateType(String rateType) {
        return rateResource.getExchangeRatesByRateType(rateType);
    }

    /**
     * 取得所有 EXCHANGE_RATE 資料
     * 
     * @return
     */
    public List<ExchangeRate> getAllExchangeRates() {
        return rateResource.getAllExchangeRateData();
    }

    /**
     * 臺轉外
     * 
     * @param rateType
     * @param exchangeTypeNo
     * @param currencyEname1
     * @return
     */
    public ExchangeRate twd2Foreign(String exchangeTypeNo, String currencyEname1) {
        return rateResource.twd2Foreign(exchangeTypeNo, currencyEname1);
    }

    /**
     * 外轉外
     * 
     * @param rateType
     * @param currencyEname1
     * @param currencyEname2
     * @return
     */
    public ExchangeRate foreign2Foreign(String currencyEname1, String currencyEname2) {
        return rateResource.foreign2Foreign(currencyEname1, currencyEname2);
    }

}
