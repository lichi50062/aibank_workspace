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

import tw.com.ibm.mf.eb.NMI002SvcRqType;
import tw.com.ibm.mf.eb.NMI002SvcRsType;

// @formatter:off
/**
 * @param 
 * @(#)NMI002.java
 * 
 * <p>Description:奈米投契約明細查詢</p>
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
        datetimeConverters = { @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "YYYYMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/StarDate | eai:Tx/TxBody/TxRepeat/NextChargeDate" + " | eai:Tx/TxBody/TxRepeat/OpenAcctDate | eai:Tx/TxBody/TxRepeat/CreateDate")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 0, fieldXPath = "eai:Tx/TxBody/TxRepeat/TargetAmt|eai:Tx/TxBody/TxRepeat/Amt|eai:Tx/TxBody/TxRepeat/Charge|eai:Tx/TxBody/TxRepeat/Amount|eai:Tx/TxBody/TxRepeat/TargetAmt|eai:Tx/TxBody/TxRepeat/IsCumulativeSwitchOn"),
        }
)
public class NMI002RS extends EAIResponse<NMI002SvcRqType, NMI002SvcRsType> {

    /**
     * 建構子
     */
    public NMI002RS() {
        super("NMI002");
    }

}
