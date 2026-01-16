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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)BranchmapCacheManager.java
 * 
 * <p>Description:服務據點檔 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class BranchmapCacheManager extends AbstractCacheManager {

    @Autowired
    private BranchResource resource;

    private List<Branchmap> dataList = new ArrayList<>();

    public List<Branchmap> getAllBranchmap() {
        return dataList;
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.BRANCHMAP_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        this.dataList = resource.getAllBranchmap();
    }

    @Override
    protected boolean isFirstLoad() {
        return this.dataList.isEmpty();
    }

}
