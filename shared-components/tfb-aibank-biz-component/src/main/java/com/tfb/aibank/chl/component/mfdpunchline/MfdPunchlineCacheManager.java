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
package com.tfb.aibank.chl.component.mfdpunchline;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.LocaleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)MfdPunchlineCacheManager.java
 * 
 * <p>Description:基金警語 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Marty
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class MfdPunchlineCacheManager extends AbstractCacheManager {

    @Autowired
    private MfdPunchlineResource mfdPunchlineResource;

    private Map<String, Map<String, MfdPunchline>> punchlineMapByLocale = new HashMap<>();

    public String getPunchlineByFundCode(String fundCode) {
        return getPunchlineByFundCodeAndLocaleAndPunchlineType(fundCode, Locale.TRADITIONAL_CHINESE, "1");
    }

    public String getPunchlineByFundCode(String fundCode, Locale locale) {
        return getPunchlineByFundCodeAndLocaleAndPunchlineType(fundCode, locale, "1");
    }

    public String getPunchlineByFundCode(String fundCode, String localeParam) {
        Locale locale = LocaleUtils.toLocale(localeParam);
        return getPunchlineByFundCodeAndLocaleAndPunchlineType(fundCode, locale, "1");
    }

    public String getPunchlineByFundCodeAndLocaleAndPunchlineType(String fundCode, Locale locale, String punchlineType) {
        if (null != punchlineMapByLocale.get(locale.toString())) {

            String desc = StringUtils.EMPTY;

            Map<String, MfdPunchline> punchlineMap = punchlineMapByLocale.get(locale.toString());

            MfdPunchline punchline = punchlineMap.getOrDefault(fundCode, null);

            if (null != punchline && null != punchline.getPunchlineTypeMap()) {
                if (StringUtils.isNotBlank(punchlineType)) {
                    desc = punchline.getPunchlineTypeMap().getOrDefault(punchlineType, StringUtils.EMPTY);
                }
            }
            return desc;
        }
        else {
            return StringUtils.EMPTY;
        }
    }

    /**
     * 回傳 global cache key
     *
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.MFD_PUNCHLINE_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<MfdPunchline> punchlinesAll = mfdPunchlineResource.queryForAllMfdPunchline();
        if (CollectionUtils.isNotEmpty(punchlinesAll)) {
            Map<String, Map<String, MfdPunchline>> punchlineMapByLocale = new HashMap<>();
            Map<String, List<MfdPunchline>> mapByLocale = punchlinesAll.stream().collect(Collectors.groupingBy(MfdPunchline::getLocale));
            for (Map.Entry<String, List<MfdPunchline>> entry : mapByLocale.entrySet()) {
                String locale = entry.getKey();
                List<MfdPunchline> punchlines = entry.getValue();
                // 同一Locale中，依fundcode產生的map
                Map<String, MfdPunchline> fCodeMap = punchlines.stream().collect(Collectors.toMap(MfdPunchline::getFundCode, Function.identity(), (a, b) -> a));
                punchlineMapByLocale.put(locale, fCodeMap);
            }
            this.punchlineMapByLocale = punchlineMapByLocale;
        }
    }

    @Override
    protected boolean isFirstLoad() {
        return this.punchlineMapByLocale.isEmpty();
    }

}
