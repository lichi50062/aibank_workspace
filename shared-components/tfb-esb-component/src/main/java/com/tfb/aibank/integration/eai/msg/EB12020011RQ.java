package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB12020011SvcRqType;

//@formatter:off
/**
* @(#)EB12020018RS.java
* 
* <p>Description: 數位存款開戶上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/15, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EB12020011RQ extends EAIRequest<EB12020011SvcRqType> {

    private static final long serialVersionUID = -6517113747804950955L;

    /**
     * 建構子
     */
    public EB12020011RQ() {
        super("EB12020011");
    }

}
