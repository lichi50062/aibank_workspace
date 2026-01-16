package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NJWEEA42SvcRqType;
import tw.com.ibm.mf.eb.NJWEEA42SvcRsType;

//@formatter:off
/**
 * @(#)NJWEEA42RS.java
 * 
 * <p>Description:NJWEEA42 新增結構型商品交易金額限制檢核電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/20, Edward Tien    
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur"),
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/Occur | eai:Tx/TxBody/TX_Total_AMT")
        }
)
//@formatter:on
public class NJWEEA42RS extends EAIResponse<NJWEEA42SvcRqType, NJWEEA42SvcRsType> {

    public NJWEEA42RS() {
        super("NJWEEA42");
    }
}
