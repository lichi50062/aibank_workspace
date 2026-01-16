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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;
import com.ibm.tw.ibmb.util.IBUtils;

// @formatter:off
/**
 * @(#)AibankServiceQuestionOptionCacheManager.java
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
public class AibankServiceQuestionOptionCacheManager extends AbstractCacheManager {

    @Autowired
    private SatisfactionResource resource;

    private static final String DEFAULT_LOCALE = Locale.TAIWAN.toString();

    /**
     * Map<LOCALE, Map<PAGE_ID + QUESTION_TYPE, AibankServiceQuestionOption>>
     */
    private Map<String, Map<String, List<AibankServiceQuestionOption>>> dataMap = null;

    public List<AibankServiceQuestionOption> getOptions(String pageId, Integer questionType) {
        return getOptions(pageId, questionType, DEFAULT_LOCALE);
    }

    public List<AibankServiceQuestionOption> getOptions(String pageId, Integer questionType, String locale) {
        List<AibankServiceQuestionOption> list = getDataByLocale(locale).get(getKey(pageId, questionType));
        IBUtils.sort(list, "optionSort", false); // 升冪排序
        return list;
    }

    private Map<String, List<AibankServiceQuestionOption>> getDataByLocale(String locale) {
        Map<String, List<AibankServiceQuestionOption>> map = dataMap.get(locale);
        return MapUtils.isEmpty(map) ? Collections.emptyMap() : map;
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.AIBANK_SERVICE_QUESTION_OPTION_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<AibankServiceQuestionOption> dataList = resource.findAllAibankServiceQuestionOption();
        if (CollectionUtils.isNotEmpty(dataList)) {
            Map<String, Map<String, List<AibankServiceQuestionOption>>> dataMap = new LinkedHashMap<>();
            dataList.forEach(data -> {
                String locale = data.getLocale();
                String subKey = getKey(data.getPageId(), data.getQuestionType());
                if (dataMap.get(locale) == null) {
                    dataMap.put(locale, new HashMap<String, List<AibankServiceQuestionOption>>());
                }
                if (dataMap.get(locale).get(subKey) == null) {
                    dataMap.get(locale).put(subKey, new ArrayList<>());
                }
                dataMap.get(locale).get(subKey).add(data);
            });
            this.dataMap = dataMap;
        }
        else {
            this.dataMap = Collections.emptyMap();
        }
    }

    private String getKey(String pageId, Integer questionType) {
        return new StringBuilder(0).append(pageId).append("_").append(questionType).toString();
    }

    @Override
    protected boolean isFirstLoad() {
        return this.dataMap == null;
    }

}
