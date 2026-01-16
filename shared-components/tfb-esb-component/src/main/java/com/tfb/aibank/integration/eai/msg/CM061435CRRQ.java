package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.CM061435CRSvcRqType;

//@formatter:off
/**
 * @(#)CM061435CRRQ.java
 *
 * <p>Description: CM061435CR上行電文</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/02/04,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class CM061435CRRQ extends EAIRequest<CM061435CRSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public CM061435CRRQ() {
        super("CM061435CR");
    }

}
