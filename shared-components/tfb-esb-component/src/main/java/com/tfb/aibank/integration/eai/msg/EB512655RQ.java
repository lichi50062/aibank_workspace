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
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB512655SvcRqType;

// @formatter:off
/**
 * @(#)EB512655RQ.java
 * 
 * <p>Description:EB512655RQ</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/17, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB512655RQ extends EAIRequest<EB512655SvcRqType> {

    private static final long serialVersionUID = -6908900038479521181L;

    /**
     * 建構子
     */
    public EB512655RQ() {
        super("EB512655");
    }

}
