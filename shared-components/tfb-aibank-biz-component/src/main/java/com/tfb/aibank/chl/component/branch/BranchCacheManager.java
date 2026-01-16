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
package com.tfb.aibank.chl.component.branch;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)BranchCacheManager.java
 * 
 * <p>Description:分行檔 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class BranchCacheManager extends AbstractCacheManager {

    @Autowired
    private BranchResource branchResource;

    /**
     * Map<LOCALE, Map<BRANCH_ID, BranchData>>
     */
    private Map<String, Map<String, BranchData>> dataMap = new LinkedHashMap<>();

    public List<BranchData> getAllBranchs(Locale locale) {
        return getAllBranchs(locale.toString());
    }

    public List<BranchData> getAllBranchs(String locale) {
        return getDataByLocale(locale).values().stream().collect(Collectors.toList());
    }

    public String getBranchName(String branchId, Locale locale) {
        return getBranchName(branchId, locale.toString());
    }

    public String getBranchName(String branchId, String locale) {
        BranchData branch = getDataByLocale(locale).get(branchId);
        return branch == null ? StringUtils.EMPTY : branch.getBranchName();
    }

    public BranchData getBranch(String branchId, Locale locale) {
        return getBranch(branchId, locale.toString());
    }

    public BranchData getBranch(String branchId, String locale) {
        return getDataByLocale(locale).get(branchId);
    }

    private Map<String, BranchData> getDataByLocale(String locale) {
        Map<String, BranchData> map = dataMap.get(locale);
        return MapUtils.isEmpty(map) ? Collections.emptyMap() : map;
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.BRANCH_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<BranchData> dataList = branchResource.getAllBranch();
        Map<String, Map<String, BranchData>> dataMap = new LinkedHashMap<>();
        dataList.forEach(branch -> {
            String locale = branch.getLocale();
            String branchId = branch.getBranchId();
            if (dataMap.get(locale) == null) {
                dataMap.put(locale, new HashMap<String, BranchData>());
            }
            dataMap.get(locale).put(branchId, branch);
        });
        this.dataMap = dataMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.dataMap.isEmpty();
    }

}
