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
package com.tfb.aibank.integration.provider;

import com.ibm.tw.ibmb.biz.component.provider.AtomicCounterSequenceProviderWrapper;
import com.ibm.tw.ibmb.biz.component.provider.SequenceProvider;

// @formatter:off
/**
 * @(#)EAISequenceProvider.java
 * 
 * <p>Description:EAI專用，產出長度為7的流水號</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EAISequenceProvider extends AtomicCounterSequenceProviderWrapper {

    public EAISequenceProvider(SequenceProvider delegate) {
        super(delegate);
    }

}
