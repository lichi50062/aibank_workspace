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

import tw.com.ibm.mf.eb.VN083NSvcRqType;

// @formatter:off
/**
 * @(#)VN083NRQ.java
 * 
 * <p>VN083N 預約交易取消 上行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/06, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class VN083NRQ extends EAIRequest<VN083NSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public VN083NRQ() {
        super("VN083N");
    }
}
