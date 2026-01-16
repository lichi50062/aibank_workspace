package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NR048NSvcRqType;

//@formatter:off
/**
* @(#)NR048NRQ.java
* 
* <p>NR048NRQ 股票歷史交易明細</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/02/19, Leiley    
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NR048NRQ extends EAIRequest<NR048NSvcRqType> {

    /**
     * 建構子
     */
    public NR048NRQ() {
        super("NR048N");
    }
}
