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

import tw.com.ibm.mf.eb.EB032655SvcRqType;

// @formatter:off
/**
 * @(#)EB032655RQ.java
 * 
 * <p>Description:EB032655 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB032655RQ extends EAIRequest<EB032655SvcRqType> {

    private static final long serialVersionUID = 602985611802022543L;

    /**
     * 建構子
     */
    public EB032655RQ() {
        super("EB032655");
    }
}
