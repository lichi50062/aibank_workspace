package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NMWEB03SvcRqType;

// @formatter:off
/**
 * @(#)NMWEB03RQ.java
 *
 * <p>Description:金錢信託定存單臺幣現值查詢 NMWEB03</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/07/10, PCY
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NMWEB03RQ extends EAIRequest<NMWEB03SvcRqType> {

    /**
     * 建構子
     */
    public NMWEB03RQ() {
        super("NMWEB03");
    }
}
