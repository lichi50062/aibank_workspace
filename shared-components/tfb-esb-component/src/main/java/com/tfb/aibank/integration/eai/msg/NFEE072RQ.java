package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NFEE072SvcRqType;

// @formatter:off
/**
 * @(#)NFEE072RQ.java
 * 
 * <p>Description:NFEE072 基金帳戶總覽</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/30, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NFEE072RQ extends EAIRequest<NFEE072SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NFEE072RQ() {
        super("NFEE072");
    }

}
