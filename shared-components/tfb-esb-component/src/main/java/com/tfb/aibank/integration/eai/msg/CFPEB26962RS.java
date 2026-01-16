package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CFPEB26962SvcRqType;
import tw.com.ibm.mf.eb.CFPEB26962SvcRsType;
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
@Converter(customConverters = { @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/ACNO_SA | eai:Tx/TxBody/FUNC | eai:Tx/TxBody/CARD_NO | eai:Tx/TxBody/NAME1 | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
})
public class CFPEB26962RS extends EAIResponse<CFPEB26962SvcRqType, CFPEB26962SvcRsType> {
    /**
     * 建構子
     */
    public CFPEB26962RS() {
        super("CFPEB26962");
    }

    @Override
    protected boolean isNoData(String errId) {
        /**
         * 20180821 CFP回覆網路語音警示訊息，errId WI開頭，視為正常
         */
        if (errId.startsWith("WI")) {
            return true;
        }
        return super.isNoData(errId);
    }

}
