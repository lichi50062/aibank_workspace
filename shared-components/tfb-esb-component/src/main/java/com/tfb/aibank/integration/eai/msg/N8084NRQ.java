package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.N8084NSvcRqType;

//@formatter:off
/**
 * @(#)N8084NRQ.java
 * 
 * <p>Description: N8084N 信託海外股票－台幣現值總計</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/02, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class N8084NRQ extends EAIRequest<N8084NSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public N8084NRQ() {
        super("N8084N");

        // 固定填入，提供AS400判斷格式式用
        getHeader().setHFMTID("0001");
    }
}
