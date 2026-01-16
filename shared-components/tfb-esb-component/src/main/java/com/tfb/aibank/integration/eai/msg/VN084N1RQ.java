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

import tw.com.ibm.mf.eb.VN084N1SvcRqType;

// @formatter:off
/**
 * @(#)VN084N1RQ.java
 * 
 * <p>Description:VN084N 基金OBU資產總覽</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class VN084N1RQ extends EAIRequest<VN084N1SvcRqType> {

    private static final long serialVersionUID = -4538697559501572308L;

    /**
     * 建構子
     */
    public VN084N1RQ() {
        super("VN084N1");
    }
}
