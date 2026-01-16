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
package com.tfb.aibank.chl.component.taxitemtype;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)TaxItemTypeCacheManager.java
 * 
 * <p>Description:繳稅繳款類別 CacheManager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/11/20, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class TaxItemTypeCacheManager extends AbstractCacheManager {

    /** 預設語系：繁中 */
    private static final String DEFAULT_LOCALE = Locale.TAIWAN.toString();

    @Autowired
    private TaxItemTypeResource resource;

    /**
     * Map<itemCode + typeCode + locale, TaxItemsType>
     */
    private Map<String, TaxItemsType> dataMap = new HashMap<>();

    /**
     * 取 繳款類別
     * 
     * @param itemCode
     * @param typeCode
     * @return
     */
    public TaxItemsType getTaxItemsType(String itemCode, String typeCode) {
        return dataMap.get(getMapKey(itemCode, typeCode, DEFAULT_LOCALE));
    }

    /**
     * 取 繳款類別
     * 
     * @param itemCode
     * @param typeCode
     * @param userlocale
     * @return
     */
    public TaxItemsType getTaxItemsType(String itemCode, String typeCode, Locale userlocale) {
        return getTaxItemsType(itemCode, typeCode, userlocale.toString());
    }

    /**
     * 取 繳款類別
     * 
     * @param itemCode
     * @param typeCode
     * @param locale
     * @return
     */
    public TaxItemsType getTaxItemsType(String itemCode, String typeCode, String locale) {
        return dataMap.get(getMapKey(itemCode, typeCode, locale));
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.TAX_ITEM_TYPE_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        List<TaxItemsType> items = resource.getAllTaxItemType();
        Map<String, TaxItemsType> tmpMap = new HashMap<String, TaxItemsType>();
        if (items != null && items.size() > 0) {
            for (TaxItemsType item : items) {
                tmpMap.put(getMapKey(item.getItemCode(), item.getTypeCode(), item.getLocale()), item);
            }
        }
        this.dataMap = tmpMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.dataMap.isEmpty();
    }

    private String getMapKey(String itemCode, String typeCode, String locale) {
        String userLocale = StringUtils.isBlank(locale) ? DEFAULT_LOCALE : locale;
        return new StringBuilder(0).append(itemCode).append("_").append(typeCode).append("_").append(userLocale).toString();
    }

}