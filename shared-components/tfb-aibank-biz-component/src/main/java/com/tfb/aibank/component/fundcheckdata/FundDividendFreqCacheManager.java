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
package com.tfb.aibank.component.fundcheckdata;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;
import com.tfb.aibank.common.type.FundDividendFreqType;

// @formatter:off
/**
 * @(#)FundDividendFreqCacheManager.java
 * 
 * <p>Description:基金配息頻率 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/29, Marty Pan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class FundDividendFreqCacheManager extends AbstractCacheManager {

    @Autowired
    private FundCheckDataResource resource;

    /**
     * 基金配息頻率(key)與代碼 ex:
     * 
     * <pre>
     *     [月配] => {"B": [ "1234", "5678"........]}
     * </pre>
     * 
     * @See FundDividendFreqType.java
     */
    private Map<String, List<String>> fundDividendFreqsMap = new LinkedHashMap<>();

    /**
     * 基金配息頻率與代碼Map
     */
    public Map<String, List<String>> getDividendFreqMap() {
        return this.fundDividendFreqsMap;
    }

    /**
     * 基金配息頻率與代碼Map
     * 
     * @return
     */
    public Map<FundDividendFreqType, List<String>> getFundDividendFreqMap() {
        Map<FundDividendFreqType, List<String>> map = new LinkedHashMap<>();
        if (MapUtils.isNotEmpty(this.fundDividendFreqsMap)) {
            for (Map.Entry<String, List<String>> entry : this.fundDividendFreqsMap.entrySet()) {
                FundDividendFreqType key = FundDividendFreqType.findByCode(entry.getKey());
                map.put(key, entry.getValue());
            }
        }
        return map;
    }

    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.FUND_DIVIDEND_FREQ_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        Map<String, List<String>> resultMap = resource.getFundDividendFreqsMap();
        if (MapUtils.isNotEmpty(resultMap)) {
            // 以 FundDividendFreqType.code，按順序排列
            List<String> list = resultMap.keySet().stream().sorted().collect(Collectors.toList());
            Map<String, List<String>> fundDividendFreqsMap = new LinkedHashMap<>();
            for (String key : list) {
                fundDividendFreqsMap.put(key, resultMap.get(key));
            }
            this.fundDividendFreqsMap = fundDividendFreqsMap;
        }
    }

    @Override
    protected boolean isFirstLoad() {
        return this.fundDividendFreqsMap.isEmpty();
    }

}
