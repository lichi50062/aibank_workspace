package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;

import tw.com.ibm.mf.eb.NMWEB10SvcRqType;

// @formatter:off
/**
 * @(#)NMWEB10RQ.java
 *
 * <p>金錢信託-單次給付指示電文(NMWEB10RQ)</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/07/09, PCY
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Converter(
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "0000000", scale = -4, fieldXPath = "eai:Tx/TxBody/PayPercentage01 | eai:Tx/TxBody/PayPercentage02 | eai:Tx/TxBody/PayPercentage03 | eai:Tx/TxBody/PayPercentage04 | eai:Tx/TxBody/PayPercentage05"),
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "0000000000000", scale = -2, fieldXPath = "eai:Tx/TxBody/PayAmt | eai:Tx/TxBody/PayAmt01 | eai:Tx/TxBody/PayAmt02 | eai:Tx/TxBody/PayAmt03 | eai:Tx/TxBody/PayAmt04 | eai:Tx/TxBody/PayAmt05")
        }
)
public class NMWEB10RQ extends EAIRequest<NMWEB10SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NMWEB10RQ() {
        super("NMWEB10");
    }
}
