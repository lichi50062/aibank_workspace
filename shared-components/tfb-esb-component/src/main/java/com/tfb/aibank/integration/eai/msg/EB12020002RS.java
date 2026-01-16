package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.ArrayUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB12020002SvcRqType;
import tw.com.ibm.mf.eb.EB12020002SvcRsType;

// @formatter:off
/**
 * @(#)EB12020002RS.java
 * 
 * <p>Description:零存整付定存明細查詢 上行電文</p>
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
                    converter = EAIDecimalConverter.class, pattern = "00000000000", scale=6, isPostSign=true, fieldXPath = "eai:Tx[./TxHead/HFMTID='D001']/TxBody/TxRepeat/FXRATE | eai:Tx[./TxHead/HFMTID='D002']/TxBody/FXRATE"),
            @DecimalConverter(
                    converter = EAIDecimalConverter.class, scale=3, isPostSign=true, fieldXPath = "eai:Tx/TxBody/TxRepeat/OPN_DPR_AMT | eai:Tx/TxBody/TxRepeat/CRLN_UTL | eai:Tx/TxBody/TxRepeat/LOAN_BAL | eai:Tx/TxBody/CRLN_UTL | eai:Tx/TxBody/LOAN_BAL | eai:Tx/TxBody/OPN_DPR_AMT"),
            @DecimalConverter(
                    converter = EAIDecimalConverter.class, scale=5, isPostSign=true, fieldXPath = "eai:Tx[./TxHead/HFMTID='D001']/TxBody/TxRepeat/INT_UN | eai:Tx[./TxHead/HFMTID='D002']/TxBody/INT_UN"),
            @DecimalConverter(
                    converter = EAIDecimalConverter.class, isPostSign=true, fieldXPath = "eai:Tx/TxBody/TxRepeat/DPR_MM | eai:Tx/TxBody/TxRepeat/DPR_DDD | eai:Tx[./TxHead/HFMTID='D002']/TxBody/Signed33 | eai:Tx[./TxHead/HFMTID='D002']/TxBody/Signed36 "),
            @DecimalConverter(
                    converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx[./TxHead/HFMTID='D001']/TxBody/TxRepeat/AUTO_TR_CNT | eai:Tx[./TxHead/HFMTID='D001']/TxBody/TxRepeat/AUTO_TR_ACT | eai:Tx[./TxHead/HFMTID='D001']/TxBody/TxRepeat/AUTO_TR_LEFT | eai:Tx[./TxHead/HFMTID='D001']/TxBody/TxRepeat/INT | eai:Tx[./TxHead/HFMTID='D001']/TxBody/TxRepeat/DR_AMT" +
                    " | eai:Tx[./TxHead/HFMTID='D002']/TxBody/AUTO_TR_CNT | eai:Tx[./TxHead/HFMTID='D002']/TxBody/AUTO_TR_ACT | eai:Tx[./TxHead/HFMTID='D002']/TxBody/AUTO_TR_LEFT | eai:Tx[./TxHead/HFMTID='D002']/TxBody/INT | eai:Tx[./TxHead/HFMTID='D002']/TxBody/DR_AMT" +
                    " | eai:Tx[./TxHead/HFMTID='D002']/TxBody/INT_AVAIL | eai:Tx[./TxHead/HFMTID='D002']/TxBody/TxRepeat/INT_AMT"),
            @DecimalConverter(
                    converter = EAIDecimalConverter.class, pattern = "00000000000", scale=6, isPostSign=true, fieldXPath = "eai:Tx/TxBody/TxRepeat/EXG_RATE"),
            @DecimalConverter(
                    converter = EAIDecimalConverter.class, pattern = "00000000000000000", scale=3, isPostSign=true, fieldXPath = "eai:Tx/TxBody/TxRepeat/TERM_VALUE"),
            @DecimalConverter(
                    converter = EAIDecimalConverter.class, pattern = "00000000000000000", scale=3, isPostSign=true, fieldXPath = "eai:Tx[./TxHead/HFMTID='D002']/TxBody/ORI_DPR_AMT | eai:Tx[./TxHead/HFMTID='D002']/TxBody/LcyIntPay | eai:Tx[./TxHead/HFMTID='D002']/TxBody/LcyNetIntPay"),
            @DecimalConverter(
                    converter = EAIDecimalConverter.class, pattern = "00000000000", scale=7, isPostSign=true, fieldXPath = "eai:Tx/TxBody/TxRepeat/INT_RATE")
        },
        datetimeConverters = {
            @DateAndTimeConverter(
                    converter = EAIDateConverter.class, inputPattern = "MMddyyyy", fieldXPath = "eai:Tx/TxBody/TxRepeat/BK_VALUE | eai:Tx/TxBody/TxRepeat/DUE_DTE | eai:Tx/TxBody/TxRepeat/CLS_DTE"),
            @DateAndTimeConverter(
                    converter = EAIDateConverter.class, inputPattern = "dd/MM/yyyy", fieldXPath = "eai:Tx/TxBody/TxRepeat/LST_INT_DATE | eai:Tx/TxBody/LST_INT_DATE")
        }
)
// @formatter:on
public class EB12020002RS extends EAIResponse<EB12020002SvcRqType, EB12020002SvcRsType> {

    /**
     * 建構子
     */
    public EB12020002RS() {
        super("EB12020002");
    }

    @Override
    protected boolean isNoData(String errId) {
        if (StringUtils.equalsAny(errId, "EK63", "0000")) {
            return true;
        }
        return ArrayUtils.contains(CBS_NO_DATA_ERRID, errId);
    }
}
