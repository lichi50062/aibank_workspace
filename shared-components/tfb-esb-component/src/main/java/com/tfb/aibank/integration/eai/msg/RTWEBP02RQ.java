package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.RTWEBP02SvcRqType;

//@formatter:off
/**
* @(#)RTWEBP02RQ.java
* 
* <p>Description: RTWEBP02 員工持股信託公司查詢 上行</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/08/14, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class RTWEBP02RQ extends EAIRequest<RTWEBP02SvcRqType> {

    /**
     * 建構子
     */
    public RTWEBP02RQ() {
        super("RTWEBP02");
        this.getHeader().setHDRVQ1("NFWEBHQ");
    }
    
}
