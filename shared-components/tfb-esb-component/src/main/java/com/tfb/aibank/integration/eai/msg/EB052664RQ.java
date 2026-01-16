package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB052664SvcRqType;

//@formatter:off
/**
* @(#)EB052664RQ.java
* 
* <p>Description:房貸變更扣繳帳號註記查詢 上行</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/06/04, Warren Lin
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EB052664RQ extends EAIRequest<EB052664SvcRqType> {
    
    /**
     * 建構子
     */
    public EB052664RQ() {
        super("EB052664");
    }
}
