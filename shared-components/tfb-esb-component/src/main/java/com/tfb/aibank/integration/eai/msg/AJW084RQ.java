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

import tw.com.ibm.mf.eb.AJW084SvcRqType;

// @formatter:off
/**
 * @(#)AJW084RQ.java
 * 
 * <p>Description:AJW084 債券OBU資產總覽</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AJW084RQ extends EAIRequest<AJW084SvcRqType> {

    private static final long serialVersionUID = -6837310592924007609L;

    /**
     * 建構子
     */
    public AJW084RQ() {
        super("AJW084");
    }
}
