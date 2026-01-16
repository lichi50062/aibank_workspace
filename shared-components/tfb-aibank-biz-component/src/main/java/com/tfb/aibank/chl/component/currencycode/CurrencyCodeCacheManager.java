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
package com.tfb.aibank.chl.component.currencycode;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)CurrencyCodeCacheManager.java
 * 
 * <p>Description:幣別代碼檔 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class CurrencyCodeCacheManager extends AbstractCacheManager {

    @Autowired
    private CurrencyCodeResource currencyCodeResource;

    private static final String DEFAULT_LOCALE = Locale.TAIWAN.toString();

    /**
     * Map<LOCALE, Map<CURRENCY_ENAME1, CurrencyCode>>
     */
    private Map<String, Map<String, CurrencyCode>> currencyMap = new LinkedHashMap<>();

    public List<CurrencyCode> getCurrencyCodes() {
        return getCurrencyCodes(DEFAULT_LOCALE);
    }

    public List<CurrencyCode> getCurrencyCodes(Locale locale) {
        if (locale == null) {
            return getCurrencyCodes();
        }
        return getCurrencyCodes(locale.toString());
    }

    public List<CurrencyCode> getCurrencyCodes(String locale) {
        return getDataByLocale(locale).values().stream().collect(Collectors.toList());
    }

    public String getCurrencyName(String code, Locale locale) {
        if (locale == null) {
            locale = Locale.TAIWAN;
        }
        return getCurrencyName(code, locale.toString());
    }

    public String getCurrencyName(String code, String locale) {
        CurrencyCode currency = getDataByLocale(locale).get(code);
        return currency == null ? StringUtils.EMPTY : currency.getCurrencyName();
    }

    public CurrencyCode getCurrencyCode(String code, Locale locale) {
        if (locale == null) {
            locale = Locale.TAIWAN;
        }
        return getCurrencyCode(code, locale.toString());
    }

    public CurrencyCode getCurrencyCode(String code, String locale) {
        return Optional.ofNullable(getDataByLocale(locale).get(code)).orElse(new CurrencyCode());
    }

    private Map<String, CurrencyCode> getDataByLocale(String locale) {
        Map<String, CurrencyCode> map = currencyMap.get(locale);
        return MapUtils.isEmpty(map) ? Collections.emptyMap() : map;
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.CURRENCY_CODE_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<CurrencyCode> currencyList = currencyCodeResource.getAllCurrencyType();
        Map<String, Map<String, CurrencyCode>> currencyMap = new LinkedHashMap<>();
        currencyList.forEach(currency -> {
            String locale = currency.getLocale();
            String code = currency.getCurrencyEname1();
            if (currencyMap.get(locale) == null) {
                currencyMap.put(locale, new HashMap<String, CurrencyCode>());
            }
            currencyMap.get(locale).put(code, currency);
        });
        this.currencyMap = currencyMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.currencyMap.isEmpty();
    }

}
