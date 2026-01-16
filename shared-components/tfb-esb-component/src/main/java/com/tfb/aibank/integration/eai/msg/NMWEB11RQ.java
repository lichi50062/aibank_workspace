package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NMWEB11SvcRqType;

// @formatter:off
/**
 * @(#)NMWEB11RQ.java
 * 
 * <p>Description:申請紀錄查詢電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/31, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NMWEB11RQ extends EAIRequest<NMWEB11SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NMWEB11RQ() {
        super("NMWEB11");
    }
}
