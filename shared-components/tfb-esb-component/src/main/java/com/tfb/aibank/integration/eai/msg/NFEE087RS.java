package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NFEE087SvcRqType;
import tw.com.ibm.mf.eb.NFEE087SvcRsType;

//@formatter:off
/**
* @(#)NFEE087RS.java
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
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ccccMMdd", fieldXPath = "eai:Tx/TxBody/TransDate")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/CurAmt | eai:Tx/TxBody/Fee | eai:Tx/TxBody/TransferFee"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/UntNum")
        }
)
//@formatter:on
public class NFEE087RS extends EAIResponse<NFEE087SvcRqType, NFEE087SvcRsType> {

    /**
     * 建構子
     */
    public NFEE087RS() {
        super("NFEE087");
    }
}
