package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NFEE073SvcRqType;

// @formatter:off
/**
 * @(#)NFEE073RQ.java
 *
 * <p>Description:金錢基金帳戶總覽 NFEE073RQ</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/07/10, PCY
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NFEE073RQ extends EAIRequest<NFEE073SvcRqType> {

    /**
     * 建構子
     */
    public NFEE073RQ() {
        super("NFEE073");
    }

}
