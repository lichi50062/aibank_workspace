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

import tw.com.ibm.mf.eb.NMP8YBSvcRqType;

// @formatter:off
/**
 * @(#)NMP8YBRQ.java
 * 
 * <p>Description:NMP8YB 奈米投電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NMP8YBRQ extends EAIRequest<NMP8YBSvcRqType> {

    private static final long serialVersionUID = 1071437069987049053L;

    /**
     * 建構子
     */
    public NMP8YBRQ() {
        super("NMP8YB");
    }
}
