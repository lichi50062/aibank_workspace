package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NMWEB03SvcRqType;
import tw.com.ibm.mf.eb.NMWEB03SvcRsType;

// @formatter:off
/**
 * @(#)NMWEB03RS.java
 *
 * <p>Description:金錢信託定存單臺幣現值查詢 NMWEB02</p>
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
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/SettleDate")
        }
)
public class NMWEB03RS extends EAIResponse<NMWEB03SvcRqType, NMWEB03SvcRsType> {

    public NMWEB03RS() {
        super("NMWEB03");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}
