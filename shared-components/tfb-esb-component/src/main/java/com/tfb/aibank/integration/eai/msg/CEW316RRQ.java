package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.CEW316RSvcRqType;

//@formatter:off
/**
 * @(#)CEW315RRS.java
 *
 * <p>Description:CEW316R 上行電文</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/05/24
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
public class CEW316RRQ extends EAIRequest<CEW316RSvcRqType> {

    private static final long serialVersionUID = 3741336179891093990L;

    /**
     * 建構子
     */
    public CEW316RRQ() {
        super("CEW316R");
    }
}
