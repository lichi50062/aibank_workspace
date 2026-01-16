package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB032151SvcRqType;

//@formatter:off
/**
* @(#)EB032151RQ.java
* 
* <p>Description: EB032151客戶基本資料維護(非臨櫃)上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/02/15, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EB032151RQ extends EAIRequest<EB032151SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public EB032151RQ() {
        super("EB032151");
    }

}
