package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIOverviewRequest;

import tw.com.ibm.mf.eb.BPM006SvcRqType;

// @formatter:off
/**
 * @(#)BPM006RQ.java
 *
 * <p>Description:投資分析明細總覽電文BPM006(上行)</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/06/27, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BPM006RQ extends EAIOverviewRequest<BPM006SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public BPM006RQ() {
        super("BPM006");
    }

}
