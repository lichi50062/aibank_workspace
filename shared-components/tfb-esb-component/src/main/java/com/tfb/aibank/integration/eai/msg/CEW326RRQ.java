package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.CEW326RSvcRqType;

//@formatter:off
/**
* @(#)CEW326RRQ.java
* 
* <p>Description:分期未入帳金額查詢</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/02, Evan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW326RRQ extends EAIRequest<CEW326RSvcRqType> {
    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public CEW326RRQ() {
        super("CEW326R");
    }

}
