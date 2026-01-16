package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NFEE074SvcRqType;


// @formatter:off
/**
 * @(#)NFEE074RQ.java
 *
 * <p>Description:金錢基金台幣總數 NFEE074RQ</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/07/10, PCY
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NFEE074RQ extends EAIRequest<NFEE074SvcRqType> {

    /**
     * 建構子
     */
    public NFEE074RQ() {
        super("NFEE074");
    }

}
