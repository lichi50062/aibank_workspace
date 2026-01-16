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

import tw.com.ibm.mf.eb.CE6210RSvcRqType;

// @formatter:off
/**
 * @(#)CE6210RRQ.java
 * 
 * <p>Description:客戶資料查詢上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CE6210RRQ extends EAIRequest<CE6210RSvcRqType> {

    private static final long serialVersionUID = -316632841179348713L;

    /**
     * 建構子
     */
    public CE6210RRQ() {
        super("CE6210R");
    }

}
