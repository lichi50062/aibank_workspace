package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB12020024SvcRqType;

//@formatter:off
/**
* @(#)EB12020024RQ.java
* 
* <p>Description:EB12020024 Email資料重複判斷</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/22, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EB12020024RQ extends EAIRequest<EB12020024SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
	public EB12020024RQ() {
		super("EB12020024");
	}

}
