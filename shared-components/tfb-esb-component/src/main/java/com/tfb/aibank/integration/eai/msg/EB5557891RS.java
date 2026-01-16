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
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB5557891D001SvcRsType;
import tw.com.ibm.mf.eb.EB5557891SvcRqType;
import tw.com.ibm.mf.eb.EB5557891SvcRsType;

// @formatter:off
/**
 * @(#)EB5557891RS.java
 * 
 * <p>Description:歸戶帳號電文下行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/IDNO | eai:Tx/TxBody/TxRepeat/*[name()!='SLIP_NO' and name()!='DOC_NO'] | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
        }, 
        datetimeConverters = { 
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/TxRepeat/PRN_STR_DATE | eai:Tx/TxBody/PRN_STR_DATE"), 
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "MMddyyyy", fieldXPath = "eai:Tx/TxBody/TxRepeat/DUE_DATE | eai:Tx/TxBody/TxRepeat/DATE_OPEN")
        }, 
        decimalConverters = { 
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 3, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/AVAL_AMT | eai:Tx/TxBody/TxRepeat/ACT_BAL | eai:Tx/TxBody/TxRepeat/ACT_BAL_NT | eai:Tx/TxBody/TxRepeat/ORI_LOAN_BAL | eai:Tx/TxBody/ORI_LOAN_BAL | eai:Tx/TxBody/TxRepeat/OD_TOT_LIMIT | eai:Tx/TxBody/ThreshAmt | eai:Tx/TxBody/odavail1"), 
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 7, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/RATE"), 
                @DecimalConverter(converter = EAIDecimalConverter.class, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/alertcount | eai:Tx/TxBody/TxRepeat/derivative | eai:Tx/TxBody/TxRepeat/SWINDLE_COUNT | eai:Tx/TxBody/TxRepeat/DECLINE_COUNT | eai:Tx/TxBody/TxRepeat/OTHERS_COUNT | eai:Tx/TxBody/TxRepeat/NO_OF_STOPS | eai:Tx/TxBody/TxRepeat/NO_OF_STOP_WDL | eai:Tx/TxBody/TxRepeat/INS_AMT")
        }
)
// @formatter:on
public class EB5557891RS extends EAIResponse<EB5557891SvcRqType, EB5557891SvcRsType> {

    /**
     * 建構子
     */
    public EB5557891RS() {
        super("EB5557891");
    }

    @Override
    public boolean validateWithRq(EAIRequest<EB5557891SvcRqType> rq) {

        // 下行表頭 HFMTID 必須為 D001
        if (!"D001".equals(StringUtils.trimToEmptyEx(getHeader().getHFMTID()))) {
            return false;
        }

        // 驗證上送和下行的IDNO是否相同
        String fmtId = StringUtils.trimToEmptyEx(getHeader().getHFMTID());
        if ("D001".equals(fmtId)) {
            EB5557891D001SvcRsType d001SvcRsType = getBodyAsType(EB5557891D001SvcRsType.class);
            // 此時還未執行 converter, 必需自行 trim
            String rqIdNo = StringUtils.trimToEmptyEx(rq.getBody().getIDNO());
            String rsIdNo = StringUtils.trimToEmptyEx(d001SvcRsType.getIDNO());
            return StringUtils.equals(rqIdNo, rsIdNo);
        }
        else {
            return true;
        }
    }
}
