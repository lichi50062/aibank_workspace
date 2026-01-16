package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;

import tw.com.ibm.mf.eb.VN080NSvcRqType;

// @formatter:off
/**
 * @(#)VN080NRQ.java
 * 
 * <p>Description:基金申購預約交易電文(VN080N) 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/14, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAIPadLeftConverter.class, padSize = 14, padChar = "0", fieldXPath = "eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/AfterExchangeAcctId | eai:Tx/TxBody/AfterExchangeInAcctId")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/Date")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "000000000000", scale = -4, fieldXPath="eai:Tx/TxBody/UntNum"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = -2, fieldXPath="eai:Tx/TxBody/PayAmt_M | eai:Tx/TxBody/PayAmt_H | eai:Tx/TxBody/Amt13 | eai:Tx/TxBody/AfterExchangeAmt")
        }
)
// @formatter:on
public class VN080NRQ extends EAIRequest<VN080NSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public VN080NRQ() {
        super("VN080N");
    }
}
