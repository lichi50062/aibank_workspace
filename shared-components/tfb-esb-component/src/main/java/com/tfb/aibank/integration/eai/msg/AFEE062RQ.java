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

import tw.com.ibm.mf.eb.AFEE062SvcRqType;

// @formatter:off
/**
 * @(#)AFEE062RQ.java
 * 
 * <p>OBU設定基金滿足停損點</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/04, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AFEE062RQ extends EAIRequest<AFEE062SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public AFEE062RQ() {
        super("AFEE062");
    }
}
