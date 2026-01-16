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

import tw.com.ibm.mf.eb.CEW227RSvcRqType;
import tw.com.ibm.mf.eb.CEW227RSvcRsType;

// @formatter:off
/**
 * @(#)CEW227RRS.java
 * 
 * <p>Description: 取得簽訂約定書註記</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/02, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CEW227RRS extends EAIResponse<CEW227RSvcRqType, CEW227RSvcRsType> {

    /**
     * @param txnId
     */
    public CEW227RRS() {
        super("CEW227R");
    }

}
