package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NFEE012SvcRqType;

// @formatter:off
/**
 * @(#)NFEE012RQ.java
 *
 * <p>Description:NFEE012 首購狀態查詢</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/10/17, MP
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NFEE012RQ extends EAIRequest<NFEE012SvcRqType> {
    /**
     * 建構子
     */
    public NFEE012RQ() {
        super("NFEE012");
        this.getHeader().setHDRVQ1("NFWEBHQ");
    }
}
