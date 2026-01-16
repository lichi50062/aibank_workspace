package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.WMSHAIASvcRqType;
import tw.com.ibm.mf.eb.WMSHAIASvcRsType;


// @formatter:off
/**
 * @(#)VN080NRQ.java
 *
 * <p>Description:WMSHAIARS- AUM判斷 下行電文</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/02/14
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        },
        decimalConverters = {
                        @DecimalConverter(converter = EAIDecimalConverter.class, scale=-4, fieldXPath="eai:Tx/TxBody/AMT_BUY_1 | eai:Tx/TxBody/AMT_SELL_1 | eai:Tx/TxBody/AMT_BUY_2" +
                                        " | eai:Tx/AMT_SELL_2 | eai:Tx/TxBody/AMT_BUY_3 | eai:Tx/TxBody/AMT_SELL_3 | eai:Tx/TxBody/AMT_BUY_4 | eai:Tx/TxBody/AMT_SELL_4" +
                                        " | eai:Tx/TxBody/DENO_AMT | eai:Tx/TxBody/AMT_1 | eai:Tx/TxBody/AMT_2 | eai:Tx/TxBody/AMT_3 | eai:Tx/TxBody/AMT_4" ),
                        @DecimalConverter(converter = EAIDecimalConverter.class, scale=-2, fieldXPath="eai:Tx/TxBody/BASE_RISK_1 | BASE_RISK_2 | BASE_RISK_3 | BASE_RISK_4" +
                                   " | eai:Tx/TxBody/RISK_1 | eai:Tx/TxBody/RISK_2 | eai:Tx/TxBody/RISK_3 | eai:Tx/TxBody/RISK_SUM"),
                        @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath="eai:Tx/TxBody/AMT_LEFT_2 | AMT_LEFT_3 | AMT_LEFT_4")
                                }
)
// @formatter:on
public class WMSHAIARS extends EAIResponse<WMSHAIASvcRqType, WMSHAIASvcRsType> {

	/**
     * 建構子
     */
    public WMSHAIARS() {
        super("WMSHAIA");
    }
}
