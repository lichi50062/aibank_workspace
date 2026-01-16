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

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;

import tw.com.ibm.mf.eb.EBHN002SvcRqType;
import tw.com.ibm.mf.eb.EBHN002SvcRsType;

// @formatter:off
/**
 * @(#)EBHN002RS.java
 * 
 * <p>Description:房貸可增貸額度 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/12  John	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter()
//@formatter:on
public class EBHN002RS extends EAIResponse<EBHN002SvcRqType, EBHN002SvcRsType> {

    /**
     * 建構子
     */
    public EBHN002RS() {
        super("EBHN002");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "E001".equals(errId);
    }
}
