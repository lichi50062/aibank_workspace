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

import tw.com.ibm.mf.eb.EB312201SvcRqType;

// @formatter:off
/**
 * @(#)EB312201RQ.java
 * 
 * <p>Description:房貸、信貸、留貸變更扣繳帳號(授信帳號基本資料維護) 上行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/05, Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB312201RQ extends EAIRequest<EB312201SvcRqType> {

    /**
     * 建構子
     */
    public EB312201RQ() {
        super("EB312201");
    }
}
