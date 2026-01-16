package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NR070NSvcRqType;

//@formatter:off
/**
* @(#)NR070NRQ.java
* 
* <p>Description: NR070N 海外股票ETF可用金額查詢上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/26, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/

//@formatter:on
public class NR070NRQ extends EAIRequest<NR070NSvcRqType> {

    /**
     * 建構子
     */
    public NR070NRQ() {
        super("NR070N");
    }

}
