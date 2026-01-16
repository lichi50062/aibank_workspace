package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NJWEE071SvcRqType;

// @formatter:off
/**
 * @(#)NJWEE071RQ.java
 * 
 * <p>Description:債券預約單長效單委託交易查詢-明細</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/11, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NJWEE071RQ extends EAIRequest<NJWEE071SvcRqType> {

    private static final long serialVersionUID = 1063018024866250493L;

    /**
     * 建構子
     */
    public NJWEE071RQ() {
        super("NJWEE071");
    }

}
