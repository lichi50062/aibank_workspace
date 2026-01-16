package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eai.TxHeadRsType;
import tw.com.ibm.mf.eb.N8067NSvcRqType;
import tw.com.ibm.mf.eb.N8067NSvcRsType;

//@formatter:off
/**
* @(#)N8067NRS.java
* 
* <p>Description: N8067N 股票定期定額訂約</p>
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
                        " | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX"),
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class,inputPattern="yyyyMMdd", fieldXPath="eai:Tx/TxBody/TradeDate | eai:Tx/TxBody/EntrustDate | eai:Tx/TxBody/TradeDateEnd | eai:Tx/TxBody/FirstTradeDay")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath="eai:Tx/TxBody/Fee | eai:Tx/TxBody/EstExchangeFee | eai:Tx/TxBody/EstOtherFee | eai:Tx/TxBody/EstCreditForLoadAmt | eai:Tx/TxBody/EntrustAmt"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 5, fieldXPath="eai:Tx/TxBody/FeeRate")
        }
)
//@formatter:on
public class N8067NRS extends EAIResponse<N8067NSvcRqType, N8067NSvcRsType> {

    public N8067NRS() {
        super("N8067N");
    }

    /**
     * 檢查下行電文狀態
     *
     * @return
     * @throws EAIException
     */
    @Override
    public boolean validateHERRID(EAIRequest<N8067NSvcRqType> request) throws EAIException {
        TxHeadRsType header = getHeader();
        if (header == null) {
            return false;
        }
        String errId = StringUtils.trimToEmptyEx(header.getHERRID());

        return super.validateHERRID(request) || StringUtils.equals(errId, "8M01");
    }

}
