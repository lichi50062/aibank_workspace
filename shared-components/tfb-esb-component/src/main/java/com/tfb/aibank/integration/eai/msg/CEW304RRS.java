package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CEW304RSvcRqType;
import tw.com.ibm.mf.eb.CEW304RSvcRsType;

//@formatter:off
/**
 * @(#)CEW304RRS.java
 *
 * <p>Description:本期帳單剩餘應繳金額</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2025/05/13, David Huang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/AcctIdub | eai:Tx/TxBody/AcctIdCram | eai:Tx/TxBody/AcctIdPcbl | eai:Tx/TxBody/AcctIdDPayd "),
                @DecimalConverter(converter = EAIDecimalConverter.class, isPostSign = true, fieldXPath = "eai:Tx/TxBody/AcctIdPcb1S | Tx/TxBody/AcctIdubS")
        })
//@formatter:on
public class CEW304RRS extends EAIResponse<CEW304RSvcRqType, CEW304RSvcRsType> {
    /**
     * 建構子
     */
    public CEW304RRS() {
        super("CEW304R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "V803".equals(errId);
    }
}
