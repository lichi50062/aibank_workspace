package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB032675SvcRqType;

// @formatter:off
/**
 * @(#)EB032675RQ.java
 *
 * <p>Description:EB032675RQ 上行電文</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/01/28,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB032675RQ extends EAIRequest<EB032675SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public EB032675RQ() {
        super("EB032675");
    }
}
