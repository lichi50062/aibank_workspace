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

import java.time.Duration;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.observability.MicrometerTracingAdapter;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.collect.ImmutableMap;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;

import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import io.lettuce.core.resource.DnsResolvers;
import io.micrometer.observation.ObservationRegistry;

// @formatter:off
/**
 * @(#)ExternalRedisCacheConfiguration.java
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
@Configuration
@EnableCaching
@EnableConfigurationProperties(CacheProperties.class)
public class ExternalRedisCacheConfiguration {
    private static final IBLog logger = IBLog.getLog(ExternalRedisCacheConfiguration.class);

    @Autowired
    private ClusterConfigurationProperties clusterConfig;

    @Autowired
    private Environment environment;

    @Value("${aibank.common.cache.redis.host:}")
    private String externalRedisHost;

    @Value("${aibank.common.cache.redis.port:-1}")
    private String externalRedisPort;

    @Value("${aibank.common.cache.redis.username:}")
    private String externalRedisUsername;

    @Value("${aibank.common.cache.redis.password:}")
    private String externalRedisPassword;

    @Autowired
    private ObservationRegistry observationRegistry;

    public RedisConnectionFactory redisConnectionFactory() {
        if (!CollectionUtils.isEmpty(clusterConfig.getNodes())) {
            logger.debug("enable redis cluster configuration");
            RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration(clusterConfig.getNodes());
            clusterConfiguration.setUsername(externalRedisUsername);
            clusterConfiguration.setPassword(RedisPassword.of(externalRedisPassword));
            ClientResources clientResource = DefaultClientResources.builder().dnsResolver(DnsResolvers.JVM_DEFAULT).tracing(new MicrometerTracingAdapter(observationRegistry, "redis-cache")).build();
            ClusterClientOptions cliOpts = ClusterClientOptions.builder().topologyRefreshOptions(ClusterTopologyRefreshOptions.builder().enableAllAdaptiveRefreshTriggers().build()).build();
            LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder().clientResources(clientResource).clientOptions(cliOpts).build();
            LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(clusterConfiguration, clientConfig);
            connectionFactory.afterPropertiesSet();
            return connectionFactory;
        }
        else {
            RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(externalRedisHost, ConvertUtils.str2Int(externalRedisPort, -1));
            config.setUsername(externalRedisUsername);
            config.setPassword(RedisPassword.of(externalRedisPassword));
            ClientResources clientResource = DefaultClientResources.builder().dnsResolver(DnsResolvers.JVM_DEFAULT).tracing(new MicrometerTracingAdapter(observationRegistry, "redis-cache")).build();
            ClusterClientOptions cliOpts = ClusterClientOptions.builder().topologyRefreshOptions(ClusterTopologyRefreshOptions.builder().enableAllAdaptiveRefreshTriggers().build()).build();
            LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder().clientResources(clientResource).clientOptions(cliOpts).build();
            LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(config, clientConfig);
            connectionFactory.afterPropertiesSet();
            return connectionFactory;
        }
    }

    @Bean
    CacheManagerRedisTemplate cacheManagerRedisTemplate() {
        CacheManagerRedisTemplate template = new CacheManagerRedisTemplate();
        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());
        template.setValueSerializer(RedisSerializer.java());
        template.setHashValueSerializer(RedisSerializer.java());
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }

    @Bean(name = "cacheManagerStringRedisTemplate")
    public CacheManagerRedisTemplate cacheManagerStringRedisTemplate() {
        CacheManagerRedisTemplate template = new CacheManagerRedisTemplate(redisConnectionFactory());
        template.setKeySerializer(RedisSerializer.string());
        template.setValueSerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());
        template.setHashValueSerializer(RedisSerializer.string());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    RedisCacheWriter redisCacheWriter() {
        logger.debug("setting external redis connection factory");
        return RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory());
    }

    @Bean
    CacheManagerCustomizers cacheManagerCustomizers() {
        CacheManagerCustomizer<RedisCacheManager> customizer = new CacheManagerCustomizer<RedisCacheManager>() {

            @Override
            public void customize(RedisCacheManager cacheManager) {
                logger.info("setting cache manager transaction aware = false");
                cacheManager.setTransactionAware(false);
            }
        };
        return new CacheManagerCustomizers(Collections.singletonList(customizer));
    }

    @Bean
    RedisCacheManager cacheManager(CacheProperties cacheProperties, CacheManagerCustomizers cacheManagerCustomizers, ObjectProvider<org.springframework.data.redis.cache.RedisCacheConfiguration> redisCacheConfiguration, ObjectProvider<RedisCacheManagerBuilderCustomizer> redisCacheManagerBuilderCustomizers, ResourceLoader resourceLoader) {
        RedisCacheConfiguration defaultCacheConfig = determineConfiguration(cacheProperties, redisCacheConfiguration, resourceLoader.getClassLoader());
        RedisCacheManagerBuilder builder = RedisCacheManager.builder(redisConnectionFactory()).cacheDefaults(defaultCacheConfig);
        List<String> cacheNames = cacheProperties.getCacheNames();
        if (!cacheNames.isEmpty()) {
            builder.initialCacheNames(new LinkedHashSet<>(cacheNames));
        }
        // 設置過期時間
        ImmutableMap.Builder<String, RedisCacheConfiguration> cacheConfig = ImmutableMap.builder();
        for (String cacheName : cacheNames) {
            Duration ttl = this.environment.getProperty("aibank.common.cache.duration-map." + cacheName, Duration.class, cacheProperties.getRedis().getTimeToLive());
            cacheConfig.put(cacheName, defaultCacheConfig.entryTtl(ttl));
        }
        builder.withInitialCacheConfigurations(cacheConfig.build());

        redisCacheManagerBuilderCustomizers.orderedStream().forEach((customizer) -> customizer.customize(builder));
        return cacheManagerCustomizers.customize(builder.build());
    }

    private org.springframework.data.redis.cache.RedisCacheConfiguration determineConfiguration(CacheProperties cacheProperties, ObjectProvider<org.springframework.data.redis.cache.RedisCacheConfiguration> redisCacheConfiguration, ClassLoader classLoader) {
        return redisCacheConfiguration.getIfAvailable(() -> createConfiguration(cacheProperties, classLoader));
    }

    private org.springframework.data.redis.cache.RedisCacheConfiguration createConfiguration(CacheProperties cacheProperties, ClassLoader classLoader) {
        Redis redisProperties = cacheProperties.getRedis();
        org.springframework.data.redis.cache.RedisCacheConfiguration config = org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig();
        // 建立一個安全的PolymorphicTypeValidator
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder().allowIfBaseType(Object.class).build();

        // 配置ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setTypeFactory(TypeFactory.defaultInstance().withClassLoader(classLoader));

        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        // 禁用預設的類型訊息輸出
        objectMapper.deactivateDefaultTyping();

        config = config.serializeValuesWith(SerializationPair.fromSerializer(RedisSerializer.java(classLoader)));
        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }
        if (redisProperties.getKeyPrefix() != null) {
            config = config.prefixCacheNameWith(redisProperties.getKeyPrefix());
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }
        return config;
    }
}
