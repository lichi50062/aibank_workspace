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

import tw.com.ibm.mf.eb.NMI003SvcRqType;

// @formatter:off
/**
 * @param 
 * @(#)NMI003.java
 * 
 * <p>Description:奈米投契約交易紀錄查詢</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/30, Jackie Chien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NMI003RQ extends EAIRequest<NMI003SvcRqType> {

    private static final long serialVersionUID = 7778561836623616402L;

    public NMI003RQ() {
        super("NMI003");
    }

}
