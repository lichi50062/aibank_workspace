package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NMWEB04SvcRqType;

// @formatter:off
/**
 * @(#)NMWEB04RQ.java
 *
 * <p>Description:金錢信託定存單明細查詢 NMWEB04</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/07/10, PCY
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NMWEB04RQ extends EAIRequest<NMWEB04SvcRqType> {

    /**
     * 建構子
     */
    public NMWEB04RQ() {
        super("NMWEB04");
    }
}
