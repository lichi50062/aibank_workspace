package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NR067NSvcRqType;
import tw.com.ibm.mf.eb.NR067NSvcRsType;

//@formatter:off
/**
* @(#)NR067NRS.java
* 
* <p>Description: NR067N 海外股票、ETF總覽明細</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/08, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        },
        datetimeConverters = {
        	@DateAndTimeConverter(converter = EAIDateConverter.class,inputPattern="yyyyMMdd", fieldXPath="eai:Tx/TxBody/TradeDate | eai:Tx/TxBody/EntrustDate | eai:Tx/TxBody/TradeDateEnd ")
        },
        decimalConverters = {
        	@DecimalConverter(converter = EAIDecimalConverter.class,pattern="00000000000", scale=2, fieldXPath="eai:Tx/TxBody/EstExchangeFee|" +
        			"eai:Tx/TxBody/EstOtherFee | eai:Tx/TxBody/EstCreditForLoadAmt | eai:Tx/TxBody/EstBuyAmt|" +
        			"eai:Tx/TxBody/Fee | eai:Tx/TxBody/LowestFee | eai:Tx/TxBody/AccBalance | eai:Tx/TxBody/AvaBalance"),
        			//預計交易所費用/預計其他費用/預計圈存總金額/預計買進金額/費用/最低費用/即時餘額/可用餘額
        	@DecimalConverter(converter = EAIDecimalConverter.class,pattern="0000000", scale=5, fieldXPath="eai:Tx/TxBody/FeeRate"),//手續費費率
        	@DecimalConverter(converter = EAIDecimalConverter.class,pattern="0000000000000", scale=6, fieldXPath="eai:Tx/TxBody/EntrustPrice")//預計每股買進價格
        }
)
//@formatter:on
public class NR067NRS extends EAIResponse<NR067NSvcRqType, NR067NSvcRsType> {

    public NR067NRS() {
        super("NR067N");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || StringUtils.equals("EN47", errId);
    }
}
