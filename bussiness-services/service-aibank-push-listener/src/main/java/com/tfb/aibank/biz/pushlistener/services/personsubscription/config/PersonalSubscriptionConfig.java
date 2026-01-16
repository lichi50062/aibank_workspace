package com.tfb.aibank.biz.pushlistener.services.personsubscription.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

// @formatter:off
/**
 * @(#)PersonalSubscriptionConfig.java
 * 
 * <p>Description:個人化訂閱推播設定</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/15, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Configuration
@ConfigurationProperties(prefix = "aibank.personal-subscription")
public class PersonalSubscriptionConfig {

    private String bootstrapServers;
    private String keyDeserializer;
    private String valueDeserializer;
    private String groupId;
    private String topicPattern;

    /**
     * @return the bootstrapServers
     */
    public String getBootstrapServers() {
        return bootstrapServers;
    }

    /**
     * @param bootstrapServers
     *            the bootstrapServers to set
     */
    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    /**
     * @return the keyDeserializer
     */
    public String getKeyDeserializer() {
        return keyDeserializer;
    }

    /**
     * @param keyDeserializer
     *            the keyDeserializer to set
     */
    public void setKeyDeserializer(String keyDeserializer) {
        this.keyDeserializer = keyDeserializer;
    }

    /**
     * @return the valueDeserializer
     */
    public String getValueDeserializer() {
        return valueDeserializer;
    }

    /**
     * @param valueDeserializer
     *            the valueDeserializer to set
     */
    public void setValueDeserializer(String valueDeserializer) {
        this.valueDeserializer = valueDeserializer;
    }

    /**
     * @return the groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * @param groupId
     *            the groupId to set
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * @return the topicPattern
     */
    public String getTopicPattern() {
        return topicPattern;
    }

    /**
     * @param topicPattern
     *            the topicPattern to set
     */
    public void setTopicPattern(String topicPattern) {
        this.topicPattern = topicPattern;
    }

    @Bean
    public ConsumerFactory<String, String> personalSubscriptionFactory() {

        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> personalSubscriptionContainerFactory(@Qualifier("personalSubscriptionFactory") ConsumerFactory<String, String> personalSubscriptionFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(personalSubscriptionFactory);
        return factory;
    }

}