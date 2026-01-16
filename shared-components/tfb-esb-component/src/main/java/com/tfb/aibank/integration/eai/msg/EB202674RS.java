/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2013.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITimeConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB202674SvcRqType;
import tw.com.ibm.mf.eb.EB202674SvcRsType;

// @formatter:off
/**
 * @(#)EB202674RS.java
 * 
 * <p>Description:一本萬利交易明細下行電文 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*[name() != 'RMK']" +
                        " | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/CustName | eai:Tx/TxBody/BIRTH_DATE | eai:Tx/TxBody/MSG_COD | eai:Tx/TxBody/MSG_TXT")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/BIRTH_DATE"),
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/TxRepeat/ACT_DATE | eai:Tx/TxBody/TxRepeat/TX_DATE "),
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "MMddyyyy", fieldXPath = "eai:Tx/TxBody/TxRepeat/BK_DATE | eai:Tx/TxBody/TxRepeat/DUE_DATE "),
                @DateAndTimeConverter(converter = EAITimeConverter.class, inputPattern = "HHmmss", fieldXPath = "eai:Tx/TxBody/TxRepeat/TX_TIME" )
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 3, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/DR_TXT | eai:Tx/TxBody/TxRepeat/CR_TXT | eai:Tx/TxBody/TxRepeat/PB_BAL | eai:Tx/TxBody/TxRepeat/AMT1 | eai:Tx/TxBody/TxRepeat/AMT2 | eai:Tx/TxBody/TxRepeat/NDD_ACT_BAL | eai:Tx/TxBody/TxRepeat/AVL_BAL"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 7, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/INT_RATE"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 8, isPostSign = true, fieldXPath = "eai:Tx[./TxHead/HFMTID='D002']/TxBody/TxRepeat/INT_RATE"),
                // 以下欄位，需要配合實際值調整
                @DecimalConverter(converter = EAIDecimalConverter.class, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/CRLN_UTL | eai:Tx/TxBody/TxRepeat/RET_AMT | eai:Tx/TxBody/TxRepeat/INT_RATE1 | eai:Tx/TxBody/TxRepeat/CRLN_AMT | eai:Tx/TxBody/TxRepeat/STK_UNIT | eai:Tx/TxBody/TxRepeat/TRUST_AMT | eai:Tx/TxBody/TxRepeat/REF_PRICE | eai:Tx/TxBody/TxRepeat/TRUST_PL | eai:Tx/TxBody/TxRepeat/PL_RATIO | eai:Tx/TxBody/TxRepeat/ACCUM_INT | eai:Tx/TxBody/TxRepeat/ESTI_FEE")
        }      
)
//@formatter:on
public class EB202674RS extends EAIResponse<EB202674SvcRqType, EB202674SvcRsType> {
    /**
     * 建構子
     */
    public EB202674RS() {
        super("EB202674");
    }

    /**
     * 檢核回復訊息是否合法
     * 
     * @param rs
     * @return
     */
    @Override
    public boolean validateWithRq(EAIRequest<EB202674SvcRqType> rq) {
        if ("0000".equals(getHeader().getHERRID())) {
            // 下行表頭 HFMTID 必須為 D007
            if (rq.getHeader().getHTLID().endsWith("4115") && !"D007".equals(StringUtils.trimToEmptyEx(getHeader().getHFMTID()))) {
                return false;
            }
        }
        return true;
    }

}
