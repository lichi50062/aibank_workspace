package com.tfb.aibank.integration.eai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tfb.aibank.integration.provider.EAISequenceProvider;
import com.tfb.aibank.integration.provider.EAIMbSeqProvider;

@Configuration
public class EAISequenceConfig {

    @Autowired
    private EAIMbSeqProvider eaiSequenceProvider;

    @Bean
    EAISequenceProvider eaiSequenceProvidor() {
        return new EAISequenceProvider(eaiSequenceProvider);
    }
}
