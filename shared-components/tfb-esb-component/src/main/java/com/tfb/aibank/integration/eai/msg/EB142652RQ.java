package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB142652SvcRqType;

// @formatter:off
/**
 * @(#)EB142652RQ.java
 * 
 * <p>Description:自動轉期歷史查詢 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/26, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB142652RQ extends EAIRequest<EB142652SvcRqType> {

    private static final long serialVersionUID = -9031497576707460696L;

    /**
     * 建構子
     */
    public EB142652RQ() {
        super("EB142652");
    }
}
