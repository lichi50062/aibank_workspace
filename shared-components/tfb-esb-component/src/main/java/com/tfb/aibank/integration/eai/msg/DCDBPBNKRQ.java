package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.DCDBPBNKSvcRqType;

/**
 * <p>Title: com.fubon.tw.pib.tr.message.DCDBPBNKRQ</p>
 * <p>Description: DCDBPBNK 上行電文</p>
 * <p>Copyright: Copyright (c) IBM Corp. 2013. All Rights Reserved.</p>
 * <p>Company: IBM GBS Team</p>
 * @author Edward Tien
 * @version 1.0
 */
public class DCDBPBNKRQ extends EAIRequest<DCDBPBNKSvcRqType> {

    /**
     * 建構子
     */
    public DCDBPBNKRQ() {
        super("DCDBPBNK");
    }

}
