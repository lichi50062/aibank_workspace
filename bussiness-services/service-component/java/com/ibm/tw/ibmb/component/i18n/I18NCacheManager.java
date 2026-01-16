package com.ibm.tw.ibmb.component.i18n;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

//@formatter:off
/**
 * @(#)I18NCacheManager.java
 * 
 * <p>Description:AI Bank i18n 設定檔 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/15, alex 
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@Component
public class I18NCacheManager extends AbstractCacheManager {

    @Autowired
    private I18nResource i18nResource;

    /**
     * Map<locale,Map<category Map<key, I18nModel>>>
     */
    private Map<String, Map<String, Map<String, I18nModel>>> i18nMap = new HashMap<>();

    /**
     * 依指定 locale CATEGORY，取出所有符合的資料
     * 
     * @param userLocale
     * @param category
     * @return
     */
    public Map<String, I18nModel> getI18nByCategory(Locale userLocale, String category) {
        if (userLocale == null) {
            userLocale = Locale.TAIWAN;
        }
        Map<String, I18nModel> valueMap = i18nMap.get(userLocale.toString()).get(category);
        return valueMap == null ? Collections.emptyMap() : valueMap;
    }

    /**
     * 依指定 locale CATEGORY，取出所有符合的資料
     * 
     * @param category
     * @return
     */
    public Map<String, I18nModel> getI18nByCategory(String locale, String category) {
        Map<String, I18nModel> valueMap = i18nMap.get(locale).get(category);
        return valueMap == null ? Collections.emptyMap() : valueMap;
    }

    /**
     * 依指定 locale CATEGORY key，取出符合的紀錄
     * 
     * @param category
     * @param key
     * @return
     */
    public I18nModel getSingleResult(String locale, String category, String key) {
        Map<String, I18nModel> valueMap = getI18nByCategory(locale, category);
        if (valueMap == null) {
            return null;
        }
        return valueMap.get(key);
    }

    public I18nModel getSingleResult(Locale locale, String category, String key) {
        return getSingleResult(locale.toString(), category, key);
    }

    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.AI_I18N_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        List<I18nModel> list = i18nResource.getAllI18n();
        Map<String, Map<String, Map<String, I18nModel>>> tempMap = new HashMap<>();
        list.stream().forEach(i18nModel -> {
            String locale = i18nModel.getLocale();
            String category = i18nModel.getCategory();
            String key = i18nModel.getKey();
            tempMap.computeIfAbsent(locale, k -> new HashMap<>());
            tempMap.get(locale).computeIfAbsent(category, k -> new HashMap<>());
            tempMap.get(locale).get(category).put(key, i18nModel);
        });
        this.i18nMap = tempMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.i18nMap.isEmpty();
    }

}