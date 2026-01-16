package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;

import tw.com.ibm.mf.eb.NMWEB09SvcRqType;

// @formatter:off
/**
 * @(#)NMWEB09RQ.java
 *
 * <p>金錢信託-不限用途-免付單據查詢電文(NMWEB09RQ)</p>
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
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = -2, fieldXPath = "eai:Tx/TxBody/PayAmt")
        }
)
public class NMWEB09RQ extends EAIRequest<NMWEB09SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NMWEB09RQ() {
        super("NMWEB09");
    }
}
