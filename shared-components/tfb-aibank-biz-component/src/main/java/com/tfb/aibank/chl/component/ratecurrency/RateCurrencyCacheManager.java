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
package com.tfb.aibank.chl.component.ratecurrency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.currencycode.CurrencyCode;

// @formatter:off
/**
 *  @(#)RateCurrencyCacheManager.java
 * 
 * <p>Description:RateCurrencyCacheManager</p>
 * <p>Modify History:</p>
 * v1.0, 2023/05/25
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class RateCurrencyCacheManager extends AbstractCacheManager {

    @Autowired
    private RateCurrencyResource rateCurrencyResource;

    /** Key: RATE_TYPE */
    private Map<String, List<RateCurrency>> rateCurrencyMap = new HashMap<>();

    /** Key: RATE_TYPE 包含台幣的 */
    private Map<String, List<RateCurrency>> rateCurrencyWithTWDMap = new HashMap<>();

    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.RATE_CURRENCY_CACHE_KEY;
    }

    /**
     * 依 RateType 取得RateCurrencys
     * 
     * @param rateType
     * @return
     */
    public List<RateCurrency> getRateCurrenciesByRateType(String rateType) {
        List<RateCurrency> list = rateCurrencyMap.get(rateType);
        if (CollectionUtils.isNotEmpty(list)) {
            List<RateCurrency> resultArray = new ArrayList<>(list);
            IBUtils.sort(resultArray, "currencySort", false); // 由小到大
            return resultArray;
        }
        return list;
    }

    /**
     * 依 RateType 取得RateCurrencys - 含台幣
     * 
     * @return
     */
    public List<RateCurrency> getRateCurrenciesByRateTypeWithTWD(String rateType) {
        return rateCurrencyWithTWDMap.get(rateType);
    }

    @Override
    protected void loadCache() {
        List<RateCurrency> rateCurrencies = rateCurrencyResource.getAllRateCurrency();
        if (CollectionUtils.isNotEmpty(rateCurrencies)) {
            Map<String, List<RateCurrency>> tmpRateCurrencyMap = new HashMap<>();
            for (var rateCur : rateCurrencies) {
                String rateType = rateCur.getRateType();
                if (null == tmpRateCurrencyMap.get(rateType)) {
                    List<RateCurrency> rateCurrencys = new ArrayList<>();
                    rateCurrencys.add(rateCur);
                    tmpRateCurrencyMap.put(rateType, rateCurrencys);
                }
                else {
                    tmpRateCurrencyMap.get(rateType).add(rateCur);
                }
            }
            this.rateCurrencyMap = tmpRateCurrencyMap;

            if (MapUtils.isNotEmpty(rateCurrencyMap)) {
                Map<String, List<RateCurrency>> rateCurrencyWithTWDMap = new HashMap<>();
                for (Map.Entry<String, List<RateCurrency>> entry : rateCurrencyMap.entrySet()) {
                    String rateType = entry.getKey();
                    List<RateCurrency> rcList = entry.getValue();
                    RateCurrency rcTwd = new RateCurrency();
                    rcTwd.setCurrencyEname1(CurrencyCode.TWD);
                    rcTwd.setCurrencyEname2(CurrencyCode.TWD);
                    rcTwd.setRateType(rateType);
                    rcTwd.setCurrencySort(0);
                    rcList.add(rcTwd);

                    IBUtils.sort(rcList, "currencySort", false);

                    rateCurrencyWithTWDMap.put(rateType, rcList);
                }
                this.rateCurrencyWithTWDMap = rateCurrencyWithTWDMap;
            }
        }
    }

    @Override
    protected boolean isFirstLoad() {
        return this.rateCurrencyMap.isEmpty();
    }

}
