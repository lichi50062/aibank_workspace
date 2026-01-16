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

import tw.com.ibm.mf.eb.NJW084SvcRqType;

// @formatter:off
/**
 * @(#)NJW084RQ.java
 * 
 * <p>Description:NJW084 債券資產總覽</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NJW084RQ extends EAIRequest<NJW084SvcRqType> {

    private static final long serialVersionUID = 6890834125328045858L;

    /**
     * 建構子
     */
    public NJW084RQ() {
        super("NJW084");
    }
}
