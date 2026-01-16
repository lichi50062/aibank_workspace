package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NR080NSvcRqType;
import tw.com.ibm.mf.eb.NR080NSvcRsType;

//@formatter:off

/**
 * @(#)NR080NRS.java
 * 
 * <p>Description: NR080N 海外ETF / 股票委託交易管理</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/12, Allen Liao
 *
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT"),
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 6, fieldXPath = "eai:Tx/TxBody/TxRepeat/EntrustPrice"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/CreditLoadAmt")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/TradeDateEnd | eai:Tx/TxBody/TxRepeat/EntrustCancelDate | eai:Tx/TxBody/TxRepeat/EntrustDate | eai:Tx/TxBody/TxRepeat/TrxMarketDat")
        })
//@formatter:on
public class NR080NRS extends EAIResponse<NR080NSvcRqType, NR080NSvcRsType> {

    /**
     * 建構子
     */
    public NR080NRS() {
        super("NR080N");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}
