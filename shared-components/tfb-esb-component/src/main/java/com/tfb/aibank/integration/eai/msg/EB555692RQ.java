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
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB555692SvcRqType;

// @formatter:off
/**
 * @(#)EB555692RQ.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB555692RQ extends EAIRequest<EB555692SvcRqType> {

    private static final long serialVersionUID = 8743871861306701803L;

    /**
     * 建構子
     */
    public EB555692RQ() {
        super("EB555692");
    }
}
