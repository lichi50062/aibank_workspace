package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NMWEB04SvcRqType;
import tw.com.ibm.mf.eb.NMWEB04SvcRsType;

// @formatter:off
/**
 * @(#)NMWEB04RS.java
 *
 * <p>Description:金錢信託定存單明細查詢 NMWEB04</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/07/10, PCY
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/OCCUR | eai:Tx/TxBody/SettleDate")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/SettleDate | eai:Tx/TxBody/TxRepeat/CDEndDate")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, showPlusSign = true, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/CDAmt"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, showPlusSign = true, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/Interest")
        }
)
public class NMWEB04RS extends EAIResponse<NMWEB04SvcRqType, NMWEB04SvcRsType> {

    public NMWEB04RS() {
        super("NMWEB04");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}
