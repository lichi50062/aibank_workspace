package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NKNE01SvcRqType;

// @formatter:off
/**
 * @(#)NKNE01RQ.java
 * 
 * <p>Description:股票客戶查詢上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/13, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NKNE01RQ extends EAIRequest<NKNE01SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NKNE01RQ() {
        super("NKNE01");
    }
}
