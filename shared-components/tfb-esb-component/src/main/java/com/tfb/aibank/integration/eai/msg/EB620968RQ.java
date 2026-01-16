package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB620968SvcRqType;

// @formatter:off
/**
 * @(#)EB620968RQ.java
 * 
 * <p>Description:匯款歷程查詢 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/08/01, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB620968RQ extends EAIRequest<EB620968SvcRqType> {

    private static final long serialVersionUID = 6672280791309015430L;

    /**
     * 建構子
     */
    public EB620968RQ() {
        super("EB620968");
    }

}
