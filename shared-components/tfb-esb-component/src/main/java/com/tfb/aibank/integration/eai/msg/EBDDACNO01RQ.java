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

import tw.com.ibm.mf.eb.EBDDACNO01SvcRqType;

// @formatter:off
/**
 * @(#)EBDDACNO01RQ.java
 * 
 * <p>Description:數位存款帳戶金融卡開卡電文 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/28, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EBDDACNO01RQ extends EAIRequest<EBDDACNO01SvcRqType> {
    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public EBDDACNO01RQ() {
        super("EBDDACNO01");
    }

}
