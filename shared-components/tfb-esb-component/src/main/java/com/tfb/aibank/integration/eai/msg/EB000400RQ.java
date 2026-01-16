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

import tw.com.ibm.mf.eb.EB000400SvcRqType;

// @formatter:off
/**
 * @(#)EB000400RQ.java
 * 
 * <p>Description:臺幣活儲即時餘額查詢</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/31, Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB000400RQ extends EAIRequest<EB000400SvcRqType> {

    private static final long serialVersionUID = -3166685447278853447L;

    /**
     * 建構子
     */
    public EB000400RQ() {
        super("EB000400");
    }
}
