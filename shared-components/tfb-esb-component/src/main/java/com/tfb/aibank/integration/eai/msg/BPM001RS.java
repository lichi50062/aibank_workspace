package com.tfb.aibank.integration.eai.msg;


import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITimeConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;
import tw.com.ibm.mf.eb.BPM001SvcRqType;
import tw.com.ibm.mf.eb.BPM001SvcRsType;


// @formatter:off
/**
 * @(#)BPM001RS.java
 *
 * <p>Description:[資產總覽電文BPM001(下行)]</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, MartyPan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */

@Converter(
                customConverters = {
                                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/IDNO " +
                                                "| eai:Tx/TxBody/TOTAL_AMT_NTD | eai:Tx/TxBody/TOTAL_AMT_FRG " +
                                                "| eai:Tx/TxBody/TOTAL_AMT_61 | eai:Tx/TxBody/TOTAL_AMT_62 | eai:Tx/TxBody/TOTAL_AMT_63 " +
                                                "| eai:Tx/TxBody/TOTAL_AMT_64 | eai:Tx/TxBody/TOTAL_AMT_65 | eai:Tx/TxBody/TOTAL_AMT_67 " +
                                                "| eai:Tx/TxBody/TOTAL_AMT_68 | eai:Tx/TxBody/TOTAL_AMT_69 | eai:Tx/TxBody/TOTAL_AMT_70 " +
                                                "| eai:Tx/TxBody/TOTAL_AMT_6302 | eai:Tx/TxBody/TOTAL_AMT_6303 | eai:Tx/TxBody/TOTAL_AMT_73 " +
                                                "| eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT"),
                                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
                },
                decimalConverters = {
                                @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "#,##0.##", fieldXPath = "eai:Tx/TxBody/TOTAL_AMT_NTD " +
                                                "| eai:Tx/TxBody/TOTAL_AMT_FRG " +
                                                "| eai:Tx/TxBody/TxRepeat/AVAL_AMT " +
                                                "| eai:Tx/TxBody/TxRepeat/AVAL_AMT_NT " +
                                                "| eai:Tx/TxBody/TxRepeat/BRD_PRICE " +
                                                "| eai:Tx/TxBody/TxRepeat/P_VALUE " +
                                                "| eai:Tx/TxBody/TxRepeat/AVG_COST " +
                                                "| eai:Tx/TxBody/TxRepeat/M_AMT " +
                                                "| eai:Tx/TxBody/TxRepeat/M_FEE_AMT " +
                                                "| eai:Tx/TxBody/TxRepeat/DETA_AMT " +
                                                "| eai:Tx/TxBody/TxRepeat/YIELD_AMT " +
                                                "| eai:Tx/TxBody/TxRepeat/INV_AMT " +
                                                "| eai:Tx/TxBody/TxRepeat/ACT_BAL " +
                                                "| eai:Tx/TxBody/TxRepeat/FEE_AMT1 " +
                                                "| eai:Tx/TxBody/TOTAL_AMT_61 " +
                                                "| eai:Tx/TxBody/TOTAL_AMT_62 " +
                                                "| eai:Tx/TxBody/TOTAL_AMT_63 " +
                                                "| eai:Tx/TxBody/TOTAL_AMT_64 " +
                                                "| eai:Tx/TxBody/TOTAL_AMT_65 " +
                                                "| eai:Tx/TxBody/TOTAL_AMT_67 " +
                                                "| eai:Tx/TxBody/TOTAL_AMT_68 " +
                                                "| eai:Tx/TxBody/TOTAL_AMT_69 " +
                                                "| eai:Tx/TxBody/TOTAL_AMT_70 " +
                                                "| eai:Tx/TxBody/TOTAL_AMT_6302" +
                                                "| eai:Tx/TxBody/TOTAL_AMT_6303" +
                                                "| eai:Tx/TxBody/TOTAL_AMT_73"),
                                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/CUR_CNT " +
                                                "| eai:Tx/TxBody/Occur " +
                                                "| eai:Tx/TxBody/TxRepeat/Amount " +
                                                "| eai:Tx/TxBody/TxRepeat/stockAmt " +
                                                "| eai:Tx/TxBody/TxRepeat/currencyAmt " +
                                                "| eai:Tx/TxBody/TxRepeat/bondAmt " +
                                                "| eai:Tx/TxBody/TxRepeat/balanceAmt " +
                                                "| eai:Tx/TxBody/TxRepeat/amountCst " +
                                                "| eai:Tx/TxBody/TxRepeat/stockCst " +
                                                "| eai:Tx/TxBody/TxRepeat/currencyCst " +
                                                "| eai:Tx/TxBody/TxRepeat/bondCst " +
                                                "| eai:Tx/TxBody/TxRepeat/balanceCst " +
                                                "| eai:Tx/TxBody/TxRepeat/PIAmount " +
                                                "| eai:Tx/TxBody/TxRepeat/GDAmount " +
                                                "| eai:Tx/TxBody/TxRepeat/AR103 " +
                                                "| eai:Tx/TxBody/TxRepeat/BAL " +
                                                "| eai:Tx/TxBody/TxRepeat/YIELD " +
                                                "| eai:Tx/TxBody/AcctIdub " +
                                                "| eai:Tx/TxBody/AcctIdDPayd "),
                                @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "#,##0.####", fieldXPath = "eai:Tx/TxBody/TxRepeat/EX_RATE "),
                                @DecimalConverter(converter = EAIDecimalConverter.class, scale=2, fieldXPath = "eai:Tx/TxBody/TxRepeat/P_VALUE "),
                },
                datetimeConverters = {
                                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ccc/MM/dd", fieldXPath = "eai:Tx/TxBody/TxRepeat/BRD_DATE"),
                                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/M_DATE | eai:Tx/TxBody/TxRepeat/OPN_DATE | eai:Tx/TxBody/TxRepeat/MTN_DATE | eai:Tx/TxBody/TxRepeat/MTN_DATE"),
                                @DateAndTimeConverter(converter = EAITimeConverter.class, inputPattern = "HH:mm", fieldXPath = "eai:Tx/TxBody/TxRepeat/BRD_TIME")
                })
// @formatter:on
public class BPM001RS extends BPMBaseRS<BPM001SvcRqType, BPM001SvcRsType> {

    /**
     * 建構子
     */
    public BPM001RS() {
        super("BPM001");
    }
    
    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "V803".equals(errId) || "V829".equals(errId);
    }
}
