package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NMWEB01SvcRqType;
import tw.com.ibm.mf.eb.NMWEB01SvcRsType;

// @formatter:off
/**
 * @(#)NMWEB01RS.java
 * 
 * <p>Description:金錢信託契約查詢(委託人及監察人角度) NMWEB01</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/10, Charlie Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/Occur")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/ContractEndDate")
        }

)
// @formatter:on
public class NMWEB01RS extends EAIResponse<NMWEB01SvcRqType, NMWEB01SvcRsType> {

    public NMWEB01RS() {
        super("NMWEB01");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "V003".equals(errId);
    }

}
