package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NJWEEB02SvcRqType;
import tw.com.ibm.mf.eb.NJWEEB02SvcRsType;

// @formatter:off
/**
 * @(#)NJWEEB02RS.java
 * 
 * <p>Description:我的觀察債券查詢 NJWEEB02</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/14, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ccccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/ExpiryDate | eai:Tx/TxBody/TxRepeat/RefPriceDate")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/Occur"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 6, fieldXPath = "eai:Tx/TxBody/TxRepeat/BondsRate | eai:Tx/TxBody/TxRepeat/YTMYTC"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 9, fieldXPath = "eai:Tx/TxBody/TxRepeat/RefPrice")
        }
)
// @formatter:on
public class NJWEEB02RS extends EAIResponse<NJWEEB02SvcRqType, NJWEEB02SvcRsType> {

    public NJWEEB02RS() {
        super("NJWEEB02");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }

}
