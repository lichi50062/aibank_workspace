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

import tw.com.ibm.mf.eb.VN084NSvcRqType;

// @formatter:off
/**
 * @(#)VN084NRQ.java
 * 
 * <p>Description:VN084N 基金資產總覽</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class VN084NRQ extends EAIRequest<VN084NSvcRqType> {

    private static final long serialVersionUID = 4036144906738500120L;

    /**
     * 建構子
     */
    public VN084NRQ() {
        super("VN084N");
    }
}
