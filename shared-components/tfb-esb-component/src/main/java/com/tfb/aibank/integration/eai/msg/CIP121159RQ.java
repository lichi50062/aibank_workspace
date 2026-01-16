package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;

import tw.com.ibm.mf.eb.CIP121159SvcRqType;

// @formatter:off
/**
 * @(#)CIP121159RQ.java
 *
 * <p>Description 各類通知事項申請及維護上行電文(CIP121159)</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/07/10,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/C_DATE")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern="0000000000000000", scale = -6, fieldXPath = "eai:Tx/TxBody/TxRepeat/FX_RATE | eai:Tx/TxBody/TxRepeat/IN_RATE")
        }
)
// @formatter:on
public class CIP121159RQ extends EAIRequest<CIP121159SvcRqType> {

    private static final long serialVersionUID = 8749606722639478776L;

    /**
     * 建構子
     */
    public CIP121159RQ() {
        super("CIP121159");
    }
}