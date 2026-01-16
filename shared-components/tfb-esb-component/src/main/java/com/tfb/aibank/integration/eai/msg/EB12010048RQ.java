package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB12010048SvcRqType;

//@formatter:off
/**
* @(#)EB12010048RQ.java
* 
* <p>Description:EB12010048 網銀查詢及登錄證券綜合戶況</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/08/01, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EB12010048RQ extends EAIRequest<EB12010048SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public EB12010048RQ() {
        super("EB12010048");
    }

}
