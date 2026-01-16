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

import tw.com.ibm.mf.eb.UK084NSvcRqType;

// @formatter:off
/**
 * @(#)UK084NRQ.java
 * 
 * <p>Description:UK084N 股票資產總覽</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class UK084NRQ extends EAIRequest<UK084NSvcRqType> {

    private static final long serialVersionUID = -8778989082593807359L;

    /**
     * 建構子
     */
    public UK084NRQ() {
        super("UK084N");
    }
}
