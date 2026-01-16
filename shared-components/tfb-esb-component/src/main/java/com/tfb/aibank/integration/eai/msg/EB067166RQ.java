package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB067166SvcRqType;

// @formatter:off
/**
 * @(#)EB067166RQ.java
 * 
 * <p>Description:EB067166 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/12, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB067166RQ extends EAIRequest<EB067166SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public EB067166RQ() {
        super("EB067166");
    }
}
