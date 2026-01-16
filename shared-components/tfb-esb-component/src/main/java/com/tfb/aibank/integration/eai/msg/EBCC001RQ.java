package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EBCC001SvcRqType;

// @formatter:off
/**
 * @(#)EBCC001RQ.java
 * 
 * <p>Description:EBCC001 固定調額啟案檢核</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/17, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EBCC001RQ extends EAIRequest<EBCC001SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public EBCC001RQ() {
        super("EBCC001");
    }
}
