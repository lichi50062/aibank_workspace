package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NR063NSvcRqType;

//@formatter:off
/**
 * @(#)NR063NRQ.java
 * 
 * <p>Description: NR063N 海外股票、ETF到價通知名單</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/17, Leiley
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class NR063NRQ extends EAIRequest<NR063NSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NR063NRQ() {
        super("NR063N");
    }
}
