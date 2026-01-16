package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NR065NSvcRqType;

//@formatter:off
/**
* @(#)NR065NRQ.java
* 
* <p>Description: 海外股票特別股說明書名單 NR065N 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/08, Evan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NR065NRQ extends EAIRequest<NR065NSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NR065NRQ() {
        super("NR065N");
    }
}
