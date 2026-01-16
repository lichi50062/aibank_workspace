package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB069024SvcRqType;

//@formatter:off
/**
* @(#)EB069024RQ.java
* 
* <p>Description:每月銀行作業優惠</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/08/07, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EB069024RQ extends EAIRequest<EB069024SvcRqType> {
    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public EB069024RQ() {
        super("EB069024");
    }
}