package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB94SvcRqType;
import tw.com.ibm.mf.eb.EB94SvcRsType;

// @formatter:off
/**
 * @(#)EB94RS.java
 * 
 * <p>Description:EB94 EB網路銀行外匯扣帳電文下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/07, Edward	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/FORMAT_ID | eai:Tx/TxBody/ACT_DATE | "
                        + "eai:Tx/TxBody/SETL_ID | eai:Tx/TxBody/HOST_ACT_DT | eai:Tx/TxBody/TX_AMT | eai:Tx/TxBody/ORFEE | eai:Tx/TxBody/ACNO_OUT | "
                        + "eai:Tx/TxBody/CURR | eai:Tx/TxBody/ACT_BAL | eai:Tx/TxBody/AVAL_BAL | eai:Tx/TxBody/RC_CODE | eai:Tx/TxBody/RC_MEMO | "
                        + "eai:Tx/TxBody/CNB | eai:Tx/TxBody/DATA_NO_OUT | eai:Tx/TxBody/JRN | eai:Tx/TxBody/HOST_SRL | eai:Tx/TxBody/EX_RATE | "
                        + "eai:Tx/TxBody/ACNO_OUT_FEE | eai:Tx/TxBody/ACT_BAL_FEE | eai:Tx/TxBody/AVAL_BAL_FEE | eai:Tx/TxBody/ORFEE_DBU | "
                        + "eai:Tx/TxBody/ORFEE_OBU | eai:Tx/TxBody/OTHER_FEE"),
        }, datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern="yyyyMMdd", fieldXPath="eai:Tx/TxBody/ACT_DATE")
        }, decimalConverters = { 
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 6, fieldXPath = "eai:Tx/TxBody/EX_RATE"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TX_AMT"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 3, isPostSign = true, showPlusSign = true, fieldXPath = "eai:Tx/TxBody/ACT_BAL | eai:Tx/TxBody/AVAL_BAL | eai:Tx/TxBody/ORFEE | eai:Tx/TxBody/ORFEE_DBU | eai:Tx/TxBody/ORFEE_OBU | eai:Tx/TxBody/OTHER_FEE | eai:Tx/TxBody/ACT_BAL_FEE | eai:Tx/TxBody/AVAL_BAL_FEE")
        }
)
// @formatter:on
public class EB94RS extends EAIResponse<EB94SvcRqType, EB94SvcRsType> {

    /**
     * 建構子
     */
    public EB94RS() {
        super("EB94");
    }

}
