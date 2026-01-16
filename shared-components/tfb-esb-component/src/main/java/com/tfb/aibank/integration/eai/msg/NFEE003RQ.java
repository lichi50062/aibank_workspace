package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;

import tw.com.ibm.mf.eb.NFEE003SvcRqType;

// @formatter:off
/**
 * @(#)NFEE003RQ.java
 *
 * <p>Description:NFEE003 專案優惠查詢</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/10/17, MP
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Converter(decimalConverters = {
        @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "#############", scale = -2, fieldXPath = "eai:Tx/TxBody/Amt13 | eai:Tx/TxBody/HAmt13") },
        customConverters = {
                @CustomConverter(converter = EAIPadLeftConverter.class, padSize = 16, padChar = "0", fieldXPath = "eai:Tx/TxBody/AcctId16") })
public class NFEE003RQ extends EAIRequest<NFEE003SvcRqType> {

    /**
     * 建構子
     */
    public NFEE003RQ() {
        super("NFEE003");
        this.getHeader().setHDRVQ1("NFMOBHQ");
    }

}
