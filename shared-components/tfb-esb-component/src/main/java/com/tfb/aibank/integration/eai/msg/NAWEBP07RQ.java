package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NAWEBP07SvcRqType;

// @formatter:off
/**
 * @(#)NAWEBP07RQ.java
 * 
 * <p>Description:NAWEBP07 持股表尾累計查詢</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/19, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NAWEBP07RQ extends EAIRequest<NAWEBP07SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NAWEBP07RQ() {
        super("NAWEBP07");
    }
}
