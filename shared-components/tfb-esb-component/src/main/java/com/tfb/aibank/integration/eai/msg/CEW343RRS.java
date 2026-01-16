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
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CEW343RSvcRqType;
import tw.com.ibm.mf.eb.CEW343RSvcRsType;

//@formatter:off
/**
* @(#)CEW343RRS.java
* 
* <p>Description: 分期查詢交易 下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(customConverters = {
                    @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/OCCUR")
            },
            // TODO 待測資確認
            datetimeConverters = { 
                    @DateAndTimeConverter(converter = EAIDateConverter.class, 
                            inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/PCHDAY | eai:Tx/TxBody/TxRepeat/NCCDAY | eai:Tx/TxBody/TxRepeat/TXIDAY")
            },
            decimalConverters = { 
                    @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = " eai:Tx/TxBody/TxRepeat/DESAMT | eai:Tx/TxBody/TxRepeat/TXUAMT | eai:Tx/TxBody/TxRepeat/TXBTIC | eai:Tx/TxBody/TxRepeat/TXSTGE"),
                    @DecimalConverter(converter = EAIDecimalConverter.class, scale=2, fieldXPath = "eai:Tx/TxBody/TxRepeat/TXRATE"),
            }
)
//@formatter:on
public class CEW343RRS extends EAIResponse<CEW343RSvcRqType, CEW343RSvcRsType> {

    /**
     * 建構子.
     */
    public CEW343RRS() {
        super("CEW343R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return StringUtils.equals(errId, "V003");
    }
}
