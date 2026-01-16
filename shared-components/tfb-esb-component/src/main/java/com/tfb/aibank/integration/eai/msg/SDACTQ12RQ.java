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

import tw.com.ibm.mf.eb.SDACTQ12SvcRqType;
import tw.com.ibm.mf.eb.SPWEBQ1SvcRqType;

// @formatter:off
/**
 * @(#)SDACTQ12RQ.java
 * 
 * <p>Description:SDACTQ12RQ 信託資產_SI產品約當臺幣</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/08, Andy Kuo
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class SDACTQ12RQ extends EAIRequest<SDACTQ12SvcRqType> {

    private static final long serialVersionUID = -2355907506160029870L;

    /**
     * 建構子
     */
    public SDACTQ12RQ() {
        super("SDACTQ12");
    }
}
