package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NMWEB08SvcRqType;

// @formatter:off
/**
 * @(#)NMWEB08RQ.java
 *
 * <p>金錢信託-受款人查詢電文(NMWEB08RQ)</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/07/09, PCY
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NMWEB08RQ extends EAIRequest<NMWEB08SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NMWEB08RQ() {
        super("NMWEB08");
    }
}
