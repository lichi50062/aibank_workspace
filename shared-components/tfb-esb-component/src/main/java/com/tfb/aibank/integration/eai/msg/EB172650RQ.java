package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB172650SvcRqType;

// @formatter:off
/**
 * @(#)EB172650RQ.java
 * 
 * <p>Description:查詢ID</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/30, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB172650RQ extends EAIRequest<EB172650SvcRqType> {

    private static final long serialVersionUID = 2707310937999922184L;

    /**
     * 建構子
     */
    public EB172650RQ() {
        super("EB172650");
    }
}
