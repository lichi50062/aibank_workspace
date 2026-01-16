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
package com.tfb.aibank.chl.component.stockinfo;

import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)StockInfoCacheManager.java
 * 
 * <p>Description:海外ETF/海外股票代碼資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/24, Horance Chou
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class StockInfoCacheManager extends AbstractCacheManager {

    @Autowired
    private StockInfoResource stockInfoResource;

    /**
     * Map<LOCALE, Map<STOCK_CODE + STOCK_CODE_TYPE, StockInfo>>
     */
    private Map<String, Map<String, StockInfoCache>> dataMap = new LinkedHashMap<>();

    public Map<String, StockInfoCache> getDataByLocale(String locale) {
        Map<String, StockInfoCache> map = dataMap.get(locale);
        return MapUtils.isEmpty(map) ? new HashMap<>() : map;
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.STOCK_INFO_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        List<StockInfoCache> dataList = stockInfoResource.getAllStockInfoData();
        Map<String, Map<String, StockInfoCache>> dataMap = new LinkedHashMap<>();
        dataList.forEach(stock -> {
            String locale = stock.getLocale();
            Map<String, StockInfoCache> localeMap = dataMap.get(locale);
            if (localeMap == null) {
                localeMap = new HashMap<String, StockInfoCache>();
                dataMap.put(locale, localeMap);
            }
            localeMap.put(stock.getInsuranceCode(), stock);
        });
        this.dataMap = dataMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.dataMap.isEmpty();
    }

    public StockInfoCache getDataByLocaleAndInsuranceCod(String locale, String insuranceNo) {
        return getDataByLocale(locale).get(insuranceNo);
    }

    @Override
    protected Duration cacheTimeout() {
        return Duration.ofMinutes(4);
    }

}
