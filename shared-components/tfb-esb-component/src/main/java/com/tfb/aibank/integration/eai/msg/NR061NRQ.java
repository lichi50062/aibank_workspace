package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NR061NSvcRqType;

// @formatter:off
/**
 * @(#)NR061NRQ.java
 * 
 * <p>Description:NR061N 海外股票ETF停損停利</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/27, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NR061NRQ extends EAIRequest<NR061NSvcRqType> {

    private static final long serialVersionUID = 4415608491298120271L;

    /**
     * 建構子
     */
    public NR061NRQ() {
        super("NR061N");

        // 固定填入，提供AS400判斷格式式用
        getHeader().setHFMTID("0001");
    }
}
