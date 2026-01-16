package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITimeConverter;

import tw.com.ibm.mf.eb.EBCC002SvcRqType;

// @formatter:off
/**
 * @(#)EBCC002RQ.java
 * 
 * <p>Description:EBCC002 信用卡額度申請(永調)啟案電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/17, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/APPLY_DATE | eai:Tx/TxBody/ID_AUTH_DATE"),
                @DateAndTimeConverter(converter = EAITimeConverter.class, outputPattern = "HHmmss", fieldXPath = "eai:Tx/TxBody/APPLY_TIME | eai:Tx/TxBody/ID_AUTH_TIME"),
                @DateAndTimeConverter(converter = EAIDateTimeConverter.class, outputPattern = "yyyyMMddHHmmss", fieldXPath = "eai:Tx/TxBody/AI_UPD_DATETIME")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 0, fieldXPath = "eai:Tx/TxBody/BEF_ADJ_AMT | eai:Tx/TxBody/ADJ_AMT | eai:Tx/TxBody/INSUR_AMT | eai:Tx/TxBody/AI_ADJ_AMT | eai:Tx/TxBody/FILECNT")
        }
)
// @formatter:on
public class EBCC002RQ extends EAIRequest<EBCC002SvcRqType> {

    private static final long serialVersionUID = -467073403029282065L;

    /**
     * 建構子
     */
    public EBCC002RQ() {
        super("EBCC002");
    }
}
