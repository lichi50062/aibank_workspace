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

import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration.LettuceClientConfigurationBuilder;
import org.springframework.data.redis.connection.lettuce.observability.MicrometerTracingAdapter;

import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import io.lettuce.core.resource.DnsResolvers;
import io.micrometer.observation.ObservationRegistry;

@Configuration
public class LettuceConfiguration {

    @Bean
    LettuceClientConfigurationBuilderCustomizer lettuceClientConfigurationBuilderCustomizer(ObservationRegistry observationRegistry) {
        LettuceClientConfigurationBuilderCustomizer customizer = new LettuceClientConfigurationBuilderCustomizer() {

            @Override
            public void customize(LettuceClientConfigurationBuilder clientConfigurationBuilder) {
                ClientResources clientResource = DefaultClientResources.builder()
                        .dnsResolver(DnsResolvers.JVM_DEFAULT).tracing(new MicrometerTracingAdapter(observationRegistry, "redis")).build();
                clientConfigurationBuilder.clientResources(clientResource);
            }
        };
        return customizer;
    }

}
