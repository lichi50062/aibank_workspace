package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIOverviewRequest;

import tw.com.ibm.mf.eb.BPM004SvcRqType;

// @formatter:off
/**
 * @(#)BPM004RQ.java
 *
 * <p>Description:資產總覽電文BPM004(上行)</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, MartyPan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BPM004RQ extends EAIOverviewRequest<BPM004SvcRqType> {

    private static final long serialVersionUID = 276707427005391935L;

    /**
     * 建構子
     */
    public BPM004RQ() {
        super("BPM004");
    }

}
