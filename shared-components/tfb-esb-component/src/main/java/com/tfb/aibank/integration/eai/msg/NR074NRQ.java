package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NR070NSvcRqType;
import tw.com.ibm.mf.eb.NR074NSvcRqType;

//@formatter:off
/**
 * @(#)NR070NRQ.java
 *
 * <p>Description: NR074N 海外股票ETF即時成交交易資訊上行電文</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/05/10, Lyon Xie
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class NR074NRQ extends EAIRequest<NR074NSvcRqType> {

    /**
     * 建構子
     */
    public NR074NRQ() {
        super("NR074N");
    }

}
