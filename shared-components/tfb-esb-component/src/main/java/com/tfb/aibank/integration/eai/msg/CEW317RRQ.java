package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.CEW317RSvcRqType;

//@formatter:off
/**
 * @(#)CEW317RRQ.java
 *
 * <p>Description:CEW317R 帳單分期申請 上行電文</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/05/24
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
public class CEW317RRQ extends EAIRequest<CEW317RSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public CEW317RRQ() {
        super("CEW317R");
    }
}
