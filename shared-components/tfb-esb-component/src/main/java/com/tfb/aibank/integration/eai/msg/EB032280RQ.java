package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB032280SvcRqType;

// @formatter:off
/**
 * @(#)EB032280RQ.java
 * 
 * <p>Description:EB032280 客戶辦理投資有價證券資訊提供聲明書維護</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/30, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB032280RQ extends EAIRequest<EB032280SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public EB032280RQ() {
        super("EB032280");
    }

}
