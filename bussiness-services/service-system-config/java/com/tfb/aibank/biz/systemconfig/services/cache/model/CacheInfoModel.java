package com.tfb.aibank.biz.systemconfig.services.cache.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 快取資訊
 * 
 * @author horance
 *
 */
public class CacheInfoModel {
    @Schema(description = "剩餘秒數")
    private long ttl;
    @Schema(description = "Cache Key 值")
    private String cacheKey;
    @Schema(description = "資料更新時間")
    private String lastUpdate;
    @Schema(description = "快取名稱")
    private String memo;
    @Schema(description = "是否為 spring cache")
    private boolean springCache;

    /**
     * @return the ttl
     */
    public long getTtl() {
        return ttl;
    }

    /**
     * @param ttl
     *            the ttl to set
     */
    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    /**
     * @return the cacheKey
     */
    public String getCacheKey() {
        return cacheKey;
    }

    /**
     * @param cacheKey
     *            the cacheKey to set
     */
    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    /**
     * @return the lastUpdate
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @param lastUpdate
     *            the lastUpdate to set
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    public boolean isSpringCache() {
        return springCache;
    }

    public void setSpringCache(boolean springCache) {
        this.springCache = springCache;
    }

}