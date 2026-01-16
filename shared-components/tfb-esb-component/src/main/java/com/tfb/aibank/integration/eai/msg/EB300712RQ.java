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

import tw.com.ibm.mf.eb.EB300712SvcRqType;

// @formatter:off
/**
 * @(#)EB362611RQ.java
 * 
 * <p>Description:查詢貸款繳費明細(貸款類型=房貸(61)-02公教/03國宅)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/05, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB300712RQ extends EAIRequest<EB300712SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public EB300712RQ() {
        super("EB300712");
    }

}
