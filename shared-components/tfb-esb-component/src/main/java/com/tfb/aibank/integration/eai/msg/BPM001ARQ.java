package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIOverviewRequest;

import tw.com.ibm.mf.eb.BPM001ASvcRqType;

// @formatter:off
/**
 * @(#)BPM001ARQ.java
 *
 * <p>Description:資產總覽電文BPM001A(上行)</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2025/05/13, David Huang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BPM001ARQ extends EAIOverviewRequest<BPM001ASvcRqType> {

    private static final long serialVersionUID = -3659590239367980202L;

    /**
     * 建構子
     */
    public BPM001ARQ() {
        super("BPM001A");
    }

}
