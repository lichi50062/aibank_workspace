package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.N8048NSvcRqType;
import tw.com.ibm.mf.eb.N8048NSvcRsType;

//@formatter:off
/**
 * @(#)N8048NRS.java
 * 
 * <p>Description: N8048N 股票定期定額歷史交易明細</p>
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
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 9, fieldXPath = "eai:Tx/TxBody/TxRepeat/Rate"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 6, fieldXPath = "eai:Tx/TxBody/TxRepeat/TradePrice | eai:Tx/TxBody/TxRepeat/TradeAmt | eai:Tx/TxBody/TxRepeat/EntrustAmt | eai:Tx/TxBody/TxRepeat/Inventory | eai:Tx/TxBody/TxRepeat/DistributeRate | eai:Tx/TxBody/TxRepeat/StockRate | eai:Tx/TxBody/TxRepeat/Dividend"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 5, fieldXPath = "eai:Tx/TxBody/TxRepeat/TradeFeeRate"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/TxRepeat/ReferenceRate"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/TradeCost | eai:Tx/TxBody/TxRepeat/TradeFee | eai:Tx/TxBody/TxRepeat/OtherFee | eai:Tx/TxBody/TxRepeat/TotalAmt | eai:Tx/TxBody/TxRepeat/TradeTax | eai:Tx/TxBody/TxRepeat/ContractAmt | eai:Tx/TxBody/TxRepeat/TrustFee | eai:Tx/TxBody/TxRepeat/ReceiveAmt | eai:Tx/TxBody/TxRepeat/DistributeAmt | eai:Tx/TxBody/TxRepeat/ReturnRate | eai:Tx/TxBody/TxRepeat/TradeCostBal" +
                        " | eai:Tx/TxBody/TxRepeat/ELAmt"),
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/TradeDate | eai:Tx/TxBody/TxRepeat/CostDate | eai:Tx/TxBody/TxRepeat/ContractDay | eai:Tx/TxBody/TxRepeat/EntrustDate | eai:Tx/TxBody/TxRepeat/MktTradeDate | eai:Tx/TxBody/TxRepeat/PayDay | eai:Tx/TxBody/TxRepeat/ecordDate | eai:Tx/TxBody/TxRepeat/DistributeDate | eai:Tx/TxBody/TxRepeat/RecordDate | eai:Tx/TxBody/TxRepeat/RcvDay")
        }
)
//@formatter:on
public class N8048NRS extends EAIResponse<N8048NSvcRqType, N8048NSvcRsType> {
    /**
     * 建構子
     */
    public N8048NRS() {
        super("N8048N");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}
