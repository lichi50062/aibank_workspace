package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NMWEB07SvcRqType;
import tw.com.ibm.mf.eb.NMWEB07SvcRsType;

// @formatter:off
/**
 * @(#)NMWEB07RS.java
 *
 * <p>金錢信託-依證號查詢契約電文(NMWEB07RS)</p>
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
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/OCCUR | eai:Tx/TxBody/TxRepeat/ContractNo | eai:Tx/TxBody/TxRepeat/AcctId01 | eai:Tx/TxBody/TxRepeat/AcctId02 | eai:Tx/TxBody/TxRepeat/AcctId03 | eai:Tx/TxBody/TxRepeat/AcctId04 | eai:Tx/TxBody/TxRepeat/AcctId05 | eai:Tx/TxBody/TxRepeat/Role | eai:Tx/TxBody/TxRepeat/ContractEndDate | eai:Tx/TxBody/TxRepeat/ContractCur ")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/ContractEndDate")
        }
)
public class NMWEB07RS extends EAIResponse<NMWEB07SvcRqType, NMWEB07SvcRsType> {

    public NMWEB07RS() {
        super("NMWEB07");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}
