package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.N8031NSvcRqType;

//@formatter:off
/**
* @(#)N8031NRQ.java
* 
* <p>Description: N8031N 海外股票ETF定期定額契約查詢</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/05/27, Edward Tien
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class N8031NRQ extends EAIRequest<N8031NSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public N8031NRQ() {
        super("N8031N");

        // 固定填入，提供AS400判斷格式式用
        getHeader().setHFMTID("0001");
    }
}
