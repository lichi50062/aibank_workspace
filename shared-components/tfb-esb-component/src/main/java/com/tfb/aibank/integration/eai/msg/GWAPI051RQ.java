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

import tw.com.ibm.mf.eb.GWAPI051SvcRqType;

// @formatter:off
/**
 * @(#)GWAPI051RQ.java
 * 
 * <p>Description:GWAPI051RQ 查詢他行戶名</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/07/24, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class GWAPI051RQ extends EAIRequest<GWAPI051SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public GWAPI051RQ() {
        super("GWAPI051");
    }

}
