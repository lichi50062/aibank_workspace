package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.VN099NSvcRqType;
import tw.com.ibm.mf.eb.VN099NSvcRsType;

// @formatter:off
/**
 * @(#)VN099NRS.java
 * 
 * <p>Description:查詢基金營業日(VN099N) 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/14, Edward Tien	
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
public class VN099NRS extends EAIResponse<VN099NSvcRqType, VN099NSvcRsType> {

    /**
     * 建構子
     */
    public VN099NRS() {
        super("VN099N");
    }
}
