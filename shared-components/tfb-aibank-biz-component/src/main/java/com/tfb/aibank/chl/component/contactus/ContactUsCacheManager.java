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
package com.tfb.aibank.chl.component.contactus;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)ContactUsCacheManager.java
 * 
 * <p>Description:聯繫我們 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/22, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class ContactUsCacheManager extends AbstractCacheManager {

    @Autowired
    private ContactUsResource resource;

    private Map<Integer, Map<String, List<ContactUs>>> contactUsMap = null;

    public List<ContactUs> getContactUs(Locale locale, boolean isIos) {
        return getContactUs(locale.toString(), isIos);
    }

    public List<ContactUs> getContactUs(String locale, boolean isIos) {
        return getDataByLocale(locale, isIos);
    }

    private List<ContactUs> getDataByLocale(String locale, boolean isIos) {
        Map<String, List<ContactUs>> localeMap = contactUsMap.get(isIos ? 1 : 2);
        if (localeMap == null)
            return null;
        List<ContactUs> contactUs = localeMap.get(locale);
        return contactUs;
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.CONTAC_US_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<ContactUs> contactUs = resource.getContactUs();
        Map<Integer, List<ContactUs>> groupedByDeviceOs = contactUs.stream().collect(Collectors.groupingBy(ContactUs::getDeviceOs));
        // @formatter:off
        Map<Integer, Map<String, List<ContactUs>>> contactUsMap = groupedByDeviceOs.entrySet().stream()
                .collect(
                        Collectors.toMap(Map.Entry::getKey, 
                                entry -> entry.getValue().stream().collect(
                                        Collectors.groupingBy(ContactUs::getLocale)
                                        )
                                )
                        );
        // @formatter:on
        this.contactUsMap = contactUsMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.contactUsMap == null;
    }

}
