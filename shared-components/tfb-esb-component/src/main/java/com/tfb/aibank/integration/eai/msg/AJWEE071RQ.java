package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.AJWEE071SvcRqType;

// @formatter:off
/**
 * @(#)AJWEE071RQ.java
 * 
 * <p>Description:OBU債券預約單長效單委託交易查詢-明細</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/29, Rong Gang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AJWEE071RQ extends EAIRequest<AJWEE071SvcRqType> {

    private static final long serialVersionUID = 1063018024866250493L;

    /**
     * 建構子
     */
    public AJWEE071RQ() {
        super("AJWEE071");
    }

}
