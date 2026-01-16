package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.AJWEE010SvcRqType;

//@formatter:off
/**
 * @(#)AJWEE010RQ.java
 * 
 * <p>Description: AJWEE010 債券OBU帳戶總覽</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/17, Leiley
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class AJWEE010RQ extends EAIRequest<AJWEE010SvcRqType> {

    private static final long serialVersionUID = -6927610754504929662L;

    /**
     * 建構子
     */
    public AJWEE010RQ() {
        super("AJWEE010");
    }

}
