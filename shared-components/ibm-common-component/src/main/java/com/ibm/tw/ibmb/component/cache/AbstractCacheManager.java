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

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.ibmb.type.GlobalCacheKey;
import com.ibm.tw.ibmb.util.IBUtils;
import com.ibm.tw.ibmb.util.ValidateParamUtils;

// @formatter:off
/**
 * @(#)AbstractCacheManager.java
 * 
 * <p>Description:Cache Manager 父類別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public abstract class AbstractCacheManager {

    /**
     * logger
     */
    protected static IBLog logger = IBLog.getLog(AbstractCacheManager.class);

    /**
     * redis template
     */
    @Autowired
    @Qualifier("cacheManagerRedisTemplate")
    private RedisTemplate<String, String> template;

    @Autowired
    private Environment environment;

    /*
     * redis 中的 lastModified
     */
    private long lastModified = 0;

    private long lastExpiredCheck = 0;

    private boolean lastExpiredCheckResult = false;

    private long lastCheckTimeStamp = 0;

    // 是否是因為逾時而更新 (第一次進入時為 true )
    private boolean updateForExpire = true;

    // 110秒檢查一次
    protected long checkInterval() {
        return 110000;
    }

    /**
     * 取得預設 cache Timeout 時間
     * 
     * @return
     */
    protected Duration cacheTimeout() {
        return Duration.ofDays(1);
    }

    /**
     * 取得 cache key
     * 
     * @return
     */
    protected abstract GlobalCacheKey globalCacheKey();

    /**
     * 將資料載入至快取
     */
    protected abstract void loadCache();

    /**
     * 判斷是否為首次載入
     * 
     * @return
     */
    protected abstract boolean isFirstLoad();

    /**
     * 檢查傳入 Key 值是否已逾時 由 CacheAdvisor 呼叫
     * 
     * @param globalCacheKey
     * @return
     */
    boolean checkExpired() {
        // 前一次沒有 expired, 才依時間決定是否跳過檢查
        if (!lastExpiredCheckResult && lastExpiredCheck != 0 && ((lastExpiredCheck + checkInterval()) > System.currentTimeMillis())) {
            return false;
        }
        lastExpiredCheck = System.currentTimeMillis();
        String redisKey = formatRedisKey();
        redisKey = ValidateParamUtils.validParam(redisKey);
        long seconds = ConvertUtils.str2Long(template.opsForValue().get(redisKey));
        // 【FORTIFY：Access Control: Database】
        // long seconds = ConvertUtils.str2Long((String) IBUtils.execute(template.opsForValue(), "get", redisKey, Object.class));
        logger.debug("checking expires for {} is {}", redisKey, seconds);
        // 0 表示抓出來的值是空的, 負值表示逾時
        lastExpiredCheckResult = seconds <= 0;
        if (lastExpiredCheckResult) { // 己逾時，直接回傳
            this.lastCheckTimeStamp = System.currentTimeMillis();
            this.updateForExpire = true;
            logger.debug("is expired for {} is {}, new lastModified is {}", redisKey, updateForExpire, this.lastCheckTimeStamp);
            return lastExpiredCheckResult;
        }
        else {
            // 未逾時，比對上次更新時間
            lastExpiredCheckResult = seconds > this.lastModified;
            if (lastExpiredCheckResult) {
                // 不是因為 expire 更新
                this.updateForExpire = false;
                // 如果己逾時，前面要 reload, 先將時間設定更新
                this.lastCheckTimeStamp = seconds;
                logger.debug("is expired for {} is {}, new lastModified is {}", redisKey, updateForExpire, this.lastCheckTimeStamp);
            }
            return lastExpiredCheckResult;
        }
    }

    protected String formatRedisKey() {
        GlobalCacheKey globalCacheKey = globalCacheKey();
        String formatRedisKey = globalCacheKey.getClass().getName() + "#" + globalCacheKey.name();
        if (globalCacheKey.isIncremental()) {
            formatRedisKey += "#" + instanceId();
        }
        return formatRedisKey;
    }

    private String instanceId() {
        return IBUtils.getInstanceId(environment);
    }

    /**
     * 將資料回存 redis
     * 
     * @param
     */
    protected void updateExpireCheck() {
        // for performance 考量，暫不回存 redis, 只放空值 for expire check
        ValueOperations<String, String> ops = template.opsForValue();
        String key = formatRedisKey();
        key = ValidateParamUtils.validParam(key);
        // 如果 lastCheckTimeStamp 是0，表示是第一次載入，需將 lastCheckTimeStamp 更新為系統時間，並寫入 redis
        if (this.lastCheckTimeStamp == 0) {
            this.lastCheckTimeStamp = System.currentTimeMillis();
        }
        this.lastModified = lastCheckTimeStamp;
        String lastModifiedStr = String.valueOf(this.lastModified);
        if (this.updateForExpire) {
            ops.set(key, lastModifiedStr, cacheTimeout());
            // // 【FORTIFY：Access Control: Database】
            // Object[] params = new Object[] { key, lastModifiedStr, cacheTimeout() };
            // Class<?>[] classes = new Class[] { Object.class, Object.class, Duration.class };
            // IBUtils.execute(ops, "set", params, classes);

            this.updateForExpire = false; // 第一次寫入 redis key 之後，將此 flag 改為 false，以免 incremental cache 每次都更新 redis
            logger.debug("updateExpireCheck set lastModified to redis for {}, lastModified is {}", key, lastModifiedStr);
        }
        else {
            logger.debug("updateExpireCheck update in-memory {}, lastModified is {}", key, lastModifiedStr);
        }
    }

    public long getLastModified() {
        return lastModified;
    }

    public void forceReload() {
        this.lastModified = 0; // reset last modified && last expired check
        this.lastExpiredCheck = 0;
    }

}
