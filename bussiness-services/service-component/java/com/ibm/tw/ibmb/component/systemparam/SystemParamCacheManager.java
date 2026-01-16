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
package com.ibm.tw.ibmb.component.systemparam;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)SystemParamCacheManager.java
 * 
 * <p>Description:系統參數檔 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class SystemParamCacheManager extends AbstractCacheManager {

    @Autowired
    private SystemParamResource systemConfigResource;

    /**
     * Map<category, Map<paramKey, SystemParam>>
     */
    private Map<String, Map<String, SystemParam>> paramMap = new HashMap<>();

    /**
     * 依指定 CATEGORY，取出所有符合的資料
     * 
     * @param category
     * @return
     */
    public Map<String, SystemParam> getSystemParamByCategory(String category) {
        Map<String, SystemParam> valueMap = paramMap.get(category);
        return valueMap == null ? Collections.emptyMap() : valueMap;
    }

    /**
     * 依指定 CATEGORY，取出所有符合的資料
     * 
     * @param category
     * @return
     */
    public SystemParamMap getParamMapByCategory(String category) {
        Map<String, SystemParam> valueMap = getSystemParamByCategory(category);
        SystemParamMap map = new SystemParamMap();
        valueMap.values().forEach(v -> {
            map.add(v);
        });
        return map;
    }

    /**
     * 依指定 CATEGORY、PARAM_KEY，取出符合的紀錄
     * 
     * @param category
     * @param key
     * @return
     */
    public SystemParam getSystemParamByCategoryAndKey(String category, String key) {
        Map<String, SystemParam> valueMap = getSystemParamByCategory(category);
        if (valueMap == null) {
            return null;
        }
        return valueMap.get(key);
    }

    /**
     * 依 CATEGORY、PARAM_KEY 從 SYSTEM_PARAM 取值
     * 
     * @param category
     * @param key
     * @return
     */
    public String getValue(String category, String key) {
        return getValue(category, key, StringUtils.EMPTY);
    }

    /**
     * 依 ISystemParam 取值
     * 
     * @param param
     * @return
     */
    public String getValue(ISystemParam param) {
        return getValue(param.getCategory().getSystemId(), param.getKey());
    }

    /**
     * 依 CATEGORY、PARAM_KEY 從 SYSTEM_PARAM 取值，若無資料回傳預設值
     * 
     * @param category
     * @param key
     * @param defaultValue
     *            預設值
     * @return
     */
    public String getValue(String category, String key, String defaultValue) {

        SystemParam model = getSystemParamByCategoryAndKey(category, key);
        return (model == null || StringUtils.isBlank(model.getParamValue())) ? defaultValue : model.getParamValue();
    }

    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.SYSTEM_PARAM_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        BaseServiceResponse<List<SystemParam>> x = systemConfigResource.getAllSystemParam();
        List<SystemParam> dataList = x.getData();
        Map<String, Map<String, SystemParam>> paramMap = new HashMap<>();
        dataList.forEach(data -> {
            Map<String, SystemParam> categoryMap = paramMap.get(data.getCategory());
            if (categoryMap == null) {
                categoryMap = new HashMap<>();
                paramMap.put(data.getCategory(), categoryMap);
            }
            categoryMap.put(data.getParamKey(), data);
        });
        this.paramMap = paramMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return paramMap.isEmpty();
    }

}
