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

import com.tfb.aibank.integration.eai.EAIResponse;

import tw.com.ibm.mf.eb.EB032655SvcRqType;
import tw.com.ibm.mf.eb.EB032655SvcRsType;

// @formatter:off
/**
 * @(#)EB032655RS.java
 * 
 * <p>Description:EB032655 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB032655RS extends EAIResponse<EB032655SvcRqType, EB032655SvcRsType> {

    /**
     * 建構子
     */
    public EB032655RS() {
        super("EB032655");
    }
}
