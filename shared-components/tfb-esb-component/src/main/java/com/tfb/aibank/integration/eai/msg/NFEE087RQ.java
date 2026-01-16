package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;

import tw.com.ibm.mf.eb.NFEE087SvcRqType;

//@formatter:off
/**
* @(#)NFEE087RQ.java
* 
* <p>Description: NFEE087 基金網行銀在途交易取消</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/05/13, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
    datetimeConverters = {
        @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TransDate")
    }
)
//@formatter:on
public class NFEE087RQ extends EAIRequest<NFEE087SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NFEE087RQ() {
        super("NFEE087");
        this.getHeader().setHDRVQ1("NFWEBHQ");
    }
}
