package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NAPBW7SvcRqType;

/**
 * <p>Title: com.fubon.tw.pib.tr.message.NAPBW7RQ</p>
 * <p>Description: 員工持股信託退出報告書查詢 上行電文</p>
 */
public class NAPBW7RQ extends EAIRequest<NAPBW7SvcRqType> {

    /**
     * 建構子
     */
    public NAPBW7RQ() {
        super("NAPBW7");
        this.getHeader().setHDRVQ1("TMWEBHQ");

    }
    
}
