package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.CEW332RSvcRqType;

//@formatter:off
/**
* @(#)CEW332RRQ.java
* 
* <p>Description:CEW332R 歸戶下附卡查詢</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/25, Aaron
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW332RRQ extends EAIRequest<CEW332RSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public CEW332RRQ() {
        super("CEW332R");
    }
}
