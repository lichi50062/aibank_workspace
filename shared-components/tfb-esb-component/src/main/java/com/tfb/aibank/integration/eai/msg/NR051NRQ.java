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

import tw.com.ibm.mf.eb.NR051NSvcRqType;

// @formatter:off
/**
 * @(#)NR051NRQ.java
 * 
 * <p>Description: NR051N 海外ETF/股票股務活動查詢</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/17, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NR051NRQ extends EAIRequest<NR051NSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NR051NRQ() {
        super("NR051N");
        this.getHeader().setHDRVQ1("NRWEBHQ");
    }

}
