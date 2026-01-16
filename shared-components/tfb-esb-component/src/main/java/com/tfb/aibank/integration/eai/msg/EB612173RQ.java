package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB612173SvcRqType;

// @formatter:off
/**
 * @(#)EB612173RQ.java
 * 
 * <p>Description:EB612173網銀國外匯款匯款申請資料建檔及維護上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/07, Edward	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB612173RQ extends EAIRequest<EB612173SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public EB612173RQ() {
        super("EB612173");
    }

}
