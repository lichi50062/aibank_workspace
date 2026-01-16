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

import tw.com.ibm.mf.eb.EB5556982SvcRqType;

// @formatter:off
/**
 * @(#)EB5556982RQ.java
 * 
 * <p>Description:檢核單一戶名</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB5556982RQ extends EAIRequest<EB5556982SvcRqType> {

    private static final long serialVersionUID = 2718547727158407907L;

    /**
     * 建構子
     */
    public EB5556982RQ() {
        super("EB5556982");
    }
}
