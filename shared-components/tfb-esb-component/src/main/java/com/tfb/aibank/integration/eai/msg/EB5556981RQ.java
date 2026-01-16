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

import tw.com.ibm.mf.eb.EB5556981SvcRqType;

// @formatter:off
/**
 * @(#)EB5556981RQ.java
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
public class EB5556981RQ extends EAIRequest<EB5556981SvcRqType> {

    private static final long serialVersionUID = -1636604670246672182L;

    /**
     * 建構子
     */
    public EB5556981RQ() {
        super("EB5556981");
    }
}