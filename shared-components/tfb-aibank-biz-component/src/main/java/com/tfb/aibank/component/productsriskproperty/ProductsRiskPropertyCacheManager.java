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
package com.tfb.aibank.component.productsriskproperty;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)ProductsRiskPropertyCacheManager.java
 * 
 * <p>Description:海外ETF/海外 商品風險屬性</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/19, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class ProductsRiskPropertyCacheManager extends AbstractCacheManager {

    /** 預設語系：繁中 */
    private static final String DEFAULT_LOCALE = Locale.TAIWAN.toString();

    @Autowired
    private ProductsRiskPropertyResource productsRiskPropertyResource;

    /**
     * Map<LOCALE, Map<INSURANCE_CODE, StockInfo>>
     */
    private Map<String, Map<String, ProductsRiskProperty>> dataMap = new LinkedHashMap<>();

    /**
     * 取得預設語系的海外ETF/海外商品風險資訊
     * 
     * @param locale
     * @param riskCode
     * @return
     */
    public ProductsRiskProperty getDataByRiskCode(String locale, String riskCode) {
        return getDataByLocale(locale).get(riskCode) != null ? getDataByLocale(locale).get(riskCode) : new ProductsRiskProperty();
    }

    /**
     * 取得預設語系的海外ETF/海外商品風險資訊List
     * 
     * @return
     */
    public List<ProductsRiskProperty> getAllProductsRiskPropertys() {
        return getAllProductsRiskPropertys(DEFAULT_LOCALE);
    }

    public List<ProductsRiskProperty> getAllProductsRiskPropertys(Locale locale) {
        if (locale == null) {
            return getAllProductsRiskPropertys();
        }
        return getAllProductsRiskPropertys(locale.toString());
    }

    public List<ProductsRiskProperty> getAllProductsRiskPropertys(String locale) {
        return getDataByLocale(locale).values().stream().toList();
    }

    public Map<String, ProductsRiskProperty> getDataByLocale(String locale) {
        Map<String, ProductsRiskProperty> map = dataMap.get(locale);
        return MapUtils.isEmpty(map) ? Collections.emptyMap() : map;
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.PRODUCTS_RISK_PROPERTY_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        List<ProductsRiskProperty> dataList = productsRiskPropertyResource.getAllProductsRiskProperties();
        Map<String, Map<String, ProductsRiskProperty>> dataMap = new LinkedHashMap<>();
        dataList.forEach(productsRiskProperty -> {
            String locale = productsRiskProperty.getLocale();
            String riskCode = productsRiskProperty.getRiskCode();
            if (dataMap.get(locale) == null) {
                dataMap.put(locale, new HashMap<>());
            }
            dataMap.get(locale).put(riskCode, productsRiskProperty);
        });
        this.dataMap = dataMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.dataMap.isEmpty();
    }

}
