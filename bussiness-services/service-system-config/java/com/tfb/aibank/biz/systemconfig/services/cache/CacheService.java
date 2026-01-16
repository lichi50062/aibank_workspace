package com.tfb.aibank.biz.systemconfig.services.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ValueOperations;

import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.ibmb.type.GlobalCacheKey;
import com.tfb.aibank.biz.systemconfig.services.cache.model.CacheInfoModel;

public class CacheService {

    private RedisTemplate<String, String> redisTemplate;

    private CacheManager springCacheManager;

    private static final ThreadLocal<FastDateFormat> FORMATTER = ThreadLocal.withInitial(() -> DateFormatUtils.CE_DATETIME_FORMAT);

    public CacheService(RedisTemplate<String, String> redisTemplate, CacheManager springCacheManager) {
        this.redisTemplate = redisTemplate;
        this.springCacheManager = springCacheManager;
    }

    private String formatRedisKey(GlobalCacheKey globalCacheKey) {
        String formatRedisKey = globalCacheKey.getClass().getName() + "#" + globalCacheKey.name();
        return formatRedisKey;
    }

    private String findNextKey(String redisKey) {
        Iterator<String> it = redisTemplate.scan(ScanOptions.scanOptions().match(redisKey + "#*").build());
        if (it.hasNext()) {
            return it.next();
        }
        return redisKey;
    }

    public List<CacheInfoModel> listCache() {
        List<CacheInfoModel> cacheInfoList = new ArrayList<>(GlobalCacheKey.values().length);
        Arrays.asList(GlobalCacheKey.values()).forEach(cacheKey -> {
            String redisKey = formatRedisKey(cacheKey);
            if (cacheKey.isIncremental()) {
                redisKey = findNextKey(redisKey);
            }
            Long expires = redisTemplate.getExpire(redisKey);
            ValueOperations<String, String> ops = redisTemplate.opsForValue();
            String lastModified = ops.get(redisKey);
            CacheInfoModel info = new CacheInfoModel();
            info.setCacheKey(cacheKey.name());
            info.setTtl(expires);
            info.setLastUpdate(StringUtils.isBlank(lastModified) || !StringUtils.isNumeric(lastModified) ? "" : FORMATTER.get().format(ConvertUtils.str2Long(lastModified)));
            info.setMemo(cacheKey.getMemo());
            cacheInfoList.add(info);
        });
        springCacheManager.getCacheNames().forEach(n -> {
            CacheInfoModel info = new CacheInfoModel();
            info.setCacheKey(n);
            info.setTtl(0);
            info.setLastUpdate("");
            info.setMemo(String.format("Spring cache under %s", n));
            cacheInfoList.add(info);
        });

        return cacheInfoList;
    }

    public String getCacheContent(String cacheKey, boolean isAEM) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String redisKey = cacheKey;
        GlobalCacheKey globalCacheKey = GlobalCacheKey.valueOf(cacheKey);
        redisKey = formatRedisKey(globalCacheKey);
        if (globalCacheKey.isIncremental()) {
            redisKey = findNextKey(redisKey);
        }

        return ops.get(redisKey);
    }

    public boolean deleteCacheContent(String cacheKey, String requestURI) {
        String redisKey = cacheKey;
        GlobalCacheKey globalCacheKey = null;
        boolean res = false;
        globalCacheKey = GlobalCacheKey.valueOf(cacheKey);
        redisKey = formatRedisKey(globalCacheKey);
        if (globalCacheKey.isIncremental()) {
            res = deleteAllCache(redisKey, globalCacheKey);
        }

        res |= deleteCache(redisKey, globalCacheKey);

        return res;

    }

    /**
     * 刪除所有 instance 的 key
     * 
     * @param redisKey
     * @param globalCacheKey
     */
    private boolean deleteAllCache(String redisKey, GlobalCacheKey globalCacheKey) {
        final boolean[] res = new boolean[1];
        redisTemplate.scan(ScanOptions.scanOptions().match(redisKey + "#*").build()).forEachRemaining(k -> {
            res[0] |= deleteCache(k, globalCacheKey);
        });
        ;
        return res[0];
    }

    /**
     * 刪除所有 instance 的 key
     * 
     * @param redisKey
     * @param globalCacheKey
     */
    public boolean deleteAllCache() {
        final boolean[] res = new boolean[1];
        for (GlobalCacheKey cacheKey : GlobalCacheKey.values()) {
            String redisKey = formatRedisKey(cacheKey);
            res[0] |= deleteCache(redisKey, cacheKey);
        }
        this.springCacheManager.getCacheNames().forEach(n -> {
            springCacheManager.getCache(n).clear();
        });
        return res[0];
    }

    /**
     * @param redisKey
     * @param globalCacheKey
     * @return
     */
    protected boolean deleteCache(String redisKey, GlobalCacheKey globalCacheKey) {
        boolean res;
        res = redisTemplate.expire(redisKey, 0, TimeUnit.SECONDS);
        return res;
    }
}
