package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.ArrayUtils;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB12020102SvcRqType;
import tw.com.ibm.mf.eb.EB12020102SvcRsType;

// @formatter:off
/**
 * @(#)EB12020102RS.java
 * 
 * <p>Description:一般定存(非零存整付)明細查詢 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/26, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
            @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/MSG_COD | eai:Tx/TxBody/MSG_TXT | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/INT_PAY_TYP")
        },
        decimalConverters = {
            @DecimalConverter(
                    converter = EAIDecimalConverter.class, pattern = "00000000000", scale=6, isPostSign=true, fieldXPath = "eai:Tx/TxBody/TxRepeat/FXRATE"),
            @DecimalConverter(
                    converter = EAIDecimalConverter.class, scale=3, isPostSign=true, fieldXPath = "eai:Tx/TxBody/TxRepeat/OPN_DPR_AMT | eai:Tx/TxBody/TxRepeat/CRLN_UTL | eai:Tx/TxBody/TxRepeat/LOAN_BAL | eai:Tx/TxBody/CRLN_UTL | eai:Tx/TxBody/LOAN_BAL | eai:Tx/TxBody/OPN_DPR_AMT"),
            @DecimalConverter(
                    converter = EAIDecimalConverter.class, scale=7, isPostSign=true, fieldXPath = "eai:Tx[./TxHead/HFMTID='D001']/TxBody/TxRepeat/INT_RATE"),
            @DecimalConverter(
                    converter = EAIDecimalConverter.class, scale=5, isPostSign=true, fieldXPath = "eai:Tx/TxBody/TxRepeat/INT_UN | eai:Tx/TxBody/INT_UN"),
            @DecimalConverter(
                    converter = EAIDecimalConverter.class, isPostSign=true, fieldXPath = "eai:Tx/TxBody/TxRepeat/DPR_MM | eai:Tx/TxBody/TxRepeat/DPR_DDD | eai:Tx[./TxHead/HFMTID='D002']/TxBody/Signed33 | eai:Tx[./TxHead/HFMTID='D002']/TxBody/Signed36 "),
            @DecimalConverter(
                    converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx[./TxHead/HFMTID='D001']/TxBody/TxRepeat/AUTO_TR_CNT | eai:Tx[./TxHead/HFMTID='D001']/TxBody/TxRepeat/AUTO_TR_ACT | eai:Tx[./TxHead/HFMTID='D001']/TxBody/TxRepeat/AUTO_TR_LEFT | eai:Tx/TxBody/TxRepeat/INT_AMT"),
            @DecimalConverter(
                    converter = EAIDecimalConverter.class, pattern = "00000000000", scale=6, isPostSign=true, fieldXPath = "eai:Tx[./TxHead/HFMTID='D004']/TxBody/TxRepeat/EXG-RATEPIC"),
            @DecimalConverter(
                    converter = EAIDecimalConverter.class, pattern = "00000000000000000", scale=3, isPostSign=true, fieldXPath = "eai:Tx[./TxHead/HFMTID='D004']/TxBody/TxRepeat/TERM-VALUEPIC"),
            @DecimalConverter(
                    converter = EAIDecimalConverter.class, pattern = "00000000000000000", scale=3, isPostSign=true, fieldXPath = "eai:Tx/TxBody/TxRepeat/ORI_DPR_AMT | eai:Tx/TxBody/TxRepeat/DR_AMT | eai:Tx[./TxHead/HFMTID='D002']/TxBody/LcyIntPay | eai:Tx[./TxHead/HFMTID='D002']/TxBody/LcyNetIntPay | eai:Tx[./TxHead/HFMTID='D001']/TxBody/TxRepeat/INT_AVAIL")
        },
        datetimeConverters = {
            @DateAndTimeConverter(
                    converter = EAIDateConverter.class, inputPattern = "dd/MM/yyyy", fieldXPath = "eai:Tx[./TxHead/HFMTID='D005']/TxBody/TxRepeat/STR_DTE | eai:Tx[./TxHead/HFMTID='D005']/TxBody/TxRepeat/END_DTE"),
            @DateAndTimeConverter(
                    converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/TxRepeat/LST_INT_DATE"),
            @DateAndTimeConverter(
                    converter = EAIDateConverter.class, inputPattern = "MMddyyyy", fieldXPath = "eai:Tx/TxBody/TxRepeat/BK_VALUE | eai:Tx/TxBody/TxRepeat/DUE_DTE | eai:Tx/TxBody/TxRepeat/CLS_DTE")
        }
)
// @formatter:on
public class EB12020102RS extends EAIResponse<EB12020102SvcRqType, EB12020102SvcRsType> {

    /**
     * 建構子
     */
    public EB12020102RS() {
        super("EB12020102");
    }

    @Override
    protected boolean isNoData(String errId) {
        return ("EK63".equals(errId) || ArrayUtils.contains(CBS_NO_DATA_ERRID, errId));
    }
}
