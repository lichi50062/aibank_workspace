package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;

import tw.com.ibm.mf.eb.NJWEEA60SvcRqType;

// @formatter:off
/**
 * @(#)NJWEEA60RQ.java
 * 
 * <p>Description: NJWEEA60RQ 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/27, Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {

                @CustomConverter(
                        converter = EAIPadLeftConverter.class, padSize = 15, padChar = "0", fieldXPath = "eai:Tx/TxBody/PurchaseAmt"),
                @CustomConverter(
                        converter = EAIPadLeftConverter.class, padSize = 8, padChar = "0", fieldXPath = "eai:Tx/TxBody/TxFeeRate"),
                @CustomConverter(
                        converter = EAIPadLeftConverter.class, padSize = 7, padChar = "0", fieldXPath = "eai:Tx/TxBody/EntrustAmt")
        },decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class,pattern="00000000000", scale=2, fieldXPath="eai:Tx/TxBody/EntrustPrice")
        }
)
public class NJWEEA60RQ extends EAIRequest<NJWEEA60SvcRqType> {

    private static final long serialVersionUID = 4762085387788612512L;

    /**
     * 建構子.
     */
    public NJWEEA60RQ() {
        super("NJWEEA60");
    }

}