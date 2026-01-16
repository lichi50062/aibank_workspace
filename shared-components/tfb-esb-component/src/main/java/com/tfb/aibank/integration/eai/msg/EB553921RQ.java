package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadRightConverter;

import tw.com.ibm.mf.eb.EB553921SvcRqType;

// @formatter:off
/**
 * @(#)EB553921RQ.java
 * 
 * <p>Description:綜合存款轉存綜合定期性存款（外幣）(EB553921) 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/27, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAIPadRightConverter.class, padSize = 11, fieldXPath="eai:Tx/TxBody/CUST_NO")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TX_DATE | eai:Tx/TxBody/ACT_DATE")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = -3, pattern="00000000000000000", isPostSign = true, showPlusSign = true, fieldXPath="eai:Tx/TxBody/TX_AMT"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = -6, pattern="00000000000", isPostSign = true, showPlusSign = true, fieldXPath="eai:Tx/TxBody/BS_RATE | eai:Tx/TxBody/BASE_RATE")
        }
)
// @formatter:on
public class EB553921RQ extends EAIRequest<EB553921SvcRqType> {

    private static final long serialVersionUID = -3697798957823901528L;

    /**
     * 建構子
     */
    public EB553921RQ() {
        super("EB553921");
    }
}
