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

import tw.com.ibm.mf.eb.CEW213RSvcRqType;
import tw.com.ibm.mf.eb.CEW213RSvcRsType;

// @formatter:off
/**
 * @(#)CEW213RRS.java
 * 
 * <p>Description:CEW213R 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/17, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CEW213RRS extends EAIResponse<CEW213RSvcRqType, CEW213RSvcRsType> {

    /**
     * 建構子
     */
    public CEW213RRS() {
        super("CEW213R");
    }

}
