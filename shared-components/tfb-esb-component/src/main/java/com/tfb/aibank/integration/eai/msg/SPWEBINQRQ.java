package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.SPWEBINQSvcRqType;

/**
 * <p>Title: com.fubon.tw.pib.tr.message.SPWEBINQRQ</p>
 * <p>Description: SPWEBINQ 上行電文</p>
 * <p>Copyright: Copyright (c) IBM Corp. 2013. All Rights Reserved.</p>
 * <p>Company: IBM GBS Team</p>
 * @author Edward Tien
 * @version 1.0
 */
public class SPWEBINQRQ extends EAIRequest<SPWEBINQSvcRqType> {

    /**
     * 建構子
     */
    public SPWEBINQRQ() {
        super("SPWEBINQ");
    }
}
