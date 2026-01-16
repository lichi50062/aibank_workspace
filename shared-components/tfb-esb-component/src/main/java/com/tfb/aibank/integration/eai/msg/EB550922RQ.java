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

import tw.com.ibm.mf.eb.EB550922SvcRqType;

// @formatter:off
/**
 * @(#)EB550922RQ.java
 * 
 * <p>Description:外幣即時定存解約 上行電文(EB550922)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/11, Leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB550922RQ extends EAIRequest<EB550922SvcRqType> {

    private static final long serialVersionUID = 2045221396858229329L;

    /**
     * 建構子
     */
    public EB550922RQ() {
        super("EB550922");
    }
}
