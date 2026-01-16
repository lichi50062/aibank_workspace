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

import com.tfb.aibank.integration.eai.EAIResponse;

import tw.com.ibm.mf.eb.EB601191SvcRqType;
import tw.com.ibm.mf.eb.EB601191SvcRsType;

// @formatter:off
/**
 * @(#)EB601191RS.java
 * 
 * <p>Description:悠遊卡綁定與查詢 下行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/03, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB601191RS extends EAIResponse<EB601191SvcRqType, EB601191SvcRsType> {

    /**
     * 建構子
     */
    public EB601191RS() {
        super("EB601191");
    }
}
