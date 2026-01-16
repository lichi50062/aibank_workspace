package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NMWEB07SvcRqType;


// @formatter:off
/**
 * @(#)NMWEB07RQ.java
 *
 * <p>金錢信託-依證號查詢契約電文(NMWEB07)</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/07/09, PCY
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NMWEB07RQ extends EAIRequest<NMWEB07SvcRqType> {

	private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NMWEB07RQ() {
        super("NMWEB07");
    }
}
