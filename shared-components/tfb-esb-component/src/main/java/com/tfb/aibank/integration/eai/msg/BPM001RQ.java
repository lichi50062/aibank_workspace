package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIOverviewRequest;

import tw.com.ibm.mf.eb.BPM001SvcRqType;

// @formatter:off
/**
 * @(#)BPM001RQ.java
 *
 * <p>Description:資產總覽電文BPM001(上行)</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, MartyPan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BPM001RQ extends EAIOverviewRequest<BPM001SvcRqType> {

    private static final long serialVersionUID = -3659590239367980202L;

    /**
     * 建構子
     */
    public BPM001RQ() {
        super("BPM001");
    }

}
