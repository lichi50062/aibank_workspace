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
package com.ibm.tw.ibmb.component.redis;

import java.util.Base64;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.ibm.tw.commons.gson.GsonHelper;
import com.ibm.tw.ibmb.base.model.RedisResourceData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

/**
 * 處理資源存放／取用 redis cache 的工具集
 * 
 * @author horance
 */
@Component
public class RedisResourceUtils {
    private static final String REDIS_RES_KEY = "AIBANK_REDIS_RES_KEY_%s";
    private static final long DEFAULT_CACHE_TIME = 3;
    private static final TimeUnit DEFAULT_CACHE_TIME_UNIT = TimeUnit.MINUTES;

    @Autowired
    @Qualifier("cacheManagerRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 將 byte array 資料存入 redis，使用預設 cache time
     * 
     * @param resourceId
     * @param content
     * @param contentType
     * @param downloadFileName
     */
    public void putResourceToRedis(String resourceId, byte[] content, String contentType, String downloadFileName) {
        putResourceToRedis(resourceId, content, contentType, downloadFileName, DEFAULT_CACHE_TIME, DEFAULT_CACHE_TIME_UNIT);
    }

    /**
     * 將 byte array 資料存入 redis，使用預設 cache time, 系統產生 resourceId
     * 
     * @param content
     * @param contentType
     * @return resourceId
     */
    public String putResourceToRedis(byte[] content, String contentType, String downloadFileName) {
        String resourceId = java.util.UUID.randomUUID().toString();
        putResourceToRedis(resourceId, content, contentType, downloadFileName);
        return resourceId;
    }

    /**
     * 將 byte array 資料存入 redis, 並指定 cache 時間
     * 
     * @param resourceId
     * @param content
     * @param contentType
     * @param time
     * @param timeUnit
     */
    public void putResourceToRedis(String resourceId, byte[] content, String contentType, String downloadFileName, long time, TimeUnit timeUnit) {
        String base64Content = Base64.getEncoder().encodeToString(content);
        RedisResourceData bo = new RedisResourceData();
        bo.setBase64Content(base64Content);
        bo.setContentType(contentType);
        bo.setDownloadFileName(downloadFileName);
        Gson gson = GsonHelper.newInstance();
        String composeResId = composeResId(resourceId);
        redisTemplate.opsForValue().set(composeResId, gson.toJson(bo));
        redisTemplate.expire(composeResId, time, timeUnit);
    }

    private String composeResId(String resourceId) {
        return String.format(REDIS_RES_KEY, resourceId);
    }

    /**
     * 檢查傳入的 resourceId 是否存在 redis
     * 
     * @param resourceId
     * @return
     */
    public boolean hasResourceInRedis(String resourceId) {
        return redisTemplate.hasKey(composeResId(resourceId));
    }

    /**
     * 依傳入 resourceId 取得redis 中的 byte array 資料
     * 
     * @param resourceId
     * @return byte array 資料，找不到或 expired 時回覆 null
     */
    public RedisResourceData getResourceContentFromRedis(String resourceId) {
        String jsonContent = redisTemplate.opsForValue().get(composeResId(resourceId));
        if (jsonContent == null) {
            return null;
        }
        Gson gson = GsonHelper.newInstance();
        return gson.fromJson(jsonContent, RedisResourceData.class);
    }

    public String putPDF(String fileName, byte[] content) {
        return putResourceToRedis(content, MediaType.APPLICATION_PDF_VALUE, fileName);
    }

    public String putJPEG(String fileName, byte[] jpegContent) {
        return putResourceToRedis(jpegContent, MediaType.IMAGE_JPEG_VALUE, fileName);
    }
}
