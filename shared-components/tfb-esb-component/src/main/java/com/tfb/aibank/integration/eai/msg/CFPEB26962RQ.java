package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.CFPEB26962SvcRqType;

//@formatter:off
/**
* @(#)CardActivateModel.java
* 
* <p>Description: CFPEB26962 金融卡/網銀/電話/行動銀行轉帳帳號電子表單申請交易</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/04, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CFPEB26962RQ  extends EAIRequest<CFPEB26962SvcRqType> {

    private static final long serialVersionUID = 1L;

    public CFPEB26962RQ() {
        super("CFPEB26962");
        // 配合CFP判斷來源，TXMODE固定帶N
        getHeader().setTXMODE("N");
        //配合CFP判斷交易行為 HFUNC 帶1為設定，不帶為查詢
        getHeader().setHFUNC("1");
    }
}
