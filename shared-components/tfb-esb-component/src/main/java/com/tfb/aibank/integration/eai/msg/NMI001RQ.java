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

import tw.com.ibm.mf.eb.NMI001SvcRqType;

// @formatter:off
/**
 * @param 
 * @(#)NMI001.java
 * 
 * <p>Description:個人風險屬_標籤</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/30, Jackie Chien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NMI001RQ extends  EAIRequest<NMI001SvcRqType> {

    private static final long serialVersionUID = -5065686538590964294L;

    public NMI001RQ() {
        super("NMI001");
    }

}
