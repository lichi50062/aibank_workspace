package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;

import tw.com.ibm.mf.eb.AML002SvcRqType;
import tw.com.ibm.mf.eb.AML002SvcRsType;

// @formatter:off
/**
 * @(#)AML002RS.java
 * 
 * <p>Description:虛擬貨幣交易平台之境外帳戶查詢 AML002 查詢下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/07, Edward	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AML002RS extends EAIResponse<AML002SvcRqType, AML002SvcRsType> {

    /**
     * 建構子
     */
    public AML002RS() {
        super("AML002");
    }

}
