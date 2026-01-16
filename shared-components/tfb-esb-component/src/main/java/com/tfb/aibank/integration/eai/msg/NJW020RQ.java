package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;

import tw.com.ibm.mf.eb.NJW020SvcRqType;

// @formatter:off
/**
 * @(#)NJW020RQ.java
 * 
 * <p>Description:債券營業時間交易</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/11, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAIPadLeftConverter.class, padSize = 14, padChar = "0", fieldXPath = "eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/ACNO"),
                @CustomConverter(converter = EAIPadLeftConverter.class, padSize = 8, padChar = "0", fieldXPath = "eai:Tx/TxBody/RefPriceDate"),
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TranDate | eai:Tx/TxBody/TrscDate | eai:Tx/TxBody/PayDate01 | eai:Tx/TxBody/PayDate02 | eai:Tx/TxBody/PayDate03")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = -4, pattern = "000000000000000", fieldXPath = "eai:Tx/TxBody/TX_AMT"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = -4, pattern = "0000000", fieldXPath = "eai:Tx/TxBody/ReedPrice | eai:Tx/TxBody/RefPrice"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = -5, pattern = "00000000", fieldXPath = "eai:Tx/TxBody/TxFeeRate"),
        }
)
// @formatter:on
public class NJW020RQ extends EAIRequest<NJW020SvcRqType> {

    private static final long serialVersionUID = 2414899011761943128L;

    /**
     * 建構子
     */
    public NJW020RQ() {
        super("NJW020");
    }

}
