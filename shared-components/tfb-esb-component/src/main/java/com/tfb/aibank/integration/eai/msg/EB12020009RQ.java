package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB12020009SvcRqType;

//@formatter:off
/**
* @(#)EB12020009RS.java
* 
* <p>Description: 預借現金交易上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/02, John
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EB12020009RQ extends EAIRequest<EB12020009SvcRqType> {

    private static final long serialVersionUID = -3039808351537167266L;

    /**
     * 建構子
     */
    public EB12020009RQ() {
        super("EB12020009");
    }

}
