package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;

import tw.com.ibm.mf.eb.EB610955SvcRqType;

//@formatter:off
/**
 * @(#)EB610955RQ.java
 * 
 * <p>Description:EB610955 即時轉帳 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/15, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAIPadLeftConverter.class, padSize = 16, padChar = "0", fieldXPath = "eai:Tx/TxBody/ACNO_OUT")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class,
                        outputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/ACT_DATE | eai:Tx/TxBody/PAY_DATE"),
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=-3, pattern="00000000000000000", showPlusSign = true,isPostSign = true, fieldXPath = "eai:Tx/TxBody/TX_AMT")
        }
)
//@formatter:on
public class EB610955RQ extends EAIRequest<EB610955SvcRqType> {

    private static final long serialVersionUID = -777012556653921994L;

    /**
     * 建構子
     */
    public EB610955RQ() {
        super("EB610955");
    }

}
