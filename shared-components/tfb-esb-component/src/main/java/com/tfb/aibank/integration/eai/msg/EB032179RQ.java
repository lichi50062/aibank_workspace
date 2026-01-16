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

import tw.com.ibm.mf.eb.EB032179SvcRqType;

// @formatter:off
/**
 * @(#)EB032179RQ.java
 * 
 * <p>Description:優惠次數查詢╱修改上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/28, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB032179RQ extends EAIRequest<EB032179SvcRqType> {

    private static final long serialVersionUID = 7146757380474594557L;

    /**
     * 建構子
     */
    public EB032179RQ() {
        super("EB032179");
    }
}
