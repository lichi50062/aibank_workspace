package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.N8088N1SvcRqType;

//@formatter:off
/**
 * @(#)N8088N1RQ.java
 * 
 * <p>Description: N8088N1 海外股票、ETF定期定額總覽明細</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/18, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class N8088N1RQ extends EAIRequest<N8088N1SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public N8088N1RQ() {
        super("N8088N1");

        // 固定填入，提供AS400判斷格式式用
        getHeader().setHFMTID("0001");
    }
}
