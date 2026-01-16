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

import tw.com.ibm.mf.eb.OA000403SvcRqType;

// @formatter:off
/**
 * @(#)OA000403RQ.java
 * 
 * <p>Description:OA000403RQ 查詢本行戶名</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/07/24, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class OA000403RQ extends EAIRequest<OA000403SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public OA000403RQ() {
        super("OA000403");
    }

}
