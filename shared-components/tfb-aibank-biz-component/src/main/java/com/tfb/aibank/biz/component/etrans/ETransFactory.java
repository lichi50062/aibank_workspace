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
package com.tfb.aibank.biz.component.etrans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.log.TraceLogPersistenceProvider;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;

// @formatter:off
/**
 * @(#)ETransFactory.java
 * 
 * <p>Description:e化繳費網 工廠類別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/17, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class ETransFactory {

    @Autowired
    private TraceLogPersistenceProvider<?, ?> traceLogPersistenceProvider;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private E2EEHsmUtils_AIBank e2EEHsmUtils_JSB;

    public <T> ETrans<T> newInstance() {
        ETrans<T> instance = new ETrans<T>();
        instance.setTraceLogPersistenceProvider(traceLogPersistenceProvider);
        instance.setSystemParamCacheManager(systemParamCacheManager);
        instance.setE2EEHsmUtils_JSB(e2EEHsmUtils_JSB);
        return instance;
    }
}
