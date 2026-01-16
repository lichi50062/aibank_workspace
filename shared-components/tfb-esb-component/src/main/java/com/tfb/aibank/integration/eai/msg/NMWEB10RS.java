package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NMWEB10SvcRqType;
import tw.com.ibm.mf.eb.NMWEB10SvcRsType;

// @formatter:off
/**
 * @(#)NMWEB10RS.java
 *
 * <p>金錢信託-單次給付指示電文(NMWEB10RS)</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/07/09, PCY
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/OCCUR | eai:Tx/TxBody/Item | eai:Tx/TxBody/AuthID01 | eai:Tx/TxBody/AuthName01 | eai:Tx/TxBody/AuthID02 | eai:Tx/TxBody/AuthName02 | eai:Tx/TxBody/AuthID03 | eai:Tx/TxBody/AuthName03 | eai:Tx/TxBody/AuthID04 | eai:Tx/TxBody/AuthName04 | eai:Tx/TxBody/AuthID05 | eai:Tx/TxBody/AuthName05 | eai:Tx/TxBody/AuthID06 | eai:Tx/TxBody/AuthName06 | eai:Tx/TxBody/AuthID07 | eai:Tx/TxBody/AuthName07 | eai:Tx/TxBody/AuthID08 | eai:Tx/TxBody/AuthName08 | eai:Tx/TxBody/AuthID09 | eai:Tx/TxBody/AuthName09 | eai:Tx/TxBody/AuthID10 | eai:Tx/TxBody/AuthName10 | eai:Tx/TxBody/AuthEndDate")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/AuthEndDate")
        }
)
public class NMWEB10RS extends EAIResponse<NMWEB10SvcRqType, NMWEB10SvcRsType> {

    public NMWEB10RS() {
        super("NMWEB10");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}
