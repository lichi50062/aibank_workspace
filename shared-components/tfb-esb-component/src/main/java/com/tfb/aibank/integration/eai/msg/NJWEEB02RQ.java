package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NJWEEB02SvcRqType;

// @formatter:off
/**
 * @(#)NJWEEB02RQ.java
 * 
 * <p>Description:我的觀察債券查詢 NJWEEB02</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/14, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NJWEEB02RQ extends EAIRequest<NJWEEB02SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NJWEEB02RQ() {
        super("NJWEEB02");
    }

}
