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
package com.ibm.tw.ibmb.component.cache;

// @formatter:off
/**
 * @(#)AbstractLRUCacheManager.java
 * 
 * <p>Description:LRU CacheManager 實作</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public abstract class AbstractLRUCacheManager extends AbstractCacheManager {
    @Override
    protected final boolean isFirstLoad() {
        // 如果是 LRU Cache, 則 isFirstLoad 一律回 false
        return false;
    }

    @Override
    protected final void loadCache() {
        // do nothing
    }

    /**
     * LRU Cache 則依傳入 key 值載入需要的資料
     * 
     * @param key
     */
    protected abstract void loadCacheByKey(Object... keys);

    /**
     * 判斷是否
     * 
     * @param key
     * @return
     */
    protected abstract boolean isFirstLoad(Object... keys);

    /**
     * 在 expired 時需要清除所有資料
     */
    protected abstract void cleanAll();

}
