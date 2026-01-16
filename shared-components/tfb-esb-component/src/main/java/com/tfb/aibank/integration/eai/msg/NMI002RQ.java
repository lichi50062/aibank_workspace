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

import tw.com.ibm.mf.eb.NMI002SvcRqType;

// @formatter:off
/**
 * @param 
 * @(#)NMI002.java
 * 
 * <p>Description:奈米投契約明細查詢</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/30, Jackie Chien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NMI002RQ extends EAIRequest<NMI002SvcRqType> {

    private static final long serialVersionUID = -6713435336294108087L;

    public NMI002RQ() {
        super("NMI002");
    }

}
