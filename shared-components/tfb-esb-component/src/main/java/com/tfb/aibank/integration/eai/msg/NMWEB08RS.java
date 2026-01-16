package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NMWEB08SvcRqType;
import tw.com.ibm.mf.eb.NMWEB08SvcRsType;

// @formatter:off
/**
 * @(#)NMWEB08RS.java
 *
 * <p>金錢信託-受款人查詢電文(NMWEB08RS)</p>
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
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/Item | eai:Tx/TxBody/TxRepeat/RelatedID | eai:Tx/TxBody/TxRepeat/RelatedName | eai:Tx/TxBody/TxRepeat/PayCur | eai:Tx/TxBody/TxRepeat/PayAmt | eai:Tx/TxBody/TxRepeat/PayRoleStatus | eai:Tx/TxBody/TxRepeat/PayBankID | eai:Tx/TxBody/TxRepeat/PayAcctID | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/OCCUR")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/PayAmt")
        }
)
public class NMWEB08RS extends EAIResponse<NMWEB08SvcRqType, NMWEB08SvcRsType> {

    public NMWEB08RS() {
        super("NMWEB08");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}
