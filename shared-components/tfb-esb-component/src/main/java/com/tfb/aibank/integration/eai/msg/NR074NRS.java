package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NR074NSvcRqType;
import tw.com.ibm.mf.eb.NR074NSvcRsType;

// @formatter:off
/**
 * @(#)NR074NRS.java
 *
 * <p>Description:海外股票ETF即時成交交易資訊</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/05/10, Lyon Xie
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT" +
                        " | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/EntrustDate | eai:Tx/TxBody/TxRepeat/TradeDate"
                        + " | eai:Tx/TxBody/TxRepeat/TradeDateEnd | eai:Tx/TxBody/TxRepeat/Date08")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 6, fieldXPath = "eai:Tx/TxBody/TxRepeat/EntrustPrice | eai:Tx/TxBody/TxRepeat/TradePrice | eai:Tx/TxBody/TxRepeat/CurAmt"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/TradeCost | eai:Tx/TxBody/TxRepeat/TradeCostFee | eai:Tx/TxBody/TxRepeat/TradeCostOtFee "
                        + "| eai:Tx/TxBody/TxRepeat/ForCurBal | eai:Tx/TxBody/TxRepeat/TradeSellFee | eai:Tx/TxBody/TxRepeat/TradeSellOtFee | eai:Tx/TxBody/TxRepeat/TradeTrustFee")
        }
)
public class NR074NRS extends EAIResponse<NR074NSvcRqType, NR074NSvcRsType> {

    /**
     * 建構子
     */
    public NR074NRS() {
        super("NR074N");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}
