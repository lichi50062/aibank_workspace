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

import tw.com.ibm.mf.eb.NAWEBP03SvcRqType;

// @formatter:off
/**
 * @(#)NAWEBP03RQ.java
 * 
 * <p>查詢參加契約</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/15, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NAWEBP03RQ extends EAIRequest<NAWEBP03SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NAWEBP03RQ() {
        super("NAWEBP03");
    }
}
