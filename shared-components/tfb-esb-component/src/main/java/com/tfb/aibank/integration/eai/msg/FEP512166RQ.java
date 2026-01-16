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

import tw.com.ibm.mf.eb.FEP512166SvcRqType;

// @formatter:off
/**
 * @(#)FEP512166RQ.java
 * 
 * <p>Description:客戶分群跨行手續費優惠查詢 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FEP512166RQ extends EAIRequest<FEP512166SvcRqType> {

    private static final long serialVersionUID = -5042326243706840355L;

    /**
     * 建構子
     */
    public FEP512166RQ() {
        super("FEP512166");
    }
}
