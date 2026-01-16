package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.N8033NSvcRqType;

//@formatter:off
/**
* @(#)N8033NRQ.java
* 
* <p>Description: N8033N 海外股票ETF定期定額契約編號查詢</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/05/27, Edward Tien
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class N8033NRQ extends EAIRequest<N8033NSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public N8033NRQ() {
        super("N8033N");

        // 固定填入，提供AS400判斷格式式用
        getHeader().setHFMTID("0001");
    }
}
