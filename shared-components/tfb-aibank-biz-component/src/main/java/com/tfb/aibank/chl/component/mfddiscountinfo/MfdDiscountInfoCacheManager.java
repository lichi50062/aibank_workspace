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
package com.tfb.aibank.chl.component.mfddiscountinfo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)MfdDiscountInfoCacheManager.java
 * 
 * <p>Description:基金優惠 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/25, PCY
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class MfdDiscountInfoCacheManager extends AbstractCacheManager {

    @Autowired
    private MfdDiscountInfoResource mfdDiscountInfoResource;

    private Map<String, Map<String, MfdDiscountInfo>> mfdDiscountInfoMap = null;

    /**
     * 預設以「zh_TW」<br>
     * 依DiscountCode取「MfdDiscountInfo」
     */
    public MfdDiscountInfo getMfdDiscountInfoByDiscountCode(String discountCode) {
        return getMfdDiscountInfoByDiscountCodeAndLocale(discountCode, Locale.TRADITIONAL_CHINESE.toString());
    }

    public MfdDiscountInfo getMfdDiscountInfoByDiscountCodeAndLocale(String discountCode, String locale) {
        Map<String, MfdDiscountInfo> mapByLocal = mfdDiscountInfoMap.getOrDefault(locale, Collections.emptyMap());
        return mapByLocal.get(discountCode);
    }

    /**
     * 回傳 global cache key
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.MFD_DISCOUNT_IFNO_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<MfdDiscountInfo> mfdDiscountInfos = mfdDiscountInfoResource.getAllMfdDiscountInfos();
        if (CollectionUtils.isNotEmpty(mfdDiscountInfos)) {
            Map<String, List<MfdDiscountInfo>> mapByLocal = mfdDiscountInfos.stream().collect(Collectors.groupingBy(MfdDiscountInfo::getLocale));

            Map<String, Map<String, MfdDiscountInfo>> mfdDiscountInfoMap = new HashMap<>();
            mapByLocal.forEach((key, list) -> {
                Map<String, MfdDiscountInfo> mapByCode = list.stream().collect(Collectors.toMap(MfdDiscountInfo::getDiscountCode, Function.identity(), (oldV, newV) -> oldV));
                mfdDiscountInfoMap.put(key, mapByCode);
            });
            this.mfdDiscountInfoMap = mfdDiscountInfoMap;
        }
        else {
            this.mfdDiscountInfoMap = Collections.emptyMap();
        }
    }

    @Override
    protected boolean isFirstLoad() {
        return this.mfdDiscountInfoMap == null;
    }

}
