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

import tw.com.ibm.mf.eb.BKDCD002SvcRqType;

// @formatter:off
/**
 * @(#)BKDCD002RQ.java
 * 
 * <p>Description:BKDCD002 組合式商品資產總覽(DCI)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BKDCD002RQ extends EAIRequest<BKDCD002SvcRqType> {

    private static final long serialVersionUID = -4860638249230153548L;

    /**
     * 建構子
     */
    public BKDCD002RQ() {
        super("BKDCD002");
    }
}
