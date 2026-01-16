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
package com.tfb.aibank.chl.component.stockcodeinfo;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)StockCodeInfoCacheManager.java
 * 
 * <p>Description:海外ETF/海外股票代碼資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/02, Jojo Wei
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class StockCodeInfoCacheManager extends AbstractCacheManager {

    @Autowired
    private StockCodeInfoResource stockCodeInfoResource;

    /** 預設語系：繁中 */
    private static final String DEFAULT_LOCALE = Locale.TAIWAN.toString();

    /**
     * Map<LOCALE, Map<STOCK_CODE + STOCK_CODE_TYPE, StockCodeInfo>>
     */
    private Map<String, Map<String, StockCodeInfo>> dataMap = new LinkedHashMap<>();

    /**
     * 取得海外ETF/海外股票代碼指定代碼及指定類型的說明
     * 
     * @param stockCode
     * @param stockCodeType
     * @return
     */
    public String getStockCodeName(String stockCode, String stockCodeType) {
        return getStockCodeName(stockCode, stockCodeType, DEFAULT_LOCALE);
    }

    public String getStockCodeName(String stockCode, String stockCodeType, Locale locale) {
        if (locale == null) {
            return getStockCodeName(stockCode, stockCodeType);
        }
        return getStockCodeName(stockCode, stockCodeType, locale.toString());
    }

    public String getStockCodeName(String stockCode, String stockCodeType, String locale) {
        StockCodeInfo stock = getDataByLocale(locale).get(getMapKey(stockCode, stockCodeType));
        return stock == null ? StringUtils.EMPTY : stock.getStockCodeName();
    }

    /**
     * 取得海外ETF/海外股票代碼指定代碼及指定類型的所有資訊
     * 
     * @param stockCode
     * @param stockCodeType(A1:產業類型;A2:投資地區;A3:投資公司)
     * @return
     */
    public StockCodeInfo getStockCode(String stockCode, String stockCodeType) {
        return getStockCode(stockCode, stockCodeType, DEFAULT_LOCALE);
    }

    public StockCodeInfo getStockCode(String stockCode, String stockCodeType, Locale locale) {
        if (locale == null) {
            return getStockCode(stockCode, stockCodeType);
        }
        return getStockCode(stockCode, stockCodeType, locale.toString());
    }

    public StockCodeInfo getStockCode(String stockCode, String stockCodeType, String locale) {
        return getDataByLocale(locale).get(getMapKey(stockCode, stockCodeType));
    }

    private Map<String, StockCodeInfo> getDataByLocale(String locale) {
        Map<String, StockCodeInfo> map = dataMap.get(locale);
        return MapUtils.isEmpty(map) ? Collections.emptyMap() : map;
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.STOCK_CODE_INFO_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        List<StockCodeInfo> dataList = stockCodeInfoResource.getAllStockCodeInfoData();
        Map<String, Map<String, StockCodeInfo>> dataMap = new LinkedHashMap<>();
        dataList.forEach(stock -> {
            String locale = stock.getLocale();
            String stockCode = stock.getStockCode();
            String stockCodeType = stock.getStockCodeType();
            if (dataMap.get(locale) == null) {
                dataMap.put(locale, new HashMap<String, StockCodeInfo>());
            }
            dataMap.get(locale).put(getMapKey(stockCode, stockCodeType), stock);
        });
        this.dataMap = dataMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.dataMap.isEmpty();
    }

    /**
     * 取得第二層Map的Key(STOCK_CODE + STOCK_CODE_TYPE)
     * 
     * @param stockCode
     * @param stockCodeType
     * @return
     */
    private String getMapKey(String stockCode, String stockCodeType) {
        return new StringBuilder(0).append(stockCode).append("_").append(stockCodeType).toString();
    }

    /**
     * 取得海外ETF/海外股票代碼指定類型資訊
     * 
     * @param stockCodeType
     * @param locale
     * @return
     */
    public List<StockCodeInfo> getAllStockType(String stockCodeType, String locale) {
        Map<String, StockCodeInfo> map = this.getDataByLocale(locale);
        List<StockCodeInfo> stockCodeInfos = map.values().stream().toList();
        return stockCodeInfos.stream().filter(stockCodeInfo -> StringUtils.equals(stockCodeType, stockCodeInfo.getStockCodeType())).toList();
    }

}
