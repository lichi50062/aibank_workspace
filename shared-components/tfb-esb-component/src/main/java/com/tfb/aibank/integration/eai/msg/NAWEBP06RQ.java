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

import tw.com.ibm.mf.eb.NAWEBP06SvcRqType;

// @formatter:off
/**
 * @(#)NAWEBP06RQ.java
 * 
 * <p>限制型股票返還詳細資訊查詢</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/15, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NAWEBP06RQ extends EAIRequest<NAWEBP06SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NAWEBP06RQ() {
        super("NAWEBP06");
    }

}
