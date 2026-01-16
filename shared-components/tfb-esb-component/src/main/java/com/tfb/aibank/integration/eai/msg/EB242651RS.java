package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB242651SvcRqType;
import tw.com.ibm.mf.eb.EB242651SvcRsType;

// @formatter:off
/**
 * @(#)EB242651RS.java
 * 
 * <p>Description:EB242651 大額換匯查詢/預佔電文(台轉外、外轉台)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/08, Edward	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | "
                        + "eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/Resp_Code | eai:Tx/TxBody/Resp_Mesg | "
                        + "eai:Tx/TxBody/EBK_Refno | eai:Tx/TxBody/Tx_Time | eai:Tx/TxBody/Dup_Flag | eai:Tx/TxBody/Ot_GroupId | "
                        + "eai:Tx/TxBody/TotAmt_Usd | eai:Tx/TxBody/totalTWDAmt |  eai:Tx/TxBody/Q_ID | eai:Tx/TxBody/BenIdType | "
                        + "eai:Tx/TxBody/BenIdNumber | eai:Tx/TxBody/OCCUR | eai:Tx/TxBody/EquivalentTWD | eai:Tx/TxBody/EquivalentUSD | "
                        + "eai:Tx/TxBody/DtOfBirth | eai:Tx/TxBody/DtOfARCissue | eai:Tx/TxBody/EffectiveDtofARC")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/DtOfBirth | eai:Tx/TxBody/DtOfARCissue | eai:Tx/TxBody/EffectiveDtofARC "),
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 3, showPlusSign = true, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/Ot_Amt | eai:Tx/TxBody/TxRepeat/Usd_Amt | eai:Tx/TxBody/TxRepeat/Twd_Amt"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 3, showPlusSign = true, isPostSign = true, fieldXPath = "eai:Tx/TxBody/totalTWDAmt | eai:Tx/TxBody/EquivalentUSD | eai:Tx/TxBody/EquivalentTWD | eai:Tx/TxBody/TxRepeat/TotAmt_Usd | eai:Tx/TxBody/TxRepeat/TotAmt_TWD")
        }
)
// @formatter:on
public class EB242651RS extends EAIResponse<EB242651SvcRqType, EB242651SvcRsType> {
    /**
     * 建構子
     */
    public EB242651RS() {
        super("EB242651");
    }
}
