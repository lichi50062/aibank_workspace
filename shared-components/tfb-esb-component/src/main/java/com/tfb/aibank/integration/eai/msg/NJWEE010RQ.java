package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NJWEE010SvcRqType;

//@formatter:off
/**
 * @(#)NJWEE010RQ.java
 * 
 * <p>Description:NJWEE010 債券DBU帳戶總覽</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/17, Leiley
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class NJWEE010RQ extends EAIRequest<NJWEE010SvcRqType> {

    private static final long serialVersionUID = 6596261650720430148L;

    /**
     * 建構子
     */
    public NJWEE010RQ() {
        super("NJWEE010");
    }

}
