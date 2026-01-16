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
package com.tfb.aibank.chl.component.fxinterestrate;

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
 * @(#)FxInterestRateCacheManager.java
 * 
 * <p>Description:外幣存款利率檔 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class FxInterestRateCacheManager extends AbstractCacheManager {

    @Autowired
    private FxInterestRateResource resource;

    /**
     * KEY => TYPE_NO
     */
    private Map<String, List<FxInterestRate>> fxInterestRateMap = new LinkedHashMap<>();

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

    /**
     * 取得指定 TYPE_NO 的資料
     * 
     * @param typeNo
     * @return
     */
    public List<FxInterestRate> getFxInterestRatesByTypeNo(String typeNo) {
        List<FxInterestRate> list = this.fxInterestRateMap.get(typeNo);
        return CollectionUtils.isEmpty(list) ? Collections.emptyList() : list;
    }

    /**
     * 取得所有 FxInterestRates
     * 
     * @return
     */
    public List<FxInterestRate> getAllFxInterestRates() {
        return this.fxInterestRateMap.values().stream().flatMap(Collection::stream).toList();
    }

    /**
     * 取得指定幣別的資料
     * 
     * @param currencyEname
     * @return
     */
    public List<FxInterestRate> getFxInterestRatesByCurrencyEname(String currencyEname) {
        List<FxInterestRate> allDataList = getAllFxInterestRates();
        return allDataList.stream().filter(fx -> StringUtils.equals(fx.getCurrencyEname(), currencyEname)).collect(Collectors.toList());
    }

    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.FX_INTEREST_RATE_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        List<FxInterestRate> fxIntRates = resource.getAllFxInterestRateData();
        if (CollectionUtils.isEmpty(fxIntRates)) {
            return;
        }
        this.fxInterestRateMap = fxIntRates.stream().collect(Collectors.groupingBy(FxInterestRate::getTypeNo));
    }

    @Override
    protected boolean isFirstLoad() {
        return fxInterestRateMap.isEmpty();
    }

}
