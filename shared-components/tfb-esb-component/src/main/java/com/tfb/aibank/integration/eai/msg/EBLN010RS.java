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

import tw.com.ibm.mf.eb.EBLN010SvcRqType;
import tw.com.ibm.mf.eb.EBLN010SvcRsType;

// @formatter:off
/**
 * @(#)EBLN010RS.java
 * 
 * <p>Description:檢核是否符合速貸通資格 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/12  John	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter()
//@formatter:on
public class EBLN010RS extends EAIResponse<EBLN010SvcRqType, EBLN010SvcRsType> {

    /**
     * 建構子
     */
    public EBLN010RS() {
        super("EBLN010");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "E001".equals(errId);
    }
}
