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

import tw.com.ibm.mf.eb.FEP512161SvcRqType;

// @formatter:off
/**
 * @(#)FEP512161RQ.java
 * 
 * <p>Description:自動化手續費優惠查詢 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FEP512161RQ extends EAIRequest<FEP512161SvcRqType> {

    private static final long serialVersionUID = -2184938371426802297L;

    /**
     * 建構子
     */
    public FEP512161RQ() {
        super("FEP512161");
    }
}
