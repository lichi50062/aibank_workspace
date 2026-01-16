package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB182651SvcRqType;

//@formatter:off
/**
 * @(#)EB182651RQ.java
 *
 * <p>Description:自動轉期歷史查詢</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/05/24,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB182651RQ extends EAIRequest<EB182651SvcRqType> {

    private static final long serialVersionUID = -4665364113155550512L;

    /**
     * 建構子
     */
    public EB182651RQ() {
        super("EB182651");
    }
}
