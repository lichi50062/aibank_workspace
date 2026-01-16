package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIOverviewRequest;

import tw.com.ibm.mf.eb.BPM003ASvcRqType;

// @formatter:off
/**
 * @(#)BPM003ARQ.java
 *
 * <p>Description:[程式說明]</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2025/05/13, David Huang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BPM003ARQ extends EAIOverviewRequest<BPM003ASvcRqType> {

    private static final long serialVersionUID = 8669121801641679235L;

    /**
     * 建構子
     */
    public BPM003ARQ() {
        super("BPM003A");
    }

}
