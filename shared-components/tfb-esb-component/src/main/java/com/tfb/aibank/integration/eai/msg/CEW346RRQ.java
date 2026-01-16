package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.CEW346RSvcRqType;

//@formatter:off
/**
* @(#)CEW346RRQ.java
* 
* <p>Description:保費權益設定-變更 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/02/10, LEIPING   
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW346RRQ extends EAIRequest<CEW346RSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public CEW346RRQ() {
        super("CEW346R");
    }

}
