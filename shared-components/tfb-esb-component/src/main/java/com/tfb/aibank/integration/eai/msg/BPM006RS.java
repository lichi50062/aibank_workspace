package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.BPM006SvcRqType;
import tw.com.ibm.mf.eb.BPM006SvcRsType;

// @formatter:off
/**
 * @(#)BPM006RS.java
 *
 * <p>Description:投資分析明細總覽電文BPM006(下行)</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/06/27, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// TODO need to check this RS converters with real EAI response
@Converter(
    customConverters = {
        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*")
    },
    decimalConverters = {
        @DecimalConverter(converter = EAIDecimalConverter.class, scale = 0, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/BondAmt1 | eai:Tx/TxBody/TxRepeat/BondAmt2 | eai:Tx/TxBody/TxRepeat/Amount"),
        @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/P_VALUE" + 
                        " | eai:Tx/TxBody/TxRepeat/AcctBal | eai:Tx/TxBody/TxRepeat/CurAmtNT | eai:Tx/TxBody/TxRepeat/CurBalNT" + 
                        " | eai:Tx/TxBody/TxRepeat/RefAmtNT"),
        @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/MarketValTwd | eai:Tx/TxBody/TxRepeat/RefAmtNT" + 
                        " | eai:Tx/TxBody/TxRepeat/TrustAmtNT"),
        @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/TxRepeat/RefAmt | eai:Tx/TxBody/TxRepeat/DCDAMOUNTNTD" + 
                        " | eai:Tx/TxBody/TxRepeat/RefAmtNT | eai:Tx/TxBody/TxRepeat/TrustAmtNT | eai:Tx/TxBody/TxRepeat/AcctBal" + 
                        " | eai:Tx/TxBody/TxRepeat/AcctBal")
    }
)
// @formatter:on
public class BPM006RS extends BPMBase006RS<BPM006SvcRqType, BPM006SvcRsType> {

    /**
     * 建構子
     */
    public BPM006RS() {
        super("BPM006");
    }

}
