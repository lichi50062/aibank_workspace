package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NAPBW6SvcRqType;

// @formatter:off
/**
 * @(#)NAPBW6RQ.java
 * 
 * <p>Description:NAPBW6 查詢公司名稱</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/19, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NAPBW6RQ extends EAIRequest<NAPBW6SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NAPBW6RQ() {
        super("NAPBW6");
    }
}
