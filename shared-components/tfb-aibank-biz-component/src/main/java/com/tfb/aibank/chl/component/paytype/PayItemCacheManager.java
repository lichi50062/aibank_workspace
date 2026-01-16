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
package com.tfb.aibank.chl.component.paytype;

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
 * @(#)PayItemCacheManager.java
 * 
 * <p>Description:繳款項目 PAY_ITEM CacheManager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/11/20, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class PayItemCacheManager extends AbstractCacheManager {

    /** 預設語系：繁中 */
    private static final String DEFAULT_LOCALE = Locale.TAIWAN.toString();

    @Autowired
    private PayItemCacheResource resource;

    /**
     * Map<itemCode + locale, PayItems>
     */
    private Map<String, PayItems> dataMap = new HashMap<>();

    /**
     * 取繳款項目
     * 
     * @param itemCode
     * @return
     */
    public PayItems getPayItems(String itemCode) {
        return dataMap.get(getMapKey(itemCode, DEFAULT_LOCALE));
    }

    /**
     * 取繳款項目
     * 
     * @param itemCode
     * @param userlocale
     * @return
     */
    public PayItems getPayItems(String itemCode, Locale userlocale) {
        return getPayItems(itemCode, userlocale.toString());
    }

    /**
     * 取繳款項目
     * 
     * @param itemCode
     * @param locale
     * @return
     */
    public PayItems getPayItems(String itemCode, String locale) {
        return dataMap.get(getMapKey(itemCode, locale));
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.PAY_ITEMS_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        List<PayItems> items = resource.getPaymentItems();
        Map<String, PayItems> tmpMap = new HashMap<String, PayItems>();
        if (items != null && items.size() > 0) {
            for (PayItems item : items) {
                tmpMap.put(getMapKey(item.getItemCode(), item.getLocale()), item);
            }
        }
        this.dataMap = tmpMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.dataMap.isEmpty();
    }

    private String getMapKey(String type, String locale) {
        String userLocale = StringUtils.isBlank(locale) ? DEFAULT_LOCALE : locale;
        return new StringBuilder(0).append(type).append("_").append(userLocale).toString();
    }

}
