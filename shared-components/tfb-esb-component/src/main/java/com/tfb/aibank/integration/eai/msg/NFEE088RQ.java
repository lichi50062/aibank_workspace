package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NFEE088SvcRqType;

//@formatter:off
/**
* @(#)NFEE088RQ.java
* 
* <p>Description: NFEE088 基金網行銀在途交易取消紀錄查詢</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/05/13, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NFEE088RQ extends EAIRequest<NFEE088SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NFEE088RQ() {
        super("NFEE088");
    }
}
