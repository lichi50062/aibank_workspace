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

import tw.com.ibm.mf.eb.EB382219SvcRqType;

// @formatter:off
/**
 * @(#)EB382219RQ.java
 * 
 * <p>Description:就貸自動扣繳設定 上行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/05, Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB382219RQ extends EAIRequest<EB382219SvcRqType> {

    /**
     * 建構子
     */
    public EB382219RQ() {
        super("EB382219");
    }
}
