package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.RTWEBP01SvcRqType;

//@formatter:off
/**
* @(#)RTWEBP01RQ.java
* 
* <p>Description: RTWEBP01 儲蓄信託明細</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/08/14, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class RTWEBP01RQ extends EAIRequest<RTWEBP01SvcRqType> {

    /**
     * 建構子
     */
    public RTWEBP01RQ() {
        super("RTWEBP01");
        this.getHeader().setHDRVQ1("NFWEBHQ");
    }
}
