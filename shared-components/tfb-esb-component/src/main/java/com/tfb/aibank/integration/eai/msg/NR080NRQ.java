package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NR080NSvcRqType;

//@formatter:off
/**
 * @(#)NR080NRQ.java
 * 
 * <p>Description: NR080N 海外股票ETF委託明細</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/12, Allen Liao
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class NR080NRQ extends EAIRequest<NR080NSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NR080NRQ() {
        super("NR080N");
    }
}
