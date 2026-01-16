package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NR048NSvcRqType;
import tw.com.ibm.mf.eb.NR048NSvcRsType;

//@formatter:off
/**
* @(#)NR048NRS.java
* 
* <p>NR048NRQ 股票歷史交易明細</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/02/19, Leiley    
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
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/Occur | eai:Tx/TxBody/TxRepeat/EntrustAmt | eai:Tx/TxBody/TxRepeat/TradeAmt" +
                        " | eai:Tx/TxBody/TxRepeat/TwdTotalAmt | eai:Tx/TxBody/TxRepeat/Inventory | eai:Tx/TxBody/TxRepeat/StockRate" +
                        " | eai:Tx/TxBody/TxRepeat/TaxRate | eai:Tx/TxBody/TxRepeat/OutVal | eai:Tx/TxBody/TxRepeat/BondStock | eai:Tx/TxBody/TxRepeat/StockCount"),

                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/TradeCost | eai:Tx/TxBody/TxRepeat/TradeFee" +
                        " | eai:Tx/TxBody/TxRepeat/OtherFee | eai:Tx/TxBody/TxRepeat/TradeTax | eai:Tx/TxBody/TxRepeat/TotalAmt" +
                        " | eai:Tx/TxBody/TxRepeat/TrustFee | eai:Tx/TxBody/TxRepeat/DistributeAmt | eai:Tx/TxBody/TxRepeat/ReceiveAmt" +
                        " | eai:Tx/TxBody/TxRepeat/InvVal | eai:Tx/TxBody/TxRepeat/ZeroStockAmt | eai:Tx/TxBody/TxRepeat/TradeCostBal" +
                        " | eai:Tx/TxBody/TxRepeat/ELAmt | eai:Tx/TxBody/TxRepeat/ReturnRate"),

                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/TxRepeat/ReferenceRate | eai:Tx/TxBody/TxRepeat/Interest2 | eai:Tx/TxBody/TxRepeat/ZeroStockPrice"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 6, fieldXPath = "eai:Tx/TxBody/TxRepeat/TradePrice | eai:Tx/TxBody/TxRepeat/DistributeRate" +
                        " | eai:Tx/TxBody/TxRepeat/Dividend | eai:Tx/TxBody/TxRepeat/ZeroStockCount | eai:Tx/TxBody/TxRepeat/ExchangeRate2"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 9, fieldXPath = "eai:Tx/TxBody/TxRepeat/ExchangeRate")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/EntrustDate" +
                        " | eai:Tx/TxBody/TxRepeat/TradeDate | eai:Tx/TxBody/TxRepeat/RecordDate | eai:Tx/TxBody/TxRepeat/TradeDateEnd " +
                        " | eai:Tx/TxBody/TxRepeat/AllocateDate | eai:Tx/TxBody/TxRepeat/CostDate | eai:Tx/TxBody/TxRepeat/RcvDay ")
        }
)
//@formatter:on
public class NR048NRS extends EAIResponse<NR048NSvcRqType, NR048NSvcRsType> {
    /**
     * 建構子
     */
    public NR048NRS() {
        super("NR048N");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }

}
