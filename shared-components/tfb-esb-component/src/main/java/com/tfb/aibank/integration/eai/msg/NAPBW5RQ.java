package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NAPBW5SvcRqType;

// @formatter:off
/**
 * @(#)NAPBW5RQ.java
 * 
 * <p>Description:NAPBW5 </p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/19, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NAPBW5RQ extends EAIRequest<NAPBW5SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NAPBW5RQ() {
        super("NAPBW5");
    }
}
