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
package com.tfb.aibank.chl.component.twdinterestrate;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
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
 * @(#)TwdInterestRateCacheManager.java
 * 
 * <p>Description:台幣存款利率檔 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class TwdInterestRateCacheManager extends AbstractCacheManager {

    @Autowired
    private TwdInterestRateResource resource;

    /**
     * KEY => Rate_Type
     */
    private Map<String, List<TwdInterestRate>> twdInterestRateMapByRateType = new LinkedHashMap<>();

    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.TWD_INTEREST_RATE_CACHE_KEY;
    }

    /**
     * 取得預設 cache Timeout 時間
     * 
     * #2503 將 Table的Cache 時間改為 1 小時
     * 
     * @return
     */
    @Override
    protected Duration cacheTimeout() {
        return Duration.ofHours(1);
    }

    @Override
    protected void loadCache() {
        List<TwdInterestRate> twdIntRates = resource.getAllTwdInterestRateData();
        if (CollectionUtils.isEmpty(twdIntRates)) {
            return;
        }
        Map<String, List<TwdInterestRate>> twdInterestRateMapByRateType = twdIntRates.stream().collect(Collectors.groupingBy(TwdInterestRate::getRateType));
        this.twdInterestRateMapByRateType = twdInterestRateMapByRateType;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.twdInterestRateMapByRateType.isEmpty();
    }

    public List<TwdInterestRate> getTwdInterestRatesByRateType(String rateType) {
        if (StringUtils.isEmpty(rateType)) {
            return Collections.emptyList();
        }
        return this.twdInterestRateMapByRateType.get(rateType);
    }

    public List<TwdInterestRate> getAllTwdInterestRates() {
        return this.twdInterestRateMapByRateType.values().stream().flatMap(Collection::stream).toList();
    }

}
