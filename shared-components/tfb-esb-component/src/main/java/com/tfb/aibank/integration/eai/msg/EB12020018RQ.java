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

import tw.com.ibm.mf.eb.EB12020018SvcRqType;

// @formatter:off
/**
 * @(#)EB12020018RS.java
 * 
 * <p>Description: 數位活儲設定的上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/07, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB12020018RQ extends EAIRequest<EB12020018SvcRqType> {

    private static final long serialVersionUID = 6035361056548671956L;

    /**
     * 建構子
     */
    public EB12020018RQ() {
        super("EB12020018");
    }
}
