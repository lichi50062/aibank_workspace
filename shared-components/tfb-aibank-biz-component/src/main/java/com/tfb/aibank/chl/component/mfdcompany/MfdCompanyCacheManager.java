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
package com.tfb.aibank.chl.component.mfdcompany;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)MfdCompanyCacheManager.java
 * 
 * <p>Description:基金公司 CacheManger</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/10, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class MfdCompanyCacheManager extends AbstractCacheManager {

    @Autowired
    private MfdCompanyResource resource;

    /** key: locale, key: companyCode */
    private Map<String, Map<String, MfdCompany>> dataMap = new HashMap<>();

    /**
     * 讀取基金公司
     * 
     * @param companyCode
     * @param locale
     * @return
     */
    public MfdCompany getMfdCompany(String companyCode, String locale) {
        return getDataByLocale(locale).get(companyCode);
    }

    private Map<String, MfdCompany> getDataByLocale(String locale) {
        Map<String, MfdCompany> data = dataMap.get(locale);
        return data == null ? Collections.emptyMap() : data;
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.MFD_COMPANY_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<MfdCompany> dataList = resource.getAllMfdCompany();
        if (CollectionUtils.isNotEmpty(dataList)) {
            Map<String, Map<String, MfdCompany>> dataMap = new HashMap<>();
            for (MfdCompany data : dataList) {
                String locale = data.getLocale();
                String companyCode = data.getCompanyCode();
                if (dataMap.get(locale) == null) {
                    dataMap.put(locale, new HashMap<>());
                }
                dataMap.get(locale).put(companyCode, data);
            }
            this.dataMap = dataMap;
        }
    }

    @Override
    protected boolean isFirstLoad() {
        return this.dataMap.isEmpty();
    }

}
