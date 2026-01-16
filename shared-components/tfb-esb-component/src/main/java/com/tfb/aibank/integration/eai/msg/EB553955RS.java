package com.tfb.aibank.integration.eai.msg;


import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIReplaceConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;
import tw.com.ibm.mf.eb.EB553955SvcRqType;
import tw.com.ibm.mf.eb.EB553955SvcRsType;


//@formatter:off
/**
 * @(#)EB553955RS.java
 *
 * <p>Description: 台轉外、外轉台、外轉外即時上行電文(EB553955)</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/05/24
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxBody/*"),
                @CustomConverter(converter = EAIReplaceConverter.class, replaceRegex = "[\\$,]", fieldXPath = "eai:Tx/TxBody/EX_AMT | eai:Tx/TxBody/O_ACC_BAL | eai:Tx/TxBody/FEE")
        },
        decimalConverters = {
        		@DecimalConverter(converter = EAIDecimalConverter.class, pattern = "00000000000", scale = 6, isPostSign = true, fieldXPath = "eai:Tx/TxBody/EX_RATE | eai:Tx/TxBody/CS_RATE"),
        		@DecimalConverter(converter = EAIDecimalConverter.class,pattern = "00000000000000000", scale = 3, isPostSign = true, fieldXPath = "eai:Tx/TxBody/EX_AMT"
        				+ " | eai:Tx/TxBody/O_ACC_BAL | eai:Tx/TxBody/O_AVL_BAL"),
        		@DecimalConverter(converter = EAIDecimalConverter.class,pattern = "00000000000", scale = 4, fieldXPath = "eai:Tx/TxBody/FEE")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyy/MM/dd", fieldXPath = "eai:Tx/TxBody/TX_DATE"),
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/ACT_DATE")
        }
)
public class EB553955RS extends EAIResponse<EB553955SvcRqType, EB553955SvcRsType> {

    /**
     * 建構子
     */
    public EB553955RS() {
        super("EB553955");
    }
    
    @Override
    public String getInternalErrorCode() {
        EB553955SvcRsType body = getBody();
        if (body != null && StringUtils.isNotBlank(body.getERROR())) {
            return StringUtils.trimToEmptyEx(body.getERROR());
        }
        return super.getInternalErrorCode();
    }
}
