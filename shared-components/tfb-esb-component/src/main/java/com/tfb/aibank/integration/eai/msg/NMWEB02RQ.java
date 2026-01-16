package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NMWEB02SvcRqType;

// @formatter:off
/**
 * @(#)NMWEB02RQ.java
 *
 * <p>Description:金錢信託關係人查詢 NMWEB02</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/07/10, PCY
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NMWEB02RQ extends EAIRequest<NMWEB02SvcRqType> {

    /**
     * 建構子
     */
    public NMWEB02RQ() {
        super("NMWEB02");
    }
}
