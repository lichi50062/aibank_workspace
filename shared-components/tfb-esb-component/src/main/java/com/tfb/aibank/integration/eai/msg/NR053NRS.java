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

import com.tfb.aibank.integration.eai.EAIResponse;

import tw.com.ibm.mf.eb.NR053NSvcRqType;
import tw.com.ibm.mf.eb.NR053NSvcRsType;

// @formatter:off
/**
 * @(#)NR053NRS.java
 * 
 * <p>Description:海外股票ETF新增股務回覆結果</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/18, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NR053NRS extends EAIResponse<NR053NSvcRqType, NR053NSvcRsType> {
    /**
     * 建構子
     */
    public NR053NRS() {
        super("NR053N");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }

}
