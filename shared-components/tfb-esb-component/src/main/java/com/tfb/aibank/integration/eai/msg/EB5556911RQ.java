package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB5556911SvcRqType;

// @formatter:off
/**
 * @(#)EB5556911RQ.java
 * 
 * <p>Description:EB5556911 約定轉入帳號 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/04, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB5556911RQ extends EAIRequest<EB5556911SvcRqType> {

    private static final long serialVersionUID = -7484469693995679970L;

    /**
     * 建構子
     */
    public EB5556911RQ() {
        super("EB5556911");
        // 改在底層，只要用這支交易的，統一是2004115
        getHeader().setHTLID("2004115");
    }

}
