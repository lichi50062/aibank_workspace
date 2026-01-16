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

import tw.com.ibm.mf.eb.NJWEEN02SvcRqType;

// @formatter:off
/**
 * @(#)NJWEEN02RQ.java
 * 
 * <p>Description:金錢信託債券網銀台幣總值DBU</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/09, Charlie Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NJWEEN02RQ extends EAIRequest<NJWEEN02SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NJWEEN02RQ() {
        super("NJWEEN02");
    }
}
