package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NR070NSvcRqType;
import tw.com.ibm.mf.eb.NR070NSvcRsType;

//@formatter:off
/**
* @(#)NR070NRS.java
* 
* <p>Description: 海外股票ETF可用金額查詢下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/26, Evan
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*")
        },
        decimalConverters = {
        	@DecimalConverter(converter = EAIDecimalConverter.class,pattern="00000000000", scale=2, fieldXPath="eai:Tx/TxBody/TxRepeat/SellAmt | eai:Tx/TxBody/TxRepeat/TDaySAmt | eai:Tx/TxBody/TxRepeat/SellUseAmt | eai:Tx/TxBody/TxRepeat/BankUseAmt")		
        }
)
//@formatter:on
public class NR070NRS extends EAIResponse<NR070NSvcRqType, NR070NSvcRsType> {

    /**
     * 建構子
     */
    public NR070NRS() {
        super("NR070N");
    }

    @Override
    protected boolean isNoData(String errId) {
        if (StringUtils.equals("V003", errId)) {
            return true;
        }
        return false;
    }
}
