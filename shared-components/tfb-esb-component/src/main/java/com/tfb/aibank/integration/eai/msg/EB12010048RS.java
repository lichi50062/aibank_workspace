package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;

import tw.com.ibm.mf.eb.EB12010048SvcRqType;
import tw.com.ibm.mf.eb.EB12010048SvcRsType;

//@formatter:off
/**
* @(#)EB12020024RS.java
* 
* <p>Description:EB12010048 網銀查詢及登錄證券綜合戶況</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/22, Evan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EB12010048RS extends EAIResponse<EB12010048SvcRqType, EB12010048SvcRsType> {
    /**
     * 建構子
     */
    public EB12010048RS() {
        super("EB12010048");
    }

}
