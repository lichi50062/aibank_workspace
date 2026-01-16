package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NMWEB01SvcRqType;

// @formatter:off
/**
 * @(#)NMWEB01RQ.java
 * 
 * <p>Description:金錢信託契約查詢(委託人及監察人角度) NMWEB01</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/10, Charlie Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NMWEB01RQ extends EAIRequest<NMWEB01SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NMWEB01RQ() {
        super("NMWEB01");
    }

}
