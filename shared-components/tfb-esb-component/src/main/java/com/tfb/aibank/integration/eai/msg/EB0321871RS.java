package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITimeConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB0321871SvcRqType;
import tw.com.ibm.mf.eb.EB0321871SvcRsType;

// @formatter:off
/**
 * @(#)EB0321871RS.java
 *
 * <p>Description:客戶資料共同行銷註記維護下行電文處理</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/05/11, Andy Kuo
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/MTN_DATE_01 | eai:Tx/TxBody/CONT_SIGN | eai:Tx/TxBody/CONT_NOTIFY | eai:Tx/TxBody/MTN_DATE_02"),
                @DateAndTimeConverter(converter = EAITimeConverter.class, inputPattern = "HHmmss", fieldXPath = "eai:Tx/TxBody/MTN_TIME_02")
        }
)
public class EB0321871RS extends EAIResponse<EB0321871SvcRqType, EB0321871SvcRsType> {

    /**
     * 建構子
     */
    public EB0321871RS() {
        super("EB0321871");
    }
}
