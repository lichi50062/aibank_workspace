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
import tw.com.ibm.mf.eb.EB555692ASvcRqType;

// @formatter:off
/**
 * @(#)EB555692ARQ.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/28,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB555692ARQ extends EAIRequest<EB555692ASvcRqType> {

    private static final long serialVersionUID = 8743871861306701803L;

    /**
     * 建構子
     */
    public EB555692ARQ() {
        super("EB555692A");
    }
}
