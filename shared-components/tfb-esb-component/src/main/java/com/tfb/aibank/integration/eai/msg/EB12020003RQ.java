package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB12020003SvcRqType;

//@formatter:off
/**
 * @(#)EB12020003RQ.java
 * 
 * <p>Description:外幣定存續約變更上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/26, Leiley
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class EB12020003RQ extends EAIRequest<EB12020003SvcRqType> {

    private static final long serialVersionUID = -7089487096148013794L;

    /**
     * 建構子
     */
    public EB12020003RQ() {
        super("EB12020003");
    }
}
