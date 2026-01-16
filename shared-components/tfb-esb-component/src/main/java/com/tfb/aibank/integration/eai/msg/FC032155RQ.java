package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.FC032155SvcRqType;

//@formatter:off
/**
 * @(#)FC032155RQ.java
 * 
 * <p>Description: 本行客戶註記上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/15, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class FC032155RQ extends EAIRequest<FC032155SvcRqType> {

    private static final long serialVersionUID = 3096121666530205637L;

    /**
     * 建構子
     */
    public FC032155RQ() {
        super("FC032155");
    }
}
