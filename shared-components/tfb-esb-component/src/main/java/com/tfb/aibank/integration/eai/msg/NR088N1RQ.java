package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NR088N1SvcRqType;

//@formatter:off
/**
 * @(#)NR088N1RQ.java
 * 
 * <p>Description: NR088N1 海外股票、ETF總覽明細</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/17, Leiley
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class NR088N1RQ extends EAIRequest<NR088N1SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NR088N1RQ() {
        super("NR088N1");
    }
}
