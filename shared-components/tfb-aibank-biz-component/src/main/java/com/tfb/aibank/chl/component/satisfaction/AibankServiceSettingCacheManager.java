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
package com.tfb.aibank.chl.component.satisfaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)AibankServiceSettingCacheManager.java
 * 
 * <p>Description:AI Bank滿意度問卷設定資料表 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/20, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class AibankServiceSettingCacheManager extends AbstractCacheManager {

    @Autowired
    private SatisfactionResource resource;

    /**
     * Map<LOCALE, List<AibankServiceSetting>
     */
    private Map<String, List<AibankServiceSetting>> dataMap = null;

    /**
     * 依據身分證號末碼，讀取開啟滿意度問卷的頁面清單
     * 
     * @param custIxd
     * @param userLocale
     * @return
     */
    public Map<String, String> getDataMap(String custIxd, Locale userLocale) {
        List<AibankServiceSetting> list = getDataByLocale(userLocale.toString());
        Map<String, String> resultMap = new HashMap<>();
        Date datetime = new Date();
        try {
            for (AibankServiceSetting x : list) {
                boolean result = false;

                if (x.getStartDate() == null || x.getEndDate() == null) {
                    result = true;
                }
                else {
                    result = x.getStartDate().compareTo(datetime) <= 0 && x.getEndDate().compareTo(datetime) >= 0; // 滿足問卷起迄日期
                }

                result = result && ConvertUtils.integer2Int(x.getShowFlag()) == 1; // 問卷開關
                result = result && StringUtils.contains(x.getUserParam(), StringUtils.right(custIxd, 1)); // ID 末碼

                if (result) {
                    resultMap.put(x.getPageId(), x.getPageName());
                }
            }
            return resultMap;
        }
        catch (Exception ex) {
            try {
                String entInfos = list.stream().map(JsonUtils::getJson).collect(Collectors.joining(", "));
                logger.warn("AibankServiceSetting get cache" + entInfos);
            }
            catch (Exception exlsit) {
                logger.warn(exlsit.getMessage(), exlsit);
            }
            logger.warn(ex.getMessage(), ex);
            return new HashMap<>();
        }

    }

    private List<AibankServiceSetting> getDataByLocale(String locale) {
        List<AibankServiceSetting> list = dataMap.get(locale);
        return CollectionUtils.isNotEmpty(list) ? list : Collections.emptyList();
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.AIBANK_SERVICE_SETTING_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<AibankServiceSetting> dataList = resource.findAllAibankServiceSetting();
        if (CollectionUtils.isNotEmpty(dataList)) {
            Map<String, List<AibankServiceSetting>> dataMap = new LinkedHashMap<>();
            dataList.forEach(data -> {
                String locale = data.getLocale();
                if (dataMap.get(locale) == null) {
                    dataMap.put(locale, new ArrayList<>());
                }
                dataMap.get(locale).add(data);
            });
            logger.info("AibankServiceSetting loadCache has data");
            this.dataMap = dataMap;
        }
        else {
            logger.info("AibankServiceSetting loadCache no data");
            this.dataMap = Collections.emptyMap();
        }
    }

    @Override
    protected boolean isFirstLoad() {
        return this.dataMap == null;
    }
}
