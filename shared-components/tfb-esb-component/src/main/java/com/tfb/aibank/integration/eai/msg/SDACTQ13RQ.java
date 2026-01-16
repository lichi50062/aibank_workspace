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

import tw.com.ibm.mf.eb.SDACTQ13SvcRqType;

// @formatter:off
/**
 * @(#)SDACTQ13RQ.java
 * 
 * <p>Description:SDACTQ13RQ 信託資產_SI產品明細</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/08, Andy Kuo
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class SDACTQ13RQ extends EAIRequest<SDACTQ13SvcRqType> {

    private static final long serialVersionUID = -2986617928427103023L;

    /**
     * 建構子
     */
    public SDACTQ13RQ() {
        super("SDACTQ13");
    }
}
