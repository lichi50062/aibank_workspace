package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NMWEB09SvcRqType;
import tw.com.ibm.mf.eb.NMWEB09SvcRsType;

// @formatter:off
/**
 * @(#)NMWEB09RS.java
 *
 * <p>金錢信託-不限用途-免付單據查詢電文(NMWEB09RS)</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/07/09, PCY
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/OCCUR")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/TxRepeat/PayPercentage"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/PayAmt")
        }
)
public class NMWEB09RS extends EAIResponse<NMWEB09SvcRqType, NMWEB09SvcRsType> {

    public NMWEB09RS() {
        super("NMWEB09");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}
