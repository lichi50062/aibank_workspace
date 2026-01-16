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

import tw.com.ibm.mf.eb.NJWEEN01SvcRqType;

// @formatter:off
/**
 * @(#)NJWEEN01RQ.java
 * 
 * <p>Description:金錢信託債券網銀歸戶查詢DBU</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/09, Charlie Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NJWEEN01RQ extends EAIRequest<NJWEEN01SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NJWEEN01RQ() {
        super("NJWEEN01");
    }
}
