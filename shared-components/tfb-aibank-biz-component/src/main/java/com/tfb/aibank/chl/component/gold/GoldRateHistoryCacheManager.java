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
package com.tfb.aibank.chl.component.gold;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)GoldRateHistoryCacheManager.java
 * 
 * <p>Description:黃金存摺歷史牌價，一年半內的資料 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/29, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class GoldRateHistoryCacheManager extends AbstractCacheManager {

    @Autowired
    private GoldResource resource;

    private List<GoldRateHistory> dataList = new ArrayList<>();

    /**
     * 讀取，指定日期區間的黃金存摺歷史牌價
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    public List<GoldRateHistory> getGoldRateHistory(Date startDate, Date endDate) {
        if (CollectionUtils.isNotEmpty(this.dataList)) {
            List<GoldRateHistory> list = new ArrayList<>();
            for (GoldRateHistory data : this.dataList) {
                Date effectDate = data.getEffectDate();
                if (DateUtils.between(effectDate, startDate, endDate)) {
                    list.add(data);
                }
            }
            return list;
        }
        return Collections.emptyList();
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.GOLD_RATE_HISTORY_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        this.dataList = resource.getAllGoldRateHistory();
    }

    @Override
    protected boolean isFirstLoad() {
        return this.dataList.isEmpty();
    }

}
