/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2013.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.log.TraceLogPersistenceProvider;
import com.tfb.aibank.integration.eai.config.EAIConfigProperties;
import com.tfb.aibank.integration.provider.EAISequenceProvider;

@Component
public class EAIFactory {

    @Autowired
    private EAISequenceProvider sequenceProvider;

    @Autowired
    private TraceLogPersistenceProvider<?, ?> traceLogPersistenceProvider;

    @Autowired
    private EAIConfigProperties eaiConfig;

    public <RQ extends EAIRequest<?>, RS extends EAIResponse<?, ?>> EAI<RQ, RS> newInstance(EAIChannel channel, Class<RQ> rqClazz, Class<RS> rsClazz) {
        EAI.setConfig(eaiConfig);
        EAI<RQ, RS> eaiInstance = EAI.newInstance(channel, rqClazz, rsClazz);
        eaiInstance.setSequenceProvider(sequenceProvider);
        eaiInstance.setTraceLogPersistenceProvider(traceLogPersistenceProvider);
        return eaiInstance;
    }

    public <RQ extends EAIOverviewRequest<?>, RS extends EAIOverviewResponse<?, ?>> EAI<RQ, RS> newOverviewInstance(EAIChannel channel, Class<RQ> rqClazz, Class<RS> rsClazz) {
        EAI.setConfig(eaiConfig);
        EAI<RQ, RS> eaiOverviewInstance = EAI.newOverviewInstance(channel, rqClazz, rsClazz);
        eaiOverviewInstance.setSequenceProvider(sequenceProvider);
        eaiOverviewInstance.setTraceLogPersistenceProvider(traceLogPersistenceProvider);
        return eaiOverviewInstance;
    }
}
