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
package com.tfb.aibank.chl.component.mfdallocatetype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)MfdCompanyCacheManager.java
 * 
 * <p>Description:基金配息方式 CacheManger</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/10, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class MfdAllocateTypeCacheManager extends AbstractCacheManager {

    @Autowired
    private MfdAllocateTypeResource resource;

    /** key: locale, key: allocateType */
    private Map<String, Map<String, MfdAllocateType>> dataMap = new HashMap<>();

    /**
     * 配息類別名稱
     * 
     * @param allocateType
     * @param locale
     * @return
     */
    public String getAllocateTypeName(String allocateType, String locale) {
        MfdAllocateType mfdAllocateType = getMfdAllocateType(allocateType, locale);
        return mfdAllocateType != null ? mfdAllocateType.getAllocateTypeName() : StringUtils.EMPTY;
    }

    /**
     * 讀取基金配息方式
     * 
     * @param companyCode
     * @param locale
     * @return
     */
    public MfdAllocateType getMfdAllocateType(String allocateType, String locale) {
        return getDataByLocale(locale).get(allocateType);
    }

    private Map<String, MfdAllocateType> getDataByLocale(String locale) {
        Map<String, MfdAllocateType> data = dataMap.get(locale);
        return data == null ? Collections.emptyMap() : data;
    }

    /**
     * 讀取全部基金配息方式
     * 
     * @return
     */
    public List<MfdAllocateType> getMfdAllocateTypeList(String locale) {
        Map<String, MfdAllocateType> map = getDataByLocale(locale);
        return new ArrayList<>(map.values());
    }

    /**
     * 讀取全部基金配息方式
     * 
     * @param locale
     * @return
     */
    public List<MfdAllocateType> getMfdAllocateTypeList(Locale locale) {
        return getMfdAllocateTypeList(locale.toString());
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.MFD_ALLOCATE_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<MfdAllocateType> mfdAllocateTypeList = resource.getAllMfdAllocateType();
        if (CollectionUtils.isNotEmpty(mfdAllocateTypeList)) {
            Map<String, Map<String, MfdAllocateType>> dataMap = new HashMap<>();
            for (MfdAllocateType data : mfdAllocateTypeList) {
                String locale = data.getLocale();
                String allocateType = data.getAllocateType();
                if (dataMap.get(locale) == null) {
                    dataMap.put(locale, new HashMap<>());
                }
                dataMap.get(locale).put(allocateType, data);
            }
            this.dataMap = dataMap;
        }
    }

    @Override
    protected boolean isFirstLoad() {
        return dataMap.isEmpty();
    }

}
