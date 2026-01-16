package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB12020007SvcRqType;

// @formatter:off
/**
 * @(#)EB12020007RQ.java
 * 
 * <p>Description:查詢/變更客戶通訊資料上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/11, Edward	Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB12020007RQ extends EAIRequest<EB12020007SvcRqType> {

    private static final long serialVersionUID = -6255139532264657186L;

    /**
     * 建構子
     */
    public EB12020007RQ() {
        super("EB12020007");
    }
}
