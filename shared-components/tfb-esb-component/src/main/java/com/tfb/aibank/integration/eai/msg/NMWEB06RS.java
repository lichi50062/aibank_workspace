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

import tw.com.ibm.mf.eb.NMWEB06SvcRqType;
import tw.com.ibm.mf.eb.NMWEB06SvcRsType;

// @formatter:off
/**
 * @(#)NMWEB06RS.java
 * 
 * <p>金錢信託-特金股票明細查詢(NMWEB06)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/09, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/OCCUR | eai:Tx/TxBody/SettleDate")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class,inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/SettleDate | eai:Tx/TxBody/TxRepeat/Date08")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/AcctBal"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/ForCurBal | eai:Tx/TxBody/TxRepeat/ReturnRate"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/Number | eai:Tx/TxBody/TxRepeat/ReferenceRate"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 6, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/AvgBuyingPrice | eai:Tx/TxBody/TxRepeat/CurAmt")
        }
)
// @formatter:on
public class NMWEB06RS extends EAIResponse<NMWEB06SvcRqType, NMWEB06SvcRsType> {

    public NMWEB06RS() {
        super("NMWEB06");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "V003".equals(errId);
    }

}
