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
package com.tfb.aibank.component.ipofund;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;
import com.tfb.aibank.chl.component.cardpaytype.CardPaytype;

// @formatter:off
/**
 * @(#)FundIpoCacheManager.java
 * 
 * <p>Description:募集中基金資訊 cache 物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/10, Rong Gang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class FundIpoCacheManager extends AbstractCacheManager {

    @Autowired
    private FundIpoResource resource;

    private Map<String, FundIpo> dataMap = new HashMap<>();

    public List<FundIpo> getAllFundIpo() {
        return this.dataMap.values().stream().collect(Collectors.toList());
    }

    /**
     * 讀取基金資訊
     * 
     * @param fundCode
     *            基金代碼(四碼)
     * @return
     */
    public FundIpo getFundIpo(String fundCode) {
        return this.dataMap.get(fundCode);
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.IPO_FUND_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<FundIpo> infoList = resource.getAllFundIpo();
        Map<String, FundIpo> dataMap = infoList.stream().collect(Collectors.toMap(FundIpo::getPrdId, Function.identity()));
        this.dataMap = dataMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.dataMap.isEmpty();
    }

}
