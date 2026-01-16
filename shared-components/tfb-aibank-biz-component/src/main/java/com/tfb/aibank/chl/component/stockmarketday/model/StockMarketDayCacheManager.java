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
package com.tfb.aibank.chl.component.stockmarketday.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)StockMarketDayCacheManager.java
 * 
 * <p>Description:海外ETF/海外股票市場交易日 cacheManager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/18 Alex PY Li,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class StockMarketDayCacheManager extends AbstractCacheManager {

    @Autowired
    private StockMarketDayResource stockMarketDayResource;

    private Map<String, StockMarketDay> dataMap = new LinkedHashMap<>();

    public StockMarketDay getDataByMarketCode(String marketCode) {
        return dataMap.get(marketCode);
    }

    public List<StockMarketDay> getAll() {
        return new ArrayList<>(dataMap.values());
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.STOCK_MARKET_DAY_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        List<StockMarketDay> dataList = stockMarketDayResource.getStockMarketDay();
        Map<String, StockMarketDay> dataMap = new LinkedHashMap<>();
        dataList.forEach(stock -> {
            String marketCode = stock.getStockMarketCode();
            dataMap.put(marketCode, stock);
        });
        this.dataMap = dataMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.dataMap.isEmpty();
    }

}
