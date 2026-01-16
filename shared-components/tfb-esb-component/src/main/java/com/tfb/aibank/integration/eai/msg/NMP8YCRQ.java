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

import tw.com.ibm.mf.eb.NMP8YCSvcRqType;

// @formatter:off
/**
 * @param 
 * @(#)NMP8YC.java
 * 
 * <p>Description:奈米投契約庫存查詢</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/30, Jackie Chien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NMP8YCRQ extends EAIRequest<NMP8YCSvcRqType> {

    private static final long serialVersionUID = -3474855593582608576L;

    public NMP8YCRQ() {
        super("NMP8YC");
    }

}
