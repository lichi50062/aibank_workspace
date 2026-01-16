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

import tw.com.ibm.mf.eb.EB032333SvcRqType;

// @formatter:off
/**
 * @(#)EB032333RQ.java
 * 
 * <p>Description:EB032333RQ</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/02, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB032333RQ extends EAIRequest<EB032333SvcRqType> {

    private static final long serialVersionUID = -8391752290173619907L;

    /**
     * 建構子
     */
    public EB032333RQ() {
        super("EB032333");
    }

}
