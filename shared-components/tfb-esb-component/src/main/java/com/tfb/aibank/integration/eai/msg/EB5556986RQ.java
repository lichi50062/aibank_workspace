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
import tw.com.ibm.mf.eb.EB5556986SvcRqType;

// @formatter:off
/**
 * @(#)EB5556986RQ.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/10/09, Lichi.Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB5556986RQ extends EAIRequest<EB5556986SvcRqType> {

    private static final long serialVersionUID = -2144008014628966547L;

    /**
     * 建構子
     */
    public EB5556986RQ() {
        super("EB5556986");
    }
}