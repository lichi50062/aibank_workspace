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

import tw.com.ibm.mf.eb.CEW209RSvcRqType;

// @formatter:off
/**
 * @(#)CEW209RRQ.java
 * 
 * <p>Description: 信用卡Email設定上行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/10, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CEW209RRQ extends EAIRequest<CEW209RSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public CEW209RRQ() {
        super("CEW209R");
    }

}
