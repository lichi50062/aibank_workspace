package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NFEE085SvcRqType;

// @formatter:off
/**
 * @(#)NFEE085RQ.java
 * 
 * <p>Description:NFEE085 基金觀察到價查詢</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/30, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NFEE085RQ extends EAIRequest<NFEE085SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NFEE085RQ() {
        super("NFEE085");
    }
}
