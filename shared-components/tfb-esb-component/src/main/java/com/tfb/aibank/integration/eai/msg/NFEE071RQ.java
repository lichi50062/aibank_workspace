package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NFEE071SvcRqType;

// @formatter:off
/**
 * @(#)NFEE071RQ.java
 * 
 * <p>Description:NFEE071 基金OBU帳戶總覽</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/30, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NFEE071RQ extends EAIRequest<NFEE071SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NFEE071RQ() {
        super("NFEE071");
    }
}
