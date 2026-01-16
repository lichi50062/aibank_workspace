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

import tw.com.ibm.mf.eb.NMP8YDSvcRqType;

// @formatter:off
/**
 * @param 
 * @(#)NMP8YD.java
 * 
 * <p>Description:契約交易明細查詢 </p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/30, Jackie Chien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NMP8YDRQ extends EAIRequest<NMP8YDSvcRqType> {

    private static final long serialVersionUID = -3535724651879487206L;

    public NMP8YDRQ() {
        super("NMP8YD");
    }

}
