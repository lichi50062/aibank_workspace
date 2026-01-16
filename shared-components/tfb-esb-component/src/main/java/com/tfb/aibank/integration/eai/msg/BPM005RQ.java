package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIOverviewRequest;

import tw.com.ibm.mf.eb.BPM005SvcRqType;

//@formatter:off
/**
* @(#)BPM005RQ.java
* 
* <p>Description: 資產總覽電文 BPM005(上行)</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/02/29, Jojo Wei
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class BPM005RQ extends EAIOverviewRequest<BPM005SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public BPM005RQ() {
        super("BPM005");
    }
}
