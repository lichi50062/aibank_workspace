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
package com.tfb.aibank.component.session;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.util.IBUtils;
import com.ibm.tw.ibmb.util.ValidateParamUtils;

// @formatter:off
/**
 * @(#)LocalSessionManager.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Horance Chou
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class LocalSessionManager implements Iterable<String>, InitializingBean {
    public static final String COMMON_SESSION_KEY = "pending_session_manager";
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private Environment environment;

    private String masterKey;

    private String masterMapKey;

    private String masterReverseMapKey;

    public void addSessionId(String sessionId) {
        sessionId = ValidateParamUtils.validParam(sessionId);
        redisTemplate.opsForSet().add(ValidateParamUtils.validParam(masterKey), sessionId);
    }

    public void addMapping(String ecashSessionId, String appSessionId) {
        redisTemplate.opsForHash().put(ValidateParamUtils.validParam(masterMapKey), ValidateParamUtils.validParam(ecashSessionId), ValidateParamUtils.validParam(appSessionId));
        redisTemplate.opsForHash().put(ValidateParamUtils.validParam(masterReverseMapKey), ValidateParamUtils.validParam(appSessionId), ValidateParamUtils.validParam(ecashSessionId));
    }

    public String getAppSessionId(String ecashSessionId) {
        return (String) redisTemplate.opsForHash().get(ValidateParamUtils.validParam(masterMapKey), ValidateParamUtils.validParam(ecashSessionId));
    }

    public String getECashSessionId(String appSessionId) {
        return (String) redisTemplate.opsForHash().get(ValidateParamUtils.validParam(masterReverseMapKey), ValidateParamUtils.validParam(appSessionId));
    }

    public boolean removeECashMapping(String ecashSessionId) {
        Long result = redisTemplate.opsForHash().delete(ValidateParamUtils.validParam(masterMapKey), ValidateParamUtils.validParam(ecashSessionId));
        return result != null && result.longValue() > 0;
    }

    public boolean removeAppMapping(String appSessionId) {
        Long result = redisTemplate.opsForHash().delete(ValidateParamUtils.validParam(masterReverseMapKey), ValidateParamUtils.validParam(appSessionId));
        return result != null && result.longValue() > 0;
    }

    public boolean removeSessionId(String sessionId) {
        Long result = redisTemplate.opsForSet().remove(ValidateParamUtils.validParam(masterKey), ValidateParamUtils.validParam(sessionId));
        return result != null && result.longValue() > 0;
    }

    public Set<String> getAppSessionIds() {
        return redisTemplate.opsForSet().members(ValidateParamUtils.validParam(masterKey));
    }

    public boolean removeAllAppSessionIds(Object[] sessionIds) {
        Long result = redisTemplate.opsForSet().remove(ValidateParamUtils.validParam(masterKey), sessionIds);
        return result != null && result.longValue() > 0;
    }

    public boolean addCommonSessionIds(String[] sessionIds) {
        Long result = redisTemplate.opsForSet().add(COMMON_SESSION_KEY, sessionIds);
        return result != null && result.longValue() > 0;
    }

    public boolean removeCommonSessionId(String sessionId) {
        Long result = redisTemplate.opsForSet().remove(COMMON_SESSION_KEY, ValidateParamUtils.validParam(sessionId));
        return result != null && result.longValue() > 0;
    }

    // @formatter:off
    /**
     * Retrieves the size of a Redis set as an integer.
     * 
     * @param setkey the key of the Redis set to query
     * @return the size of the set as an integer, or 0 if the set does not exist
     * @throws IllegalArgumentException if the size of the set exceeds the maximum value of an integer
     */
    // @formatter:on
    public int getSetSizeAsInt(String setkey) {
        Long size = redisTemplate.opsForSet().size(ValidateParamUtils.validParam(setkey));
        if (size == null) {
            return 0;
        }
        if (size > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Set size exceeds int maximum value!");
        }
        return size.intValue();
    }

    // @formatter:off
    /**
     * Scans Redis keys that match a specified pattern and returns them as a Set.
     * 
     * @param pattern the pattern to match Redis keys (e.g., "prefix:*")
     * @return a Set of Redis keys that match the given pattern; returns an empty Set if no keys match
     */
    // @formatter:on
    public Set<String> scanCacheKeyByPattern(String pattern) {
        // 建立 Redis 掃描選項，指定匹配的模式
        ScanOptions scanOptions = ScanOptions.scanOptions().match(pattern).build();
        // 使用 try-with-resources 確保 Cursor 正確關閉
        try (Cursor<String> cursor = redisTemplate.scan(scanOptions)) {
            // 將 Cursor 轉換成流，然後收集到 Set 中
            return StreamSupport.stream(Spliterators.spliteratorUnknownSize(cursor, Spliterator.ORDERED), false).collect(Collectors.toSet());
        }
    }

    @Override
    public Iterator<String> iterator() {
        Set<String> members = redisTemplate.opsForSet().members(ValidateParamUtils.validParam(masterKey));
        if (CollectionUtils.isNotEmpty(members)) {
            return members.iterator();
        }
        else {
            return new HashSet<String>().iterator();
        }

    }

    public Iterator<String> commonCacheIterator() {
        Set<String> members = redisTemplate.opsForSet().members(COMMON_SESSION_KEY);
        if (CollectionUtils.isNotEmpty(members)) {
            return members.iterator();
        }
        else {
            return new HashSet<String>().iterator();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.masterKey = IBUtils.getInstanceId(environment) + "_" + this.getClass().getSimpleName();
        this.masterMapKey = this.getClass().getSimpleName() + "_SESSION_MAP";
        this.masterReverseMapKey = this.getClass().getSimpleName() + "_SESSION_R_MAP";
    }
}