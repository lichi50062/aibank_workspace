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
package com.tfb.aibank.chl.component.remititemlevelthree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)RemitItemLevelThreeLargeLargeCacheManager.java
 * 
 * <p>Description:大額匯款性質細項說明 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/11, Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class RemitItemLevelThreeLargeCacheManager extends AbstractCacheManager {

    private static final String DEFAULT_LOCALE = Locale.TAIWAN.toString();
    @Autowired
    private RemitItemLevelThreeResource remitItemLevelThreeResource;
    /**
     * Map<LOCALE, Map<BRANCH_ID, BranchData>>
     */
    // private Map<String, Map<String, RemitItemLevelThreeLarge>> dataMap = new LinkedHashMap<>();
    private Map<String, List<RemitItemLevelThreeLarge>> localeMap = new HashMap<>();

    private Map<String, List<RemitItemLevelThreeLarge>> localeMapIn = new HashMap<>();

    /**
     * 無參數，預設回傳zh_TW
     */
    public List<RemitItemLevelThreeLarge> getRemitItemLevelThreeLarges() {
        return getRemitItemLevelThreeLarges(DEFAULT_LOCALE);
    }

    public List<RemitItemLevelThreeLarge> getAllRemitItemLevelThreeLarges(Locale locale) {
        if (locale == null) {
            return getRemitItemLevelThreeLarges();
        }
        return getRemitItemLevelThreeLarges(locale.toString());
    }

    public List<RemitItemLevelThreeLarge> getAllRemitItemInLevelThrees(Locale locale) {
        if (locale == null) {
            return getRemitItemInLevel3ByLocale(DEFAULT_LOCALE);
        }
        return getRemitItemInLevel3ByLocale(locale.toString());
    }

    public List<RemitItemLevelThreeLarge> getRemitItemLevelThreeLarges(String locale) {
        return getDataByLocale(locale);
    }

    public List<RemitItemLevelThreeLarge> getRemitItemLevelThreeLargesByType(String remitType, String remitCode, String remitValue, String remitCodeO, String locale) {
        List<RemitItemLevelThreeLarge> remitItemLevelThreeLarge = getDataByLocale(locale).stream().filter(createPredicate(remitType, remitCode, remitValue, remitCodeO)).collect(Collectors.toList());

        return remitItemLevelThreeLarge;
    }

    /**
     * 取得匯款細項對應名稱(大額換匯) 參考現行
     *
     * @param remitCode
     * @param remitValue
     * @param remitCodeO
     * @param locale
     * @return
     */
    public String getRemitItemLevelThreeLargeNameByType(String remitType, String remitCode, String remitValue, String remitCodeO, String locale) {
        List<RemitItemLevelThreeLarge> remitItemLevelThreeLarges = getRemitItemLevelThreeLargesByType(remitType, remitCode, remitValue, remitCodeO, locale);
        if (CollectionUtils.isNotEmpty(remitItemLevelThreeLarges)) {
            RemitItemLevelThreeLarge remitItemLevelThreeLarge = remitItemLevelThreeLarges.get(0);
            return remitItemLevelThreeLarge == null ? remitCode : remitItemLevelThreeLarge.getRemitName();
        }
        else {
            return remitCode;
        }
    }

    public List<RemitItemLevelThreeLarge> getRemitItemLevelThreeLargesByTypeAndCode(String remitType, String remitCode, String locale) {
        List<RemitItemLevelThreeLarge> remitLv3s = new ArrayList<>();
        if (null != getDataByLocale(locale)) {
            List<RemitItemLevelThreeLarge> remitLv3sInLocale = getDataByLocale(locale);
            for (var three : remitLv3sInLocale) {
                if (StringUtils.equals(remitType, three.getRemitType()) && StringUtils.equals(remitCode, three.getRemitCode())) {
                    remitLv3s.add(three);
                }
            }
        }
        return remitLv3s;
    }

    private Predicate<RemitItemLevelThreeLarge> createPredicate(String remitType, String remitCode, String remitValue, String remitCodeO) {
        return three -> {
            boolean match = true;

            if (StringUtils.isNotBlank(remitType)) {
                match = match && remitType.equals(three.getRemitType());
            }

            if (StringUtils.isNotBlank(remitCode)) {
                match = match && remitCode.equals(three.getRemitCode());
            }
            else {
                match = match && StringUtils.isBlank(three.getRemitCode());
            }

            if (StringUtils.isNotBlank(remitValue)) {
                match = match && remitValue.equals(three.getRemitValue());
            }
            else {
                match = match && StringUtils.isBlank(three.getRemitValue());
            }

            if (StringUtils.isNotBlank(remitCodeO)) {
                match = match && remitCodeO.equals(three.getRemitCodeO());
            }
            else {
                match = match && StringUtils.isBlank(three.getRemitCodeO());
            }

            return match;
        };
    }

    private List<RemitItemLevelThreeLarge> getDataByLocale(String locale) {
        return MapUtils.isEmpty(localeMap) ? Collections.emptyList() : localeMap.get(locale);
    }

    private List<RemitItemLevelThreeLarge> getRemitItemInLevel3ByLocale(String locale) {
        return MapUtils.isEmpty(localeMapIn) ? Collections.emptyList() : localeMapIn.get(locale);
    }

    /**
     * 回傳 global cache key
     *
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.REMIT_ITEM_LEVEL_THREE_LARGE_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<RemitItemLevelThreeLarge> dataList = remitItemLevelThreeResource.getAllRemitItemLevelThreeLarge();

        if (CollectionUtils.isNotEmpty(dataList)) {
            localeMap.clear();
            localeMap = dataList.stream().collect(Collectors.groupingBy(RemitItemLevelThreeLarge::getLocale));
        }

    }

    @Override
    protected boolean isFirstLoad() {
        return localeMap.isEmpty();
    }

}
