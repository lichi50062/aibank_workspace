package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.CEW328RSvcRqType;

//@formatter:off
/**
* @(#)CEW328RRQ.java
* 
* <p>Description:保費權益設定-變更 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/27, Edward Tien    
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW328RRQ extends EAIRequest<CEW328RSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public CEW328RRQ() {
        super("CEW328R");
    }

}
