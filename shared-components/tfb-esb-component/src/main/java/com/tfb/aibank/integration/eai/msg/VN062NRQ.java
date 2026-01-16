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

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.VN062NSvcRqType;

// @formatter:off
/**
 * @(#)VN062NRQ.java
 * 
 * <p>DBU設定基金滿足停損點</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/01, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class VN062NRQ extends EAIRequest<VN062NSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public VN062NRQ() {
        super("VN062N");
    }
}
