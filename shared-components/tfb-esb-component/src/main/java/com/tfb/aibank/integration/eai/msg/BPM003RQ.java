package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIOverviewRequest;

import tw.com.ibm.mf.eb.BPM003SvcRqType;

// @formatter:off
/**
 * @(#)BPM003RQ.java
 *
 * <p>Description:[程式說明]</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, MartyPan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BPM003RQ extends EAIOverviewRequest<BPM003SvcRqType> {

    private static final long serialVersionUID = 8669121801641679235L;

    /**
     * 建構子
     */
    public BPM003RQ() {
        super("BPM003");
    }

}
