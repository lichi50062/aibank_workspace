package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NMWEB13SvcRqType;

// @formatter:off
/**
 * @(#)NMWEB13RQ.java
 * 
 * <p>Description:申請紀錄有權指示人查詢電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/31, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NMWEB13RQ extends EAIRequest<NMWEB13SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NMWEB13RQ() {
        super("NMWEB13");
    }
}
