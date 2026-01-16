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

import tw.com.ibm.mf.eb.NMWEB05SvcRqType;

// @formatter:off
/**
 * @(#)NMWEB05RQ.java
 * 
 * <p>金錢信託-特金股票台幣現值查詢(NMWEB05)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/09, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NMWEB05RQ extends EAIRequest<NMWEB05SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NMWEB05RQ() {
        super("NMWEB05");
    }
}
