package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.VN097NSvcRqType;
import tw.com.ibm.mf.eb.VN097NSvcRsType;

// @formatter:off
/**
 * @(#)VN097NRS.java
 * 
 * <p>Description:資料查詢變更交易(VN097N) 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/01, Jojo Wei
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TrscDate")
        }
)
// @formatter:on
public class VN097NRS extends EAIResponse<VN097NSvcRqType, VN097NSvcRsType> {

    /**
     * 建構子
     */
    public VN097NRS() {
        super("VN097N");
    }
}
