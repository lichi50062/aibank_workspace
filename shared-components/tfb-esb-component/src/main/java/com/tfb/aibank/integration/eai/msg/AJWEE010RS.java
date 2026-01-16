package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.AJWEE010SvcRqType;
import tw.com.ibm.mf.eb.AJWEE010SvcRsType;

//@formatter:off
/**
 * @(#)AJWEE010RS.java
 * 
 * <p>Description: AJWEE010 債券OBU帳戶總覽</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/17, Leiley
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur"),
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/RefDt | eai:Tx/TxBody/TxRepeat/Strdate" +
                        		" | eai:Tx/TxBody/TxRepeat/Enddate | eai:Tx/TxBody/TxRepeat/ClosingDate | eai:Tx/TxBody/TxRepeat/TrustDt" +
                        		" | eai:Tx/TxBody/TxRepeat/TrscDate | eai:Tx/TxBody/TxRepeat/BackDt"),
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "0cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/BondStrDate")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/Occur | eai:Tx/TxBody/TxRepeat/UntVal" +
                		" | eai:Tx/TxBody/TxRepeat/UntNum"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/IntRate | eai:Tx/TxBody/TxRepeat/Frontfee1 | eai:Tx/TxBody/TxRepeat/Frontfee2"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/TxRepeat/InvVal | eai:Tx/TxBody/TxRepeat/TrustAmt" +
                        " | eai:Tx/TxBody/TxRepeat/AdjustInterest | eai:Tx/TxBody/TxRepeat/Interest | eai:Tx/TxBody/TxRepeat/TrustVal" +
                        " | eai:Tx/TxBody/TxRepeat/OnWayAmt | eai:Tx/TxBody/TxRepeat/RefAmtNT | eai:Tx/TxBody/TxRepeat/TrustAmtNT" +
                        " | eai:Tx/TxBody/TxRepeat/BackAmt | eai:Tx/TxBody/TxRepeat/ReedPrice | eai:Tx/TxBody/TxRepeat/RefAmt"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 9, fieldXPath = "eai:Tx/TxBody/TxRepeat/RefValue | eai:Tx/TxBody/TxRepeat/ExchangeRate" +
                		" | eai:Tx/TxBody/TxRepeat/RefExRate")
        }
)
//@formatter:on
public class AJWEE010RS extends EAIResponse<AJWEE010SvcRqType, AJWEE010SvcRsType> {

    public AJWEE010RS() {
        super("AJWEE010");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || StringUtils.equalsAny(errId, "EBB2", "E005", "SP01", "SP02");
    }
}
