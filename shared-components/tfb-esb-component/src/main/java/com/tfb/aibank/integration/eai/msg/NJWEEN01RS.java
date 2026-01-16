package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NJWEEN01SvcRqType;
import tw.com.ibm.mf.eb.NJWEEN01SvcRsType;

// @formatter:off
/**
 * @(#)NJWEEN01RS.java
 * 
 * <p>Description:金錢信託債券網銀歸戶查詢DBU</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/09, Charlie Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = 
                "eai:Tx/TxBody/TxRepeat/RefDt | "+
                "eai:Tx/TxBody/TxRepeat/Strdate | "+
                "eai:Tx/TxBody/TxRepeat/Enddate | "+
                "eai:Tx/TxBody/TxRepeat/ClosingDate | "+
                "eai:Tx/TxBody/TxRepeat/BackDt | "+
                "eai:Tx/TxBody/TxRepeat/TrustDt | "+
                "eai:Tx/TxBody/TxRepeat/RefDt | "+
                "eai:Tx/TxBody/TxRepeat/Strdate | "+
                "eai:Tx/TxBody/TxRepeat/Enddate | "+
                "eai:Tx/TxBody/TxRepeat/TrustDt | "+
                "eai:Tx/TxBody/TxRepeat/TrscDate" )
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/Occur | eai:Tx/TxBody/TxRepeat/UntVal"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/IntRate | eai:Tx/TxBody/TxRepeat/Frontfee1"+
                        " | eai:Tx/TxBody/TxRepeat/Frontfee2"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/TxRepeat/InvVal | eai:Tx/TxBody/TxRepeat/UntNum"+
                        " | eai:Tx/TxBody/TxRepeat/TrustAmt | eai:Tx/TxBody/TxRepeat/TrustAmtNT" +
                        " | eai:Tx/TxBody/TxRepeat/RefAmt | eai:Tx/TxBody/TxRepeat/RefAmtNT" +
                        " | eai:Tx/TxBody/TxRepeat/Interest | eai:Tx/TxBody/TxRepeat/AdjustInterest" +
                        " | eai:Tx/TxBody/TxRepeat/TrustVal | eai:Tx/TxBody/TxRepeat/OnWayAmt" +
                        " | eai:Tx/TxBody/TxRepeat/OnWayAmt"
                ),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 9, fieldXPath = 
                        "eai:Tx/TxBody/TxRepeat/ExchangeRate |" +
                        "eai:Tx/TxBody/TxRepeat/RefExRate |" +
                        "eai:Tx/TxBody/TxRepeat/RefValue" 
                )
        }
)
// @formatter:on
public class NJWEEN01RS extends EAIResponse<NJWEEN01SvcRqType, NJWEEN01SvcRsType> {

        public NJWEEN01RS() {
                super("NJWEEN01");
        }

}

