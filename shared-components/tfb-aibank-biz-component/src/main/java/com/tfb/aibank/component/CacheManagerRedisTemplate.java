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
package com.tfb.aibank.component;

import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

// @formatter:off
/**
 * @(#)CacheManagerRedisTemplate.java
 * 
 * <p>Description:this class is a clone of StringRedisTemplate for separate cache & session redis cluster</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Horance Chou
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CacheManagerRedisTemplate extends RedisTemplate<String, String> {
    public CacheManagerRedisTemplate() {
        setKeySerializer(RedisSerializer.string());
        setValueSerializer(RedisSerializer.string());
        setHashKeySerializer(RedisSerializer.string());
        setHashValueSerializer(RedisSerializer.string());
    }

    /**
     * Constructs a new <code>StringRedisTemplate</code> instance ready to be used.
     *
     * @param connectionFactory
     *            connection factory for creating new connections
     */
    public CacheManagerRedisTemplate(RedisConnectionFactory connectionFactory) {
        setKeySerializer(RedisSerializer.string());
        setValueSerializer(RedisSerializer.string());
        setHashKeySerializer(RedisSerializer.string());
        setHashValueSerializer(RedisSerializer.string());
        setConnectionFactory(connectionFactory);
        afterPropertiesSet();
    }

    @Override
    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }
}
