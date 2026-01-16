package com.tfb.aibank.integration.eai.msg;


import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;
import tw.com.ibm.mf.eb.CIP121159SvcRqType;
import tw.com.ibm.mf.eb.CIP121159SvcRsType;

// @formatter:off
/**
 * @(#)CIP121159RS.java
 *
 * <p>Description 各類通知事項申請及維護上行電文(CIP121159)</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/07/10,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Converter(
		customConverters = {
				@CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*" +
						" | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
		},
		datetimeConverters = {
				@DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath="eai:Tx/TxBody/TxRepeat/C_DATE | eai:Tx/TxBody/TxRepeat/S_DATE" +
						" | eai:Tx/TxBody/TxRepeat/E_DATE | eai:Tx/TxBody/CIPDate"),
				@DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "HHmmssSS", fieldXPath="eai:Tx/TxBody/CIPTime")
		},
		decimalConverters = {
				@DecimalConverter(converter = EAIDecimalConverter.class, pattern="0000000000000000", scale = 6, fieldXPath = "eai:Tx/TxBody/TxRepeat/FX_RATE | eai:Tx/TxBody/TxRepeat/IN_RATE")
		}
)

public class CIP121159RS extends EAIResponse<CIP121159SvcRqType, CIP121159SvcRsType> {

	/**
     * 建構子
     */
    public CIP121159RS() {
        super("CIP121159");
    }
}