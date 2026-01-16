package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.AML002SvcRqType;

// @formatter:off
/**
 * @(#)AML002RQ.java
 * 
 * <p>Description:虛擬貨幣交易平台之境外帳戶查詢 AML002 查詢上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/07, Edward	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AML002RQ extends EAIRequest<AML002SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public AML002RQ() {
        super("AML002");
    }
}
