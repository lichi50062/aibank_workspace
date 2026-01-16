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

import tw.com.ibm.mf.eb.EB12010029SvcRqType;

// @formatter:off
/**
 * @(#)EB12010029RQ.java
 * 
 * <p>Description:EB12010029 約定自動扣繳設定(國民年金)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/12, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB12010029RQ extends EAIRequest<EB12010029SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public EB12010029RQ() {
        super("EB12010029");
    }
}
