package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NR061NSvcRqType;
import tw.com.ibm.mf.eb.NR061NSvcRsType;

// @formatter:off
/**
 * @(#)NR061NRS.java
 * 
 * <p>Description:NR061N 海外股票ETF停損停利</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/27, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT" +
                        " | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur"),
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/Occur"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/BalCost | eai:Tx/TxBody/TxRepeat/ReturnRate | eai:Tx/TxBody/TxRepeat/StopLossRate | eai:Tx/TxBody/TxRepeat/StopEarnRate"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 6, fieldXPath = "eai:Tx/TxBody/TxRepeat/TrustAmt")
        }
)
// @formatter:on
public class NR061NRS extends EAIResponse<NR061NSvcRqType, NR061NSvcRsType> {
    /**
     * 建構子
     */
    public NR061NRS() {
        super("NR061N");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "V003".equals(errId);
    }

}
