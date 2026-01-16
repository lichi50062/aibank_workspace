/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NMP8YDSvcRqType;
import tw.com.ibm.mf.eb.NMP8YDSvcRsType;

// @formatter:off
/**
 * @param 
 * @(#)NMP8YD.java
 * 
 * <p>Description:契約交易明細查詢 </p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/30, Jackie Chien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Converter(
        customConverters = {
                        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* ")
        },
        datetimeConverters = { @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "YYYYMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/TxDate")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/TxAmt|eai:Tx/TxBody/TxRepeat/ReceCharge"), @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/Unit|eai:Tx/TxBody/TxRepeat/Price|eai:Tx/TxBody/TxRepeat/SplitUnit"),
        }
)
public class NMP8YDRS extends EAIResponse<NMP8YDSvcRqType, NMP8YDSvcRsType> {

    /**
     * 建構子
     */
    public NMP8YDRS() {
        super("NMP8YD");
    }

}
