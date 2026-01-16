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
package com.tfb.aibank.chl.component.newfunctionintro;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)NewFunctionIntroCacheManager.java
 * 
 * <p>Description:新功能介紹 NewFunctionIntro Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/25
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NewFunctionIntroCacheManager extends AbstractCacheManager {

    @Autowired
    private NewFunctionIntroResource resource;

    /** 預設語系：繁中 */
    private static final String DEFAULT_LOCALE = Locale.TAIWAN.toString();

    private Map<String, List<NewFunctionIntro>> newFunctionIntroMap = null;

    /**
     * 取得所有 NewFunctionIntro
     *
     * @return
     */
    public List<NewFunctionIntro> getNewFunctionIntrosByLocale(String locale) {
        String introLocale = StringUtils.defaultString(locale, DEFAULT_LOCALE);
        return this.newFunctionIntroMap.getOrDefault(introLocale, Collections.emptyList());
    }

    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.NEW_FUNC_INTROS_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        List<NewFunctionIntro> newFunctionIntros = resource.queryNewFunctions();
        if (CollectionUtils.isEmpty(newFunctionIntros)) {
            this.newFunctionIntroMap = Collections.emptyMap();
        }
        else {
            this.newFunctionIntroMap = newFunctionIntros.stream().collect(Collectors.groupingBy(NewFunctionIntro::getLocale));
        }
    }

    @Override
    protected boolean isFirstLoad() {
        return this.newFunctionIntroMap == null;
    }

}
