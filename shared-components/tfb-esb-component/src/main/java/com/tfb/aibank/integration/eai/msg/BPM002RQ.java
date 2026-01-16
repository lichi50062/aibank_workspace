package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIOverviewRequest;

import tw.com.ibm.mf.eb.BPM002SvcRqType;

// @formatter:off
/**
 * @(#)BPM002RQ.java
 *
 * <p>Description: 資產總覽電文BPM002(上行)</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, MartyPan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BPM002RQ extends EAIOverviewRequest<BPM002SvcRqType> {

    private static final long serialVersionUID = -4177073199341904503L;

    /**
     * 建構子
     */
    public BPM002RQ() {
        super("BPM002");
    }

}
