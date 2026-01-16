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

import tw.com.ibm.mf.eb.EB032154SvcRqType;

// @formatter:off
/**
 * @(#)EB032154RQ.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB032154RQ extends EAIRequest<EB032154SvcRqType> {

    private static final long serialVersionUID = -1473520781746504934L;

    /**
     * 建構子
     */
    public EB032154RQ() {
        super("EB032154");
    }
}
