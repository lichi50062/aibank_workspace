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

import tw.com.ibm.mf.eb.SPWEBQ1SvcRqType;

// @formatter:off
/**
 * @(#)SPWEBQ1RQ.java
 * 
 * <p>Description:SPWEBQ1 組合式商品資產總覽(AS400 DCI+SI)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class SPWEBQ1RQ extends EAIRequest<SPWEBQ1SvcRqType> {

    private static final long serialVersionUID = -3985701442432291295L;

    /**
     * 建構子
     */
    public SPWEBQ1RQ() {
        super("SPWEBQ1");
    }
}
