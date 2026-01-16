package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NJW086SvcRqType;

//@formatter:off
/**
* @(#)NJW086RQ.java
* 
* <p>Description: 債券行銀W-8BEN交易 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/02/15, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NJW086RQ extends EAIRequest<NJW086SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NJW086RQ() {
        super("NJW086");
    }
}
