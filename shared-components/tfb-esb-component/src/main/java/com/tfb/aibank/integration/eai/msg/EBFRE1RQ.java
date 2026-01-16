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

import tw.com.ibm.mf.eb.EBFRE1SvcRqType;

// @formatter:off
/**
 * @(#)EBFRE1RQ.java
 * 
 * <p>Description:外幣匯入款查詢 上行電文(EBFRE1)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/17, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EBFRE1RQ extends EAIRequest<EBFRE1SvcRqType> {

    private static final long serialVersionUID = -4442614467845737932L;

    /**
     * 建構子
     */
    public EBFRE1RQ() {
        super("EBFRE1");
    }
}
