package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;

import tw.com.ibm.mf.eb.VN067NSvcRqType;

// @formatter:off
/**
 * @(#)VN067NRQ.java
 * 
 * <p>Description:基金申購即時交易電文(VN067N) 上行電文</p>
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
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = -6, fieldXPath="eai:Tx/TxBody/ExRate"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = -4, pattern = "000000000000", fieldXPath="eai:Tx/TxBody/UntNum"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = -2, fieldXPath="eai:Tx/TxBody/PayAmt_M | eai:Tx/TxBody/PayAmt_H | eai:Tx/TxBody/Amt13 | eai:Tx/TxBody/AfterExchangeAmt")
        }        
)
// @formatter:on
public class VN067NRQ extends EAIRequest<VN067NSvcRqType> {

    private static final long serialVersionUID = -6103504425579404099L;

    /**
     * 建構子
     */
    public VN067NRQ() {
        super("VN067N");
    }
}
