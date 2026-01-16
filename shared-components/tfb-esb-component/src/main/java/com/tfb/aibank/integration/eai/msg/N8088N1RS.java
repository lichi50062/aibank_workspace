package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.N8088N1SvcRqType;
import tw.com.ibm.mf.eb.N8088N1SvcRsType;

//@formatter:off
/**
 * @(#)N8088N1RS.java
 * 
 * <p>Description: N8088N1 海外股票、ETF定期定額總覽明細</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/18, Edward Tien
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
                @DecimalConverter(converter = EAIDecimalConverter.class,  scale = 6, fieldXPath = "eai:Tx/TxBody/TxRepeat/Number | eai:Tx/TxBody/TxRepeat/AvgBuyingPrice | eai:Tx/TxBody/TxRepeat/CurAmt" +
                		" | eai:Tx/TxBody/TxRepeat/EntrustPrice | eai:Tx/TxBody/TxRepeat/TradePrice | eai:Tx/TxBody/TxRepeat/EntrustAmt | eai:Tx/TxBody/TxRepeat/TradeAmt"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/TxRepeat/ReferenceRate"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/ForCurBal | eai:Tx[./TxHead/HFMTID='0001']/TxBody/TxRepeat/AcctBal | eai:Tx/TxBody/TxRepeat/ReturnRate" +
                		" | eai:Tx/TxBody/TxRepeat/ForCurCost | eai:Tx/TxBody/TxRepeat/ReturnRate2 | eai:Tx/TxBody/TxRepeat/TradeCost | eai:Tx/TxBody/TxRepeat/TrmCurBal | eai:Tx/TxBody/TxRepeat/StopLossRate | eai:Tx/TxBody/TxRepeat/StopEarnRate" +
                		" | eai:Tx/TxBody/TxRepeat/StockUForCurBal | eai:Tx/TxBody/TxRepeat/StockCForCurBal | eai:Tx/TxBody/TxRepeat/StockTForCurBal | eai:Tx/TxBody/TxRepeat/ForCurBalDiv | eai:Tx/TxBody/TxRepeat/AcctBalCostCost" +
                		" | eai:Tx/TxBody/TxRepeat/Dividend"),
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/TxRepeat/Number | eai:Tx/TxBody/TxRepeat/AcctBalCost" +
                        " | eai:Tx[./TxHead/HFMTID='0003']/TxBody/TxRepeat/AcctBal | eai:Tx[./TxHead/HFMTID='0004']/TxBody/TxRepeat/AcctBal")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/Date08 | eai:Tx/TxBody/TxRepeat/EntrustDate" +
                		" | eai:Tx/TxBody/TxRepeat/TradeDate | eai:Tx/TxBody/TxRepeat/TrxMarketDat"+ 
                		" | eai:Tx/TxBody/TxRepeat/TrxMarketDat2 | eai:Tx/TxBody/TxRepeat/TrxMarketDat3 | eai:Tx/TxBody/TxRepeat/TrxMarketDat4"+ 
                		" | eai:Tx/TxBody/TxRepeat/TrxMarketDat5 | eai:Tx/TxBody/TxRepeat/TrxMarketDat6")
        }
)
//@formatter:on
public class N8088N1RS extends EAIResponse<N8088N1SvcRqType, N8088N1SvcRsType> {
    /**
     * 建構子
     */
    public N8088N1RS() {
        super("N8088N1");
    }
}
