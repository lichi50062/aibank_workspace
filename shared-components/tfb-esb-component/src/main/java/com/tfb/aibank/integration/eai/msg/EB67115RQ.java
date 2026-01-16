package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB67115SvcRqType;


///@formatter:off
/**
* @(#)EB67115RQ.java
* 
* <p>Description:EB67115 取得客戶是否具備FATCA排外註記上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/05/02, xwr
* <ol>
*  <li>初版</li>
* </ol>
*/
public class EB67115RQ extends EAIRequest<EB67115SvcRqType> {
    /**
     * 建構子
     */
    public EB67115RQ() {
        super("EB67115");
    }
}