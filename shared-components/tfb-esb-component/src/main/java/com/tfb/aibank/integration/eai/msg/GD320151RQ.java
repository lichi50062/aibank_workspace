package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;

import tw.com.ibm.mf.eb.GD320151SvcRqType;

// @formatter:off
/**
 * @(#)GD320151RQ.java
 * 
 * <p>Description:GD320151 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/25, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/STR_DATE | eai:Tx/TxBody/END_DATE")
        }
)
// @formatter:on
public class GD320151RQ extends EAIRequest<GD320151SvcRqType> {

    private static final long serialVersionUID = 2759296525780087563L;

    /**
     * 建構子
     */
    public GD320151RQ() {
        super("GD320151");
    }
}
