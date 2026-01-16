package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB032282SvcRqType;

/**
 * <p>Title: com.fubon.tw.pib.tr.message.EB032282RQ</p>
 * <p>Description: EB032282 上行電文</p>
 * <p>Copyright: Copyright (c) IBM Corp. 2013. All Rights Reserved.</p>
 * <p>Company: IBM GBS Team</p>
 * @author Edward Tien
 * @version 1.0
 */
public class EB032282RQ extends EAIRequest<EB032282SvcRqType> {

    /**
     * 建構子
     */
    public EB032282RQ() {
        super("EB032282");
    }
}
